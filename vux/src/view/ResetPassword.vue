<template>
  <div class="bg">
    <div style="border-radius: 10px;background-color: #fbf9fe;height: 230px;position: fixed;margin:auto 40px;left:0;right:0;top:0;bottom:0;">
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
