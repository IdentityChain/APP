<template>
  <div style="height: 100%; width: 100%;background-color: white">
    <view-box body-padding-top="46px" body-padding-bottom="55px">
      <div class="header-css" slot="header"
           style="position: fixed;height: calc(40px + env(safe-area-inset-top));width:100%;left: 0;top: 0;background-color: rgb(50,112,222);z-index: 100">
        <x-header :left-options="{showBack: true, backText: ''}"
                  style="position: fixed;left:0;top: env(safe-area-inset-top);width: 100%;z-index: 100;background-color: rgb(50,112,222);">排行榜
        </x-header>
      </div>
      <div class="body-header">
        <div class="top-bang">
          <div>
            星球总榜
          </div>
          <div class="x-table" style="position: absolute;top: 40px;left: 0; width: 100%">
            <x-table :cell-bordered="false" :content-bordered="false" style="border: none">
              <thead style="color: grey;border: none;font-size: small;background-color: rgb(247, 247, 247);line-height: 26px;">
              <tr>
                <th>名次</th>
                <th>用户名</th>
                <th>洪荒之力</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="listItem in paihangList">
                <td>
                  <div class="mingci" :class="mingciCSS(listItem.ranking)">
                    {{(listItem.ranking === 1 || listItem.ranking === 2 || listItem.ranking === 3) ? ' ' : listItem.ranking}}
                  </div>
                </td>
                <td>{{listItem.name}}</td>
                <td>{{listItem.calculateValue}}</td>
              </tr>
              </tbody>
            </x-table>
          </div>
        </div>
      </div>
    </view-box>
  </div>
</template>

<script>
  import {ViewBox, XHeader, XTable} from 'vux'

  export default {
    name: 'TopBang',
    components: {
      ViewBox,
      XHeader,
      XTable
    },
    created: function () {
      this.getPaihangBang()
    },
    data () {
      return {
        paihangList: []
      }
    },
    methods: {
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
    height: calc(8vh + 15vw);
    width: 100vw;
    background-color: rgb(50, 112, 222);
  }

  .top-bang {
    position: absolute;
    top: calc(8vh + 5vw);
    left: 10vw;
    background-color: white;
    height: 80vh;
    width: 80vw;
    border-radius: 5vw;
    text-align: center;
    padding-top: 2vw;
  }

  .x-table /deep/ .vux-table {
    /*line-height: 30px;*/
  }
  .mingci {
    height: 26px;
    width: 26px;
    border-radius: 13px;
    margin: auto;
    color: white;
    font-size: medium;
    line-height: 26px;
  }
  .first {
    background:url('../../assets/top/first.png') no-repeat center;
  }
  .second {
    background:url('../../assets/top/second.png') no-repeat center;  }
  .third {
    background:url('../../assets/top/third.png') no-repeat center;  }
  .other {
    color: black;
  }

</style>
