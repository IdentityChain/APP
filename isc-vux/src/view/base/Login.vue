<template>
  <div>
    <img style="height: 100%;width: 100%;z-index: -10;position: absolute;top: 0px; left: 0px;" src="../../assets/bg.png"/>
    <!--<div style="text-align: center;margin-top: 15%">-->
      <!--<img class="logo" src="../../assets/logo.jpeg" style="width: 40%;height: 40%">-->
      <!--<h1> </h1>-->
    <!--</div>-->
    <box gap="70% 40px" style="border-radius: 10px;background-color: #fbf9fe;padding-top: 20px">
        <!--<group title="" style="margin-top: 30px">-->
          <div style="margin-top: 10px;margin-bottom: 30px;margin-left: 20px;margin-right: 20px;background-color: ghostwhite">


          <x-input title="手机号码" mask="999 9999 9999" required v-model="telNum" :max="13" ref="input1" is-type="china-mobile">
            <!--<img slot="label" style="padding-right:10px;display:block;" src="../../assets/icon/tel.png"-->
            <!--width="24" height="24">-->

          </x-input>

          <x-input title="密码" type="password"  required v-model="password" :min="6" :max="13" ref="input2">
            <!--<img slot="label" style="padding-right:10px;display:block;" src="../../assets/icon/tel.png"-->
            <!--width="24" height="24">-->
          </x-input>
          </div>
        <!--</group>-->
      <flexbox>
        <flexbox-item>
          <x-button plain type="default" @click.native="register">注册</x-button>
        </flexbox-item>
        <flexbox-item>
          <x-button plain type="default" @click.native="doLogin" :disabled="isDisableLogin">登陆</x-button>
        </flexbox-item>
      </flexbox>
      <div style="text-align: right;margin-right: 10px;margin-top: 5px">
        <a href="">忘记密码</a>
      </div>
    </box>
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
      // note: changing this line won't causes changes
      // with hot-reload because the reloaded component
      // preserves its current state and we are modifying
      // its initial state.
      telNum: '',
      password: ''
    }
  },
  methods: {
    register () {
      console.log('goto register')
      this.$router.push({name: 'register'})
    },
    doLogin () {
      if (this.$refs.input1.valid && this.$refs.input2.valid) {
        let requestOption = {
          url: this.AppConfig.apiServer + '/user/login',
          params: {
            account: this.telNum,
            password: this.password
          }
        }
        this.doPost(requestOption).then(result => {
          if (result.success) {
            console.log(result)
          } else {
            console.log(result.message)
          }
        })
      }
    },
    nextStep () {
      if (this.$refs.input1.valid) {
        const urlstr = this.AppConfig.apiServer + '/user/findByAccount?account=administrator'
        this.doGet({
          url: urlstr
        }).then(result => {
          if (result.success) {
            console.log(result.data)
          } else {
            console.log('请求失败')
          }
        })
      } else {
        this.$vux.toast.show({
          type: 'text',
          text: '手机号码格式不正确',
          width: '10em'
        })
      }
    }
  },
  computed: {
    isDisableLogin () {
      return this.telNum.length === 0 || this.password.length === 0
    }
  }
}
</script>

<style>
.vux-demo {
  text-align: center;
}
.logo {
  width: 100px;
  height: 100px
}
</style>
