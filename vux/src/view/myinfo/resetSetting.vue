<template>
  <div style="height: 100%;background-color: #ECECEC">
    <view-box body-padding-top="46px">
      <div v-if="true" slot="header"
           style="position: absolute;height: calc(40px + env(safe-area-inset-top));width:100%;left: 0;top: 0;background-color: mediumslateblue;z-index: 100">
        <x-header :left-options="{showBack: true}"
                  style="position: absolute;left:0;top: env(safe-area-inset-top);width: 100%;z-index: 100;">用户信息
        </x-header>
      </div>
      <group style="padding-top: env(safe-area-inset-top);">
        <cell title="头像"><img src="../../assets/my/people.png" style="height: 50px;width: 50px"/></cell>
        <cell title="手机号码"> {{currentUser.userPhone}}</cell>
        <cell title="账号" is-link> {{currentUser.account}}</cell>
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
            <div style="margin-left: 15px;margin-top: 10px;" v-if="!currentUser.userStatus">
            <h5>提示</h5>
            <p style="color: #666666;font-size: small;margin-top: 5px;">该信息一经提交无法修改，请确保输入准确。</p>
            </div>
            <divider></divider>
            <div v-if="currentUser.userStatus">
              <x-input title="真实姓名" ref="realNameInput" v-model="currentUser.realName" :disabled=true></x-input>
              <x-input title="身份证号码" ref="identityNoInput" v-model="currentUser.identityNo" :disabled=true></x-input>
            </div>
            <div v-else>
              <x-input title="真实姓名" ref="realNameInput" v-model="setRealName"></x-input>
              <x-input title="身份证号码" ref="identityNoInput" v-model="setIdentityNo"></x-input>
            </div>
          </group>
          <group v-if="editModel.currentEdit === 'nickName'">
            <!--修改昵称-->
            <x-input ref="nickNameInput" v-model="newNickName"></x-input>
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
    ViewBox,
    Msg
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
      Msg,
      CellBox,
      PopupHeader,
      Popup
    },
    mounted: function () {
      this.newNickName = this.currentUser.nickName
    },
    data () {
      return {
        msg: 'resetSetting page',
        showEdit: false,
        newNickName: '',
        setRealName: '',
        currentUser: this.$db.get('User'),
        setIdentityNo: '',
        editModel: {
          title: '设置昵称',
          currentEdit: 'nickName'
        }
      }
    },
    computed: {
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
      },
      doReset () {
        // 修改真实姓名
        if (this.editModel.currentEdit === 'realName' && this.currentUser.userStatus === true) {
          this.showEdit = false
        }
        if (this.editModel.currentEdit === 'realName' && this.currentUser.userStatus === false) {
          console.log('修改真实身份信息')
          this.$api.userApi.initUserInfo(this.currentUser.userId, this.setRealName, this.setIdentityNo).then(res => {
            if (res.data.success) {
              this.updateUserInfo()
              this.showEdit = false
              this.$vux.toast.show({
                type: 'success',
                text: '完成'
              })
            }
          })
        }
        // 修改昵称
        if (this.editModel.currentEdit === 'nickName') {
          if (this.newNickName === this.currentUser.nickName) {
            this.showEdit = false
          } else {
            this.$api.userApi.updateNickName(this.currentUser.userId, this.currentUser.nickName, this.newNickName).then(res => {
              if (res.data.success) {
                this.updateUserInfo()
                this.showEdit = false
                this.$vux.toast.show({
                  type: 'success',
                  text: '完成'
                })
              }
            })
          }
        }
      },
      doRequest (requestOptions) {
        this.doPost(requestOptions).then(result => {
          if (result.success) {
            this.updateUserInfo()
            this.showEdit = false
            this.$vux.toast.show({
              type: 'success',
              text: '完成'
            })
          } else {
            this.$vux.toast.show({
              type: 'text',
              text: result.message,
              width: '10em'
            })
          }
        })
      },
      updateUserInfo () {
        this.$api.userApi.getUserInfoById(this.currentUser.userId).then(res => {
          if (res.data.success) {
            this.$db.set('User', res.data.data)
            this.currentUser = res.data.data
          }
        })
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
