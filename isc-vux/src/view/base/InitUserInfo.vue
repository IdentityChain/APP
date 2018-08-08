<template>
  <div class="">
    <div style="text-align: center;margin-top: 10%">
      <h3>完善用户资料 </h3>
    </div>
    <box gap="10px 10px" style="margin-top: 20%">
      <group title="">

        <x-input title="密码" mask="999 9999 9999" required v-model="telNum" :max="13" ref="input1"
                 is-type="china-mobile">
          <!--<img slot="label" style="padding-right:10px;display:block;" src="../../assets/icon/tel.png"-->
          <!--width="24" height="24">-->

        </x-input>

        <x-input title="验证密码" mask="999 9999 9999" required v-model="telNum" :max="13" ref="input1"
                 is-type="china-mobile">
          <!--<img slot="label" style="padding-right:10px;display:block;" src="../../assets/icon/tel.png"-->
          <!--width="24" height="24">-->

        </x-input>

        <x-input title="钱包地址" mask="999 9999 9999" required v-model="telNum" :max="13" ref="input1"
                 is-type="china-mobile">
          <!--<img slot="label" style="padding-right:10px;display:block;" src="../../assets/icon/tel.png"-->
          <!--width="24" height="24">-->

        </x-input>
      </group>
      <br>
      <x-button plain @click.native="nextStep" :disabled="isInput" :action-type="reset">下一步</x-button>
    </box>
  </div>
</template>

<script>
  import {XInput, XButton, Group, Box} from 'vux'
  export default {
    name: 'InitUserInfo',
    components: {
      XButton,
      XInput,
      Group,
      Box
    },
    data () {
      return {}
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
