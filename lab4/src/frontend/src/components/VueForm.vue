<template>
    <form id="answer" @submit.prevent="submit">
                <div id="content-form">
                    X:
                    <input class="text" type="text" name="x" v-model="x" @input="validateLength()"
                        onkeypress='return ((event.charCode >= 43 && event.charCode <= 57) || event.charCode === 101)'
                        placeholder="(-3..5)" autocomplete="off" onpaste="return false"><br>
                    Y:
                    <input class="text" type="text" name="y" v-model="y" @input="validateLength()"
                        onkeypress='return ((event.charCode >= 43 && event.charCode <= 57) || event.charCode === 101)'
                        placeholder="(-5..5)" autocomplete="off" onpaste="return false"><br>
                    R:
                    <input class="text" type="text" name="R" v-model="r" @input="validateLength()" 
                        onkeypress='return ((event.charCode >= 43 && event.charCode <= 57) || event.charCode === 101)'
                        placeholder="(0..5)" autocomplete="off" onpaste="return false"><br>
                    <input :disabled="isDisabled" value="Подтвердить" @click="addHit" type="submit">
                </div>
            </form>
</template>

<script>
import axios from 'axios'
import {computed} from 'vue'
import moment from 'moment'

export default{
    props: ['dataBaseHits'],
    data() {
        return {
            x: null,
            y: null,
            r: null,
            errorMessage: null,
            hitsRes: null,
            isDisabled: false,
        }
    },
    provide() {
        return {
            hitsRes: computed(() => this.dataBaseHits),
        }
    },
    watch: {
        r: {
            handler() {
                this.$emit("getR", this.r)
            }
        }
    },
    methods: {
        format_date(value){
            if (value) {
            return moment(String(value)).format('yyyy-MM-dd HH:mm:ss')
            }
        },
        validateLength() {
            if (this.x != null && this.x.length >= 8) this.x = this.x.substr(0, 7)
            if (this.y != null && this.y.length >= 8) this.y = this.y.substr(0, 7)
            if (this.r != null && this.r.length >= 8) this.r = this.r.substr(0, 7)
        },
        async addHit() {
            this.errorMessage = null;
            this.$emit("getErrMsg", this.errorMessage);
            let dataBaseHit = {
                x: this.x,
                y: this.y,
                r: this.r
            }
            if (this.x != null && this.y != null && this.r != null) {
                var x = this.x.replace(",", ".")
                var y = this.y.replace(",", ".")
                var r = this.r.replace(",", ".")
            }
            if (r == null || r <= 0 || r >= 5) this.errorMessage = "Введён неправильный R"
            if (y == null || y <= -5 || y >= 5) this.errorMessage = "Введён неправильный Y"
            if (x == null || x <= -3 || x >= 5) this.errorMessage = "Введён неправильный X"
            if (this.errorMessage != null) {
                this.$emit("getErrMsg", this.errorMessage);
                return;
            }
            this.isDisabled = true;
            await axios.post("/api/hit", dataBaseHit).catch(function (error) {
                this.errorMessage = error.response.data;
                this.$emit("getErrMsg", this.errorMessage);
            }).then(response => {
                this.hitsRes = response.data;
                for (let i = 0; i < this.hitsRes.length; i++) {
                        this.hitsRes[i].curTime = this.format_date(this.hitsRes[i].curTime)
                    }
                this.$emit('getHits', this.hitsRes);
            });
            this.isDisabled = false;
        },
    },
}
</script>

<style>
    .text {
        margin-bottom: 5%;
        width: 20%
        }
</style>