<template>
  <div style="height: 100%">
    <view-box body-padding-top="55px">
      <div v-if="true" class="header-css" slot="header"
           style="position: absolute;height: calc(40px + env(safe-area-inset-top));width:100%;left: 0;top: 0;background-color: rgb(50,112,222);z-index: 100">
        <x-header :left-options="{showBack: true, backText: ''}"
                  style="position: absolute;left:0;top: env(safe-area-inset-top);width: 100%;z-index: 100;">应用中心
        </x-header>
      </div>
      <panel style="padding-top: env(safe-area-inset-top);" :list="list" :type="type" @on-img-error="onImgError" @on-click-item="gotoProgram"></panel>
      <load-more :show-loading="false" :tip="msg" background-color="#fbf9fe"></load-more>
      <div v-transfer-dom>
        <popup v-model="showProgram" height="100%" :popup-style="styleObj" :hide-on-blur=false :show-mask=false :should-rerender-on-show=true>
          <!-- group already has a top border, so we need to hide header's bottom border-->
          <!--<write-mail :name="programName" :program="currentProgram" @back-list="backList"></write-mail>-->
          <program @close-program="backList" :show-title=false :program-name="programName" :program="currentProgram">
          </program>
        </popup>
      </div>
    </view-box>
  </div>
</template>

<script>
  import {Panel, Group, LoadMore, TransferDom, Popup, ViewBox, XHeader} from 'vux'
  import {WriteMail, DuoBao, Comming} from './index'
  import Send from './sendMailToFuture/WriteMail'
  import Program from './Program'
  // import Template from "../../components/Template";

  export default {
    directives: {
      TransferDom
    },
    components: {
      Panel,
      'send': Send,
      Group,
      LoadMore,
      ViewBox,
      XHeader,
      Program,
      Popup,
      'writeMail': WriteMail,
      'duobao': DuoBao
    },
    methods: {
      onImgError (item, $event) {
        console.log(item, $event)
      },
      gotoProgram (item) {
        switch (item.program) {
          case 'writeMail':
            this.currentProgram = WriteMail
            break
          case 'duobao':
            this.currentProgram = Comming
            break
          case 'suocang':
            this.currentProgram = Comming
            break
          case 'chargePhone':
            this.currentProgram = Comming
            break
          case 'onlineHealth':
            this.currentProgram = Comming
            break
        }
        this.showProgram = true
        this.programName = item.title
        console.log(item.program)
      },
      backList () {
        this.showProgram = false
      }
    },
    data () {
      return {
        msg: '更多应用敬请期待',
        currentProgram: WriteMail,
        styleObj: {
          'zIndex': 101
        },
        programName: '',
        showProgram: false,
        type: '1',
        list: [{
          src: require('../../assets/activity/sendMail/mail.jpeg'),
          fallbackSrc: 'http://placeholder.qiniudn.com/60x60/3cc51f/ffffff',
          title: '给未来写封信',
          desc: '将你对未来的期望写入到区块链，让他永远的存在下去，未来你打开这个信息，希望你过的比现在好。',
          program: 'writeMail'
        }, {
          src: require('../../assets/activity/duobao/duobao.jpeg'),
          title: '开心夺宝',
          desc: '平台提供各种奖品，每个奖品分配对应数量的号码，每个号码价值1个ISC币，当一件奖品所有号码售出后，根据既定的规则计算出一个幸运号码，持有该号码的用户，直接获得该奖品。',
          program: 'duobao'
        }, {
          src: require('../../assets/activity/suocang/suocang.jpeg'),
          title: '锁仓奖励',
          desc: '锁仓奖励说明锁仓奖励说明锁仓奖励说明锁仓奖励说明锁仓奖励说明锁仓奖励说明锁仓奖励说明锁仓奖励说明',
          program: 'suocang'
        }, {
          src: require('../../assets/activity/huafeichognzhi/chargePhone.jpeg'),
          title: '话费充值',
          desc: '使用ISC元素兑换话费使用ISC元素兑换话费使用ISC元素兑换话费使用ISC元素兑换话费使用ISC元素兑换话费使用ISC元素兑换话费',
          program: 'chargePhone'
        }, {
          src: require('../../assets/activity/health/health.jpeg'),
          title: '在线医疗',
          desc: '在线医疗在线医疗在线医疗在线医疗在线医疗在线医疗在线医疗在线医疗在线医疗在线医疗',
          program: 'onlineHealth'
        }]
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
  .header-css /deep/ .vux-header {
    background-color: rgb(50,112,222);
  }
</style>
