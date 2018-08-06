<template>
  <div>
    <div style="text-align: center;margin-top: 15%">
      <img class="logo" src="../../assets/logo.jpeg" style="width: 40%;height: 40%">
      <h1> </h1>
    </div>
    <box gap="10px 10px">
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
import { Group, Cell, XInput, XButton, Box } from 'vux'

export default {
  components: {
    Group,
    Cell,
    XInput,
    XButton,
    Box
  },
  data () {
    return {
      // note: changing this line won't causes changes
      // with hot-reload because the reloaded component
      // preserves its current state and we are modifying
      // its initial state.
      telNum: ''
    }
  },
  methods: {
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
    isInput () {
      return this.telNum.length === 0
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
