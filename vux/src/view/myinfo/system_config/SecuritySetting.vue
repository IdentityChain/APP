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
        <cell title="交易密码" is-link @click.native="resetPayPasswd">{{currentUser.passwordReset ? '' : '未设置'}}</cell>
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
          <group v-if="editModel.currentEdit === 'nickName'">
            <!--修改昵称-->
            <x-input ref="nickNameInput" v-model="$store.state.currentUser.nickName"></x-input>
          </group>
          <!--修改登录密码-->
          <div v-if="editModel.currentEdit === 'loginPassword'">
            <group>
              <x-input type="password" title="旧密码" :min="6" :max="20" ref="input1"
                       v-model="resetLoginPassword.oldPassword" placeholder="请填写旧密码"></x-input>
              <x-input type="password" title="新密码" :min="6" :max="20" ref="input2"
                       v-model="resetLoginPassword.newPassword" placeholder="请输入新的密码"></x-input>
              <x-input type="password" title="确认密码" :min="6" :max="20" ref="input3"
                       v-model="resetLoginPassword.repeatPassword" placeholder="请再次输入新密码"></x-input>
            </group>
            <div style="font-size: small;margin-top: 5px;margin-left: 10px;">
              <div>密码必须至少6个字符</div>
              <a style="color: #586c94;margin-top: 5px" href="#" @click="resetPasswd">忘记旧密码</a>
            </div>
          </div>

          <div v-if="editModel.currentEdit === 'payPassword'">
            <group v-if="currentUser.passwordReset">
              <x-input type="tel" required title="手机号码" ref="spp1" v-model="resetPayPassword.phone"
                       placeholder="请填写手机号码"></x-input>
              <x-input type="password" required title="登录密码" ref="spp2" :min="6" :max="20"
                       v-model="resetPayPassword.loginPassword" placeholder="请输入登录密码"></x-input>
              <x-input type="password" required title="旧交易密码" ref="spp3" :min="6" :max="6"
                       v-model="resetPayPassword.oldPayPassword" placeholder="请输入旧交易密码"></x-input>
              <x-input type="password" required title="新交易密码" ref="spp4" :min="6" :max="6"
                       v-model="resetPayPassword.newPayPassword" placeholder="请输入新交易密码"></x-input>
              <x-input type="password" required title="确认交易密码" ref="spp5" :min="6" :max="6"
                       v-model="resetPayPassword.confirmPayPassword" placeholder="请再次输入新交易密码"></x-input>
            </group>
            <group v-else>
              <x-input type="tel" title="手机号码" is-type="china-mobile" required ref="setPayPasswordInput1"
                       v-model="setPayPassword.phone"></x-input>
              <x-input title="验证码" required class="weui-vcode" v-model="setPayPassword.smsCode" type="number" :min="4"
                       :max="4" :show-clear="false" ref="setPayPasswordInput2">
                <x-button slot="right" type="primary" mini @click.native="getSmsCode" :disabled="smsCode.waiting">
                  获取验证码{{smsCode.reget}}
                </x-button>
              </x-input>
              <x-input :is-type="isNumber" type="password" :max="6" required title="交易密码" ref="setPayPasswordInput3"
                       v-model="setPayPassword.payPassword" placeholder="请输入交易密码"></x-input>
            </group>
          </div>
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
  import {mapState} from 'vuex'

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
    data () {
      return {
        msg: 'resetSetting page',
        showEdit: false,
        setPayPassword: {
          phone: '',
          smsCode: '',
          payPassword: ''
        },
        resetPayPassword: {
          phone: '',
          loginPassword: '',
          oldPayPassword: '',
          newPayPassword: '',
          confirmPayPassword: ''
        },
        resetLoginPassword: {
          oldPassword: '',
          newPassword: '',
          repeatPayPassword: ''
        },
        smsCode: {
          waiting: false,
          counter: 59,
          reget: ''
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
      resetPayPasswd () {
        this.editModel.title = this.currentUser.passwordReset ? '修改交易密码' : '设置交易密码'
        this.editModel.currentEdit = 'payPassword'
        this.showEdit = true
      },
      getSmsCode () {
        if (this.$refs.input1.valid) {
          console.log(this.phoneNumber)
          this.$api.smsApi.getSmsCode(this.phoneNumber, 'PHONECHANGEPAYPWD').then(result => {
            if (result.data.success) {
              this.waiting = true
              this.timer = setInterval(this.changeBtnText, 1000)
              this.$vux.toast.show({
                type: 'success',
                text: '发送成功'
              })
            } else {
              this.$vux.toast.show({
                type: 'warn',
                text: '请求失败'
              })
            }
          })
        } else {
          this.$vux.toast.show({
            type: 'text',
            text: '手机号码格式不正确',
            width: '10em'
          })
        }
      },
      changeBtnText () {
        this.smsCode.reget = '(' + this.smsCode.counter + ')'
        this.smsCode.counter--
        if (this.smsCode.counter < 0) {
          this.smsCode.waiting = false
          this.smsCode.reget = ''
          window.clearInterval(this.timer)
          this.smsCode.counter = 59
        }
      },
      doReset () {
        if (this.editModel.currentEdit === 'loginPassword') {
          if (this.resetLoginPassword.oldPassword.length <= 0 || this.resetLoginPassword.newPassword.length <= 0 || this.resetLoginPassword.repeatPassword.length <= 0) {
            this.$vux.toast.show({
              type: 'text',
              text: '请完整填写信息',
              width: '10em'
            })
          } else {
            if (this.$refs.input1.valid && this.$refs.input2.valid && this.$refs.input3.valid) {
              if (this.newPassword === this.repeatPassword) {
                this.$api.userApi.updateLoginPassword(this.currentUser.account, this.resetLoginPassword.oldPassword, this.resetLoginPassword.repeatPassword).then(result => {
                  if (result.data.success) {
                    this.resetLoginPassword.oldPassword = ''
                    this.resetLoginPassword.newPassword = ''
                    this.resetLoginPassword.repeatPassword = ''
                    this.showEdit = false
                    this.$vux.toast.show({
                      type: 'success',
                      text: '完成'
                    })
                  } else {
                    this.$vux.toast.show({
                      type: 'text',
                      text: result.data.message
                    })
                  }
                })
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
        if (this.editModel.currentEdit === 'payPassword') {
          if (this.currentUser.passwordReset) {
            // 重置交易密码
            if (this.$refs.spp1.valid && this.$refs.spp2.valid && this.$refs.spp3.valid && this.$refs.spp4.valid && this.$refs.spp5.valid) {
              // 重设交易密码
              // phone, account, loginPassword, oldPayPassword, newPayPassword
              this.$api.userApi.resetPayPassword(this.resetPayPassword.phone, this.currentUser.userPhone, this.resetPayPassword.loginPassword, this.resetPayPassword.oldPayPassword, this.resetPayPassword.newPayPassword).then(result => {
                if (result.data.success) {
                  this.showEdit = false
                  this.$vux.toast.show({
                    type: 'success',
                    text: '完成'
                  })
                } else {
                  this.$vux.toast.show({
                    type: 'text',
                    text: result.data.message
                  })
                }
              })
            }
          } else {
            this.$vux.toast.show({
              type: 'text',
              text: '请完整填写信息'
            })
          }
        } else {
          // 第一次设置交易密码
          if (this.$refs.setPayPasswordInput1.valid && this.$refs.setPayPasswordInput2.valid && this.$refs.setPayPasswordInput3.valid) {
            // 表单验证通过,执行API调用
            this.$api.userApi.setPayPasswordBySmsCode(this.setPayPassword.phone, this.setPayPassword.smsCode, this.setPayPassword.payPassword).then(result => {
              if (result.data.success) {
                this.showEdit = false
                this.$vux.toast.show({
                  type: 'success',
                  text: '完成'
                })
                this.$api.userApi.getUserInfoById(this.currentUser.userId).then(result => {
                  this.$store.commit('updateCurrentUser', result.data.data)
                })
              } else {
                this.$vux.toast.show({
                  type: 'text',
                  text: result.data.message
                })
              }
            })
          } else {
            this.$vux.toast.show({
              type: 'text',
              text: '请完整填写信息'
            })
          }
        }
      },
      isNumber () {
        if (/^\d{6}$/.test(this.setPayPassword.payPassword)) {
          return {
            valid: true
          }
        } else {
          return {
            valid: false,
            msg: '交易密码为6位数字'
          }
        }
      }
    },
    computed: {
      ...mapState({
        currentUser: state => state.currentUser
      })
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
