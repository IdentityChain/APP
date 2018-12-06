<template>
  <div class="bg">
    <view-box>
      <!--<div v-if="showHeader" slot="header"-->
           <!--style="position: absolute;height: calc(40px + env(safe-area-inset-top));width:100%;left: 0;top: 0;background-color: white;z-index: 100">-->
        <!--<x-header :left-options="{showBack: false, backText: ''}"-->
                  <!--style="position: absolute;left:0;top: env(safe-area-inset-top);width: 100%;z-index: 100;">应用下载-->
        <!--</x-header>-->
      <!--</div>-->
      <div class="form-main">
        <div style="position: absolute;top: 45vh;text-align: center">
          <h1 style="color: rgb(78,168,255)">注册成功</h1>
          <img src="../assets/izone-desc.png" width="80%"/>

        </div>
        <div style="position: absolute;bottom: 8vh;text-align: center">
          <a href="http://identitychain.vip:8080/IdentityChain.github.io/download/isc.apk"><img src="../assets/android_download.png" width="60%"/></a>
          <div style="height: 15px;">
          </div>
          <img src="../assets/ios_download.png" width="60%"/>
        </div>

      </div>
    </view-box>
  </div>
</template>

<script>
  import {Group, XInput, XButton, Box, Flexbox, FlexboxItem, XHeader, ViewBox, CheckIcon} from 'vux'

  export default {
    components: {
      Group,
      CheckIcon,
      XHeader,
      XInput,
      XButton,
      ViewBox,
      Box,
      Flexbox,
      FlexboxItem
    },
    data () {
      return {
      }
    },
    mounted: function () {
    },
    methods: {
    }
  }
</script>

<style lang="less">
  html, body {
    background-color: white;
  }

  .bg {
    position: fixed;
    background: url('../assets/down-bg.png') no-repeat center;
    background-size: cover;
    height: 100%;
    width: 100%;
  }

  .vux-demo {
    text-align: center;
  }

  .logo {
    width: 100px;
    height: 100px
  }

  .form-main {
    position: absolute;
    height: 100%;
    width: 100%;
  }
</style>
