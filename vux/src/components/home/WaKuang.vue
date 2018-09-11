<template>
  <div style="width: 100%; height:100%; background-color: #EFEFEF">

    <!--主显示面板-->
    <div class="topbg">
      <!--我的算力-->
      <div class="wodesuanli">
        <div style="float: left;padding-left: 4px">成就点</div>
        <div style="padding-right: 3px">
          {{chengjiudian}}
        </div>
      </div>

      <!--邀请-->
      <div class="yaoqing anniu">
        <img class="yaoqingimg" src="../../assets/wakuang/yaoqing.png"/>
      </div>

      <!--钱包-->
      <div class="qianbao anniu">
        <img src="../../assets/wakuang/wallet.png" style="height: 50px;width: 50px;"/>
      </div>

      <!--任务-->
      <div class="renwu anniu">
        <img src="../../assets/wakuang/renwu.png" style="height: 50px;width: 50px;"/>
      </div>

      <!--待拾取的ISC币-->
      <div class="coins">
        <!--<coin class="coin" style="top:40px;left: 150px"></coin>-->
        <!--<coin class="coin" style="top:40px;left: 150px"></coin>-->
        <!--<coin class="coin" style="top:20px;left: 80px"></coin>-->
        <!--<coin class="coin" style="top:50px;left: 110px"></coin>-->
        <!--<coin class="coin" style="top:70px;left: 200px"></coin>-->
        <coin class="coin" :style="{'top': (Math.random() * 100 - 50) + 'px', 'left': (Math.random() * 100 + 20) + 'px'}"></coin>
        <coin class="coin" :style="{'top': (Math.random() * 100 - 50) + 'px', 'left': (Math.random() * 100 + 20) + 'px'}"></coin>
        <coin class="coin" :style="{'top': (Math.random() * 100 - 50) + 'px', 'left': (Math.random() * 100 + 20) + 'px'}"></coin>
        <coin class="coin" :style="{'top': (Math.random() * 100 - 50) + 'px', 'left': (Math.random() * 100 + 20) + 'px'}"></coin>
        <coin class="coin" :style="{'top': (Math.random() * 100 - 50) + 'px', 'left': (Math.random() * 100 + 20) + 'px'}"></coin>
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
  import Coin from '../../components/home/GetCoin'
  export default {
    name: 'wakuang',
    components: {
      Coin
    },
    data () {
      return {
        chengjiudian: 1000
      }
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
    top: calc(40px + env(safe-area-inset-top));
    right: 15px;
    height: 25px;
    width: 110px;
    text-align: right;
    padding-right: 5px;
    color: white;
  }
  .yaoqing {
    top: calc(390px);
    right: 20px;
  }
  .yaoqingimg {
    height: 50px;
    width: 50px;
  }
  .anniu {
    height: 50px;
    width: 50px;
    position: absolute;
  }
  .qianbao {
    top: calc(390px);
    left: 20px;
  }
  .renwu {
    top: calc(390px);
    left: 100px;
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
  .coin {
    position: absolute;
  }
</style>
