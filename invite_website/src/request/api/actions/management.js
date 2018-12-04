import http from '../../http'
const get = 'get'

const requestObj = {
  getNotice: {
    method: get,
    url: '/management/getNewAnnouncement'
  }
}

const managementApi = {
  getNotice () {
    return http.doRequest(requestObj.getNotice)
  }
}

export default managementApi
