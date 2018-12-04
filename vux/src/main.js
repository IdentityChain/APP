// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import FastClick from 'fastclick'
import VueRouter from 'vue-router'
import router from './router/index'
import App from './App'
// import Vuex from 'vuex'
import store from './store/index'
import * as myUtil from './util/myUtils'
import { sync } from 'vuex-router-sync'
import {ToastPlugin, LoadingPlugin, BusPlugin} from 'vux'
import VueClipboard from 'vue-clipboard2'
import * as config from './config/config'
import HttpRequest from './util/ajax'
import api from './request/api'
var db = require('store')

Vue.use(VueRouter)
Vue.use(HttpRequest)
Vue.use(VueClipboard)
Vue.use(BusPlugin)
Vue.use(ToastPlugin)
Vue.use(LoadingPlugin)
Vue.prototype.$db = db
Vue.prototype.$appConfig = config
Vue.prototype.$api = api
Vue.prototype.$utils = myUtil

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
  console.log('路由拦截')
  if (to.meta.requiresAuth) {
    console.log('访问需要授权的网页,判断是否存在token')
    if (typeof (db.get('token')) === 'undefined') {
      console.log('未找到token')
      history.clear()
      router.push({name: 'login'})
    } else {
      console.log(db.get('token'))
    }
  } else {
    console.log('访问未授权的网页')
  }
  // 判断是否从home跳转到login,清空历史记录
  if (to.path === '/login' && from.path === '/') {
    history.clear()
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
  // if (config.deployAPP) {
  //   if (to.path === '/' || to.path === '/login') {
  //     window.WkWebView.allowsBackForwardNavigationGestures(false)
  //   } else {
  //     console.log('开启滑动返回')
  //     window.WkWebView.allowsBackForwardNavigationGestures(true)
  //   }
  // }
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
if (config.deployAPP) {
  document.addEventListener('deviceready', function () {
    new Vue({
      store,
      router,
      render: h => h(App)
    }).$mount('#app-box')
    console.log('platform is:' + window.cordova.platformId)
    window.navigator.splashscreen.hide()
    window.addEventListener('keyboardWillHide', function () {
      document.getElementsByTagName('html')[0].style.height = '101vh'
      setTimeout(() => {
        document.getElementsByTagName('html')[0].style.height = '100vh'
      }, 40)
    })
  }, false)
} else {
  new Vue({
    store,
    router,
    render: h => h(App)
  }).$mount('#app-box')
}
