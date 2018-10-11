import http from '../../http'
const post = 'post'
const get = 'get'

const requestObj = {
  initUserInfo: {
    method: post,
    url: '/user/initUserInfo',
    params: {
      'identityNo': '',
      'realName': '',
      'userid': ''
    }
  },
  updateNickName: {
    method: post,
    url: '/user/updateNickName',
    params: {
      'newNickName': '',
      'oldNickName': '',
      'userid': ''
    }
  },
  getUserInfoById: {
    method: get,
    url: '/user/findByUserId',
    params: {
      'userid': ''
    }
  },
  userLogin: {
    method: post,
    url: '/user/login',
    params: {
      'account': '',
      'password': ''
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
  },
  updateNickName (userId, oldNickName, newNickName) {
    requestObj.updateNickName.params.userid = userId
    requestObj.updateNickName.params.oldNickName = oldNickName
    requestObj.updateNickName.params.newNickName = newNickName
    return http.doRequest(requestObj.updateNickName)
  },
  initUserInfo (userId, realName, identityNo) {
    requestObj.initUserInfo.params.userid = userId
    requestObj.initUserInfo.params.realName = realName
    requestObj.initUserInfo.params.identityNo = identityNo
    return http.doRequest(requestObj.initUserInfo)
  }
}

export default userapi
