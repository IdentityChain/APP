<template>
    <div class="coin" v-if="showStatus" v-on:click.stop="clickCoin">
      <img class="img" :class="isMiss" :style="{'transform': 'rotate('+ jiaodu + 'deg)'}" src="../../assets/wakuang/coin.png"/>
    </div>
</template>

<script>
  import { XButton } from 'vux'
  export default {
    components: {XButton},
    mounted: function () {
      this.jiaodu = (Math.random() * 1000)
    },
    data () {
      return {
        jiaodu: '50',
        showStatus: true,
        isMiss: ''
      }
    },
    methods: {
      clickCoin: function () {
        console.log('click img')
        this.isMiss = 'miss'
        var _that = this
        setTimeout(function () {
          _that.showStatus = false
        }, '900')
      }
    }
  }
</script>

<style scoped>
  .img {
    height: 50px;
    width: 50px;
  }
  .miss {
    animation: miss1 0.5s  ;
    animation-fill-mode: forwards;
  }
  .coin {
    width: 50px;
    height: 50px;
    /*iphone上悬浮效果会影响定位,建议关闭*/
    animation: heart 1.3s ease-in-out 0.7s infinite alternate;
  }
  @keyframes heart{
    from{
      transform: scale(1);
      /*transform:translate(0,0);*/
    }
    to{
      transform: scale(1.2);
      /*transform:translate(0,8px);*/
    }
  }
  @keyframes miss1{
    0%{
      opacity: 1;
    }
    100%{
      opacity: 0;
    }
  }
</style>
