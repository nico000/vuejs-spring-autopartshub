import './assets/tailwind.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import axios from "axios";
//axios.defaults.baseURL = process.env.VUE_APP_API_URL;
//axios.defaults.baseURL = 'https://jsonplaceholder.typicode.com/';

const app = createApp(App)
console.log(process.env.VUE_APP_API_ROOT_URL);
app.use(router)

app.mount('#app')
