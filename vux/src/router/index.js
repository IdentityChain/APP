import Vue from 'vue'
import Router from 'vue-router'
// import Common from '@/components/common'
// import Page2 from '@/components/HelloFromVux2'
// import Page3 from '@/components/HelloFromVux3'
import Home from '@/view/Home'
import Login from '@/view/Login'
import ResetPasswd from '@/view/ResetPassword'
import Register from '@/view/Register'
import ConvertConsumeCoin from '@/components/Template'

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
      path: '/resetPasswd',
      name: 'resetPasswd',
      component: ResetPasswd
    },
    {
      path: '/register',
      name: 'register',
      component: Register
    },
    {
      path: '/my/convertConsumeCoin',
      name: 'convertConsumeCoin',
      component: ConvertConsumeCoin
    },
    {
      path: '*',
      component: Login
    }
  ]
})
