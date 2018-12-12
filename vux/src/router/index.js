import Vue from 'vue'
import Router from 'vue-router'
import Template from '@/components/Template'
import Home from '@/view/main/Home'
import Login from '@/view/Login'
import TopBang from '@/view/top/TopBang'
import Invite from '@/view/main/invite/InvitePage'
import Application from '@/view/activity/Activity'
import SendMailHome from '@/view/activity/sendMailToFuture/Home'
import WriteMail from '@/view/activity/sendMailToFuture/WriteMail'

import wallet from './wallet'
import persion from './persion'
import power from './power'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    ...persion,
    ...wallet,
    ...power,
    {
      path: '/applications',
      name: 'applications',
      component: Application,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/topBang',
      name: 'topBang',
      component: TopBang,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/invite',
      name: 'invite',
      component: Invite,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/template',
      name: 'template',
      component: Template,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/activity/sendMailHome',
      name: 'sendMailHome',
      component: SendMailHome,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/activity/wirteMail',
      name: 'wirteMail',
      component: WriteMail,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '*',
      component: Login
    }
  ]
})
