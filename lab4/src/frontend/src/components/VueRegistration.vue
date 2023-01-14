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
                        <input v-model="user.username" class="login" type="text" name="username"><br>

                        <b>Password:</b>
                        <input v-model="user.password" class="login " type="password" name="password"> <br>
                        <input id="register" type="submit" value="Registration" />
                    </form>
                    <router-link to="/login">Back to login screen</router-link>
                </td>
            </tr>
        </table>
    </div>
</template>

<script>
import { reactive } from 'vue';
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
            if (/[^a-zA-Z0-9]/.test(user.username)) {
                errorMsg.errorMessage = "В логине содержатся недопустимые символы"
                return;
            }
            if (user.username.length < 7) {
                errorMsg.errorMessage = "Логин должен быть больше 7 символов"
                return;
            }
            if (user.password.length < 6) {
                errorMsg.errorMessage = "Пароль должен быть больше 6 символов"
                return;
            }
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

    },
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
    margin-bottom: 1%;
}

#register {
    margin-bottom: 1%;
}

h1,
h2 {
    font-family: sans-serif;
    color: black;
    font-size: 25px;
    text-align: center;
    vertical-align: text-top;
}

input[type=text]:focus {
    border: 3px solid #555;
}
</style>