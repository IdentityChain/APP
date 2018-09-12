<template>
  <div style="width: 100%; height:100%; background-color: white">

    <!--主显示面板-->
    <div class="topbg">
      <!--我的算力-->
      <div class="wodesuanli coin" >
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
        <coin v-for="(coinItem,index) in coins"
              :key="index"
              :addISC="coinItem.addISC"
              :isclID="coinItem.isclID"
              @getCoin="doGetCoin"
              class="coin"
              :style="{'top': (Math.random() * 100 * 2) + 'px', 'left': (Math.random() * 100 * 2) + 'px'}">
        </coin>
      </div>

    </div>

    <!--公告-->
    <div class="gonggao">
      <div class="ggtitle">
        &nbsp;公&nbsp;告&nbsp;
      </div>
      <!--<p id="scrollobj" style="white-space:nowrap;overflow:hidden;width:400px;margin: auto;" onmouseover="stop()" onmouseout="start()"><span style="padding-left: 210px;">欢迎您登录拜博医疗口腔集团内部网</span></p>-->
      <!--<div class="rightJS li"><div>暂无公告1暂无公告暂无公3告暂无公告</div></div>-->
      <!--<marquee direction="left" scrollamount="3" style="padding-top: 5px; background-color: #E8F4F6;z-index: 50">-->
        <!--{{gonggao === '' ? '暂无公告暂无公告暂无公告暂无公告暂无公告暂无公告暂无公告暂无公告' : gonggao}}-->
        <!--&lt;!&ndash;<marquee-item  class="align-mißddle">{{gonggao === '' ? '暂无公告暂无公告暂无公告暂无公告暂无公告暂无公告暂无公告暂无公告' : gonggao}}</marquee-item>&ndash;&gt;-->
        <!--&lt;!&ndash;<marquee-item  class="">{{gonggao === '' ? '暂无公告2' : gonggao}}</marquee-item>&ndash;&gt;-->
        <!--&lt;!&ndash;<marquee-item  class="align-mißddle">111111111111111111111111111111111111111111111111111</marquee-item>&ndash;&gt;-->
      <!--</marquee>-->
    </div>

    <!--排行榜-->
    <div style="height: 2000px;width:100%;position: absolute;top:503px;background-color: white">
      <div class="paihangbang">
        <div class="phb-title">
          ▍封神榜
        </div>
        <div class="jumin">
          <span>居民人数</span>
          <span style="text-align: right;position: absolute; right: 0px;padding-right: 5px;">{{totalPeople}}</span>
        </div>
        <br style='clear:both'/>
      </div>
      <br style='clear:both'/>
      <div style="position: absolute;top: 40px;left: 0; width: 100%">
        <x-table :cell-bordered="false" :content-bordered="false">
          <thead style="color: mediumslateblue">
          <tr>
            <th>名次</th>
            <th>用户名</th>
            <th>成就点</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="listItem in paihangList">
            <td>
              <div class="mingci" :class="mingciCSS(listItem.no)">
                {{listItem.no}}
              </div>
            </td>
            <td>{{listItem.name}}</td>
            <td style="color: mediumslateblue">{{listItem.chengjiu}}</td>
          </tr>
          </tbody>
        </x-table>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>
        1<br>

      </div>
    </div>
  </div>
</template>

<script>
  import {MarqueeItem, XTable} from 'vux'
  import Coin from '../../components/home/GetCoin'
  export default {
    mounted: function () {
      this.gundong()
      if (window.indexedDB) {
        console.log("I'm in WKWebView!")
      } else {
        console.log("I'm in UIWebView")
      }
    },
    name: 'wakuang',
    components: {
      Coin,
      MarqueeItem,
      XTable
    },
    data () {
      return {
        chengjiudian: 1000,
        gonggao: '',
        totalPeople: 10000,
        coins: [
          {
            isclID: 1,
            addISC: 1.235
          }],
        paihangList: [
          {
            no: 1,
            name: 'xxxx',
            chengjiu: 1000
          },
          {
            no: 2,
            name: 'xxxx',
            chengjiu: 1000
          },
          {
            no: 3,
            name: 'xxxx',
            chengjiu: 1000
          },
          {
            no: 4,
            name: 'xxxx',
            chengjiu: 1000
          }
        ]
      }
    },
    methods: {
      doGetCoin (iscLID) {
        console.log('获取币ID: ' + iscLID)
      },
      mingciCSS: function (item) {
        if (item === 1) {
          return 'first'
        } else if (item === 2) {
          return 'second'
        } else if (item === 3) {
          return 'third'
        } else {
          return 'other'
        }
      }
    },
    computed: {}
  }
</script>

<style scoped>
  * {
    -webkit-backface-visibility: hidden;
  }
  .topbg {
    /*background-image: url(../../assets/wakuang/bg_panel.png);*/
    background-repeat: no-repeat;
    background-size: 100% 100%;
    -moz-background-size: 100% 100%;
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
    right: 10px;
    height: 25px;
    width: 110px;
    text-align: right;
    padding-right: 5px;
    color: white;
  }
  .li {
    /*float: left;*/
    width: auto;
    padding-top: 5px;
    /*margin: 1% 10%;*/
    /*height: 20px;*/
    /*border-radius: 0.5em;*/
    box-shadow: 0 0em 0em;
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

  .ggtitle {
    color: white;
    position: absolute;
    left: 15px;
    top: 5px;
    border-radius: 10px;
    background-color: mediumslateblue;
    height: 30px;
    width: 57px;
  }

  .gonggao {
    background-color: #E8F4F6;
    position: absolute;
    width: 100%;
    height: 40px;
    color: mediumslateblue;
    font-size: larger;
    top: 463px;
    padding-left: 100px;
    padding-right: 5px;
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

  .paihangbang {
    background-color: white;
    width: 100%;
    height: auto;
    position: absolute;
  }

  .phb-title {
    position: absolute;
    width: 80px;
    top: 5px;
    height: auto;
    color: mediumslateblue;
    padding-left: 20px;
  }

  .jumin {
    position: absolute;
    right: 10px;
    width: 150px;
    padding-left: 5px;
    top: 5px;
    color: white;
    border-radius: 15px;
    background-color: mediumslateblue;
  }

  .mingci {
    height: 30px;
    width: 30px;
    border-radius: 15px;
    margin: auto;
    color: white;
    font-size: large;
    line-height: 30px;
  }

  .first {
    background-color: #ffbe00;
  }

  .second {
    background-color: lightgrey;
  }
  .third {
    background-color: burlywood;
  }
  .other {
    background-color: #888888;
  }
  .coin {
    -webkit-animation: heart 1.3s ease-in-out 0.7s infinite alternate;
  }
  @-webkit-keyframes heart{
    from{
      /*transform: scale(1);*/
      /*transform:translate(0,0);*/
      /*top:0;*/
      /*left: 0;*/
      /*height: 50px;*/
      /*width: 50px;*/
      /*transform: scale(1);*/
      -webkit-transform: scale(1);
    }
    to{
      /*top:-10px;*/
      /*left: -10px;*/
      /*height: 70px;*/
      /*width: 70px;*/
      /*transform: scale(1);*/
      /*transform: scale(1.2);*/
      -webkit-transform: scale(1.2);
      /*transform:translate(0,8px);*/
    }
  }
</style>
