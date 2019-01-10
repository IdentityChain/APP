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
  },
  getAchievementDay: {
    method: get,
    url: '/calculate/getAchievementDay/'
  },
  getAchievementUser: {
    method: get,
    url: 'GET /calculate/getAchievementUser/{userid}/{achievementUserid}/{achievementid}'
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
  },
  // 查询用户每日任务
  getAchievementDayRequest (type, userId, page, pageSize) {
    requestObj.getAchievementDay.url = '/calculate/getAchievementDay/' + type + '/' + userId + '/' + page + '/' + pageSize
    return http.doRequest(requestObj.getAchievementDay)
  },
  // 获得任务成就奖励，对满足100条件的
  getAchievementUser (userID, auID, auachID) {
    requestObj.getAchievementUser.url = '/calculate/getAchievementUser/' + userID + '/' + auID + '/' + auachID
    return http.doRequest(requestObj.getAchievementUser)
  }
}

export default calculateApi
