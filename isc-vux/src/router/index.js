import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/view/base/Login'
import Register from '@/view/base/Register'
import InitUserInfo from '@/view/base/InitUserInfo'
import Home from '@/view/base/Home'

Vue.use(Router)

export default new Router({
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
      path: '/initUserInfo',
      name: 'initUserInfo',
      component: InitUserInfo
    }
  ]
})
