<template>
  <div class="bg">
    <div style="border-radius: 10px;background-color: #fbf9fe;height: 275px;position: fixed;margin:auto 40px;left:0;right:0;top:0;bottom:0;">
      <!--<group title="" style="margin-top: 30px">-->
      <div style="margin-top: 10px;margin-bottom: 30px;margin-left: 20px;margin-right: 20px;background-color: ghostwhite">
        <x-input title="手机号" mask="999 9999 9999" required v-model="telNum" :max="13" ref="input1" is-type="china-mobile">
          <!--<img slot="label" style="padding-right:10px;display:block;" src="../../assets/icon/tel.png"-->
          <!--width="24" height="24">-->

        </x-input>
        <x-input title="验证码" required class="weui-vcode" v-model="vcode" :min="4" :max="4" :show-clear="false" ref="input2">
          <x-button slot="right" type="primary" mini @click.native="getSmsCode" :disabled="waiting">获取验证码{{reget}}</x-button>
        </x-input>

        <x-input title="密码" type="password" placeholder="设置您的登陆密码"  required v-model="password" :min="6" :max="20" ref="input3"></x-input>
        <x-input title="邀请码" mask="999 9999 9999" :min="6"  :max="6" ref="input3" placeholder="可选填邀请码"></x-input>
      </div>
      <!--</group>-->
      <flexbox>
        <flexbox-item>
          <x-button plain type="default" @click.native="doRegister">注册</x-button>
        </flexbox-item>
        <flexbox-item>
          <x-button plain type="default" @click.native="login">登陆</x-button>
        </flexbox-item>
      </flexbox>
    </div>

  </div>
</template>

<script>
  import { Group, XInput, XButton, Box, Flexbox, FlexboxItem } from 'vux'

  export default {
    components: {
      Group,
      XInput,
      XButton,
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
        yaoqingma: '',
        waiting: false,
        counter: 59
      }
    },
    methods: {
      doRegister () {
        if (this.$refs.input1.valid && this.$refs.input2.valid && this.$refs.input3.valid) {
          console.log('do register')
          let requestOptions = {
            url: this.AppConfig.apiServer + '/user/firstsave',
            params: {
              phone: this.telNum.replace(/[ ]/g, ''),
              password: this.password,
              smsCode: this.vcode,
              invitationCode: this.yaoqingma
            }
          }
          this.doPost(requestOptions).then(result => {
            if (result.success) {
              this.$vux.toast.show({
                type: 'success',
                text: '注册成功'
              })
            } else {
              this.$vux.toast.show({
                type: 'text',
                text: result.message
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
          const urlstr = this.AppConfig.apiServer + '/sms/getCodeByPhone/' + this.telNum.replace(/[ ]/g, '')
          this.doGet({
            url: urlstr
          }).then(result => {
            if (result.success) {
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
        this.reget = '(' + this.counter + ')'
        this.counter --
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

<style scoped>
  html, body {
    background-color: white;
  }
  .bg {
    background-image:url(../assets/bg.jpg);
    background-repeat:no-repeat;
    background-size:100% 100%;
    -moz-background-size:100% 100%;
    position: fixed;
    height: 100%;
    width: 100%;
    background-attachment: fixed;
  }
  .vux-demo {
    text-align: center;
  }
  .logo {
    width: 100px;
    height: 100px
  }
</style>
