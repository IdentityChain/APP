import AddPowerHome from '@/view/power/AddPowerHome'

export default [
  {
    path: '/addPowerHome',
    name: 'addPowerHome',
    component: AddPowerHome,
    meta: {
      requiresAuth: true
    }
  }
]
