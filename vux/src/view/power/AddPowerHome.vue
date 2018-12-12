<template>
  <div style="height: 100%; width: 100%;background-color: white">
    <view-box body-padding-top="46px" body-padding-bottom="55px">
      <div class="header-css" slot="header"
           style="position: fixed;height: calc(40px + env(safe-area-inset-top));width:100%;left: 0;top: 0;background-color: rgb(50,112,222);z-index: 100">
        <x-header :left-options="{showBack: true, backText: ''}"
                  style="position: fixed;left:0;top: env(safe-area-inset-top);width: 100%;z-index: 100;background-color: rgb(50,112,222);">
          提升洪荒之力
        </x-header>
      </div>
      <div class="body-header">
        <div class="body-content">
          <div style="margin-top: 10px;height: 60px;width: 100%;text-align: left;">
            <div style="width: 80%;height: 55px;background-color: rgb(42,124,246);margin-left: 10%;border-radius: 8px;">
              <img src="../../assets/power/star.png" width="30px" height="30px" style="margin-top: 12.5px;margin-left: 15px;float: left;"/>
              <p style="color: white;font-size: larger;line-height: 55px;margin-left: 15px;float: left;">洪荒之力</p>
              <p style="color: white;line-height: 55px;float: right;margin-right: 15px;"> 200</p>
            </div>
          </div>
        </div>
        <div style="width: 80%; margin-left: 10%;">
          <divider style="margin-left: 10%;width: 80%;">任务列表</divider>
        </div>
      </div>
    </view-box>
  </div>
</template>

<script>
  import {ViewBox, XHeader, Divider} from 'vux'

  export default {
    name: 'AddPowerHome',
    components: {
      ViewBox,
      XHeader,
      Divider
    }
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

  .body-header {
    position: absolute;
    top: 45px;
    height: 0px;
    width: 100vw;
    background-color: rgb(50, 112, 222);
  }

  .body-content {
    margin-top: 0px;
  }

</style>
