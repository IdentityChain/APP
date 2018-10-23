<template>
  <div>
    <view-box class="iphoneX">

    </view-box>
  </div>

</template>

<script>
  import {ViewBox} from 'vux'

  export default {
    name: 'XViewBox',
    components: {
      ViewBox
    }
  }
</script>

<style scoped>
  .iphoneX {
    margin-top: calc(46px + env(safe-area-inset-top));
    margin-bottom: env(safe-area-inset-bottom);
  }

</style>
