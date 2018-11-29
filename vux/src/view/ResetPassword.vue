<template>
  <div class="bg">
    <view-box body-padding-top="46px" body-padding-bottom="55px">
      <div slot="header"
           style="position: absolute;height: calc(40px + env(safe-area-inset-top));width:100%;left: 0;top: 0;background-color: white;;z-index: 100">
        <x-header :left-options="{showBack: true, backText: ''}"
                  style="position: absolute;left:0;top: env(safe-area-inset-top);width: 100%;z-index: 100;">找回密码
        </x-header>
      </div>
      <div class="form-main">
        <div class="form-input">
          <x-input placeholder="请输入手机号" style="margin-bottom: 5px;" mask="999 9999 9999" required v-model="telNum" :max="13" ref="input1" is-type="china-mobile">
            <x-button slot="right" type="default" plain mini @click.native="getSmsCode" :disabled="waiting">获取验证码{{reget}}
            </x-button>
          </x-input>
          <x-input placeholder="请输入短信验证码" style="margin-bottom: 5px;" required v-model="vcode" :min="4" :max="4"
                   ref="input2">
          </x-input>
          <x-input placeholder="请输入新密码" style="margin-bottom: 5px;" type="password"  required v-model="password" :min="8" :max="13" ref="input3">
          </x-input>
          <x-input placeholder="请再次输入新密码" style="margin-bottom: 5px;" type="password" required v-model="confirmPassword" :min="8" :max="13" ref="input4">
          </x-input>
          <x-button type="default" style="margin-top: 25px;border-radius: 20px;color: white;" @click.native="doResetPasswd">重设密码
          </x-button>
        </div>
      </div>
    </view-box>
    <div v-show="false" style="border-radius: 10px;background-color: #fbf9fe;height: 230px;position: fixed;margin:auto 40px;left:0;right:0;top:0;bottom:0;">
      <!--<group title="" style="margin-top: 30px">-->
      <div style="margin-top: 10px;margin-bottom: 30px;margin-left: 20px;margin-right: 20px;background-color: ghostwhite">
        <x-input title="手机号" mask="999 9999 9999" required v-model="telNum" :max="13" ref="input1" is-type="china-mobile">
          <!--<img slot="label" style="padding-right:10px;display:block;" src="../../assets/icon/tel.png"-->
          <!--width="24" height="24">-->

        </x-input>
        <x-input title="验证码" class="weui-vcode" v-model="vcode">
          <x-button slot="right" type="primary" mini @click.native="getSmsCode" :disabled="waiting">获取验证码{{reget}}</x-button>
        </x-input>

        <x-input title="新密码" type="password"  required v-model="password" :min="6" :max="13" ref="input2"></x-input>
      </div>
      <!--</group>-->
      <flexbox>
        <flexbox-item>
          <x-button plain type="default" @click.native="goLogin">返回登陆</x-button>
        </flexbox-item>
        <flexbox-item>
          <x-button plain type="default" @click.native="doResetPasswd">重置密码</x-button>
        </flexbox-item>
      </flexbox>
    </div>

  </div>
</template>

<script>
  import { Group, XInput, XButton, Box, Flexbox, FlexboxItem, ViewBox, XHeader } from 'vux'
  export default {
    components: {
      Group,
      ViewBox,
      XInput,
      XButton,
      XHeader,
      Box,
      Flexbox,
      FlexboxItem
    },
    data () {
      return {
        telNum: '',
        vcode: '',
        reget: '',
        password: '',
        confirmPassword: '',
        waiting: false,
        counter: 59
      }
    },
    methods: {
      goLogin () {
        this.$router.push({name: 'login'})
      },
      doResetPasswd () {
        console.log('do reset')
        if (this.$refs.input1.valid && this.$refs.input2.valid && this.$refs.input3.valid && this.$refs.input4.valid) {
          if (this.password === this.confirmPassword) {
            this.$api.userApi.resetLoginPasswordBySms(this.telNum.replace(/[ ]/g, ''), this.password, this.vcode).then(result => {
              if (result.data.success) {
                this.$vux.toast.show({
                  type: 'success',
                  text: '重置成功'
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
              text: '两次输入的密码不一致',
              width: '11em'
            })
          }
        } else {
          this.$vux.toast.show({
            type: 'text',
            text: '请完整输入信息',
            width: '10em'
          })
        }
      },
      nextStep () {
        this.$router.push({name: 'home'})
      },
      getSmsCode () {
        if (this.$refs.input1.valid) {
          console.log(this.telNum)
          console.log(this.telNum.replace(/[ ]/g, ''))
          this.$api.smsApi.getSmsCode(this.telNum.replace(/[ ]/g, ''), 'PHONECHANGEPWD').then(result => {
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
        console.log('sdf')
      },
      changeBtnText () {
        this.reget = '(' + this.counter + ')'
        this.counter --
        if (this.counter < 0) {
          this.waiting = false
          this.reget = ''
          window.clearInterval(this.timer)
          this.counter = 59
        }
      }
    }
  }
</script>

<style scoped>
  html, body {
    background-color: white;
  }
  .bg {
    position: fixed;
    height: 100%;
    width: 100%;
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
</style>
