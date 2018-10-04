<template>
  <div style="height: 100%">
    <panel :list="list" :type="type" @on-img-error="onImgError" @on-click-item="gotoProgram"></panel>
    <load-more :show-loading="false" :tip="msg" background-color="#fbf9fe"></load-more>
    <div v-transfer-dom>
      <popup v-model="showProgram" height="100%">
        <!-- group already has a top border, so we need to hide header's bottom border-->
        <!--<write-mail :name="programName" :program="currentProgram" @back-list="backList"></write-mail>-->
        <Template @close-program="backList">
          <component :is="currentProgram" slot="program"></component>
        </Template>
      </popup>
    </div>
  </div>
</template>

<script>
  import {Panel, Group, LoadMore, TransferDom, Popup} from 'vux'
  import {WriteMail} from './index'
  import Send from './sendMailToFuture/WriteMail'
  import ProgramButton from './components/ProgramHeader'
  import Program from './Program'
  import Template from './Template'
  // import Template from "../../components/Template";

  export default {
    directives: {
      TransferDom
    },
    components: {
      Template,
      Panel,
      'send': Send,
      Group,
      LoadMore,
      Program,
      Popup,
      ProgramButton,
      'writeMail': WriteMail
    },
    methods: {
      onImgError (item, $event) {
        console.log(item, $event)
      },
      gotoProgram (item) {
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
        msg: '更多活动敬请期待',
        currentProgram: 'send',
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
        }]
      }
    }
  }
</script>
<style>

</style>
