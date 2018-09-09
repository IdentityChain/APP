<template>
  <div class="bg">
    <div style="border-radius: 10px;background-color: #fbf9fe;height: 200px;position: fixed;margin:auto 40px;left:0;right:0;top:0;bottom:0;">
      <!--<group title="" style="margin-top: 30px">-->
      <div style="margin-top: 10px;margin-bottom: 30px;margin-left: 20px;margin-right: 20px;background-color: ghostwhite">
        <x-input title="手机号码" mask="999 9999 9999" required  :max="13" ref="input1" is-type="china-mobile">
        </x-input>
        <x-input title="密码" type="password"  required  :min="6" :max="13" ref="input2">
        </x-input>
      </div>
      <flexbox>
        <flexbox-item>
          <x-button plain type="default" @click.native="back">注册</x-button>
        </flexbox-item>
        <flexbox-item>
          <x-button plain type="default" @click.native="gotoHome">登陆</x-button>
        </flexbox-item>
      </flexbox>
      <div style="text-align: right;margin-right: 10px;margin-top: 5px">
        <a href="#">忘记密码</a>
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
        // note: changing this line won't causes changes
        // with hot-reload because the reloaded component
        // preserves its current state and we are modifying
        // its initial state.
        msg: 'Hello World!'
      }
    },
    methods: {
      back () {
        // this.$store.commit('setTransition', 'turn-off')
        this.$router.back(-1)
      },
      gotoHome () {
        this.$router.push({name: 'home'})
        // this.$store.commit('setTransition', 'turn-on')
      },
      gotoPage1 () {
        this.$router.push({name: 'page1'})
        // this.$store.commit('setTransition', 'turn-on')
      },
      gotoPage2 () {
        this.$router.push({name: 'page2'})
        // this.$store.commit('setTransition', 'turn-on')
      },
      gotoPage3 () {
        this.$router.push({name: 'page3'})
        // this.$store.commit('setTransition', 'turn-on')
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
