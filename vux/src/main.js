// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import FastClick from 'fastclick'
import VueRouter from 'vue-router'
import router from './router/index'
import App from './App'
// import Vuex from 'vuex'
import store from './store/index'
import { sync } from 'vuex-router-sync'
import {ToastPlugin, LoadingPlugin, BusPlugin} from 'vux'
import AppConfig from './config/config'
import HttpRequest from './util/ajax'
import api from './request/api'

Vue.use(VueRouter)
Vue.use(HttpRequest)
Vue.use(BusPlugin)
Vue.use(ToastPlugin)
Vue.use(LoadingPlugin)
Vue.prototype.AppConfig = AppConfig
Vue.prototype.$api = api

// Vue.http.interceptors.push((request, next) => {
//   // request.credentials = true
//   Vue.$vux.loading.hide()
//   console.log('进入拦截器拦截方法')
//   Vue.$vux.loading.show({
//     text: '加载中',
//     delay: 1000
//   })
//   console.log(request)
//   var timeout
//   if (request._timeout) {
//     timeout = setTimeout(() => {
//       console.log('进入拦截器超时方法')
//       Vue.$vux.toast.show({
//         type: 'cancel',
//         text: '请求超时'
//       })
//       Vue.$vux.loading.hide()
//       if (request.onTimeout) request.onTimeout(request)
//       request.abort()
//     }, request._timeout)
//   }
//   next((response) => {
//     Vue.$vux.loading.hide()
//     clearTimeout(timeout)
//     if (AppConfig.useAuth) {
//       console.log('进入拦截器响应方法,输出获取的相应数据,读取cookie和header')
//       console.log('获取登陆状态:' + response.headers.get('loginstatus'))
//       if (!(response.headers.get('loginStatus') === 'true')) {
//         window.localStorage.clear()
//         window.sessionStorage.clear()
//         if (request.url === AppConfig.apiServer + '/user/login' || request.url === AppConfig.apiServer + '/user/firstsave' || request.url === AppConfig.apiServer + '/sms/getCodeByPhone/13520580169') {
//           console.log('登陆注册页面,不进行刷新')
//         } else {
//           // window.location.href = 'http://localhost:8000/index.html'
//           Vue.$vux.toast.show({
//             type: 'text',
//             text: '登录超时'
//           })
//           router.push({name: 'login'})
//         }
//       } else {
//         console.log('已登录状态')
//         Vue.$vux.loading.hide()
//         console.log(response.body)
//       }
//     } else {
//       console.log(response.body)
//     }
//   })
// })

const shouldUseTransition = true

Vue.use(store)
store.registerModule('vux', {
  state: {
    direction: shouldUseTransition ? 'forward' : ''
  },
  mutations: {
    updateDirection (state, payload) {
      if (!shouldUseTransition) {
        return
      }
      state.direction = payload.direction
    }
  }
})

Vue.use(router)
sync(store, router)

const history = window.sessionStorage
history.clear()

let historyCount = history.getItem('count') * 1 || 0
history.setItem('/', 0)
let isPush = false
let endTime = Date.now()
let methods = ['push', 'go', 'replace', 'forward', 'back']

document.addEventListener('touchend', () => {
  endTime = Date.now()
})
methods.forEach(key => {
  let method = router[key].bind(router)
  router[key] = function (...args) {
    isPush = true
    method.apply(null, args)
  }
})

router.beforeEach(function (to, from, next) {
  if (AppConfig.useAuth) {
    if (to.name === 'login' || to.name === 'register' || to.name === 'resetPasswd') {
      console.log(to.name + '不需要登陆')
    } else {
      console.log(to.name + '需要检测是否登陆')
      if (window.localStorage.getItem('token') == null) {
        history.clear()
        router.push({name: 'login'})
      }
    }
  }
  // 判断是否从home跳转到login,清空历史记录
  if (from.path === '/' && to.path === '/login') {
    console.log('登陆跳转')
    history.getItem('login')
  }
  const toIndex = history.getItem(to.path)
  const fromIndex = history.getItem(from.path)
  if (toIndex) {
    if (!fromIndex || parseInt(toIndex, 10) > parseInt(fromIndex, 10) || (toIndex === '0' && fromIndex === '0')) {
      store.commit('updateDirection', {direction: 'forward'})
    } else {
      // 判断是否是ios左滑返回
      if (!isPush && (Date.now() - endTime) < 377) {
        store.commit('updateDirection', {direction: ''})
      } else {
        store.commit('updateDirection', { direction: 'reverse' })
      }
    }
  } else {
    ++historyCount
    history.setItem('count', historyCount)
    to.path !== '/' && history.setItem(to.path, historyCount)
    store.commit('updateDirection', {direction: 'forward'})
  }

  if (/\/http/.test(to.path)) {
    let url = to.path.split('http')[1]
    window.location.href = `http${url}`
  } else {
    next()
  }
})

router.afterEach(function (to) {
  isPush = false
  // store.commit('updateLoadingStatus', {isLoading: false})
  // if (process.env.NODE_ENV === 'production') {
  //   ga && ga('set', 'page', to.fullPath)
  //   ga && ga('send', 'pageview')
  // }
})
FastClick.attach(document.body)
Vue.config.productionTip = false

/* eslint-disable no-new */
if (AppConfig.deployAPP) {
  document.addEventListener('deviceready', function () {
    new Vue({
      store,
      router,
      render: h => h(App)
    }).$mount('#app-box')
    window.navigator.splashscreen.hide()
  }, false)
} else {
  new Vue({
    store,
    router,
    render: h => h(App)
  }).$mount('#app-box')
}
