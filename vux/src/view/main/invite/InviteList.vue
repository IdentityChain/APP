<template>
  <div>
    <page-view>
      <x-header slot="header" :left-options="{showBack: true, backText: ''}"
                style="position: fixed;left:0;top: env(safe-area-inset-top);width: 100%;z-index: 100;background-color: rgb(50,112,222);">
        邀请好友列表
      </x-header>

      <div slot="body" style="width: 100vw;height: calc(100vh - 100px);">
        <div style="height: 80px;background: white;z-index: 200">
          <div style="margin-top: 0;height: 60px;width: 100%;text-align: left;background: white">
            <div style="margin-top: 10px;width: 80%;height: 55px;background-color: rgb(42,124,246);margin-left: 10%;border-radius: 8px;">
              <img src="../../../assets/my/peoples.png" width="30px" height="30px" style="margin-top: 12.5px;margin-left: 15px;float: left;"/>
              <p style="color: white;font-size: larger;line-height: 55px;margin-left: 15px;float: left;">累积邀请人数</p>
              <p style="color: white;line-height: 55px;float: right;margin-right: 15px;"> {{totalCount}}</p>
            </div>
          </div>
        </div>

        <div v-if="totalCount > 0" style="height: calc(100vh - 180px);width: 100vw; position: absolute;top: 80px;">
          <scroller style="top: 0;"
                    :noDataText="loadingTips"
                    ref="my_scroller"
                    :on-infinite="infinite"
          >
            <!--<div v-for="(item, index) in items" class="row" :class="{'grey-bg': index % 2 == 0}">-->
              <!--{{ item }}-->
            <!--</div>-->
            <div style="width: 100%;text-align: center">
              <x-table :cell-bordered="false" :content-bordered="false" style="border: none;width: 80vw;margin-left: 10vw">
                <thead style="color: grey;border: none;font-size: small;background-color: rgb(247, 247, 247);line-height: 26px;">
                <tr>
                  <th style="width: 10vw;">序号</th>
                  <th style="width: 35vw;">昵称</th>
                  <th style="width: 35vw;">加入时间</th>
                </tr>
                </thead>
                <tbody style="">
                <tr v-for="(listItem,index) in items" style="height: 30px;line-height: 30px;">
                  <td>{{index + 1}}</td>
                  <td>
                    {{listItem.nickName}}
                  </td>
                  <td>{{listItem.createTime}}</td>
                </tr>
                </tbody>
              </x-table>
            </div>
            <br>
          </scroller>
        </div>

      </div>

    </page-view>
  </div>

</template>

<script>
  import PageView from '../../../components/common/PageBox'
  import {XHeader, XTable} from 'vux'
  export default {
    components: {PageView, XHeader, XTable},
    name: 'InviteList',
    data () {
      return {
        totalCount: 0,
        items: [],
        loadingTips: '没有更多数据',
        pageNum: 0,
        pageSize: 20,
        noData: false
      }
    },
    mounted () {
      this.init()
    },
    methods: {
      init () {
        let userObj = this.$db.get('User')
        this.$api.inviteApi.getInvitaCountRequest(userObj.userId).then(result => {
          if (result.data.success) {
            this.totalCount = result.data.data
          }
        })
      },
      sendRequest (done) {
        if (this.noData) {
          setTimeout(() => {
            done(true)
          }, 1500)
        } else {
          let userObj = this.$db.get('User')
          this.$api.inviteApi.getUserInvitaInfoRequest(userObj.userId, this.pageNum, this.pageSize).then(result => {
            this.$vux.loading.hide()
            this.pageNum += 1
            if (result.data.success) {
              if (result.data.data.length > 0) {
                this.items = this.items.concat(result.data.data)
                done()
              } else {
                this.noData = true
                done(true)
              }
            }
          })
        }
      },
      infinite (done) {
        if (this.noData) {
          setTimeout(() => {
            done(true)
          }, 1500)
          return
        }
        setTimeout(() => {
          this.sendRequest(done)
        }, 500)
      }
    }
  }
</script>

<style scoped>
  html, body {
    margin: 0;
  }
  * {
    box-sizing: border-box;
  }
  .row {
    width: 100%;
    height: 50px;
    padding: 10px 0;
    font-size: 16px;
    line-height: 30px;
    text-align: center;
    color: #444;
    background-color: #fff;
  }
  .grey-bg {
    background-color: #eee;
  }
  .header {
    position: fixed;
    top: 0;
    left: 0;
    height: 44px;
    width: 100%;
    box-shadow: 0 2px 10px 0 rgba(0,0,0,0.1);
    background-color: #fff;
    z-index: 1000;
    color: #666;
  }
  .header > .title {
    font-size: 16px;
    line-height: 44px;
    text-align: center;
    margin: 0 auto;
  }
</style>
