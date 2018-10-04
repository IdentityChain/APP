<template>
  <div style="height: 100%">
    <div class="bg">
      <div class="backHome" v-on:click="backHome">
        <img src="../../../assets/activity/sendMail/home.png" style="height: 40px;width: 40px;"/>
      </div>
      <div class="sendMail" v-on:click="sendMail">
        <img src="../../../assets/activity/sendMail/send.png" style="height: 40px;width: 40px;"/>
      </div>
      <div v-transfer-dom>
        <popup v-model="showEdit" height="50%" :is-transparent=false :hide-on-blur=false>
          <!-- group already has a top border, so we need to hide header's bottom border-->
          <div class="popup2">
            <group>
              sdsfsdfsfs
            </group>
            The mask cannot be clicked!
          </div>

          <!--查看公开信件-->
        </popup>
      </div>
    </div>
  </div>
</template>

<script>
  import {XButton, XHeader, ViewBox, Tabbar, TabbarItem, PopupHeader, TransferDom, Popup, Scroller, LoadMore, Group} from 'vux'
  export default {
    directives: {
      TransferDom
    },
    components: {
      Scroller,
      ViewBox,
      XButton,
      XHeader,
      Tabbar,
      PopupHeader,
      Popup,
      TabbarItem,
      Group,
      LoadMore
    },
    data () {
      return {
        showHeader: true,
        showEdit: false,
        title: '',
        sendImg: require('../../../assets/activity/sendMail/send.png')
      }
    },
    mounted () {
    },
    methods: {
      backHome () {
        window.history.back()
      },
      sendMail () {
        this.showEdit = true
      },
      closeEdit () {
        this.showEdit = false
      },
      openAbout () {
        console.log('open about')
      }
    }
  }
</script>

<style scoped>
  .header {
    background-image:url(../../../assets/activity/sendMail/view-bg.jpg);
  }
  .bg {
    /*background-image:url(../../../assets/activity/sendMail/view-bg.jpg);*/
    height: 100%;
    width: 100%;
  }
  .backHome {
    position: absolute;
    top: 15px;
    left: 30px;
  }
  .sendMail {
    position: absolute;
    top: 15px;
    right: 30px;
  }

</style>
