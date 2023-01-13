<template>
<div id="body">
<table id="table">
    <tr>
        <td colspan="5" style="text-align: right">
            <p>{{ username }}</p>
            <form action="http://localhost:8080/logout" method="post">
                <input type="submit" value="Sign Out"/>
            </form>
        </td>
    </tr>
    <tr class="firstRow">
        <td class="form" colspan="1" width="50%">
            <vueForm/>
        </td>
        <td class="block">
            <div id = "graph">
                <vueGraph/>
            </div>
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
                            <vueResults/>
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

export default{
    name: "VueIndex",
    components: {
        vueGraph,
        vueForm,
        vueResults,
    },
    data() {
        return {
            hits: [],
        }
    }

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

        .text {
            margin-bottom: 5%;
            min-width: 15%;
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
            height: 90%;
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
                min-width: 800px;
                min-height: 600px;
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
                min-width: 1000px;
                min-height: 800px;
            }
        }
    </style>