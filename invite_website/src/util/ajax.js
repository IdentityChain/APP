exports.install = function (Vue, params) {
  Vue.prototype.doPost = function (requestOption) {
    return this.$http.post(requestOption.url, requestOption.params)
      .then(response => {
        return response.body
      }, responseErr => {
        if (responseErr.status === 408) {
          let result = {
            message: '访问超时',
            code: 503,
            data: null,
            success: false
          }
          return result
        } else {
          // return result
        }
      })
  }
  Vue.prototype.doGet = function (requestOption) {
    return this.$http.get(requestOption.url)
      .then(response => {
        return response.body
      }, responseErr => {
        if (responseErr.status === 408) {
          let result = {
            message: '访问超时',
            code: 503,
            data: null,
            success: false
          }
          return result
        } else {
          // let result = {
          //   message: '系统错误',
          //   code: 500,
          //   data: null,
          //   success: false
          // }
          // return result
        }
      })
  }
}
