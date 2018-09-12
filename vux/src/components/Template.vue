<template>
  <div style="width: 100%">
    <view-box body-padding-top="46px" body-padding-bottom="55px">
      <div v-if="showHeader" slot="header"
           style="position: absolute;height: calc(40px + env(safe-area-inset-top));width:100%;left: 0;top: 0;background-color: black;z-index: 100">
        <x-header :left-options="{showBack: true}"
                  style="position: absolute;left:0;top: env(safe-area-inset-top);width: 100%;z-index: 100;">{{title}}
        </x-header>
      </div>
    </view-box>
  </div>
</template>

<script>
  import {XButton, XHeader, ViewBox} from 'vux'

  export default {
    components: {
      ViewBox,
      XButton,
      XHeader
    },
    data () {
      return {
        showHeader: true,
        title: 'title'
      }
    },
    methods: {}
  }
</script>

<style scoped>

</style>
