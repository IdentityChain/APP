<template>
  <div style="height: 100%;background-color: #EDEAF3">
    <view-box :body-padding-top="showHeader ? '46px' : '0px'" body-padding-bottom="55px">
      <div v-if="showHeader" slot="header"
           style="position: absolute;height: calc(40px + env(safe-area-inset-top));width:100%;left: 0;top: 0;background-color: mediumslateblue;z-index: 100">
        <x-header :left-options="{showBack: false}"
                  style="position: absolute;left:0;top: env(safe-area-inset-top);width: 100%;z-index: 100;">IZone
        </x-header>
      </div>
      <keep-alive>
        <component :is="currentView" ref="nowView"
                   style="padding-top: env(safe-area-inset-top);padding-bottom: env(safe-area-inset-bottom)"></component>
      </keep-alive>
      <tabbar slot="bottom"  class="toolbar-footer">
        <tabbar-item :selected="currentView === 'wakuang'" @click.native="changeView('wakuang')">
          <img slot="icon" src="../assets/icon/WK_hui.png">
          <img slot="icon-active" src="../assets/icon/WK.png">
          <span slot="label">挖宝</span>
        </tabbar-item>
        <tabbar-item :selected="currentView === 'activity'" @click.native="changeView('activity')">
          <img slot="icon" src="../assets/icon/yingyong_hui.png">
          <img slot="icon-active" src="../assets/icon/YINGYONG.png">
          <span slot="label">活动</span>
        </tabbar-item>
        <tabbar-item :selected="currentView === 'my'" @click.native="changeView('my')">
          <img slot="icon" src="../assets/icon/my_hui.png">
          <img slot="icon-active" src="../assets/icon/MY.png">
          <span slot="label">我的</span>
        </tabbar-item>
      </tabbar>
    </view-box>
  </div>
</template>

<script>
  import {Group, Cell, XButton, XHeader, Tabbar, TabbarItem, ViewBox} from 'vux'
  import {WaKuang, My, Activity} from './homeIndex'
  import WKHome from './main/Home'

  export default {
    components: {
      ViewBox,
      Group,
      Cell,
      XButton,
      XHeader,
      Tabbar,
      TabbarItem,
      'wakuang': WaKuang,
      'wkHome': WKHome,
      'my': My,
      'activity': Activity
    },
    data () {
      return {
        msg: 'Hello World!'
      }
    },
    computed: {
      currentView: {
        get: function () {
          return this.$store.state.homeObj.homeView
        },
        set: function () {
        }
      },
      showHeader: {
        get: function () {
          return this.$store.state.homeObj.headerStatus
        },
        set: function () {
        }
      }
    },
    activated: function () {
      this.$nextTick(function () {
        if (this.$appConfig.deployAPP) {
          console.log('禁止滑动返回')
          window.WkWebView.allowsBackForwardNavigationGestures(false)
        }
      })
    },
    methods: {
      changeView (view) {
        let homeObj1 = {
          headerStatus: false,
          nowView: 'wakuang'
        }
        if (view === 'wakuang') {
          homeObj1.headerStatus = false
        } else {
          homeObj1.headerStatus = true
        }
        this.$store.commit('updateHeaderStatus', homeObj1)
        homeObj1.nowView = view
        this.$store.commit('updateHomeView', homeObj1)
        this.$nextTick(function () {
          if (view === 'wakuang') {
            this.$refs.nowView.init()
          }
        })
      },
      back () {
        // this.$store.commit('setTransition', 'turn-off')
        this.$router.back(-1)
      },
      gotoHome () {
        this.$router.push({name: 'home'})
        // this.$store.commit('setTransition', 'turn-on')
      }
    }
  }
</script>

<style scoped>
  html, body {
    height: 100%;
    width: 100%;
    overflow-x: hidden;
  }
  /*.main {*/
  /*height: 100%;*/
  /*width: 100%;*/
  /*position: fixed;*/
  /*background-color: black;*/
  /*padding-top: env(safe-area-inset-top);*/
  /*!*padding-bottom: env(safe-area-inset-bottom);*!*/
  /*!*padding-bottom: env(safe-area-inset-bottom);*!*/
  /*}*/
  .toolbar-footer {
    position: fixed;
    z-index: 100;
    padding-bottom: env(safe-area-inset-bottom);
  }

  .vux-demo {
    text-align: center;
  }

  .logo {
    width: 100px;
    height: 100px
  }
  .program {
    border-radius: 15px;
    height: 28px;
    width: 80px;
    z-index: 1999;
    background:rgba(2,2,2,0.1);
    position: fixed;
    top: calc(9px + env(safe-area-inset-top));
    right: 10px;
    border: #cccccc 1px solid;
  }
</style>
