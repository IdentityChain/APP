<template>
  <div class="body1" style="margin-top: calc(46px + env(safe-area-inset-top))">
    <msg title="功能建设中" description="程序猿兄弟增加加急开发,请耐心等待..." icon="info">
    </msg>
  </div>
</template>

<script>
  import {Msg} from 'vux'

  export default {
    name: 'Comming',
    components: {
      Msg
    },
    methods: {
      closeWindow () {
      }
    }
  }
</script>

<style scoped>
  .body1 /deep/ .weui-icon-info {
    color: rgb(50,112,222);
  }
</style>
