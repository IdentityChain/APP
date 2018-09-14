import Vue from 'vue'
import Router from 'vue-router'
// import Common from '@/components/common'
// import Page2 from '@/components/HelloFromVux2'
// import Page3 from '@/components/HelloFromVux3'
import Home from '@/view/Home'
import Login from '@/view/Login'
import ResetPasswd from '@/view/ResetPassword'
import Register from '@/view/Register'
import convertConsumeCoin from '@/view/myinfo/convertConsumeCoin'
import orderView from '@/view/myinfo/orderView'
import reConsume from '@/view/myinfo/reConsume'
import resetSetting from '@/view/myinfo/resetSetting'
import transferConsumeCoin from '@/view/myinfo/transferConsumeCoin'

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
      component: convertConsumeCoin
    },
    {
      path: '/my/orderView',
      name: 'orderView',
      component: orderView
    },
    {
      path: '/my/reConsume',
      name: 'reConsume',
      component: reConsume
    },
    {
      path: '/my/resetSetting',
      name: 'resetSetting',
      component: resetSetting
    },
    {
      path: '/my/transferConsumeCoin',
      name: 'transferConsumeCoin',
      component: transferConsumeCoin
    },
    {
      path: '*',
      component: Login
    }
  ]
})
