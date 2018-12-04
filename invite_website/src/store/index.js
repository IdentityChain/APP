import Vuex from 'vuex'
import Vue from 'vue'
Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    homeObj: {
      homeView: 'wkHome',
      headerStatus: false
    },
    currentUser: {}
  },
  mutations: {
    updateHomeView (state, payload) {
      state.homeObj.homeView = payload.nowView
    },
    updateHeaderStatus (state, payload) {
      state.homeObj.headerStatus = payload.headerStatus
    },
    updateHomeObj (state, payload) {
      state.homeObj.homeView = payload.nowView
      state.homeObj.headerStatus = payload.headerStatus
    },
    updateCurrentUser (state, payload) {
      state.currentUser = payload
    }
  }
})
