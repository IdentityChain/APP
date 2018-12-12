<template>
  <div style="height: 100%; width: 100%;background-color: ghostwhite">
    <view-box body-padding-top="46px" body-padding-bottom="55px">
      <div class="header-css" slot="header"
           style="position: fixed;height: calc(46px + env(safe-area-inset-top));width:100%;left: 0;top: 0;background-color: white;z-index: 100">
        <x-header :left-options="{showBack: true, backText: ''}"
                  style="position: fixed;left:0;top: env(safe-area-inset-top);width: 100%;z-index: 100;background-color: white;">余币宝详情
        </x-header>
      </div>
      <div class="body-header">
        <div class="body-content">
          <div style="height: 120px;width: 100%;background-color: rgb(42 124 246);position: relative;">
            <div style="height: 80px;width: 60%;background-color: white;border-radius: 10px;position: absolute;left: 20%;top: 20px">
              <div style="width: 50%;text-align: center;float: left;">
                <div style="color: rgb(188 2 0)">
                  <p style="display: inline">+</p><p style="display: inline;font-size: xx-large">{{Math.floor(expectIncrease)}}</p><p style="display: inline">.{{floatNum}}%</p>
                </div>
                <p style="font-size: xx-small;color: gray">预期增长率</p>
              </div>
              <div style="width: 50%; margin-left: 50%;">
                <div style="text-align: center;margin-top: 20px;">
                  <p style="display: inline;font-size: small;color: rgb(42 124 246)">管理期限</p> <p style="display: inline;font-size: medium;color: rgb(188 2 0)">30天</p>
                  <p style="font-size: small;color: gray;">较低风险</p>
                </div>
              </div>
            </div>
            <div style="width: 20px;height: 20px;border-radius: 10px;background-color: rgb(42 124 246);position: absolute;left: calc(50% - 10px);top: 10px;">
            </div>
            <div style="width: 20px;height: 20px;border-radius: 10px;background-color: rgb(42 124 246);position: absolute;left: calc(50% - 10px);top: 90px;">
            </div>
          </div>
          <div style="width: 100%;">
            <group class="my-group" style="color: gray;">
              <cell title="产品特点">量化自动交易、风险低</cell>
              <cell title="投资币种">ISC</cell>
              <cell title="投资资格">实名认证通过</cell>
              <cell title="限制数量">10000ISC起投</cell>
            </group>
          </div>
          <div style="height: 100px;width: 100%;background-color: rgb(242 242 242);text-align: center;">

            <div style="width: 100%;position: relative;">
              <div style="transform: scale(0.8);width: 100%;text-align: left;position: absolute;top: 10px;">
                <div style="float: left;width: 28%;text-align: center">
                  <p style="font-size: xx-small;color: gray;display: inline;width: 22%;">起投日23:59前</p>
                </div>
                <div style="float: left;width: 36%;text-align: center">
                  <p style="font-size: xx-small;color: gray;display: inline;margin-left: 5px;width: 32%;">起投日+30天23:59前</p>
                </div>
                <div style="float: left;width: 34%;text-align: center">
                  <p style="font-size: xx-small;color: gray;display: inline;margin-left: 5px;width: 32%;">起投日+31天00:00后</p>
                </div>
              </div>
              <div style="border-bottom: 4px solid rgb(42 124 246);border-right: 4px solid rgb(42 124 246);height: 10px;width: 22%;float: left;margin-left: 5%;top: 40px;position: absolute;left: 5%">
              </div>
              <div style="height: 10px;width: 34%;border-bottom: 4px solid rgb(42 124 246);border-right: 4px solid rgb(42 124 246);float:left;top: 40px;position: absolute;left: 27%;">
              </div>
              <div style="height: 10px;width: 30%;border-bottom: 4px solid rgb(42 124 246);float: left;top: 40px;position: absolute;left: 59%">
              </div>
              <div style="position: absolute;top: 55px;width: 80%;left: 10%;">
                <div style="float: left;width: 28%;text-align: center">
                  <p style="font-size: xx-small;color: gray;display: inline;width: 22%;">准备期</p>
                </div>
                <div style="float: left;width: 35%;text-align: center">
                  <p style="font-size: xx-small;color: gray;display: inline;margin-left: 5px;width: 32%;">锁仓区</p>
                </div>
                <div style="float: left;width: 35%;text-align: center">
                  <p style="font-size: xx-small;color: gray;display: inline;margin-left: 5px;width: 32%;">发还回余额</p>
                </div>
              </div>
            </div>
          </div>
          <div>
            <group class="my-group" style="color: gray;">
              <cell title="起投日"></cell>
              <cell title="锁仓期"></cell>
              <cell title="赎回期"></cell>
              <cell title="投资数量">10000</cell>
            </group>
          </div>
          <div style="width: 100%;text-align: center;margin-top: 15px;">
            <img src="../../assets/wallet/confirmBtn.png" width="60%" height="35px"/>
          </div>
        </div>
      </div>
    </view-box>
  </div>
</template>

<script>
  import {ViewBox, XHeader, Flow, FlowLine, Divider, XInput, Group, Cell} from 'vux'
  import BiBao from '../../components/Wallet/BiBaoComponent'
  export default {
    name: 'BiBaoDetail',
    components: {
      XInput,
      ViewBox,
      XHeader,
      BiBao,
      Group,
      Cell,
      Flow,
      Divider,
      FlowLine
    },
    created: function () {
    },
    data () {
      return {
        expectDay: 0,
        expectIncrease: 30
      }
    },
    methods: {
    }
  }
</script>

<style scoped>
  .header-css /deep/ .vux-header-title {
    color: black;
  }

  .header-css /deep/ .vux-header .vux-header-left .left-arrow:before {
    border: 1px solid black;
    border-width: 1px 0 0 1px;
  }

  .my-group /deep/ .vux-no-group-title {
    margin-top: 0px;
    font-size: small;
    line-height: 20px;
  }

  .body-header {
    position: absolute;
    top: 46px;
    height: 0px;
    width: 100vw;
    background-color: rgb(50, 112, 222);
  }

  .body-content {
    position: absolute;
    margin-top: 0px;
    width: 100vw;
  }

</style>
