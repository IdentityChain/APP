<template>
  <div class="main">
    <x-header :left-options="{showBack: true}" style="position: fixed;width: 100%;z-index: 100;">ISC</x-header>
    <view-box style="background-color: white;" body-padding-top="46px" body-padding-bottom="55px">
      <div style="width: 100px; background-color: white;padding-bottom: calc(55px + env(safe-area-inset-bottom))">
        11111111<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        22222222222<br>
      </div>
      <tabbar slot="bottom" class="toolbar-footer">
        <tabbar-item selected @click.native="changeView('index1')">
          <img slot="icon" src="../assets/icon/WK_hui.png">
          <img slot="icon-active" src="../assets/icon/WK.png">
          <span slot="label">挖宝</span>
        </tabbar-item>
        <tabbar-item @click.native="changeView('index2')">
          <img slot="icon" src="../assets/icon/yingyong_hui.png">
          <img slot="icon-active" src="../assets/icon/YINGYONG.png">
          <span slot="label">应用</span>
        </tabbar-item>
        <tabbar-item @click.native="changeView('my')">
          <img slot="icon" src="../assets/icon/my_hui.png">
          <img slot="icon-active" src="../assets/icon/MY.png">
          <span slot="label">我的</span>
        </tabbar-item>
      </tabbar>
    </view-box>
  </div>


  <!--<tabbar slot="bottom" class="toolbar-footer">-->
  <!--<tabbar-item selected @click.native="changeView('index1')">-->
  <!--<img slot="icon" src="../assets/icon/WK_hui.png">-->
  <!--<img slot="icon-active" src="../assets/icon/WK.png">-->
  <!--<span slot="label" style="color: mediumslateblue">挖宝</span>-->
  <!--</tabbar-item>-->
  <!--<tabbar-item @click.native="changeView('index2')">-->
  <!--<img slot="icon" src="../assets/icon/yingyong_hui.png">-->
  <!--<img slot="icon-active" src="../assets/icon/YINGYONG.png">-->
  <!--<span slot="label">应用</span>-->
  <!--</tabbar-item>-->
  <!--<tabbar-item @click.native="changeView('my')">-->
  <!--<img slot="icon" src="../assets/icon/my_hui.png">-->
  <!--<img slot="icon-active" src="../assets/icon/MY.png">-->
  <!--<span slot="label">我的</span>-->
  <!--</tabbar-item>-->
  <!--</tabbar>-->

</template>

<script>
  import {Group, Cell, XButton, XHeader, Tabbar, TabbarItem, ViewBox} from 'vux'

  export default {
    components: {
      ViewBox,
      Group,
      Cell,
      XButton,
      XHeader,
      Tabbar,
      TabbarItem
    },
    data () {
      return {
        // note: changing this line won't causes changes
        // with hot-reload because the reloaded component
        // preserves its current state and we are modifying
        // its initial state.
        msg: 'Hello World!'
      }
    },
    methods: {
      back () {
        // this.$store.commit('setTransition', 'turn-off')
        this.$router.back(-1)
      },
      gotoHome () {
        this.$router.push({name: 'home'})
        // this.$store.commit('setTransition', 'turn-on')
      },
      gotoPage1 () {
        this.$router.push({name: 'page1'})
        // this.$store.commit('setTransition', 'turn-on')
      },
      gotoPage2 () {
        this.$router.push({name: 'page2'})
        // this.$store.commit('setTransition', 'turn-on')
      },
      gotoPage3 () {
        this.$router.push({name: 'page3'})
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

  .main {
    height: 100%;
    width: 100%;
    position: fixed;
    background-color: black;
    padding-top: env(safe-area-inset-top);
    /*padding-bottom: env(safe-area-inset-bottom);*/
    /*padding-bottom: env(safe-area-inset-bottom);*/
  }

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
