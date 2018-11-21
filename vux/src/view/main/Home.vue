<template>
  <div class="bg">
    <div class="notice-body">
      <p style="position: absolute;left: 20px;color: white;font-weight: bold;">公告:</p>
      <div style="position: absolute; left: 80px;color: black;margin: 0 auto;width: calc(100vw - 100px);">
        <MarqueeTips :content="personal.notice === '' ? '暂无最新公告' : personal.notice" :speed="5"></MarqueeTips>
      </div>
    </div>

    <div class="people-body">
      <div class="people-head">
        <img src="../../assets/login/touxiang.png" width="60px" height="60px" @click="personal.showCenter = true"/>
      </div>
      <p class="peopel-name">{{personal.account}}</p>
      <!--个人中心弹窗-->
      <div v-transfer-dom>
        <popup v-model="personal.showCenter" height="100%" width="100%" :show-mask=false position="left" :hide-on-deactivated=false>
          <personal-center @close-center="personal.showCenter = false"></personal-center>
        </popup>
      </div>
    </div>

    <div class="wallet-body">
      <div style="margin-bottom: 15px;">
        <p style="margin-left: 3px;">洪荒之力</p>
        <img style="float: left;margin-right: 3px;" src="../../assets/wakuang/wallte-power.png" width="30px" height="30px"/>
        <p style="line-height: 32px;color: deepskyblue">{{personal.power}}</p>
      </div>
      <div class="wallet-elm">
        <p style="margin-left: 3px;">ISC元素</p>
        <img style="float: left;margin-right: 3px;" src="../../assets/wakuang/elm.png" width="30px" height="30px"/>
        <p style="line-height: 32px;color: deepskyblue">
          <!--{{personal.iscElm.toFixed(2)}}-->
          <countup :start-val="totalElm.startNum" :end-val="totalElm.endNum" :decimals="2" :duration="1"></countup>
        </p>
      </div>
    </div>

    <div class="count-people-body">
      <p>iZone总人数</p>
      <p style="background-color: #10aeff;width: auto;display: inline; border-radius: 5px;">
        &nbsp;&nbsp;{{totalPeople}}&nbsp;&nbsp;
      </p>
    </div>
    <keep-alive>
      // 待拾取的ISC元素
      <Conis class="coin-body" @add-event="doGetCoin" :coins="coins"></Conis>
    </keep-alive>
    <div class="add-power">
      <button class="add-button" style="height: 30px;">提升洪荒之力</button>
    </div>

    <div class="function">
      <img src="../../assets/wakuang/icon-yaoqing.png" height="35px" width="35px" @click="goTo('invite')"/>
      <p>邀请好友</p>
      <br>
      <img src="../../assets/wakuang/icon-apps.png" height="35px" width="35px" @click="goTo('applications')"/>
      <p>应用</p>
      <br>
      <img src="../../assets/wakuang/icon-top.png" height="35px" width="35px" @click="goTo('topBang')"/>
      <p>排行榜</p>
    </div>

  </div>
</template>

<script>
  import {XButton, TransferDom, Popup, Countup} from 'vux'
  import Coin from './GetCoin'
  import Conis from './Coins'
  import MarqueeTips from 'vue-marquee-tips'
  import PersonalCenter from './PersonalCenter'
  export default {
    directives: {
      TransferDom
    },
    created: function () {
      this.init()
    },
    name: 'wakuang',
    components: {
      XButton,
      Countup,
      Conis,
      MarqueeTips,
      PersonalCenter,
      Coin,
      Popup
    },
    data () {
      return {
        totalPeople: 0,
        coins: [],
        personal: {
          showCenter: false,
          power: 0,
          iscElm: 0,
          account: '',
          notice: ''
        },
        totalElm: {
          startNum: 0,
          endNum: 0
        }
      }
    },
    methods: {
      init () {
        console.log('进入Init 方法')
        let user = this.$db.get('User')
        console.log(user)
        this.personal.power = user.calculateValue
        this.personal.iscElm = user.iscCoin
        this.totalElm.startNum = user.iscCoin
        this.totalElm.endNum = user.iscCoin
        this.personal.account = user.account
        this.getCoinRequest(user.userId)
        this.getNotice()
        // this.getTotalCount()
      },
      goTo (target) {
        this.$router.push({name: target})
      },
      doGetCoin (addISC) {
        // console.log('获取币ID: ' + iscLID)
        const user = this.$db.get('User')
        user.iscCoin += addISC
        this.$db.set('User', user)
        this.totalElm.endNum += addISC
      },
      mingciCSS: function (item) {
        if (item === 1) {
          return 'first'
        } else if (item === 2) {
          return 'second'
        } else if (item === 3) {
          return 'third'
        } else {
          return 'other'
        }
      },
      getCoinRequest (userId) {
        this.$api.calculateApi.getCalculateLog(userId).then(result => {
          console.log(result)
          if (result.data.success) {
            this.coins = result.data.data
          } else {
            this.$vux.toast.show({
              type: 'cancel',
              text: result.message
            })
          }
        })
      },
      getNotice () {
        this.$api.managementApi.getNotice().then(result => {
          if (result.data.success) {
            if (result.data.data !== null && result.data.data !== '') {
              this.personal.notice = result.data.data.content
            }
          }
        })
      },
      getTotalCount () {
        this.$api.userApi.getTotalCount().then(result => {
          if (result.data.success) {
            this.totalPeople = result.data.data
          } else {
          }
        })
      }
    },
    computed: {}
  }
</script>

<style scoped>
  html, body {
    background-color: white;
  }
  .bg {
    background: url(../../assets/wakuang/beijingsmall.png) no-repeat center;
    background-size: cover;
    position: fixed;
    top: 0;
    left: 0;
    height: 100%;
    width: 100%;
  }

  .notice-body {
    position: absolute;
    top: calc(46px + env(safe-area-inset-top));
    width: 100%;
    height: 26px;
    opacity:0.7;
    background: linear-gradient(left, black, white, white, black);
  }

  .people-body {
    position: absolute;
    top: calc(46px + 10vh + env(safe-area-inset-top));
    width: auto;
    margin-top: 26px;
    height: 60px;
  }

  .people-head {
    position: absolute;
    top: 0;
    left: 15px;
    height: 60px;
    width: 60px;
    border-radius: 30px;
    background-color: wh100e;
  }

  .peopel-name {
    position: absolute;
    left: 85px;
    top: 25px;
    color: white;
  }

  .wallet-body {
    margin-top:90px;
    position: absolute;
    top: calc(126px + 10vh);
    left: 15px;
    height: 120px;
    width: 100px;
    font-size: small;
    color: white;
  }

  .count-people-body {
    position: absolute;
    bottom: 12%;
    left: 15px;
    color: white;
    font-size: small;
    width: 100px;
  }

  .add-power {
    position: absolute;
    bottom: 5%;
    width: 50%;
    left: 25%;
  }

  .add-button {
    background: linear-gradient(left,lightblue,deepskyblue, dodgerblue);
    color: white;
    border-radius: 10px;
    border: none;
    width: 100%;
    font-style: revert;
    font-size: 13pt;
  }

  .function {
    position: absolute;
    right: 5px;
    bottom: 12%;
    width: 60px;
    color: white;
    font-size: 6pt;
    text-align: center;
  }
  .coin-body {
    position: absolute;
    bottom: calc(25vh);
    height: 50vw;
    width: 50vw;
    left: 26vw;
  }
  .coin {
    position: absolute;
  }

</style>
