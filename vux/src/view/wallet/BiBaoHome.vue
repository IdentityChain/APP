<template>
  <div style="height: 100%; width: 100%;background-color: ghostwhite">
    <view-box body-padding-top="46px" body-padding-bottom="55px">
      <div class="header-css" slot="header"
           style="position: fixed;height: calc(40px + env(safe-area-inset-top));width:100%;left: 0;top: 0;background-color: rgb(50,112,222);z-index: 100">
        <x-header :left-options="{showBack: true, backText: ''}"
                  style="position: fixed;left:0;top: env(safe-area-inset-top);width: 100%;z-index: 100;background-color: rgb(50,112,222);">余币宝
        </x-header>
      </div>
      <div class="body-header">

        <msg title="功能建设中" description="程序猿兄弟增加加急开发,请耐心等待..." icon="info">
        </msg>
        <div v-if="isDone" class="body-content">
          <bi-bao :expect-increase=15.52 :expect-day=30 @click.native="showDetail"></bi-bao>
          <bi-bao :expect-increase=15.52 :expect-day=30></bi-bao>
          <bi-bao :expect-increase=15.52 :expect-day=30></bi-bao>
        </div>
      </div>
    </view-box>
  </div>
</template>

<script>
  import {ViewBox, XHeader, Flow, FlowLine, Divider, XInput, Msg} from 'vux'
  import BiBao from '../../components/Wallet/BiBaoComponent'
  export default {
    name: 'BiBaoHome',
    components: {
      XInput,
      ViewBox,
      XHeader,
      BiBao,
      Msg,
      Flow,
      Divider,
      FlowLine
    },
    created: function () {
    },
    data () {
      return {
        isDone: false
      }
    },
    methods: {
      showDetail () {
        this.$router.push({path: '/myWallet/yuBiBao/detail'})
      }
    }
  }
</script>

<style scoped>

  .header-css /deep/ .vux-header-title {
    color: white;
  }

  .header-css /deep/ .vux-header .vux-header-left .left-arrow:before {
    border: 1px solid white;
    border-width: 1px 0 0 1px;
  }
  .body-header {
    position: absolute;
    top: 45px;
    height: 0;
    padding-top: env(safe-area-inset-top);
    width: 100vw;
    background-color: rgb(50, 112, 222);
  }

  .body-content {
    position: absolute;
    margin-top: 0;
    width: 100vw;
  }

</style>
