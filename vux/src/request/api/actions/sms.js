import http from '../../http'
const post = 'post'
const get = 'get'

const requestObj = {
  updateNickName: {
    method: post
  },
  getUserInfoById: {
    method: get,
    url: 'findByUserId',
    params: {
      userid: ''
    }
  },
  userLogin: {
    method: post,
    url: 'login',
    params: {
      account: '',
      password: ''
    }
  }
}
const userapi = {
  getUserInfoById (userid) {
    requestObj.getUserInfoById.params.userid = userid
    return http.doRequest(requestObj.getUserInfoById)
  },
  userLogin (account, password) {
    requestObj.userLogin.params.account = account
    requestObj.userLogin.params.password = password
    return http.doRequest(requestObj.userLogin)
  }
}

export default userapi
