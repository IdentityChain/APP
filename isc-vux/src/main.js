// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import FastClick from 'fastclick'
import VueRouter from 'vue-router'
import router from './router/index'
import VueResource from 'vue-resource'
import { ToastPlugin } from 'vux'
import AppConfig from './config/config'
import HttpRequest from './util/ajax'
import App from './App'

Vue.use(VueRouter)
Vue.use(VueResource)
Vue.use(HttpRequest)
Vue.use(ToastPlugin)
Vue.prototype.AppConfig = AppConfig

Vue.http.options.emulateJSON = true
FastClick.attach(document.body)

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  router,
  render: h => h(App)
}).$mount('#app-box')
