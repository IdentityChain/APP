<template>
  <div style="height: 100%">
    <view-box body-padding-top="46px">
      <div v-if="true" slot="header"
           style="position: absolute;height: calc(40px + env(safe-area-inset-top));width:100%;left: 0;top: 0;background-color: black;z-index: 100">
        <x-header :left-options="{showBack: true}"
                  style="position: absolute;left:0;top: env(safe-area-inset-top);width: 100%;z-index: 100;">完善信息
        </x-header>
      </div>
      <load-more :show-loading="false" :tip="msg" background-color="#fbf9fe"></load-more>
    </view-box>
  </div>
</template>

<script>
  import {XButton, Box, Divider, XHeader} from 'vux'

  export default {
    name: 'resetSetting',
    components: {
      XButton,
      XHeader,
      Box,
      Divider
    },
    data () {
      return {
        msg: 'resetSetting page'
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .rel {
    position: relative;
  }

  .fixed {
    position: fixed;
  }

  .transform {
    transform: scale(1);
  }
</style>
