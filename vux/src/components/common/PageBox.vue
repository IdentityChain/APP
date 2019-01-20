<template>
  <div style="height: 100%; width: 100%;position: fixed;top: 0;left: 0;overflow-y: scroll">
      <div class="header-css"
           style="position: fixed;height: calc(46px + env(safe-area-inset-top));width:100%;left: 0;top: 0;background-color: rgb(50,112,222);z-index: 100">
        <slot name="header"></slot>
      </div>
      <div style="position: absolute;top: calc(46px + env(safe-area-inset-top));padding-bottom: env(safe-area-inset-bottom);overflow-y: scroll">
        <slot name="body"></slot>
      </div>



  </div>

</template>

<script>
  export default {
    name: 'PageBox'
  }
</script>

<style scoped>
  .header-css /deep/ .vux-header-title {
    color: white;
  }

  .header-css /deep/ .vux-header .vux-header-left .left-arrow:before {
    border: 1px solid white;
    border-width: 1px 0 0 1px;
  }
  .main-body {
    position: absolute;
    top: 45px;
    height: 0px;
    width: 100vw;
    padding-top: env(safe-area-inset-top);
    background-color: rgb(50, 112, 222);
  }
</style>
