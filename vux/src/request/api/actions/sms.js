import http from '../../http'
const get = 'get'

const requestObj = {
  getSmsCode: {
    method: get,
    url: '/sms/getCodeByPhone/'
  }
}

const smsApi = {
  getSmsCode (phoneNumber, codeType) {
    requestObj.getSmsCode.url = '/sms/getCodeByPhone/' + phoneNumber + '/' + codeType
    return http.doRequest(requestObj.getSmsCode)
  }
}

export default smsApi
