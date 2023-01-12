import {createRouter, createWebHistory} from "vue-router"
import VueRegistration from "@/components/VueRegistration.vue";
import VueLogin from "@/components/VueLogin";
import VueIndex from "@/components/VueIndex";


const routes = [
    {path: '/registration', component: VueRegistration},
    {path: '/login', component: VueLogin},
    {path: '/index', component: VueIndex}
];

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router