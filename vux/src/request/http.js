import axios from 'axios'
import router from '../router'
import Vue from 'vue'
import {ToastPlugin} from 'vux'
Vue.use(ToastPlugin)

const tip = msg => {
  Vue.toast.show({
    text: msg
  })
}

const toLogin = () => {
  router.replace({
    name: 'login'
  })
}

const errorHandle = (status, other) => {
  switch (status) {
    case 401:
      toLogin()
      break
    case 403:
      tip('登录过期')
      setTimeout(() => {
        toLogin()
      }, 1000)
      break
    case 404:
      tip('请求的资源不存在')
      break
    default:
      console.log(other)
  }
}

var instance = axios.create({timeout: 10000})
instance.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8'

instance.interceptors.request.use(config => {

})
