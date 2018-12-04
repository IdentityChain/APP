<template>
  <div class="bg">
    <view-box body-padding-top="46px" body-padding-bottom="55px">
      <div v-if="showHeader" slot="header"
           style="position: absolute;height: calc(40px + env(safe-area-inset-top));width:100%;left: 0;top: 0;background-color: white;z-index: 100">
        <x-header :left-options="{showBack: false, backText: ''}"
                  style="position: absolute;left:0;top: env(safe-area-inset-top);width: 100%;z-index: 100;">应用下载
        </x-header>
      </div>
      <div class="form-main">

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
