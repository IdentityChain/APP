<template>
  <div style="height: 100%">
    <view-box body-padding-top="46px" body-padding-bottom="55px">
      <div v-if="showHeader" slot="header"
           style="position: absolute;height: calc(40px + env(safe-area-inset-top));width:100%;left: 0;top: 0;background-color: black;z-index: 100">
        <x-header :left-options="{showBack: false}"
                  style="position: absolute;left:0;top: env(safe-area-inset-top);width: 100%;z-index: 100;">ISC
        </x-header>
      </div>
      <keep-alive>
        <component :is="currentView" ref="nowView"
                   style="padding-top: env(safe-area-inset-top);padding-bottom: env(safe-area-inset-bottom)"></component>
      </keep-alive>
      <tabbar slot="bottom" class="toolbar-footer">
        <tabbar-item selected @click.native="changeView('wakuang')">
          <img slot="icon" src="../assets/icon/WK_hui.png">
          <img slot="icon-active" src="../assets/icon/WK.png">
          <span slot="label">挖宝</span>
        </tabbar-item>
        <tabbar-item @click.native="changeView('activity')">
          <img slot="icon" src="../assets/icon/yingyong_hui.png">
          <img slot="icon-active" src="../assets/icon/YINGYONG.png">
          <span slot="label">活动</span>
        </tabbar-item>
        <tabbar-item @click.native="changeView('my')">
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
  import WaKuang from '../components/home/WaKuang'
  import My from '../components/home/My'
  //活动页面
  import Activity from '../components/home/Activity'

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
      'my': My,
      'activity': Activity
    },
    data () {
      return {
        // note: changing this line won't causes changes
        // with hot-reload because the reloaded component
        // preserves its current state and we are modifying
        // its initial state.
        msg: 'Hello World!',
        currentView: 'wakuang',
        showHeader: false
      }
    },
    methods: {
      changeView (view) {
        if (view === 'wakuang') {
          this.showHeader = false
        } else {
          this.showHeader = true
        }
        this.currentView = view
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
    padding-bottom: env(safe-area-inset-bottom);
  }

  .vux-demo {
    text-align: center;
  }

  .logo {
    width: 100px;
    height: 100px
  }
</style>
