import http from '../../http'
const get = 'get'

const requestObj = {
  getUserInvitaInfo: {
    method: get,
    url: '/invita/getUserInvitaInfo/'
  },
  getInviteCount: {
    method: get,
    url: '/invita/getUserInvitaInfo/'
  }
}

const mailApi = {
  getUserInvitaInfoRequest (userId, pageNum, pageSize) {
    requestObj.getUserInvitaInfo.url = '/invita/getUserInvitaInfo/' + userId + '/' + pageNum + '/' + pageSize
    return http.doRequest(requestObj.getUserInvitaInfo)
  },
  getInvitaCountRequest (userId) {
    requestObj.getInviteCount.url = '/invita/getUserInvitaInfo/' + userId
    return http.doRequest(requestObj.getInviteCount)
  }
}

export default mailApi
