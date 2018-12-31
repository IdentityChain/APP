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
              <p style="color: white;line-height: 55px;float: right;margin-right: 15px;"> {{personal.power}}</p>
            </div>
          </div>
        </div>
        <div style="width: 90%; margin-left: 5%;">
          <divider style="margin-left: 10%;width: 80%;">任务列表</divider>
          <tab :line-width=2 active-color='rgb(50, 112, 222)' v-model="TaskList.listGroupIndex">
            <tab-item class="vux-center" v-for="(item,index) in GroupList" :selected="TaskList.listGroupName === item"  @click.native="changeGroup(item)" :key="index">{{item}}</tab-item>
          </tab>
          <div style="width: 100%;height: calc(100vh - 220px) ; overflow-x:hidden;overflow-y:auto;">
          <swiper :height="swiperHeight" ref="swiper1" :show-dots=false v-model="TaskList.listGroupIndex">
            <!--<swiper-item class="black" >-->
              <!--&lt;!&ndash;高分任务&ndash;&gt;-->
              <!--<div class="tab-swiper vux-center">-->
                <!--&lt;!&ndash;<task-list v-for="(item,index) in HighScoreTaskList" :key="index" :task-obj="item" @do-task="clickTaskBtn">&ndash;&gt;-->
                <!--&lt;!&ndash;</task-list>&ndash;&gt;-->
              <!--</div>-->
            <!--</swiper-item>-->
            <swiper-item class="black" v-for="(item,index) in GroupList" :key="index">
              <!--日常任务-->
              <div class="tab-swiper vux-center">
                <task-list v-for="(item,index) in currentList" :key="index" :task-obj="item" @do-task="clickTaskBtn">
                </task-list>
              </div>
            </swiper-item>
            <!--<swiper-item class="black" >-->
              <!--&lt;!&ndash;成就奖励&ndash;&gt;-->
              <!--<div class="tab-swiper vux-center">-->
              <!--</div>-->
            <!--</swiper-item>-->
          </swiper>
          </div>
        </div>
      </div>
    </view-box>
  </div>
</template>

<script>
  import {ViewBox, XHeader, Divider, Tab, TabItem, Swiper, SwiperItem} from 'vux'
  import TaskList from '../../components/AddPower/TaskList'
  export default {
    name: 'AddPowerHome',
    components: {
      ViewBox,
      XHeader,
      Swiper,
      SwiperItem,
      Divider,
      TaskList,
      Tab,
      TabItem
    },
    data () {
      return {
        // 用户ISC数目
        personal: {
          showCenter: false,
          power: 0,
          iscElm: 0,
          account: '',
          notice: ''
        },
        // GroupList: ['高分任务', '日常任务', '成就奖励'],
        GroupList: ['日常任务'],
        TaskList: {
          listGroupIndex: 0,
          listGroupName: '高分任务'
        },
        currentList: [1, 2, 1, 1],
        HighScoreTaskList: [
          {
            aaimg_path: 'webchat.png',
            aagrayImgPath: 'webchat-done.png',
            aucompleteRate: 1,
            aatitle: '关注微信公众号',
            aacontent: '关注微信公众号XXX即可获取奖励',
            toolType: 'text',
            toolText: '+10'
          },
          {
            aaimg_path: 'webchat.png',
            aagrayImgPath: 'webchat-done.png',
            aucompleteRate: 1,
            aatitle: '关注微信公众号',
            aacontent: '关注微信公众号XXX即可获取奖励',
            toolType: 'text',
            toolText: '+10'
          },
          {
            aaimg_path: 'webchat.png',
            aagrayImgPath: 'webchat-done.png',
            aucompleteRate: 0,
            aatitle: '关注微信公众号',
            aacontent: '关注微信公众号XXX即可获取奖励',
            toolType: 'text',
            toolText: '+10'
          },
          {
            aaimg_path: 'webchat.png',
            aagrayImgPath: 'webchat-done.png',
            aucompleteRate: 0,
            aatitle: '关注微信公众号',
            aacontent: '关注微信公众号XXX即可获取奖励',
            toolType: 'button',
            toolEvent: 'checkin',
            toolText: '签到'
          }
        ]
      }
    },
    methods: {
      init () {
        this.loadDailyTash(1)
        const user = this.$db.get('User')
        this.personal.power = user.calculateValue
        this.personal.iscElm = user.iscCoin
        this.totalElm.startNum = user.iscCoin
        this.totalElm.endNum = user.iscCoin
        this.personal.account = user.account
      },
      loadDailyTash (pageNum) {
        const user = this.$db.get('User')
        // this.personal.power = user.calculateValue
        // this.personal.iscElm = user.iscCoin
        // this.totalElm.startNum = user.iscCoin
        // this.totalElm.endNum = user.iscCoin
        // this.personal.account = user.account
        this.$api.calculateApi.getAchievementDayRequest('EVERYDAY', user.userId, pageNum, 10).then(result => {
          if (result.data.success) {
            this.currentList = result.data.data
          }
        })
      },
      changeGroup (index) {
        switch (index) {
          case '高分任务':
            this.currentList = [1, 1, 1, 1]
            break
          case '日常任务':
            this.currentList = [1, 1]
            break
          case '成就奖励':
            this.currentList = [1, 1, 1, 1, 1, 1, 1]
            break
        }
        this.$nextTick(() => {
          this.$refs.swiper1.xheight = this.swiperHeight
        })
      },
      clickTaskBtn (event) {
        console.log(event)
      },
      sortList () {
        this.HighScoreTaskList.sort(function (a, b) {
          if (a.aucompleteRate < b.aucompleteRate) {
            return -1
          } else {
            return 1
          }
        })
      }
    },
    computed: {
      swiperHeight: function () {
        return this.currentList.length * 80 + 40 + 'px'
      }
    },
    mounted: function () {
      this.sortList()
      this.init()
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
    padding-top: env(safe-area-inset-top);
    background-color: rgb(50, 112, 222);
  }

  .body-content {
    margin-top: 0px;
  }

</style>
