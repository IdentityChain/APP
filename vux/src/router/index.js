import Vue from 'vue'
import Router from 'vue-router'
import Common from '@/components/common'
import Home from '@/view/Home'
import Page2 from '@/components/HelloFromVux2'
import Page3 from '@/components/HelloFromVux3'
import Login from '@/view/Login'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'index',
      component: Login
    },
    {
      path: '/home',
      name: 'home',
      component: Home
    },
    {
      path: '/page2',
      name: 'page2',
      component: Common
    },
    {
      path: '/page3',
      name: 'page3',
      component: Page3
    },
    {
      path: '/page4',
      name: 'page4',
      component: Page2
    }
  ]
})
