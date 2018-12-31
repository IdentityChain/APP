<template>
  <div style="height: 100%; width: 100%;background-color: white">
    <view-box body-padding-top="46px" body-padding-bottom="55px">
      <div class="header-css" slot="header"
           style="position: fixed;height: calc(40px + env(safe-area-inset-top));width:100%;left: 0;top: 0;background-color: rgb(50,112,222);z-index: 100">
        <x-header :left-options="{showBack: true, backText: ''}"
                  style="position: fixed;left:0;top: env(safe-area-inset-top);width: 100%;z-index: 100;background-color: rgb(50,112,222);">充值提现
        </x-header>
      </div>
      <div class="body-header">
        <div class="body-content">
          <div>
            <divider style="margin-bottom: 20px;margin-top: 10px;">ISC提现</divider>
            <div style="height: 90px;width: 100%;border-radius: 10px; border: 1px solid lightgray">
              <div style="color: grey;">
                <x-input title="提现数量">
                  <p style="display: inline" slot="right">ISC</p>
                </x-input>
                <x-input title="提现地址" class="input-my" value="">
                  <button slot="right" style="border: none;background-color: rgb(50,112,222);border-radius: 5px;color: white">&nbsp;&nbsp;开发中&nbsp;&nbsp;</button>
                </x-input>
                <p style="display: inline;margin-top: 5px;">提现手续费: <p style="font-weight: bold;display: inline">20</p> ISC</p>
                <p style="font-size: small;">注意:该地址用于ISC提现地址.请自行填写,如果因为用户操作造成的一切后果IZONE概不负责。</p>
              </div>
            </div>`
          </div>
          <div style="margin-top: 50px;">
            <divider style="margin-bottom: 20px;margin-top: 10px;">ISC充值</divider>
            <div style="height: 90px;width: 100%;border-radius: 10px; border: 1px solid lightgray">
              <div style="color: grey;">
                <x-input title="充值数量" type="number">
                  <p style="display: inline" slot="right">ISC</p>
                </x-input>
                <x-input class="input-my" title="充值地址" :value='ethaddress'>
                  <button slot="right" style="border: none;background-color: rgb(50,112,222);border-radius: 5px;color: white">&nbsp;&nbsp;充值&nbsp;&nbsp;</button>
                </x-input>
                <p style="font-size: small;margin-top: 5px;">注意:该地址只用于ISC充值.如果因为用户操作造成的一切后果IZONE概不负责。</p>
              </div>
            </div>`
          </div>
        </div>
      </div>
    </view-box>
  </div>
</template>

<script>
  import {ViewBox, XHeader, Flow, FlowLine, Divider, XInput} from 'vux'
  import FlowState from '../../components/Flow/flow-state'
  import MyFlowState from '../../components/Flow/MyFlowState'
  export default {
    name: 'BalanceDetail',
    components: {
      XInput,
      ViewBox,
      XHeader,
      Flow,
      FlowState,
      MyFlowState,
      Divider,
      FlowLine
    },
    created: function () {
      let user = this.$db.get('User')
      this.ethaddress = user.ethAddress
    },
    data () {
      return {
        ethaddress: '',
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
      init () {
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

  .input-my /deep/ .weui-input {
    font-size: xx-small;
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
