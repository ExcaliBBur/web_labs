<template>
    <head>
    <meta charset="UTF-8">
    <title>Login screen</title>
</head>
<table id="reg_table">
    <tr>
        <td style="vertical-align: top">
            <h1>Павлов Александр P32111</h1>
            <h2>Лабораторная работа №4</h2>
            <h2>Вариант 11117</h2>
            Вход в аккаунт <br>
            <p style="color:red">
                {{ errorMsg.errorMessage }}
            </p>
            <form @submit.prevent="submit">
                <b>Username:</b>
                <input v-model="user.username" class="text" type="text" name="username"> <br>
                <b>Password:</b>

                <input v-model="user.password" class="text" type="password" name="password"> <br>
                <input id="register" type="submit" value="Sign in"/>
            </form>

            <router-link to="registration">Registration</router-link>
        </td>
    </tr>
</table>
</template>

<script>
import { reactive} from 'vue';
import axios from 'axios'
import { useRouter } from 'vue-router';

export default {
    name: "VueLogin",
    setup() {
    const user = reactive({
        username: '',
        password: ''
    })
    const errorMsg = reactive({
        errorMessage: null
    })

    const router = useRouter();

    const submit = async () => {
        errorMsg.errorMessage = null;

        await axios.post("http://localhost:8080/login", user).catch(function (error) {
            errorMsg.errorMessage = error.response.data;
        })
        if (errorMsg.errorMessage == null) {
            await router.push('index');
        }
    }

    return {
        user,
        errorMsg,
        submit
    }

  }
}
</script>

<style>
        body {
            background-color: #BAFFED;
        }

        #reg_table {
            width: 70%;
            height: 60%;
            margin: auto;
            text-align: center;
        }
        .text {
            margin-bottom: 10px;
        }
        h1, h2 {
            font-family: sans-serif;
            color: black;
            font-size: 25px;
            text-align: center;
            vertical-align: text-top;
        }

        input[type=text]:focus {
            border: 3px solid #555;
        }

        input[type=text] {
            width: 10%;
        }

        input[type=password] {
            width: 10%;
        }
</style>
