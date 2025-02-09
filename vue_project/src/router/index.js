import { createRouter, createWebHistory } from 'vue-router'
import AccountPage from '../views/Account.vue'
import LoginPage from '../views/Login.vue'
import RegisterPage from '../views/Register.vue'
import StudentList from '../components/StudentList.vue'
import HomePage from '@/views/Home.vue'
import AttendancePage from '@/views/Attendance.vue'

const routes = [
  { path: '/home', component: HomePage },
  { path: '/attendance', component: AttendancePage },
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
