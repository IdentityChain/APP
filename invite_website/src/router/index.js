import Vue from 'vue'
import Router from 'vue-router'
// import Common from '@/components/common'
// import Page2 from '@/components/HelloFromVux2'
// import Page3 from '@/components/HelloFromVux3'
import Register from '@/view/Register'
import AppDownload from '@/view/AppDownload'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/register',
      name: 'register',
      component: Register
    },
    {
      path: '/download',
      name: 'download',
      component: AppDownload
    },
    {
      path: '*',
      component: Register
    }
  ]
})
