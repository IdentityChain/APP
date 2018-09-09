import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/view/base/Login'
import Register from '@/view/base/Register'
import Home from '@/view/base/Home'
import ResetPassword from '@/view/base/ResetPassword'

Vue.use(Router)

export default new Router({
  // mode: 'history',
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/register',
      name: 'register',
      component: Register
    },
    {
      path: '/resetPasswd',
      name: 'resetPasswd',
      component: ResetPassword
    },
    {
      path: '*',
      component: Login
    }
  ]
})
