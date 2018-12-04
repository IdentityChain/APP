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
  },
  updateLoginPassword: {
    method: post,
    url: '/user/updateLoginPassword',
    params: {
      account: '',
      oldPassword: '',
      newPassword: ''
    }
  },
  setPayPasswordBySmsCode: {
    method: post,
    url: '/user/updatePaymentPasswordBySms',
    params: {
      'payPassword': '',
      'phone': '',
      'smsCode': ''
    }
  },
  resetPayPassword: {
    method: post,
    url: '/user/updatePaymentPassword',
    params: {
      'account': '',
      'loginPassword': '',
      'newPaymentPassword': '',
      'oldPaymentPassword': '',
      'phone': ''
    }
  },
  registerUser: {
    method: post,
    url: '/user/firstsave',
    params: {
      'password': '',
      'phone': '',
      'smsCode': ''
    }
  },
  resetLoginPasswordBySms: {
    method: post,
    url: '/user/updateLoginPasswordBySms',
    params: {
      'password': '',
      'phone': '',
      'smsCode': ''
    }
  },
  getTotalCount: {
    method: get,
    url: '/user/findUserCount',
    params: {}
  }
}
const userapi = {
  // 获取系统所有用户数量
  getTotalCount () {
    return http.doRequest(requestObj.getTotalCount)
  },
  // 根据短信重置密码
  resetLoginPasswordBySms (phone, password, smsCode) {
    requestObj.resetLoginPasswordBySms.params.phone = phone
    requestObj.resetLoginPasswordBySms.params.password = password
    requestObj.resetLoginPasswordBySms.params.smsCode = smsCode
    return http.doRequest(requestObj.resetLoginPasswordBySms)
  },
  // 注册用户
  registerUser (phone, smsCode, password) {
    requestObj.registerUser.params.password = password
    requestObj.registerUser.params.phone = phone
    requestObj.registerUser.params.smsCode = smsCode
    return http.doRequest(requestObj.registerUser)
  },
  // 根据用户ID获取用户信息
  getUserInfoById (userid) {
    requestObj.getUserInfoById.params.userid = userid
    return http.doRequest(requestObj.getUserInfoById)
  },
  // 用户使用用户名密码登录
  userLogin (account, password) {
    requestObj.userLogin.params.account = account
    requestObj.userLogin.params.password = password
    return http.doRequest(requestObj.userLogin)
  },
  // 更新昵称
  updateNickName (userId, oldNickName, newNickName) {
    requestObj.updateNickName.params.userid = userId
    requestObj.updateNickName.params.oldNickName = oldNickName
    requestObj.updateNickName.params.newNickName = newNickName
    return http.doRequest(requestObj.updateNickName)
  },
  // 完善用户真实身份信息
  initUserInfo (userId, realName, identityNo) {
    requestObj.initUserInfo.params.userid = userId
    requestObj.initUserInfo.params.realName = realName
    requestObj.initUserInfo.params.identityNo = identityNo
    return http.doRequest(requestObj.initUserInfo)
  },
  // 使用旧密码更改登录密码
  updateLoginPassword (account, oldPassword, newPasswod) {
    requestObj.updateLoginPassword.params.account = account
    requestObj.updateLoginPassword.params.oldPassword = oldPassword
    requestObj.updateLoginPassword.params.newPassword = newPasswod
    return http.doRequest(requestObj.updateLoginPassword)
  },
  // 第一次设置交易密码
  setPayPasswordBySmsCode (phone, smsCode, payPassword) {
    requestObj.setPayPasswordBySmsCode.params.payPassword = payPassword
    requestObj.setPayPasswordBySmsCode.params.phone = phone
    requestObj.setPayPasswordBySmsCode.params.smsCode = smsCode
    return http.doRequest(requestObj.setPayPasswordBySmsCode)
  },
  // 使用旧交易密码重设交易密码
  resetPayPassword (phone, account, loginPassword, oldPayPassword, newPayPassword) {
    requestObj.resetPayPassword.params.phone = phone
    requestObj.resetPayPassword.params.account = account
    requestObj.resetPayPassword.params.loginPassword = loginPassword
    requestObj.resetPayPassword.params.oldPaymentPassword = oldPayPassword
    requestObj.resetPayPassword.params.newPaymentPassword = newPayPassword
    return http.doRequest(requestObj.resetPayPassword)
  }
}

export default userapi
