<template>
  <div class="bg">
    <div class="dp-header">
     <h1>hello</h1>
    </div>
    <div class="body">
      <h2>sdfsfd</h2>
    </div>
    <tabbar>
      <tabbar-item>
        <img slot="icon" src="../../assets/icon/tel.png">
        <span slot="label">Wechat</span>
      </tabbar-item>
      <tabbar-item show-dot>
        <img slot="icon" src="../../assets/icon/tel.png">
        <span slot="label">Message</span>
      </tabbar-item>
      <tabbar-item selected link="/component/demo">
        <img slot="icon" src="../../assets/icon/tel.png">
        <span slot="label">Explore</span>
      </tabbar-item>
      <tabbar-item badge="2">
        <img slot="icon" src="../../assets/icon/tel.png">
        <span slot="label">News</span>
      </tabbar-item>
    </tabbar>
  </div>
</template>

<script>
  import { Tabbar, TabbarItem } from 'vux'
  import BGIMG from '../../assets/bg.jpeg'
  export default {
    name: 'Home',
    mounted () {
      this.calBgImage = (document.body.clientWidth / document.body.clientHeight) > 1.92
// 然后监听window的resize事件．在浏览器窗口变化时再设置下背景图高度．
      const that = this
      window.onresize = function temp () {
        that.calBgImage = (document.body.clientWidth / document.body.clientHeight) > 1.92
      }
    },
    components: {
      TabbarItem,
      Tabbar
    },
    data () {
      return {
        calBgImage: false,
        img: BGIMG,
        bg: {
          backgroundImage: 'url(' + require('../../assets/bg.jpeg') + ')',
          backgroundRepeat: 'no-repeat',
          backgroundSize: '100% auto',
          marginTop: '0px'
        }
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
  .bg {
    background:url('../../assets/bg.jpeg') no-repeat;
    height: 100%;
    position: fixed;
    width: 100%;
  }


</style>
