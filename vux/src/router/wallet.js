import TransRecord from '@/view/Wallet/TransRecord'
import MyWallet from '@/view/Wallet/MyWallet'
import BalanceDetail from '@/view/Wallet/BalanceDetail'
import YuBiBao from '@/view/Wallet/BiBaoHome'
import YuBiBaoDetail from '@/view/Wallet/BiBaoDetail'
export default [
  {
    path: '/myWallet',
    name: 'Wallet',
    component: MyWallet,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/myWallet/transRecord',
    name: 'transRecord',
    component: TransRecord,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/myWallet/balanceDetail',
    name: 'balanceDetail',
    component: BalanceDetail,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/myWallet/yuBiBao',
    name: 'yuBiBao',
    component: YuBiBao,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/myWallet/yuBiBao/detail',
    name: 'yuBiBaoDetail',
    component: YuBiBaoDetail,
    meta: {
      requiresAuth: true
    }
  }
]
