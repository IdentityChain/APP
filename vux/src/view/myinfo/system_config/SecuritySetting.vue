<template>
  <div style="height: 100%;background-color: #ECECEC">
    <view-box body-padding-top="46px">
      <div v-if="true" slot="header"
           style="position: absolute;height: calc(40px + env(safe-area-inset-top));width:100%;left: 0;top: 0;background-color: mediumslateblue;z-index: 100">
        <x-header :left-options="{showBack: true}"
                  style="position: absolute;left:0;top: env(safe-area-inset-top);width: 100%;z-index: 100;">安全设置
        </x-header>
      </div>

      <group style="padding-top: env(safe-area-inset-top);">
        <cell title="登陆密码" is-link @click.native="resetLoginPasswd"></cell>
        <cell title="交易密码" is-link></cell>
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
          <group >
            <!--修改昵称-->
            <x-input  v-if="editModel.currentEdit === 'nickName'" ref="nickNameInput" v-model="currentUser.nickName"></x-input>
          </group>
          <!--修改登录密码-->
          <div v-if="editModel.currentEdit === 'loginPassword'">
            <group>
              <x-input type="password"  title="旧密码" :min="6" :max="20" ref="input1" v-model="oldPassword" placeholder="请填写旧密码"></x-input>
              <x-input type="password"  title="新密码" :min="6" :max="20" ref="input2" v-model="newPassword" placeholder="请输入新的密码"></x-input>
              <x-input type="password"  title="确认密码" :min="6" :max="20" ref="input3" v-model="repeatPassword"  placeholder="请再次输入新密码"></x-input>
            </group>
            <div style="font-size: small;margin-top: 5px;margin-left: 10px;">
              <div>密码必须至少6个字符</div>
              <a style="color: #586c94;margin-top: 5px" href="#" @click="resetPasswd">忘记旧密码</a>
            </div>
          </div>
        </popup>
      </div>
    </view-box>
  </div>
</template>

<script>
  import {XButton, Box, Divider, XHeader, Group, Cell, CellBox, PopupHeader, TransferDom, Popup, XInput, ViewBox} from 'vux'

  export default {
    name: 'SecuritySetting',
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
        oldPassword: '',
        newPassword: '',
        repeatPassword: '',
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
      logout () {
        window.localStorage.clear()
        this.$router.push({'name': 'login'})
      },
      resetPasswd () {
        this.$router.push({name: 'resetPasswd'})
      },
      resetLoginPasswd () {
        this.editModel.title = '修改登录密码'
        this.editModel.currentEdit = 'loginPassword'
        this.showEdit = true
      },
      doReset () {
        if (this.editModel.currentEdit === 'loginPassword') {
          if (this.oldPassword.length <= 0 || this.newPassword.length <= 0 || this.repeatPassword.length <= 0) {
            this.$vux.toast.show({
              type: 'text',
              text: '请完整填写信息',
              width: '10em'
            })
          } else {
            if (this.$refs.input1.valid && this.$refs.input2.valid && this.$refs.input3.valid) {
              if (this.newPassword === this.repeatPassword) {
                let requestOptions = {
                  url: this.AppConfig.apiServer + '/user/updateLoginPassword',
                  params: {
                    account: this.currentUser.account,
                    oldPassword: this.oldPassword,
                    newPassword: this.repeatPassword
                  }
                }
                this.doRequest(requestOptions)
              } else {
                this.$vux.toast.show({
                  type: 'text',
                  text: '两次输入的新密码不一致',
                  width: '10em'
                })
              }
            }
          }
        }
      },
      doRequest (requestOptions) {
        this.doPost(requestOptions).then(result => {
          if (result.success) {
            this.oldPassword = ''
            this.newPassword = ''
            this.repeatPassword = ''
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
