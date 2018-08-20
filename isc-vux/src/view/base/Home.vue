<template>
  <div class="bg">
    <view-box body-padding-bottom="55px">
    <keep-alive>
      <component :is="currentView" @linkTo="changeView" ref="nowView"></component>
    </keep-alive>
    <tabbar slot="bottom">
      <tabbar-item selected @click.native="changeView('index1')">
        <img slot="icon" src="../../assets/icon/WK_hui.png">
        <img slot="icon-active" src="../../assets/icon/WK.png">
        <!--<span slot="label" style="color: mediumslateblue">挖宝</span>-->
      </tabbar-item>
      <tabbar-item  @click.native="changeView('index2')">
        <img slot="icon" src="../../assets/icon/yingyong_hui.png">
        <img slot="icon-active" src="../../assets/icon/YINGYONG.png">
        <!--<span slot="label">应用</span>-->
      </tabbar-item>
      <tabbar-item  @click.native="changeView('my')" >
        <img slot="icon" src="../../assets/icon/my_hui.png">
        <img slot="icon-active" src="../../assets/icon/MY.png">
        <!--<span slot="label">我的</span>-->
      </tabbar-item>
    </tabbar>
    </view-box>
  </div>
</template>

<script>
  import { Tabbar, TabbarItem, ViewBox } from 'vux'
  import Index1 from '../home/Index1'
  import Index2 from '../home/Index2'
  import My from '../home/my'
  export default {
    name: 'Home',
    components: {
      TabbarItem,
      Tabbar,
      ViewBox,
      'index1': Index1,
      'index2': Index2,
      'my': My
    },
    data () {
      return {
        currentView: 'index1'
      }
    },
    methods: {
      changeView (view) {
        this.currentView = view
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
  html, body {
    height: 100%;
    width: 100%;
    overflow-x: hidden;
  }
  .bg {
    /*background:url('../../assets/bg.jpeg') no-repeat;*/
    height: 100%;
    position: fixed;
    width: 100%;
  }


</style>
