<template>
<div class="registration">
<table id="reg_table">
    <tr>
        <td style="vertical-align: top">
            <h1>Павлов Александр Р32111</h1>
            <h2>Лабораторная работа №4</h2>
            <h2>Вариант 11117</h2>
            Регистрация пользователя: <br>
            <p style="color:red">
                {{ errorMsg.errorMessage }}
            </p>
            <form @submit.prevent="submit">
                <b>Username:</b>
                <input v-model="user.username" class="text" type="text" name="username" lazy-rules
                :rules="[
            (val) =>
              (val && val.length > 0) || 'Please write your userusername',
            ]"> <br>
                <b>Password:</b>

                <input v-model="user.password" class="text" type="password" name="password"> <br>
                <input id="register" type="submit" value="Registration"/>
            </form>
            <router-link to="/login">Back to login screen</router-link>
        </td>
    </tr>
</table>
  </div>
</template>

<script>
import { reactive} from 'vue';
import axios from 'axios'
import { useRouter } from 'vue-router';

export default {
  name: 'VueRegistration',
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

        await axios.post("http://localhost:8080/api/auth/registration", user).catch(function (error) {
            errorMsg.errorMessage = error.response.data;
        })
        if (errorMsg.errorMessage == null) {
            await router.push('login');
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
        #registration {
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
