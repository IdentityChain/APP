import Vue from 'vue'
import Router from 'vue-router'
// import Common from '@/components/common'
// import Page2 from '@/components/HelloFromVux2'
// import Page3 from '@/components/HelloFromVux3'
import Template from '@/components/Template'
import Home from '@/view/Home'
import Login from '@/view/Login'
import ResetPasswd from '@/view/ResetPassword'
import Register from '@/view/Register'
import convertConsumeCoin from '@/view/myinfo/convertConsumeCoin'
import orderView from '@/view/myinfo/orderView'
import reConsume from '@/view/myinfo/reConsume'
import resetSetting from '@/view/myinfo/resetSetting'
import SystemSetting from '@/view/myinfo/SystemSetting'
import SecuritySetting from '@/view/myinfo/system_config/SecuritySetting'
import transferConsumeCoin from '@/view/myinfo/transferConsumeCoin'
import SendMailHome from '@/view/activity/sendMailToFuture/Home'
import WriteMail from '@/view/activity/sendMailToFuture/WriteMail'

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
      component: convertConsumeCoin,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/my/orderView',
      name: 'orderView',
      component: orderView,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/my/reConsume',
      name: 'reConsume',
      component: reConsume,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/my/resetSetting',
      name: 'resetSetting',
      component: resetSetting,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/my/systemSetting',
      name: 'systemSetting',
      component: SystemSetting,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/my/systemSetting/securitySetting',
      name: 'securitySetting',
      component: SecuritySetting,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/my/transferConsumeCoin',
      name: 'transferConsumeCoin',
      component: transferConsumeCoin,
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
