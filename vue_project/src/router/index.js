import { createRouter, createWebHistory } from 'vue-router'
import HomePage from '../views/home.vue'
import AccountPage from '../views/Account.vue'
import LoginPage from '../views/Login.vue'
import RegisterPage from '../views/Register.vue'
import StudentList from '../components/StudentList.vue'

const routes = [
  { path: '/', component: HomePage },
  { path: '/students', component: StudentList },
  { path: '/account', component: AccountPage },
  { path: '/login', component: LoginPage },
  { path: '/register', component: RegisterPage }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
