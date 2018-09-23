<template>
  <div style="height: 100%">
    <view-box body-padding-top="46px">
      <div v-if="showHeader" slot="header"
           style="position: absolute;height: calc(40px + env(safe-area-inset-top));width:100%;left: 0;top: 0;background-color: black;z-index: 100">
        <x-header :left-options="{showBack: true}"
                  style="position: absolute;left:0;top: env(safe-area-inset-top);width: 100%;z-index: 100;">{{title}}
        </x-header>
      </div>
      <p v-for="i in 100">{{i}}</p>
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
  import {XButton, XHeader, ViewBox, Tabbar, TabbarItem} from 'vux'

  export default {
    components: {
      ViewBox,
      XButton,
      XHeader,
      Tabbar,
      TabbarItem
    },
    data () {
      return {
        showHeader: true,
        title: 'title'
      }
    },
    methods: {}
  }
</script>

<style scoped>
  .toolbar-footer {
    position: fixed;
    /*padding-bottom: env(safe-area-inset-bottom);*/
  }

</style>
