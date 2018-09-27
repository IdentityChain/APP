<template>
  <div style="height: 100%;background-color: #ECECEC">
    <view-box body-padding-top="46px">
      <div v-if="true" slot="header"
           style="position: absolute;height: calc(40px + env(safe-area-inset-top));width:100%;left: 0;top: 0;background-color: mediumslateblue;z-index: 100">
        <x-header :left-options="{showBack: true}"
                  style="position: absolute;left:0;top: env(safe-area-inset-top);width: 100%;z-index: 100;">完善信息
        </x-header>
      </div>
      <group style="padding-top: env(safe-area-inset-top);">
        <cell title="头像"><img src="../../assets/my/people.png" style="height: 50px;width: 50px"/></cell>
        <cell title="手机号码"> {{currentUser.userPhone}}</cell>
        <cell title="昵称" is-link @click.native="nickNameReset"> {{currentUser.nickName}}</cell>
      </group>

      <group>
        <cell title="真实身份" is-link @click.native="realNameReset">{{ currentUser.userStatus === false ? '未设置' : '已设置'}}
        </cell>
      </group>

      <group>
        <cell title="收货地址" is-link></cell>
      </group>

      <div v-transfer-dom>
        <popup v-model="showEdit" height="100%">
          <!-- group already has a top border, so we need to hide header's bottom border-->
          <popup-header
            left-text="取消"
            right-text="完成"
            :title="editModel.title"
            :show-bottom-border="false"
            @on-click-left="showEdit = false"
            @on-click-right="doReset"
            style="background-color: mediumslateblue;color: white;padding-top: env(safe-area-inset-top)"></popup-header>
          <group v-if="editModel.currentEdit === 'realName'">
            <!--修改真实姓名-->
            <!--修改身份证号码-->
            <x-input title="真实姓名" ref="realNameInput" v-model="currentUser.realName"></x-input>

            <x-input title="身份证号码" ref="identityNoInput" v-model="currentUser.identityNo"></x-input>
          </group>
          <group v-if="editModel.currentEdit === 'nickName'">
            <!--修改昵称-->
            <x-input ref="nickNameInput" v-model="currentUser.nickName"></x-input>
          </group>
        </popup>
      </div>
    </view-box>
  </div>
</template>

<script>
  import {
    XButton,
    Box,
    Divider,
    XHeader,
    Group,
    Cell,
    CellBox,
    PopupHeader,
    TransferDom,
    Popup,
    XInput,
    ViewBox
  } from 'vux'

  export default {
    name: 'resetSetting',
    directives: {
      TransferDom
    },
    components: {
      XInput,
      ViewBox,
      XButton,
      XHeader,
      Box,
      Divider,
      Group,
      Cell,
      CellBox,
      PopupHeader,
      Popup
    },
    mounted: function () {
      this.currentUser = JSON.parse(window.localStorage.getItem('User'))
    },
    data () {
      return {
        msg: 'resetSetting page',
        showEdit: false,
        nickName: '李晓明',
        currentUser: {
          nickName: ''
        },
        editModel: {
          title: '设置昵称',
          currentEdit: 'nickName'
        }
      }
    },
    methods: {
      nickNameReset () {
        this.editModel.title = '设置昵称'
        this.editModel.currentEdit = 'nickName'
        this.showEdit = true
        // let _that = this
        // this.$nextTick(function () {
        //   _that.$refs.nickNameInput.focus()
        // })
      },
      realNameReset () {
        this.editModel.title = '设置真实身份'
        this.editModel.currentEdit = 'realName'
        this.showEdit = true
        // let _that = this
        // this.this.$nextTick(function () {
        //   _that.$refs.realNameInput.focus()
        // })
      },
      doReset () {
        this.showEdit = false
        if (this.editModel.currentEdit === 'realName') {
          console.log('修改真实身份信息')
        }
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .rel {
    position: relative;
  }

  .fixed {
    position: fixed;
  }

  .transform {
    transform: scale(1);
  }
</style>
