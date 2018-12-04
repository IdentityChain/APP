import http from '../../http'
const get = 'get'

const requestObj = {
  getCalculateLog: {
    method: get,
    url: '/calculate/getCalculateLog/'
  },
  getCalculateTop100: {
    method: get,
    url: '/calculate/getCalculateStatistic100'
  },
  checkCalculate: {
    method: get,
    url: '/calculate/checkCalculateLog/'
  }
}

const calculateApi = {
  // 根据用户ID 获取用户可以拾取的币
  getCalculateLog (userId) {
    requestObj.getCalculateLog.url = '/calculate/getCalculateLog/' + userId
    return http.doRequest(requestObj.getCalculateLog)
  },
  // 获取成就点排名前100人
  getCalculateTop100 () {
    return http.doRequest(requestObj.getCalculateTop100)
  },
  // 用户点击币 拾取币
  checkCalculate (iscLID) {
    requestObj.checkCalculate.url = '/calculate/checkCalculateLog/' + iscLID
    return http.doRequest(requestObj.checkCalculate)
  }
}

export default calculateApi
