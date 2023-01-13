import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import VueCookies from 'vue-cookies'

createApp(App).use(router).mount('#app')
axios.defaults.headers.post['Authorization'] = 'Bearer ' + VueCookies.get("jwt")
