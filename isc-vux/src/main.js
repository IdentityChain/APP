// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import FastClick from 'fastclick'
import VueRouter from 'vue-router'
import router from './router/index'
import VueResource from 'vue-resource'
import {ToastPlugin, LoadingPlugin} from 'vux'
import AppConfig from './config/config'
import HttpRequest from './util/ajax'
import App from './App'

Vue.use(VueRouter)
Vue.use(VueResource)
Vue.use(HttpRequest)
Vue.use(ToastPlugin)
Vue.use(LoadingPlugin)
Vue.prototype.AppConfig = AppConfig
Vue.http.options.timeout = 5000

Vue.http.options.emulateJSON = true
Vue.http.options.xhr = {withCredentials: true}
Vue.http.interceptors.push((request, next) => {
  request.credentials = true
  // if (request.url === AppConfig.apiServer + '/user/login') {
  //
  // }
  // request.headers.set('Token', '111')
  console.log('进入拦截器拦截方法')
  Vue.$vux.loading.hide()
  Vue.$vux.loading.show({
    text: '加载中',
    delay: 500
  })
  console.log(request)
  var timeout
  if (request._timeout) {
    timeout = setTimeout(() => {
      console.log('进入拦截器超时方法')
      Vue.$vux.toast.show({
        type: 'cancel',
        text: '请求超时'
      })
      Vue.$vux.loading.hide()
      if (request.onTimeout) request.onTimeout(request)
      request.abort()
    }, request._timeout)
  }
  next((response) => {
    Vue.$vux.loading.hide()
    clearTimeout(timeout)
    if (AppConfig.useAuth) {
      console.log('进入拦截器响应方法,输出获取的相应数据,读取cookie和header')
      console.log('获取登陆状态:' + response.headers.get('loginstatus'))
      if (!(response.headers.get('loginStatus') === 'true')) {
        window.localStorage.clear()
        if (request.url === AppConfig.apiServer + '/user/login' || request.url === AppConfig.apiServer + '/user/firstsave' || request.url === AppConfig.apiServer + '/sms/getCodeByPhone/13520580169') {
          console.log('登陆注册页面,不进行刷新')
        } else {
          // window.location.href = 'http://localhost:8000/index.html'
          router.push({name: 'login'})
        }
      } else {
        console.log('已登录状态')
        Vue.$vux.loading.hide()
        console.log(response.body)
      }
    } else {
      console.log(response.body)
    }
  })
})

router.beforeEach(function (to, from, next) {
  // alert(window.location.href)
  console.log('跳转至' + to.name)
  if (to.name === 'login' || to.name === 'register' || to.name === 'resetPasswd') {
    console.log(to.name + '不需要登陆')
  } else {
    console.log(to.name + '需要检测是否登陆')
    if (window.localStorage.getItem('User') == null) {
      router.push({name: 'login'})
    }
  }
  next()
})

FastClick.attach(document.body)

Vue.config.productionTip = false

/* eslint-disable no-new */
document.addEventListener('deviceready', function () {
  new Vue({
    router,
    render: h => h(App)
  }).$mount('#app-box')
  window.navigator.splashscreen.hide()
}, false)
