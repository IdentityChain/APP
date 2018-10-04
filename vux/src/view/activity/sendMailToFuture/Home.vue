<template>
  <div style="height: 100%">
    <view-box body-padding-top="0px" class="bg">
      <!--<slot name="probtn" style="z-index: 99"></slot>-->
      <program-button :name="name" @close-pro="backList"  @open-about="openAbout"></program-button>
      <div style="z-index: 80">
        <div v-if="currentPage === 'home'">
          <div style="margin-left: 5px;margin-top: 15px;color: white;">
            <h1>给未来写封信</h1>
            <h3>已寄出 22343 封</h3>
            <div class="mailBox" v-on:click.stop="vieMail">
              <img src="../../../assets/activity/sendMail/view.png" style="height: 40px;width: 40px;margin-left: 6px;"/>
              <p style="display: block;font-size: small;">公开信箱</p>
            </div>
            <div class="writeMyMail" v-on:click="writeMail">
              <img src="../../../assets/activity/sendMail/sey-write-a.png" style="height: 40px;width: 40px;margin-left: 16px;"/>
              <p style="display: block;font-size: small;">撰写我的信件</p>
            </div>
          </div>
        </div>
        <div v-if="currentPage === 'about'">
          <div style="margin-left: 5px;margin-top: 15px;color: white;">
            <h1>关于</h1>
            <p style="font-style:italic;margin-top: 10px;padding-bottom: 60px;">
              两年前，我给未来的自己写了一封信。<br>
              我从来没有想过，给自己写信会有那么复杂的心情，<br>
              紧张、焦虑、纠结<br>
              坐在那里开始回想过往的遗憾，写着当下的生活，
              想着未来希望成为的样子，有没有实现偏执七年
              的梦想<br>
              <br>
              当我快要写完的时候，眼泪已经忍不住了。<br>
              记得上一次这样，还是给学生时期的女朋友写
              毕业留言本<br>
              <br>
              两年过去了，我几乎忘了当时写的具体内容，也
              忘了什么时候我可能会收到<br>
              但当某一天，突然看到的时候，应该会泪崩吧
              <br><br>
              多年以后，愿我也不负所期。
            </p>
          </div>
        </div>
        <div v-if="currentPage === 'mailList'">
          <scroller lock-x scrollbar-y use-pullup height="-50px" @on-pullup-loading="load1" ref="demo1"
                    :pullup-config="pullUpConfig" style="margin: 5px 10px 5px 10px;color: white;padding-bottom: 20px;">
            <div class="mail-list">
              <p @click="currentPage = 'home'">返回</p>
              <div class="view-mail" v-for="item in publicMailList">
                <p style="margin-top: 8px; margin-bottom: 8px; color: gray">{{item.content}}</p>
                <br>
                <p style="text-align: right;font-size: 14px;color: #999999;margin-right: 10px;margin-bottom: 10px;">
                  寄往 {{item.toTime}} 年<br>
                  寄信人: {{item.sender}}
                </p>
              </div>
              <br>
            </div>
          </scroller>
        </div>
        <div v-transfer-dom>
          <popup v-model="showEdit" height="100%" :is-transparent=false>
            <!-- group already has a top border, so we need to hide header's bottom border-->
              <!--查看公开信件-->

          </popup>
        </div>
      </div>
    </view-box>
  </div>
</template>

<script>
  import {XButton, XHeader, ViewBox, Scroller, PopupHeader, TransferDom, Popup, LoadMore, Group} from 'vux'
  import ProgramButton from '../components/ProgramHeader'
  export default {
    directives: {
      TransferDom
    },
    components: {
      ProgramButton,
      Scroller,
      ViewBox,
      XButton,
      XHeader,
      PopupHeader,
      Popup,
      Group,
      LoadMore
    },
    data () {
      return {
        showEdit: false,
        showHome: true,
        currentPage: 'home',
        oldPage: '',
        scrollTop: 0,
        pullUpConfig: {
          content: '上拉加载更多',
          downContent: '松开进行加载',
          upContent: '上拉加载更多',
          loadingContent: '加载中...'
        },
        title: '给未来写封信',
        publicMailList: [
          {
            sender: 'xxx@163.com',
            toTime: '2020',
            content: '1两年前，我给未来的自己写了一封信。1两年前，我给未来的自己写了一封信。1两年前，我给未来的自己写了一封信。1两年前，我给未来的自己写了一封信。1两年前，我给未来的自己写了一封信。1两年前，我给未来的自己写了一封信。1两年前，我给未来的自己写了一封信。1两年前，我给未来的自己写了一封信。1两年前，我给未来的自己写了一封信。1两年前，我给未来的自己写了一封信。1两年前，我给未来的自己写了一封信。'
          },
          {
            sender: 'xxx@163.com',
            toTime: '2020',
            content: '2两年前，我给未来的自己写了一封信。1两年前，我给未来的自己写了一封信。1两年前，我给未来的自己写了一封信。1两年前，我给未来的自己写了一封信。1两年前，我给未来的自己写了一封信。1两年前，我给未来的自己写了一封信。1两年前，我给未来的自己写了一封信。1两年前，我给未来的自己写了一封信。1两年前，我给未来的自己写了一封信。1两年前，我给未来的自己写了一封信。1两年前，我给未来的自己写了一封信。'
          },
          {
            sender: 'xxx@163.com',
            toTime: '2020',
            content: '3两年前，我给未来的自己写了一封信。1两年前，我给未来的自己写了一封信。1两年前，我给未来的自己写了一封信。1两年前，我给未来的自己写了一封信。1两年前，我给未来的自己写了一封信。1两年前，我给未来的自己写了一封信。1两年前，我给未来的自己写了一封信。1两年前，我给未来的自己写了一封信。1两年前，我给未来的自己写了一封信。1两年前，我给未来的自己写了一封信。1两年前，我给未来的自己写了一封信。'
          },
          {
            sender: 'xxx@163.com',
            toTime: '2020',
            content: '4两年前，我给未来的自己写了一封信。1两年前，我给未来的自己写了一封信。1两年前，我给未来的自己写了一封信。1两年前，我给未来的自己写了一封信。1两年前，我给未来的自己写了一封信。1两年前，我给未来的自己写了一封信。1两年前，我给未来的自己写了一封信。1两年前，我给未来的自己写了一封信。1两年前，我给未来的自己写了一封信。1两年前，我给未来的自己写了一封信。1两年前，我给未来的自己写了一封信。'
          },
          {
            sender: 'xxx@163.com',
            toTime: '2020',
            content: '5两年前，我给未来的自己写了一封信。'
          }
        ]
      }
    },
    mounted () {
    },
    methods: {
      vieMail () {
        // this.showEdit = true
        this.currentPage = 'mailList'
        // this.$nextTick(() => {
        //   this.$refs.demo1.reset({top: 0})
        // })
      },
      writeMail () {
        this.$router.push({name: 'wirteMail'})
      },
      closeEdit () {
        this.showEdit = false
      },
      backList () {
        this.$emit('back-list')
      },
      openAbout () {
        console.log('open about')
      },
      load1 () {
        setTimeout(() => {
          this.publicMailList = this.publicMailList.concat(this.publicMailList)
          setTimeout(() => {
            this.$refs.demo1.donePullup()
          }, 100)
        }, 1000)
      }
    }
  }
</script>

<style scoped>
  .bg {
    background-image:url(../../../assets/activity/sendMail/mailbg.png);
    background-repeat:no-repeat;
    background-size:100% 100%;
    -moz-background-size:100% 100%;
    position: fixed;
    height: 100%;
    width: 100%;
    background-attachment: fixed;
  }
  .mail-list {
    position: absolute;
    top: 50px;
  }
  .view-mail {
    background-image: url(../../../assets/activity/sendMail/view-bg.jpg);
  }
  .mailBox {
    position: absolute;
    bottom: 20px;
    left: 30px;
  }
  .writeMyMail {
    position: absolute;
    bottom: 20px;
    right: 30px;
  }

</style>
