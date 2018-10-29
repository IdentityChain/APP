<template>
  <div class="bg">
    <div class="touxiang">

    </div>
    <div class="login-form">
      <x-input mask="999 9999 9999" v-model="telNum" required :max="13" ref="input1"
               is-type="china-mobile" placeholder="请输入手机号码">
        <img slot="label" style="padding-right:10px;display:block;" src="../assets/login/user.png" width="24px"
             height="24px">
      </x-input>
      <x-input type="password" v-model="password" required :min="6" :max="13" ref="input2" placeholder="请输入密码">
        <img slot="label" style="padding-right:10px;display:block;" src="../assets/login/password.png" width="24px"
             height="24px">
      </x-input>
      <x-button style="margin-top: 25px;background-color: rgb(49,110, 219);border-radius: 20px;color: white;" @click.native="doLogin" :disabled="isDisableLogin">登录
      </x-button>
      <div style="margin-top: 15px;color: grey;font-size: small;">
        <p style="float: left;position: absolute;left: 5px;" @click="register">立即注册</p>
        <p style="position:absolute;right: 5px;" @click="resetPasswd">忘记密码?</p>
      </div>
    </div>
    <div class="third-login">
      <divider>快速登录</divider>
      <div style="position: absolute;top: 40px;width: 100%">
        <img style="position: absolute;left: 0" src="../assets/login/weixin.png" width="40px" height="40px"/>
        <img style="position: absolute;left: calc(50% - 22px)"src="../assets/login/weibo.png" width="40px" height="40px"/>
        <img style="position: absolute;right: 0" src="../assets/login/QQ.png" width="40px" height="40px"/>
      </div>
    </div>
  </div>
</template>

<script>
  import {Group, XInput, XButton, Box, Flexbox, FlexboxItem, Divider} from 'vux'

  export default {
    components: {
      Group,
      XInput,
      XButton,
      Flexbox,
      Box,
      Divider,
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
                this.$db.set('token', token)
                this.$db.set('User', userObj)
                this.$router.replace({name: 'home'})
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
    background-color: rgb(245, 246, 248);
    position: fixed;
    height: 100%;
    width: 100%;
  }

  .touxiang {
    position: absolute;
    top: 15%;
    left: calc(50% - 50px);
    width: 100px;
    height: 100px;
    background-image: url("../assets/login/touxiang.png");
    border-radius: 50px;
  }

  .login-form {
    position: absolute;
    top: calc(20% + 100px);
    left: 12.5%;
    width: 75%;
    height: 100px;
    border: 1px solid #c0bfc4;
    border-radius: 15px;
  }

  .third-login {
    position: absolute;
    height: 20%;
    width: 75%;
    left: 12.5%;
    bottom: env(safe-area-inset-bottom);
  }
</style>
