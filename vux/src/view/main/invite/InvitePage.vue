<template>
  <div style="height: 100%; width: 100%;background-color: white">
    <view-box body-padding-top="calc(46px + env(safe-area-inset-top)" body-padding-bottom="55px">
      <div class="header-css" slot="header"
           style="position: fixed;height: calc(40px + env(safe-area-inset-top));width:100%;left: 0;top: 0;background-color: rgb(50,112,222);z-index: 100">
        <x-header :left-options="{showBack: true, backText: ''}"
                  style="position: fixed;left:0;top: env(safe-area-inset-top);width: 100%;z-index: 100;background-color: rgb(50,112,222);">
          邀请好友
        </x-header>
      </div>
      <div :class="platform === 'android' ? 'main-content-android' : 'main-content'">
        <div :class="platform === 'android' ? 'son-content-android' : 'son-content'">
          <div :class="platform === 'android' ? 'body-header-android' : 'body-header'">
            <p style="font-size: small">我的邀请码</p>
            <p style="font-size: x-large">{{inviteCode}}</P>
            <button
              style="border: 1px solid white; border-radius: 10px;width: auto;display: inline;height: 20px;line-height: 20px;color: white;margin-top: 5px;background: transparent"
              :style="clipButton.isClick ? clipButton.clickedCss : ''" @click="clickClip" v-clipboard="inviteCode">&nbsp;&nbsp;&nbsp;{{clipButton.text}}&nbsp;&nbsp;&nbsp;
            </button>
          </div>
          <div :class="platform === 'android' ? 'invite-qrcode-android' : 'invite-qrcode'">
            <p>邀请链接二维码</p>
            <br>
            <!--<qrcode :value="'http://identitychain.vip/invite?inviteCode=' + inviteCode" type="img" size="200"></qrcode>-->
            <qrcode :value="'http://invite.mobile.identitychain.vip/#/?inviteCode=' + inviteCode" type="img" :size=200 ref="qrcodeImg"></qrcode>
            <br>
            <p style="color: gray;text-align: left;">
              奖励规则<br>
              1. 每邀请一个好友完成注册,且该好友完成实名认证,即可获得1个ISC元素<br>
              2. 累计邀请10名好友,即可获得5个ISC元素奖励
            </p>
          </div>
          <div class="create-invite-card" @click="createCard">
            生成邀请卡
          </div>

        </div>
        <div v-transfer-dom>
          <popup v-model="showCard" height="100%"  width="100%" :show-mask=false :hide-on-deactivated=false id="canvas-container">
            <div class="invite-body"  v-show="showMyInfo" >
              <div style="height: 7vh">
              </div>
              <img src="../../../assets/main/izone.png" width="50%" height="40px"/>
              <p style="color: white">为你打开未来之门</p>
              <div class="invite-content" ref="imageWrapper">
                <div class="qrcode-card">
                  <p style="color: blueviolet;font-size: larger">您的专属邀请码</p>
                  <p style="font-size: x-large;color: dodgerblue">{{inviteCode}}</P>
                  <p style="font-size: small;color:grey;margin-bottom: 20px">每邀请一位好友+10算力<br>上不封顶</p>
                  <div style="border-bottom: 1px solid lightgray;width: 100%;"></div>
                  <qrcode style="padding-top: 20px" :value="'http://invite.mobile.identitychain.vip/#/?inviteCode=' + inviteCode" type="img"
                          :size=100 ref="qrcodeImg"></qrcode>
                  <p style="font-size: small;color:grey;padding-bottom: 25px">扫码下载iZone<br>加入iZone提早享受更多权益</p>
                </div>
              </div>
              <img src="../../../assets/main/inviteButton.png" width="40%" height="40px" @click="toImage" style="position:absolute;bottom: 6vh;left: 30vw">
            </div>
          </popup>
        </div>
      </div>
    </view-box>
  </div>
</template>

<script>
  import {ViewBox, XHeader, Qrcode, TransferDom, Popup} from 'vux'
  import html2canvas from 'html2canvas'

  export default {
    directives: {
      TransferDom
    },
    name: 'invite',
    components: {
      ViewBox,
      XHeader,
      Popup,
      Qrcode
    },
    data () {
      return {
        platform: window.cordova.platformId,
        inviteCode: this.$db.get('User').invitationCode,
        dataURL: '',
        showMyInfo: false,
        showCard: false,
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
        // this.toImage()
      },
      gotoInvitePage () {
        window.location.href = 'www.baidu.com'
      },
      createCard () {
        this.showMyInfo = true
        this.showCard = true
      },
      toImage () {
        let that = this
        html2canvas(this.$refs.imageWrapper, {
          useCORS: true,
          backgroundColor: null
        }).then((canvas) => {
          console.log(canvas.toDataURL('image/png'))
          var params = {data: canvas.toDataURL('image/png'), quality: 80, mediaScanner: true}
          console.log(params)
          window.imageSaver.saveBase64Image(params,
            function (filePath) {
              that.$router.replace({name: 'home'})
              alert('已保存到相册,请分享给您的好友')
              console.log('File saved on ' + filePath)
            },
            function (msg) {
              console.log('1111111')
              console.error(msg)
            }
          )
        }).catch(err => {
          console.log('err:' + err)
        })
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

  .main-content {
    position: absolute;
    top: calc(46px + env(safe-area-inset-top));
    background-color: white;
    width: 100vw;
    height: calc(100vh - 46px - env(safe-area-inset-top));
  }

  .main-content-android {
    position: absolute;
    top: calc(46px);
    background-color: white;
    width: 100vw;
    height: calc(100vh - 46px);
  }

  .son-content {
     height: calc(100% - 46px - env(safe-area-inset-top))
   }

  .son-content-android {
    height: calc(100% - 46px)
  }

  #myCanvas {
    position: absolute;
    color: black;
    top: 20vh;
  }

  .main-body {
    position: absolute;
    height: 100vh;
    width: 100vw;
  }

  .body-header {
    position: absolute;
    top: 0;
    height: calc(10vh + 10vw);
    width: 100vw;
    background: linear-gradient(top, rgb(50, 112, 222), lightskyblue);
    color: white;
    padding-top: 2vh;
    text-align: center;
  }

  .body-header-android {
    position: absolute;
    top: 0;
    height: calc(10vh + 10vw);
    width: 100vw;
    background: linear-gradient(top, rgb(50, 112, 222), lightskyblue);
    color: white;
    padding-top: 2vh;
    text-align: center;
  }

  .invite-qrcode {
     position: absolute;
     top: calc(10vh + 10vw + 50px + env(safe-area-inset-top));
     width: 86vw;
     left: 7vw;
     height: 86vw;
     text-align: center;
   }

  .invite-qrcode-android {
    position: absolute;
    top: calc(10vh + 10vw + 50px);
    width: 86vw;
    left: 7vw;
    height: 86vw;
    text-align: center;
  }
  .create-invite-card {
    position: fixed;
    bottom: 5vh;
    height: 35px;
    width: 100vw;
    color: white;
    text-align: center;
    line-height: 35px;
    background: linear-gradient(right, rgb(50, 112, 222), lightskyblue);
  }

  .invite-body {
    position: absolute;
    text-align: center;
    height: 100vh;
    width: 100vw;
    margin: auto;
    top: 0; left: 0; bottom: 0; right: 0;
    background: url(../../../assets/main/invite-bg.png) no-repeat center;
    background-size: cover;
  }

  .invite-content {
    position: absolute;
    margin: auto;
    top: 0; left: 0; bottom: 0; right: 0;
    width: 80vw;
    height: 60vh;
    /*left: 10vw;*/
  }

  .qrcode-card {
    border-radius: 10px;
    background-color: white;
    margin-top: 10px;
    padding-top: 15px;
  }
</style>
