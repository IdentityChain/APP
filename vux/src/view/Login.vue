<template>
  <div class="bg">
    <div class="touxiang">

    </div>
    <div class="login-form">
      <x-input v-model="telNum"  :max="13" ref="input1" is-type="china-mobile" placeholder="请输入用户账号">
        <img slot="label" style="padding-right:10px;display:block;" src="../assets/login/user.png" width="24px" height="24px">
      </x-input>
      <x-input type="password" v-model="password" :min="6" :max="13" ref="input2" placeholder="请输入密码">
        <img slot="label" style="padding-right:10px;display:block;" src="../assets/login/password.png" width="24px" height="24px">
      </x-input>
      <x-button style="margin-top: 25px;background-color: rgb(49,110, 219);border-radius: 20px;color: white;">登录</x-button>
      <div style="margin-top: 20px;">
          <p></p>
      </div>
    </div>
    <div v-show="false" style="border-radius: 10px;background-color: #fbf9fe;height: 200px;position: fixed;margin:auto 40px;left:0;right:0;top:0;bottom:0;">
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
    background-color: rgb(245, 246,248);
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
  .touxiang {
    position: absolute;
    top: 20%;
    left: calc(50% - 50px);
    width: 100px;
    height: 100px;
    background-image: url("../assets/login/touxiang.png");
    border-radius: 50px;
  }
  .login-form {
    position: absolute;
    top: calc(20% + 150px);
    left: 12.5%;
    width: 75%;
    height: 100px;
    border: 1px solid #c0bfc4;
    border-radius: 15px;
  }
</style>
