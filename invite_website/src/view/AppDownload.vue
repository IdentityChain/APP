<template>
  <div class="bg">
    <view-box body-padding-top="46px" body-padding-bottom="55px">
      <div v-if="showHeader" slot="header"
           style="position: absolute;height: calc(40px + env(safe-area-inset-top));width:100%;left: 0;top: 0;background-color: white;z-index: 100">
        <x-header :left-options="{showBack: false, backText: ''}"
                  style="position: absolute;left:0;top: env(safe-area-inset-top);width: 100%;z-index: 100;">应用下载
        </x-header>
      </div>
      <div class="form-main">
        <div class="form-input">
          <x-input placeholder="请输入手机号" style="margin-bottom: 5px;" mask="999 9999 9999" required v-model="telNum" :max="13" ref="input1"
                   is-type="china-mobile">
            <!--<button  slot="right" style="font-weight: bold;color: dimgrey" @click="getSmsCode">获取验证码{{reget}}</button>-->
            <x-button slot="right" type="default" plain mini @click.native="getSmsCode" :disabled="waiting">获取验证码{{reget}}
            </x-button>
          </x-input>
          <x-input placeholder="请输入短信验证码" style="margin-bottom: 5px;" required class="weui-vcode" v-model="vcode" :min="4" :max="4"
                   ref="input2">
          </x-input>
          <x-input placeholder="请输入新密码,(最少8位,字母+数字)" style="margin-bottom: 5px;" required v-model="password" :min="8" :max="20"
                   ref="input3" type="password">
          </x-input>
          <x-input  style="margin-bottom: 5px;" required v-model="inviteCode" :min="8" :max="20" :disabled=true
                   ref="input3" type="text">
            <x-button slot="right" style="background-color: #6699FF;color: white" plain mini @click.native="getSmsCode" :disabled=true>好友邀请码
            </x-button>
          </x-input>

          <check-icon style="margin-top: 10px;" :value='isAccept' @click.native="changeAccept"></check-icon>
          <p style="display: inline;color: grey;font-size: small">请阅读并同意《IZONE用户使用协议》及《IZONE用户服务协议》</p>

          <x-button type="default" style="margin-top: 25px;border-radius: 20px;color: white;" @click.native="doRegister" :disabled="!isAccept" >注册
          </x-button>
        </div>
      </div>
      <!--<div v-show="false"-->
           <!--style="border-radius: 10px;background-color: #fbf9fe;height: 275px;position: fixed;margin:auto 40px;left:0;right:0;top:0;bottom:0;">-->
        <!--&lt;!&ndash;<group title="" style="margin-top: 30px">&ndash;&gt;-->
        <!--<div-->
          <!--style="margin-top: 10px;margin-bottom: 30px;margin-left: 20px;margin-right: 20px;background-color: ghostwhite">-->
          <!--<x-input title="手机号" mask="999 9999 9999" required v-model="telNum" :max="13" ref="input1"-->
                   <!--is-type="china-mobile">-->
            <!--&lt;!&ndash;<img slot="label" style="padding-right:10px;display:block;" src="../../assets/icon/tel.png"&ndash;&gt;-->
            <!--&lt;!&ndash;width="24" height="24">&ndash;&gt;-->

          <!--</x-input>-->
          <!--<x-input title="验证码" required class="weui-vcode" v-model="vcode" :min="4" :max="4" :show-clear="false"-->
                   <!--ref="input2">-->
            <!--<x-button slot="right" type="primary" mini @click.native="getSmsCode" :disabled="waiting">获取验证码{{reget}}-->
            <!--</x-button>-->
          <!--</x-input>-->

          <!--<x-input title="密码" type="password" placeholder="设置您的登陆密码" required v-model="password" :min="8" :max="20"-->
                   <!--ref="input3"></x-input>-->
          <!--<x-input title="邀请码" mask="999 9999 9999" :min="6" :max="6" ref="input3" placeholder="可选填邀请码"></x-input>-->
        <!--</div>-->
        <!--&lt;!&ndash;</group>&ndash;&gt;-->
        <!--<flexbox>-->
          <!--<flexbox-item>-->
            <!--<x-button plain type="default" @click.native="doRegister">注册</x-button>-->
          <!--</flexbox-item>-->
          <!--<flexbox-item>-->
            <!--<x-button plain type="default" @click.native="login">登陆</x-button>-->
          <!--</flexbox-item>-->
        <!--</flexbox>-->
      <!--</div>-->
    </view-box>
  </div>
</template>

<script>
  import {Group, XInput, XButton, Box, Flexbox, FlexboxItem, XHeader, ViewBox, CheckIcon} from 'vux'

  export default {
    components: {
      Group,
      CheckIcon,
      XHeader,
      XInput,
      XButton,
      ViewBox,
      Box,
      Flexbox,
      FlexboxItem
    },
    data () {
      return {
        showHeader: true,
        isAccept: false,
        telNum: '',
        inviteCode: '',
        vcode: '',
        reget: '',
        password: '',
        waiting: false,
        counter: 59
      }
    },
    mounted: function () {
      this.inviteCode = this.$route.query.inviteCode
    },
    methods: {
      changeAccept () {
        this.isAccept = !this.isAccept
      },
      doRegister () {
        if (this.$refs.input1.valid && this.$refs.input2.valid && this.$refs.input3.valid) {
          console.log('do register')
          this.$api.userApi.registerUser(this.telNum.replace(/[ ]/g, ''), this.vcode, this.password).then(result => {
            if (result.data.success) {
              this.$vux.toast.show({
                type: 'success',
                text: '注册成功'
              })
              this.$router.replace({name: 'login'})
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
      },
      login () {
        this.$router.push({name: 'login'})
      },
      nextStep () {
        this.$router.push({name: 'home'})
      },
      getSmsCode () {
        if (this.$refs.input1.valid) {
          console.log(this.telNum)
          console.log(this.telNum.replace(/[ ]/g, ''))
          this.$api.smsApi.getSmsCode(this.telNum.replace(/[ ]/g, ''), 'PHONELOGIN').then(result => {
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
                text: result.data.message
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
        this.reget = '(' + this.counter + ')'
        this.counter--
        if (this.counter < 0) {
          this.waiting = false
          this.reget = ''
          window.clearInterval(this.timer)
          this.counter = 59
        }
      }
    },
    computed: {
      isInput () {
        return this.vcode.length !== 4
      }
    }
  }
</script>

<style lang="less">
  html, body {
    background-color: white;
  }

  .bg {
    position: fixed;
    height: 100%;
    width: 100%;
  }

  .vux-demo {
    text-align: center;
  }

  .logo {
    width: 100px;
    height: 100px
  }

  .form-main {
    position: absolute;
    height: 100%;
    width: 100%;
  }

  .form-input {
    position: absolute;
    height: auto;
    font-size: small;
    top: 10%;
    width: 75%;
    left: 12.5%;
  }

  .form-input /deep/ .vux-check-icon > .weui-icon-success:before, .vux-check-icon > .weui-icon-success-circle:before {
    color: rgb(49,110, 219);
  }
</style>
