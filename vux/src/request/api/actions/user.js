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
  }
}
const userapi = {
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
  }
}

export default userapi
