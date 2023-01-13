<template>
    <form id="answer" @submit.prevent="submit">
                <div id="content-form">
                    <p id="errorMessage">{{errorMessage}}</p>
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
                    <input value="Подтвердить" @click="addHit" type="submit">
                </div>
            </form>
</template>

<script>
import axios from 'axios'

export default{
    data() {
        return {
            x: null,
            y: null,
            r: null,
            errorMessage: null,
            hits: null,
        }
        },
    methods: {
        validateLength() {
            if (this.x != null && this.x.length >= 8) this.x = this.x.substr(0, 7)
            if (this.y != null && this.y.length >= 8) this.y = this.y.substr(0, 7)
            if (this.r != null && this.r.length >= 8) this.r = this.r.substr(0, 7)
        },
        async addHit() {
            let hit = {
                x: this.x,
                y: this.y,
                r: this.r
            }
            await axios.post("http://localhost:8080/api/hit", hit).catch(function (error) {
                this.errorMessage = error.response.data;
            }).then(response => {
                this.hits = response.data.reverse();
            });         
        },
    },
    created() {
            // sendHit.get().then(
            //     result => result.json().then(
            //         data => {
            //             results.$data.hits = data.reverse()
            //             drawHits(results.$data.hits)
            // }));

    }
}
</script>