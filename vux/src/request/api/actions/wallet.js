import http from '../../http'
const get = 'get'

const requestObj = {
  getTradingFind: {
    method: get,
    url: '/trading/getTradingFind'
  }
}

const walletApi = {
  // 根据用户ID 获取用户可以拾取的币
  getBalance (userId, address) {
    requestObj.getTradingFind.url = '/trading/getTradingFind/' + userId + '/' + address
    return http.doRequest(requestObj.getTradingFind)
  }
}

export default walletApi
