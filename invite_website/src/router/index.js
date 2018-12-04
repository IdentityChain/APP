import Vue from 'vue'
import Router from 'vue-router'
// import Common from '@/components/common'
// import Page2 from '@/components/HelloFromVux2'
// import Page3 from '@/components/HelloFromVux3'
import Register from '@/view/Register'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/register',
      name: 'register',
      component: Register
    },
    {
      path: '*',
      component: Register
    }
  ]
})
