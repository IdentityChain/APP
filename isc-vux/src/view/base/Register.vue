<template>
  <div>
    <img style="height: 100%;width: 100%;z-index: -10;position: absolute;top: 0px; left: 0px;" src="../../assets/bg.png"/>
    <box gap="70% 40px" style="border-radius: 10px;background-color: #fbf9fe;padding-top: 20px">

      <!--<group title="" style="margin-top: 30px">-->
      <div style="margin-top: 10px;margin-bottom: 30px;margin-left: 20px;margin-right: 20px;background-color: ghostwhite">
        <x-input title="手机号" mask="999 9999 9999" required v-model="telNum" :max="13" ref="input1" is-type="china-mobile">
          <!--<img slot="label" style="padding-right:10px;display:block;" src="../../assets/icon/tel.png"-->
          <!--width="24" height="24">-->

        </x-input>
        <x-input title="验证码" class="weui-vcode" v-model="vcode">
          <x-button slot="right" type="primary" mini @click.native="getSmsCode" :disabled="waiting">获取验证码{{reget}}</x-button>
        </x-input>

        <x-input title="密码" type="password" placeholder="设置您的登陆密码"  required v-model="password" :min="6" :max="20" ref="input2"></x-input>
        <x-input title="邀请码" mask="999 9999 9999"  :max="13" ref="input3" placeholder="可选填邀请码"></x-input>
      </div>
      <!--</group>-->
      <flexbox>
        <flexbox-item>
          <x-button plain type="default">注册</x-button>
        </flexbox-item>
        <flexbox-item>
          <x-button plain type="default" @click.native="login">登陆</x-button>
        </flexbox-item>
      </flexbox>
    </box>
  </div>
</template>

<script>
import { XInput, XButton, Group, Box, Flexbox, FlexboxItem } from 'vux'
export default {
  name: 'Register',
  components: {
    XInput,
    XButton,
    Group,
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
        const urlstr = this.AppConfig.apiServer + '/sms/getCodeByPhone/phoneNumber=' + this.telNum.replace(/[ ]/g, '')
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

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
