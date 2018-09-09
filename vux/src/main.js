// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import FastClick from 'fastclick'
import VueRouter from 'vue-router'
import router from './router/index'
import App from './App'
import Vuex from 'vuex'
import { sync } from 'vuex-router-sync'
import { BusPlugin } from 'vux'

Vue.use(VueRouter)
Vue.use(Vuex)
Vue.use(BusPlugin)

const shouldUseTransition = true

let store = new Vuex.Store({
  state: {
    // states: 'turn-on'
    // direction: shouldUseTransition ? 'forward' : ''
    fullPage: true
  },
  mutations: {
    // setTransition (state, states) {
    //   state.states = states
    // }
    // updateDirection (state, payload) {
    //   if (!shouldUseTransition) {
    //     return
    //   }
    //   state.direction = payload.direction
    // }
  }
})
Vue.use(store)
store.registerModule('vux', {
  state: {
    direction: shouldUseTransition ? 'forward' : '',
    showHeader: true,
    showTabBar: true
  },
  mutations: {
    updateDirection (state, payload) {
      if (!shouldUseTransition) {
        return
      }
      state.direction = payload.direction
    },
    updateShowHeaderStatus (state, status) {
      state.showHeader = status
    },
    updateShowTabBarStatus (state, status) {
      state.showTabBar = status
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
  const toIndex = history.getItem(to.path)
  const fromIndex = history.getItem(from.path)
  if (to.path === '/') {
    store.commit('updateShowHeaderStatus', true)
    store.commit('updateShowTabBarStatus', true)
  }
  if (to.path === '/page1') {
    store.commit('updateShowHeaderStatus', false)
    store.commit('updateShowTabBarStatus', false)
  }
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
// new Vue({
//   store,
//   router,
//   render: h => h(App)
// }).$mount('#app-box')

document.addEventListener('deviceready', function () {
  new Vue({
    store,
    router,
    render: h => h(App)
  }).$mount('#app-box')
  window.navigator.splashscreen.hide()
}, false)
