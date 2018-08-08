<template>
  <div>
    <div style="text-align: left;margin-left:14px;margin-top: 25%">
      <h3>ISC身份登陆/创建 </h3>
    </div>
    <box gap="10px 10px" style="margin-top: 10%">
      <group title="">

        <x-input title="手机号" mask="999 9999 9999" required v-model="telNum" :max="13" ref="input1" is-type="china-mobile">
          <!--<img slot="label" style="padding-right:10px;display:block;" src="../../assets/icon/tel.png"-->
          <!--width="24" height="24">-->

        </x-input>
        <x-input title="验证码" class="weui-vcode" v-model="vcode">
          <x-button slot="right" type="primary" mini @click.native="getSmsCode" :disabled="waiting">获取验证码{{reget}}</x-button>
        </x-input>

      </group>
      <br>
      <x-button plain  @click.native="nextStep" :disabled="isInput">确认</x-button>
    </box>
  </div>
</template>

<script>
import { XInput, XButton, Group, Box } from 'vux'
export default {
  name: 'Register',
  components: {
    XInput,
    XButton,
    Group,
    Box
  },
  data () {
    return {
      telNum: '',
      vcode: '',
      reget: '',
      waiting: false,
      counter: 59
    }
  },
  methods: {
    nextStep () {
      this.$router.push({name: 'initUserInfo'})
    },
    getSmsCode () {
      if (this.$refs.input1.valid) {
        console.log(this.telNum)
        console.log(this.telNum.replace(/[ ]/g, ''))
        const urlstr = this.AppConfig.apiServer + '/sms/getCodeByPhone?phoneNumber=' + this.telNum.replace(/[ ]/g, '')
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
