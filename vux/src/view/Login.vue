<template>
  <div class="bg">
    <div style="border-radius: 10px;background-color: #fbf9fe;height: 200px;position: fixed;margin:auto 40px;left:0;right:0;top:0;bottom:0;">
      <!--<group title="" style="margin-top: 30px">-->
      <div style="margin-top: 10px;margin-bottom: 30px;margin-left: 20px;margin-right: 20px;background-color: ghostwhite">
        <x-input title="手机号码" mask="999 9999 9999" v-model="telNum" required  :max="13" ref="input1" is-type="china-mobile">
        </x-input>
        <x-input title="密码" type="password" v-model="password"  required  :min="6" :max="13" ref="input2">
        </x-input>
      </div>
      <flexbox>
        <flexbox-item>
          <x-button plain type="default" @click.native="register">注册</x-button>
        </flexbox-item>
        <flexbox-item>
          <x-button plain type="default" @click.native="doLogin" :disabled="isDisableLogin">登陆</x-button>
        </flexbox-item>
      </flexbox>
      <div style="text-align: right;margin-right: 10px;margin-top: 5px">
        <a href="#" @click="resetPasswd">忘记密码</a>
      </div>
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
        password: ''
      }
    },
    methods: {
      resetPasswd () {
        this.$router.push({name: 'resetPasswd'})
      },
      register () {
        console.log('goto register')
        this.$router.push({name: 'register'})
      },
      doLogin () {
        if (this.$refs.input1.valid && this.$refs.input2.valid) {
          this.$api.userApi.userLogin(this.telNum.replace(/[ ]/g, ''), this.password)
            .then(result => {
              console.log(result)
              if (result.data.success) {
                const token = result.headers.authorization
                const userObj = result.data.data
                this.$store.commit('updateCurrentUser', result.data.data)
                window.localStorage.setItem('token', token)
                window.localStorage.setItem('User', JSON.stringify(userObj))
                this.$router.push({name: 'home'})
                window.sessionStorage.setItem('/', 0)
                this.$vux.toast.show({
                  type: 'success',
                  text: result.data.message
                })
              } else {
                this.$vux.toast.show({
                  type: 'text',
                  text: result.data.message,
                  width: '10em'
                })
              }
            })
          // let requestOption = {
          //   url: this.AppConfig.apiServer + '/user/login',
          //   params: {
          //     account: this.telNum.replace(/[ ]/g, ''),
          //     password: this.password
          //   }
          // }
          // this.doPost(requestOption).then(result => {
          //   if (result.success) {
          //     console.log(result)
          //     const userObj = result.data
          //     window.localStorage.setItem('User', JSON.stringify(userObj))
          //     this.$router.push({name: 'home'})
          //     window.sessionStorage.setItem('/', 0)
          //     this.$vux.toast.show({
          //       type: 'success',
          //       text: result.message
          //     })
          //   } else {
          //     this.$vux.toast.show({
          //       type: 'text',
          //       text: result.message,
          //       width: '10em'
          //     })
          //   }
          // })
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
