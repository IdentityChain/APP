import base from '../base'
import {httpget, httppost} from '../../http'

const API = `${base.local}/user/`
const userapi = {
  userInfo (userid) {
    return httpget(`${API}findByUserId?userid=` + userid)
  },
  userLogin (account, password) {
    const loginParam = {
      account: account,
      password: password
    }
    console.log('user-api-login')
    return httppost(`${API}login`, loginParam)
  }
}

export default userapi
