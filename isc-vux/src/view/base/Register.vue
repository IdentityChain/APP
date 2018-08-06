<template>
  <div>
    <div style="text-align: center;margin-top: 25%">
      <h1>欢迎加入ISC </h1>
    </div>
    <box gap="10px 10px" style="margin-top: 20%">
      <group title="">

        <x-input title="手机号" mask="999 9999 9999" required v-model="telNum" :max="13" ref="input1" is-type="china-mobile">
          <!--<img slot="label" style="padding-right:10px;display:block;" src="../../assets/icon/tel.png"-->
          <!--width="24" height="24">-->

        </x-input>
        <x-input title="验证码" class="weui-vcode">
          <x-button slot="right" type="primary" mini>发送验证码</x-button>
        </x-input>
      </group>
      <br>
      <x-button plain  @click.native="nextStep" :disabled="isInput">下一步</x-button>
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
      telNum: ''
    }
  },
  computed: {
    isInput () {
      return this.telNum.length === 0
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
