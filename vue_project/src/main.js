import { createApp } from 'vue'
import App from './App.vue'
import axios from 'axios'
import VueAxios from 'vue-axios'
import './output.css';
import router from './router'

const app = createApp(App)

app.config.productionTip = false
app.use(VueAxios, axios)
app.use(router)

app.mount('#app')
