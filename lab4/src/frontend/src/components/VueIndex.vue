<template>
<div id="body">
<table id="table">
    <tr>
        <td colspan="5" style="text-align: right">
            <p>{{ username }}</p>
            <form action="http://localhost:8080/api/logout" method="post">
                <input id="logout_btn" type="submit" value="Sign Out" />
            </form>
        </td>
    </tr>
    <tr class="firstRow">
        <td class="form" colspan="1" width="50%">
            <p id="errorMessage">{{ errorMessage }}</p>
            <vueForm :dataBaseHits="dataBaseHits" @getR="R = $event" @getHits="dataBaseHits = $event" 
            @getErrMsg="errorMessage = $event"></vueForm>
        </td>
        <td class="block">
            <vueGraph :dataBaseHits="dataBaseHits" :R="R" @getHits="dataBaseHits = $event" @getErrMsg="errorMessage = $event"></vueGraph>
        </td>
    </tr>
    <tr>
        <td colspan="5" id="last_requests">Последние 10 запросов:</td>
    </tr>
    <tr>
        <td colspan="3">
            <div id="results">
                <table id="example" class="answerTable">
                        <thead>
                        <tr>
                            <th>Время запроса</th>
                            <th>X</th>
                            <th>Y</th>
                            <th>R</th>
                            <th>Попадание</th>
                            <th>Время выполнения скрипта</th>
                        </tr>
                        </thead>
                        <tbody id="rows" align="center">
                            <vueResults :dataBaseHits="dataBaseHits"></vueResults>
                        </tbody>
                </table>
            </div>
        </td>
    </tr>
</table>
</div>
</template>

<script>
import vueGraph from "@/components/VueGraph.vue"
import vueForm from "@/components/VueForm.vue"
import vueResults from "@/components/VueResults.vue"
import axios from 'axios'
import VueCookies from "vue-cookies"
import moment from "moment"
import { useRouter } from 'vue-router';

export default{
    name: "VueIndex",
    components: {
        vueGraph,
        vueForm,
        vueResults,
    },
    data() {
        return {
            dataBaseHits: [],
            username: '',
            R: null,
            errorMessage: null,
            authError: false,
            router: useRouter(),
        }
    },
    created() {
        axios.defaults.headers.common['Authorization'] = 'Bearer ' + VueCookies.get("jwt")
        this.getHitsOnCreation();
    },
    methods: {
        format_date(value){
            if (value) {
            return moment(String(value)).format('yyyy-MM-dd HH:mm:ss')
            }
        },
        async getHitsOnCreation() {
                await axios.get("/api/hit").catch(error => {
                    if (error.response.status == 401) {
                        this.authError = true; 
                        this.router.push("login")
                }
                }).then(response => {
                    if (this.authError) return;
                    this.authError = false;
                    if (typeof response.data == 'number' || typeof response.data == 'string') {
                        this.username = response.data;
                        return;
                    }
                    this.dataBaseHits = response.data;
                    for (let i = 0; i < this.dataBaseHits.length; i++) {
                        this.dataBaseHits[i].curTime = this.format_date(this.dataBaseHits[i].curTime)
                    }
                    this.username = this.dataBaseHits[0].username;
                });  
            }
    },
}
</script>

<style>
        #body {
            background-color: #BAFFED;
        }

        #table {
            margin: auto;
            text-align: center;
            border: 5px solid red;
        }

        #last_requests {
            text-align: center;
            font-size: 23px
        }

        #errorMessage {
            color: red;
            margin-bottom: 5%;
        }

        .answerTable {
            width: 100%;
            height: 100%;
            margin-bottom: 2%;
            border: 1px solid;
            border-collapse: collapse;
        }

        .answerTable th {
            font-weight: bold;
            padding: 5px;
            border: 1px solid;
            background: white;
        }

        .answerTable td {
            border: 1px solid;
            padding: 5px;
            background: white;
        }

        #logout_btn {
            margin-bottom: 1%;   
        }
        .firstRow {
            background: white;
        }

        h1, h2 {
            font-family: sans-serif;
            color: black;
            font-size: 25px;
            text-align: center;
        }

        input[type=text]:focus {
            border: 3px solid #555;
        }

        td[colspan="3"] {
            font-family: sans-serif;
            color: black;
        }

        #results {
            height: 80%;
        }
        @media screen and (max-width: 886px) {
            .block {
                position: relative;
                margin: 0;
                padding: 0;
                min-width: 350px;
                min-height: 350px;
            }

            .block .point {
                position: absolute;
                width: 5px;
                height: 5px;
                background: #000;
            }

            td[colspan="5"] {
                height: 0;
            }
        }
        @media screen and (min-width: 886px) and (max-width: 1061px) {
            .block {
                position: relative;
                margin: 0;
                padding: 0;
                min-width: 500px;
                min-height: 500px;
            }

            .block .point {
                position: absolute;
                width: 8px;
                height: 8px;
                background: #000;
            }
            #table{
                height: 70%;
            }
            td[colspan="5"] {
                height: 0;
                position: relative;
            }
        }
        @media screen and (min-width: 1061px) {
            .block {
                position: relative;
                margin: 0;
                padding: 0;
                min-width: 600px;
                min-height: 600px;
            }

            .block .point {
                position: absolute;
                width: 10px;
                height: 10px;
                background: #000;
            }
            #table{
                height: 70%;
            }
        }
    </style>