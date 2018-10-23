import http from '../../http'
const get = 'get'
const post = 'post'

const requestObj = {
  getPublicMailList: {
    method: get,
    url: '/futureLetters/getPublicLetterList',
    params: {
      'page': '',
      'pagesize': 10
    }
  },
  getMailCount: {
    method: get,
    url: '/futureLetters/getLetterCount'
  },
  sendMail: {
    method: post,
    url: '/futureLetters/sendmessage',
    params: {
      'flIsPublic': '',
      'flMessage': '',
      'flemail': '',
      'sendTime': '',
      'userid': ''
    }
  }
}

const mailApi = {
  getPublicMailList (pageNo) {
    requestObj.getPublicMailList.params.page = pageNo
    return http.doRequest(requestObj.getPublicMailList)
  },
  getMailCount () {
    return http.doRequest(requestObj.getMailCount)
  },
  sendMail (receiverMail, content, sendTime, isPublic, userId) {
    requestObj.sendMail.params.flemail = receiverMail
    requestObj.sendMail.params.flMessage = content
    requestObj.sendMail.params.sendTime = sendTime
    requestObj.sendMail.params.flIsPublic = isPublic
    requestObj.sendMail.params.userid = userId
    return http.doRequest(requestObj.sendMail)
  }
}

export default mailApi
