import Register from '@/view/Register'
import ResetPasswd from '@/view/ResetPassword'
import Login from '@/view/Login'
import resetSetting from '@/view/myinfo/resetSetting'
import SystemSetting from '@/view/myinfo/SystemSetting'
import SecuritySetting from '@/view/myinfo/system_config/SecuritySetting'
export default [
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
    path: '/login',
    name: 'login',
    component: Login
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
  }
]
