<template>
  <div style="height: 100%">
    <view-box body-padding-top="46px">
      <div v-if="true" slot="header"
           style="position: absolute;height: calc(40px + env(safe-area-inset-top));width:100%;left: 0;top: 0;background-color: black;z-index: 100">
        <x-header :left-options="{showBack: true}"
                  style="position: absolute;left:0;top: env(safe-area-inset-top);width: 100%;z-index: 100;">联系人
        </x-header>
      </div>
      <load-more :show-loading="false" :tip="msg" background-color="#fbf9fe"></load-more>
    </view-box>
  </div>
</template>

<script>
  import {XButton, Box, Divider, XHeader} from 'vux'

  export default {
    name: 'convertConsumeCoin',
    components: {
      XButton,
      XHeader,
      Box,
      Divider
    },
    data () {
      return {
        msg: '功能建设中'
      }
    }
  }
</script>

<style lang="less">
  .custom-primary-red {
    border-radius: 99px !important;
    border-color: #CE3C39 !important;
    color: #CE3C39 !important;
    &:active {
      border-color: rgba(206, 60, 57, 0.6) !important;
      color: rgba(206, 60, 57, 0.6) !important;
      background-color: transparent;
    }

  }
</style>
