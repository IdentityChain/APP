import axios from 'axios'
import router from '../router'
import Vue from 'vue'
import * as appConfig from '../config/config'

// 错题提示方法
const tip = msg => {
  const width = msg.length + 2
  Vue.$vux.toast.show({
    type: 'text',
    width: width + 'em',
    text: msg
  })
}

// 跳转到登录页面
const toLogin = () => {
  router.replace({
    name: 'login'
  })
}

var db = require('store')

// 处理非200返回的代码
const errorHandle = (status, other) => {
  switch (status) {
    case 401:
      tip('未登录')
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

// 创建axios实例
var instance = axios.create({timeout: 5000, withCredentials: false})
axios.defaults.baseURL = appConfig.apiServer
instance.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded'

// 请求前的拦截器
instance.interceptors.request.use(
  config => {
    // console.log('add cookie')
    // const token = '13520580169:1538848165531:6IGiCPqstodzNTzyZwgw2A=='
    // token && (config.headers.autoLogin = token)
    const token = db.get('token')
    token && (config.headers['Authorization'] = token)
    Vue.$vux.loading.show({
      text: '加载中',
      delay: 1000
    })
    return config
  },
  error => {
    Promise.error(error)
  })

// 相应拦截器
instance.interceptors.response.use(
  resonse => {
    console.log('进入响应')
    Vue.$vux.loading.hide()
    if (appConfig.useAuth) {
      if (resonse.status === 200) {
        return Promise.resolve(resonse)
      } else {
        return Promise.reject(resonse)
      }
    } else {
      return Promise.resolve(resonse)
    }
  },
  err => {
    if (err.response) {
      console.log(err.response)
    }
    console.log('进入拒绝')
    console.log(err.toString())
    Vue.$vux.loading.hide()
    const {response} = err
    // console.log(response)
    if (response) {
      errorHandle(response.status, response.data.message)
      return Promise.reject(response)
    } else {
      console.log(err)
      const errMsg = err.toString()
      if (errMsg.includes('timeout')) {
        tip('请求超时')
      } else if (errMsg.includes('Network Error')) {
        tip('请检查网络连接')
      } else {
        tip('无法访问,请稍后重试')
      }
    }
  }
)

function doRequest (requestObj) {
  if (requestObj.method === 'get') {
    return new Promise((resolve, reject) => {
      instance.get(requestObj.url, {params: requestObj.params}).then(res => {
        resolve(res)
      }).catch(err => {
        reject(err)
      })
    })
  } else if (requestObj.method === 'post') {
    return new Promise((resolve, reject) => {
      instance.post(requestObj.url, requestObj.params).then(res => {
        resolve(res)
      }).catch(err => {
        reject(err)
      })
    })
  }
}

export default {instance, doRequest}
