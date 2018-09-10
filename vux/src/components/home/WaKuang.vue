<template>
  <div style="width: 100%; height:100%; background-color: #EFEFEF">

    <!--主显示面板-->
    <div class="topbg">
      <!--我的算力-->
      <div class="wodesuanli">
        <div style="float: left;padding-left: 4px">算力</div>
        <div style="padding-right: 3px">
          10000
        </div>
      </div>

      <!--邀请-->
      <div class="yaoqing">
        <button>邀请</button>
      </div>

      <!--钱包-->
      <div class="qianbao">
        <button>钱包</button>
      </div>

      <!--任务-->
      <div class="renwu">
        <button>任务</button>
      </div>

      <!--待拾取的ISC币-->
      <div class="coins">

      </div>

    </div>

    <!--公告-->
    <div class="gonggao" style="width: 100%; height: 40px; position: absolute;top: 463px; background-color: #E8F4F6;">
    </div>

    <!--排行榜-->
    <div style="height: 1000px;width:100%;position: absolute;top:503px;background-color: #EFEFEF">
      111
    </div>
  </div>
</template>

<script>
  export default {
    name: 'wakuang',
    data () {
      return {}
    },
    methods: {
    }
  }
</script>

<style scoped>
  .topbg {
    background-image:url(../../assets/wakuang/bg_panel.png);
    background-repeat:no-repeat;
    background-size:100% 100%;
    -moz-background-size:100% 100%;
    position: absolute;
    top: 0;
    left: 0;
    height: 500px;
    width: 100%;
    background-attachment: fixed;
  }
  .wodesuanli {
    border-radius: 15px;
    background-color: mediumslateblue;
    position: absolute;
    top: calc(30px + env(safe-area-inset-top));
    right: 15px;
    height: 25px;
    width: 110px;
    text-align: right;
    padding-right: 5px;
    color: white;
  }
  .yaoqing {
    position: absolute;
    top: calc(390px);
    right: 20px;
    height: 50px;
    border-radius: 30px;
    width: 50px;
    background-color: #ec8b89;
  }
  .qianbao {
    position: absolute;
    top: calc(390px);
    left: 20px;
    height: 50px;
    border-radius: 30px;
    width: 50px;
    background-color: #ec8b89;
  }
  .renwu {
    position: absolute;
    top: calc(390px);
    left: 100px;
    height: 50px;
    border-radius: 30px;
    width: 50px;
    background-color: #ec8b89;
  }
  .gonggao {
    background-image:url(../../assets/wakuang/gonggao.png);
    background-repeat:no-repeat;
    background-size:100% 100%;
    -moz-background-size:100% 100%;
    position: absolute;
  }
  .coins {
    position: absolute;
    top: 100px;
    left: 10%;
    height: 250px;
    width: 80%;
  }
</style>
