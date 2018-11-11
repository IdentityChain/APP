<template>
  <div style="height: 100%; width: 100%;background-color: white">
    <view-box body-padding-top="calc(46px + env(safe-area-inset-top)" body-padding-bottom="55px">
      <div class="header-css" slot="header"
           style="position: absolute;height: calc(40px + env(safe-area-inset-top));width:100%;left: 0;top: 0;background-color: rgb(50,112,222);z-index: 100">
        <x-header :left-options="{showBack: true, backText: ''}"
                  style="position: absolute;left:0;top: env(safe-area-inset-top);width: 100%;z-index: 100;background-color: rgb(50,112,222);">邀请好友
        </x-header>
      </div>
      <div class="body-header">
        <p style="font-size: small">我的邀请码</p>
        <p style="font-size: x-large">{{inviteCode}}</P>
        <button style="border: 1px solid white; border-radius: 10px;width: auto;display: inline;height: 20px;line-height: 20px;color: white;margin-top: 5px;background: transparent" :style="clipButton.isClick ? clipButton.clickedCss : ''" @click="clickClip" v-clipboard="inviteCode">&nbsp;&nbsp;&nbsp;{{clipButton.text}}&nbsp;&nbsp;&nbsp;</button>
      </div>

      <div class="invite-qrcode">
        <p>邀请链接二维码</p>
        <br>
        <!--<qrcode :value="'http://identitychain.vip/invite?inviteCode=' + inviteCode" type="img" size="200"></qrcode>-->
        <qrcode value="http://identitychain.vip/webapp/index.html" type="img" :size=200 ref="qrcodeImg"></qrcode>
        <br>
        <p style="color: gray;text-align: left;">
          奖励规则<br>
          1. 每邀请一个好友完成注册,且该好友完成实名认证,即可获得1个ISC元素<br>
          2. 累计邀请10名好友,即可获得5个ISC元素奖励
        </p>
      </div>

    </view-box>
  </div>
</template>

<script>
  import {ViewBox, XHeader, Qrcode} from 'vux'
  export default {
    name: 'invite',
    components: {
      ViewBox,
      XHeader,
      Qrcode
    },
    data () {
      return {
        inviteCode: this.$db.get('User').invitationCode,
        clipButton: {
          text: '复制',
          isClick: false,
          clickedCss: {
            'background-color': 'grey'
          }
        }
      }
    },
    methods: {
      clickClip () {
        this.clipButton.isClick = true
        this.clipButton.text = '已复制'
        console.log(this.$refs.qrcodeImg.imgData)
      },
      gotoInvitePage () {
        window.location.href = 'www.baidu.com'
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
    height: calc(10vh + 10vw);
    width: 100vw;
    background: linear-gradient(top,rgb(50,112,222), lightskyblue);
    color: white;
    padding-top: 2vh;
    text-align: center;
  }
  .invite-qrcode {
    position: absolute;
    top: calc(10vh + 10vw + 80px);
    width: 86vw;
    left: 7vw;
    height: 86vw;
    text-align: center;
  }
</style>
