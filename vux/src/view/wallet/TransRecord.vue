<template>
  <div style="height: 100%; width: 100%;background-color: white">
    <view-box body-padding-top="46px" body-padding-bottom="55px">
      <div class="header-css" slot="header"
           style="position: fixed;height: calc(40px + env(safe-area-inset-top));width:100%;left: 0;top: 0;background-color: rgb(50,112,222);z-index: 100">
        <x-header :left-options="{showBack: true, backText: ''}"
                  style="position: fixed;left:0;top: env(safe-area-inset-top);width: 100%;z-index: 100;background-color: rgb(50,112,222);">交易记录
        </x-header>
      </div>
      <div class="body-header">
        <div class="body-content">
          <divider style="margin-bottom: 20px;margin-top: 10px;">交易记录</divider>
            <div v-for="(transItem,index) in transList">
              <my-flow-state :transType="transItem.transType"
                             :account="transItem.account"
                             :transTime="transItem.transTime"
                             :transNumber="transItem.transNumber"
              >
              </my-flow-state>
            </div>

        </div>
      </div>
    </view-box>
  </div>
</template>

<script>
  import {ViewBox, XHeader, Flow, FlowLine, Divider} from 'vux'
  import FlowState from '../../components/Flow/flow-state'
  import MyFlowState from '../../components/Flow/MyFlowState'
  export default {
    name: 'TransRecord',
    components: {
      ViewBox,
      XHeader,
      Flow,
      FlowState,
      MyFlowState,
      Divider,
      FlowLine
    },
    created: function () {
    },
    data () {
      return {
        transList: [
          {
            transType: '充值',
            account: 'xxxxxsdsfsfsssssds',
            transTime: '2018-12-09 12:22:00',
            transNumber: 100.22
          },
          {
            transType: '充值',
            account: 'xxxxxsdsfsfsssssds',
            transTime: '2018-12-09 12:22:00',
            transNumber: 100.22
          },
          {
            transType: '充值',
            account: 'xxxxxsdsfsfsssssds',
            transTime: '2018-12-09 12:22:00',
            transNumber: 100.22
          }
        ]
      }
    },
    methods: {
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
    width: 80vw;
    left: 10vw;
    height: 100%;
  }

</style>
