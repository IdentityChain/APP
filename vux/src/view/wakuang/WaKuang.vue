<template>
  <div style="width: 100%; height:100%; background-color: white">

    <!--主显示面板-->
    <div class="topbg">
      <!--我的算力-->
      <div class="wodesuanli" >
        <div style="float: left;padding-left: 4px">成就点</div>
        <div style="padding-right: 3px">
          {{chengjiudian}}
        </div>
      </div>

      <!--邀请-->
      <div class="yaoqing anniu">
        <router-link to="/login" >
          <img class="yaoqingimg" src="../../assets/wakuang/yaoqing.png"/>
        </router-link>
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
              v-if="coinItem.status === '可采集'"
              :addISC="coinItem.addISC"
              :isclID="coinItem.isclId"
              @getCoin="doGetCoin"
              class="coin"
              :style="{'top': (Math.random() * 100 * 2) + 'px', 'left': (Math.random() * 100 * 2.5) + 'px'}">
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
    <div style="height: auto;width:100%;position: absolute;top:503px;background-color: white">
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
              <div class="mingci" :class="mingciCSS(listItem.ranking)">
                {{listItem.ranking}}
              </div>
            </td>
            <td>{{listItem.name}}</td>
            <td style="color: mediumslateblue">{{listItem.calculateValue}}</td>
          </tr>
          </tbody>
        </x-table>
      </div>
    </div>
  </div>
</template>

<script>
  import {MarqueeItem, XTable} from 'vux'
  import Coin from './GetCoin'
  export default {
    mounted: function () {
      this.init()
    },
    name: 'wakuang',
    components: {
      Coin,
      MarqueeItem,
      XTable
    },
    data () {
      return {
        chengjiudian: 0,
        gonggao: '',
        totalPeople: 10000,
        coins: [],
        paihangList: []
      }
    },
    methods: {
      init () {
        console.log('进入Init 方法')
        let user = JSON.parse(window.localStorage.getItem('User'))
        console.log(user)
        this.chengjiudian = user.calculateValue
        this.getCoinRequest(user.userId)
        this.getPaihangBang()
      },
      doGetCoin (iscLID) {
        console.log('获取币ID: ' + iscLID)
        this.$api.calculateApi.checkCalculate(iscLID).then(result => {
          if (result.data.success) {
            console.log('获取成功')
          } else {
            this.$vux.toast.show({
              type: 'text',
              text: result.data.message
            })
          }
        })
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
      },
      getCoinRequest (userId) {
        this.$api.calculateApi.getCalculateLog(userId).then(result => {
          console.log(result)
          if (result.data.success) {
            this.coins = result.data.data
          } else {
            this.$vux.toast.show({
              type: 'cancel',
              text: result.message
            })
          }
        })
      },
      getPaihangBang () {
        this.$api.calculateApi.getCalculateTop100().then(result => {
          if (result.data.success) {
            this.paihangList = result.data.data
          } else {
            this.$vux.toast.show({
              type: 'text',
              text: result.data.message
            })
          }
        })
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
    background-image: url(../../assets/wakuang/bg_panel.png);
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
</style>
