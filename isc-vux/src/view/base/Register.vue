<template>
  <div>
    <box gap="10px 10px" style="margin-top: 20%">
      <group title="">

        <x-input title="手机号" mask="999 9999 9999" required v-model="telNum" :max="13" ref="input1" is-type="china-mobile">
          <!--<img slot="label" style="padding-right:10px;display:block;" src="../../assets/icon/tel.png"-->
          <!--width="24" height="24">-->

        </x-input>

      </group>
      <br>
      <x-button plain  @click.native="nextStep" :disabled="isInput">下一步</x-button>


      <p style="text-align: right;font-size: small;margin-top: 10px;color: grey">新用户注册</p>
    </box>
  </div>
</template>

<script>
export default {
  name: 'Register',
  data () {
    return {
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
