<template>
  <div style="height: 100%;">
    <view-box body-padding-top="env(safe-area-inset-top)" body-padding-bottom="env(safe-area-inset-bottom)" class="bg">
      <div style="z-index: 80">
        <div v-if="showPop === false" :style="{'margin-top': showTitle ? '0px' : '46px'}">
          <div v-if="currentPage === 'home'">
            <div style="margin-left: 5px;color: white;">
              <!--<h1>给未来写封信</h1>-->
              <br>
              <h3 v-on:click.stop="testAxios">已寄出 {{mailOptions.mailCount}} 封</h3>
              <div class="mailBox" v-on:click.stop="vieMail">
                <img src="../../../assets/activity/sendMail/view.png"
                     style="height: 40px;width: 40px;margin-left: 6px;"/>
                <p style="display: block;font-size: small;">公开信箱</p>
              </div>
              <div class="writeMyMail" v-on:click="writeMail">
                <img src="../../../assets/activity/sendMail/sey-write-a.png"
                     style="height: 40px;width: 40px;margin-left: 16px;"/>
                <p style="display: block;font-size: small;">撰写我的信件</p>
              </div>
            </div>
          </div>

          <div v-if="currentPage === 'about'">
            <div style="margin-left: 5px;margin-top: 15px;color: white;">
              <h2 style="float: left;">关于</h2>
              <div style="text-align: right;margin-right: 15px" @click="backHome">
                <img src="../../../assets/activity/sendMail/home.png" height="28px" width="28px;"
                     style="text-align: right"/>
              </div>
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
        </div>
        <div v-transfer-dom>
          <popup v-model="showPop" width="100%" :hide-on-blur=false :popup-style="styleObj" :is-transparent=true
                 :show-mask=false position="right" style="overflow: hidden;">
            <!-- group already has a top border, so we need to hide header's bottom border-->
            <!--查看公开信件-->
            <div v-show="modelShow === 'mailList'">
              <scroller lock-x scrollbar-y use-pulldown use-pullup height="-100px" @on-pullup-loading="pullUpLoad"
                        @on-pulldown-loading="pullDownLoad" ref="demo1"
                        :pullup-config="pullUpConfig" :pulldown-config="pullDownConfig"
                        style="margin: 0px 10px 10px 10px;color: white;">
                <div class="mail-list">
                  <div style="width: 100%;height: 30px; margin-bottom: 10px;margin-top: 10px;">
                    <p style="text-align: left;float: left;padding-bottom: 4px;margin-right: 5px;" @click="closeWindow">
                      <img src="../../../assets/activity/sendMail/home.png" height="28px" width="28px;"
                           style="text-align: right"/>
                    </p>
                    <p
                      style="border: white solid 1px;border-radius: 6px;width: 80px;text-align: center;margin-left: calc(50% - 40px)">
                      公开信件</p>
                  </div>
                  <div v-if="!publicMailList" style="text-align: center">
                    <p>未找到信件!</p>
                  </div>
                  <div v-else class="view-mail" v-for="item in publicMailList">
                    <p style="margin-top: 8px; margin-bottom: 8px; color: gray">{{item.content}}</p>
                    <br>
                    <p
                      style="text-align: right;font-size: 14px;color: #999999;margin-right: 10px;margin-bottom: 10px;padding-bottom: 5px;">
                      寄往 {{item.totime}} 年<br>
                      寄信人: {{replaceStr(item.sender)}}
                    </p>
                  </div>
                  <br>
                </div>
              </scroller>
            </div>
            <div v-show="modelShow === 'sendMail'" style="height: 100%;width: 100%;">
              <div class="write-mail-bg">
                <div style="width: 100%;height: 50px; margin-bottom: 10px;margin-top: 0px;">
                  <p style="text-align: left;float: left;position: absolute;top: 10px;left: 10px" @click="closeWindow">
                    <img src="../../../assets/activity/sendMail/home-grey.png" height="35px" width="35px;"
                         style="text-align: right"/>
                  </p>
                  <p style="text-align: left;top: 10px;position: absolute;right: 10px;" @click="clickSendMail">
                    <img src="../../../assets/activity/sendMail/send.png" height="40px" width="40px"
                         style="text-align: right"/>
                  </p>
                </div>
                <div style="width: 100%;height: calc(100% - 60px);overflow: hidden;">
                  <textarea
                    style="overflow-y: scroll;height: calc(100% - 100px);width: calc(100% - 30px);margin-left: 15px;border-style: none;background:transparent;position: static;font-size: 13pt;"
                    placeholder=". . ." v-on:focus="setBodyTop" v-model="mailOptions.content"> </textarea>
                </div>
              </div>
              <div v-transfer-dom>
                <popup v-model="sendMailOption" :popup-style="sendOptionStyle" position="right" width="100%"
                       :hide-on-blur=false :show-mask=false>
                  <!-- group already has a top border, so we need to hide header's bottom border-->
                  <div v-if="!mailOptions.sendSuccess" class="write-mail-bg">
                    <div style="width: 80%;margin-left: 10%;padding-top: 10px;">
                      <!--<input placeholder="寄信邮箱" v-model="mailOptions.senderMail"-->
                             <!--style="font-size: 13pt;width: 100%;height: 30px;background: transparent;border-style: none;border-bottom: 1px solid gray"/>-->
                      <!--<p style="font-size: 10pt;color: grey;margin-bottom: 15px;">用于校验发信人信息</p>-->
                      <p style="margin-top: 10px;">收件邮箱</p>
                      <input placeholder="example@example.com" v-model="mailOptions.receiverMail"
                             style="margin-bottom: 15px;font-size: 13pt;width: 100%;height: 30px;background: transparent;border-style: none;border-bottom: 1px solid gray"/>
                      <p style="margin-bottom: 15px;">选择送信时间</p>
                      <checker
                        v-model="sendTime.value"
                        radio-required
                        default-item-class="send-time-item"
                        selected-item-class="send-time-item-selected"
                        style="margin-bottom: 15px;float: leftΩ"
                        @on-change="timeChange"
                      >
                        <checker-item value="0.5">半年</checker-item>
                        <checker-item value="1">一年</checker-item>
                        <checker-item value="2">两年</checker-item>
                        <checker-item value="3">三年</checker-item>
                        <checker-item value="5">五年</checker-item>
                        <checker-item value="10">十年</checker-item>
                        <div @click="showTimeWindow" class="send-time-item"
                             :class="sendTime.custom_time === '' ? '' : 'send-time-item-selected'"
                             style="width: auto;display: inline-block;">{{sendTime.custom_time === '' ? '自定义' :
                          sendTime.custom_time}}
                        </div>
                        <!--<checker-item value="99" style="width: auto;">{{sendTime.custom_time === '' ? '自定义' : sendTime.custom_time}}</checker-item>-->
                        <datetime v-show="false" @on-hide="closeDataView" v-model="sendTime.custom_time"
                                  @on-confirm="setCustomTime" :show.sync="showSelectTime"></datetime>
                      </checker>

                      <div style="float: left">
                        <p style="float: left;">允许放入公开信箱</p>
                      </div>
                      <div style="text-align: right;margin-bottom: 5px;">
                        <check-icon :value="mailOptions.isPublic" style="text-align: right"
                                    @click.native="changePublic"></check-icon>
                      </div>
                      <p style="color: grey;font-size: 10pt;">
                        勾选后，将在某一天随机出现在公开信箱中。
                      </p>
                      <!--<div style="float: left">-->
                        <!--<p style="float: left;">是否加密</p>-->
                      <!--</div>-->
                      <!--<div style="text-align: right;margin-bottom: 5px;">-->
                        <!--<check-icon :value="mailOptions.isEncryption" style="text-align: right"-->
                                    <!--@click.native="changePublic"></check-icon>-->
                      <!--</div>-->
                      <!--<p style="color: grey;font-size: 10pt;">-->
                      <!--</p>-->
                    </div>
                    <div
                      style="position: fixed; bottom: calc(30px + env(safe-area-inset-bottom));width: 80%;margin-left: 10%;">
                      <p style="position: absolute;left: 0;bottom: 0;"
                         @click="sendMailOption = false; changeTitle('撰写我的信件')">
                        <img src="../../../assets/activity/sendMail/back.png" height="28px" width="28px;"
                             style="text-align: right"/>
                      </p>
                      <p style="position: absolute;right: 0;bottom: 0;" @click="doSendMail">
                        <img src="../../../assets/activity/sendMail/send.png" height="32px" width="32px;"
                             style="text-align: right"/>
                      </p>
                    </div>
                    <!--<div v-transfer-dom>-->
                    <!--<x-dialog v-model="showSelectTime" hide-on-blur mask-z-index="555" :dialog-style="dialogStyle">-->
                    <!--<x-button @click.native="submitSelectTime">确定</x-button>-->
                    <!--</x-dialog>-->
                    <!--</div>-->
                  </div>
                  <div v-else>
                    <msg title="发送成功" description="您的未来邮件已寄出成功，等待写入到区块链" icon="success"></msg>
                    <div style="text-align: center;color: grey;">
                      <p style="border: grey 1px solid;width: 80%;margin-left: 10%;border-radius: 5px;" @click="sendMailOption = false; showPop = false">返回</p>
                    </div>
                  </div>
                </popup>
              </div>
            </div>
          </popup>
        </div>
      </div>
    </view-box>
  </div>
</template>

<script>
  import {
    XButton,
    XHeader,
    ViewBox,
    Msg,
    Scroller,
    PopupHeader,
    TransferDom,
    Popup,
    LoadMore,
    Group,
    Cell,
    Checker,
    CheckerItem,
    CheckIcon,
    Datetime,
    XDialog
  } from 'vux'
  import WriteMail from './WriteMail'
  import * as TimeUtil from './timeUtil'

  export default {
    props: {
      showTitle: Boolean
    },
    directives: {
      TransferDom
    },
    components: {
      Scroller,
      XDialog,
      CheckIcon,
      Msg,
      Checker,
      Datetime,
      CheckerItem,
      Cell,
      WriteMail,
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
        showPop: false,
        showSelectTime: false,
        sendMailOption: false,
        currentUser: {},
        currentPage: 'home',
        pageNo: 0,
        sendTime: {
          time: '',
          custom_time: '',
          value: '0.5'
        },
        mailOptions: {
          mailCount: 0,
          receiverMail: '',
          isPublic: true,
          content: '',
          isEncryption: false,
          sendSuccess: false
        },
        modelShow: 'mailList',
        styleObj: {
          'z-index': 301,
          'height': 'calc(100% - 46px - env(safe-area-inset-top))',
          'top': 'calc(46px + env(safe-area-inset-top))'
        },
        sendOptionStyle: {
          'z-index': 501,
          'height': 'calc(100% - 46px - env(safe-area-inset-top))',
          'top': 'calc(46px + env(safe-area-inset-top))'
        },
        dialogStyle: {
          'max-width': '100%',
          'width': '90%',
          'height': '50%',
          'background-color': 'transparent'
        },
        pullUpConfig: {
          content: '上拉加载更多',
          downContent: '松开进行加载',
          upContent: '上拉加载更多',
          loadingContent: '加载中...'
        },
        pullDownConfig: {
          content: '下拉加载更多',
          downContent: '松开进行加载',
          upContent: '下拉加载更多',
          loadingContent: '加载中...'
        },
        tempMailList: [],
        publicMailList: []
      }
    },
    mounted () {
      this.getMailCount()
      this.currentUser = this.$db.get('User')
      this.sendTime.time = TimeUtil.getYear(0.5)
      this.loadMailListByPage(0)
      console.log(this.publicMailList)
      this.$nextTick(() => {
        this.$refs.demo1.reset({top: 0})
      })
    },
    methods: {
      loadMailListByPage (pageNo) {
        this.$api.mailApi.getPublicMailList(pageNo).then(result => {
          if (result.data.success) {
            this.publicMailList = result.data.data
            this.pageNo += 1
          } else {
            this.$vux.toast.show({
              type: 'text',
              text: '加载信件失败'
            })
          }
        })
      },
      doSendMail () {
        if (this.$utils.checkEmail(this.mailOptions.receiverMail)) {
          console.log('发送日期:')
          const sendDate = this.sendTime.custom_time === '' ? this.sendTime.time : this.sendTime.custom_time
          if (TimeUtil.isBigThanToday(sendDate)) {
            // receiverMail, content, sendTime, isPublic, userId
            this.$api.mailApi.sendMail(this.mailOptions.receiverMail, this.mailOptions.content, sendDate, this.mailOptions.isPublic, this.currentUser.userId).then(result => {
              if (result.data.success) {
                this.mailOptions.sendSuccess = true
              } else {
                this.$vux.toast.show({
                  type: 'text',
                  text: result.data.message
                })
              }
            })
          } else {
            this.$vux.toast.show({
              type: 'text',
              width: '12em',
              text: '请选择未来的一个日期!'
            })
          }
        } else {
          this.$vux.toast.show({
            type: 'text',
            width: '12em',
            text: '输入的邮箱格式不正确!'
          })
        }
      },
      timeChange (val) {
        console.log(val)
        if (val <= 10) {
          this.sendTime.custom_time = ''
          this.sendTime.time = TimeUtil.getYear(val)
        } else {
          const that = this
          this.$nextTick(() => {
            that.showSelectTime = true
          })
        }
      },
      setCustomTime () {
        this.sendTime.value = 99
        console.log(this.sendTime.custom_time)
      },
      closeDataView () {
        this.sendTime.value = 99
      },
      changePublic () {
        console.log('click public')
        this.mailOptions.isPublic = !this.mailOptions.isPublic
      },
      setBodyTop () {
        console.log('输入框激活,置顶')
        document.body.scrollTop = 0
        document.documentElement.scrollTop = 0
        // console.log(document.documentElement.scrollTop)
      },
      clickSendMail () {
        this.sendMailOption = true
        this.changeTitle('发送信件')
      },
      showTimeWindow () {
        this.showSelectTime = true
        this.sendTime.value = 99
      },
      vieMail () {
        this.showPop = true
        this.currentPage = 'mailList'
        this.modelShow = 'mailList'
        this.changeTitle('公开信箱')
        // this.currentPage = 'mailList'
        // this.$nextTick(() => {
        //   this.$refs.demo1.reset({top: 0})
        // })
      },
      changeTitle (title) {
        this.$emit('change-title', title)
      },
      writeMail () {
        // this.$router.push({name: 'wirteMail'})
        this.showPop = true
        this.modelShow = 'sendMail'
        this.changeTitle('撰写我的信件')
      },
      closeWindow () {
        this.changeTitle('给未来写封信')
        this.currentPage = 'home'
        this.showPop = false
        this.sendMailOption = false
      },
      backHome () {
        this.changeTitle('给未来写封信')
        this.currentPage = 'home'
        this.showPop = false
      },
      replaceStr (str) {
        return str.replace(str.substring(0, 4), '****')
      },
      openAbout () {
        this.changeTitle('关于')
        this.showPop = false
        this.currentPage = 'about'
        this.$emit('close-about')
      },
      pullUpLoad () {
        this.$api.mailApi.getPublicMailList(this.pageNo).then(result => {
          if (result.data.success) {
            this.$refs.demo1.donePullup()
            this.tempMailList.length = 0
            this.tempMailList = result.data.data
            if (this.tempMailList.length > 0) {
              this.pageNo += 1
              this.publicMailList = this.publicMailList.concat(this.tempMailList)
            }
          } else {
            this.$vux.toast.show({
              type: 'text',
              text: '加载信件失败'
            })
          }
        })
      },
      getMailCount () {
        this.$api.mailApi.getMailCount().then(result => {
          if (result.data.success) {
            this.mailOptions.mailCount = result.data.data
          }
        })
      },
      pullDownLoad () {
        this.$api.mailApi.getPublicMailList(this.pageNo).then(result => {
          if (result.data.success) {
            this.$refs.demo1.donePulldown()
            this.tempMailList.length = 0
            this.tempMailList = result.data.data
            if (this.tempMailList.length > 0) {
              this.pageNo += 1
              this.publicMailList = this.tempMailList.concat(this.publicMailList)
            }
          } else {
            this.$vux.toast.show({
              type: 'text',
              text: '加载信件失败'
            })
          }
        })
      }
    }
  }
</script>

<style scoped>

  .bg {
    background-image: url(../../../assets/activity/sendMail/mailbg.png);
    background-repeat: no-repeat;
    background-size: 100% 100%;
    -moz-background-size: 100% 100%;
    position: fixed;
    height: 100%;
    width: 100%;
    background-attachment: fixed;
  }

  .mail-list {
    /*position: absolute;*/
    margin-bottom: 100px;
    /*top: 50px;*/
  }

  .view-mail {
    background-image: url(../../../assets/activity/sendMail/view-bg.jpg);
    padding: 8px 8px 0px 8px;
  }

  .mailBox {
    position: fixed;
    bottom: 20px;
    left: 30px;
  }

  .writeMyMail {
    position: fixed;
    bottom: 20px;
    right: 30px;
  }

  .write-mail-bg {
    background-image: url(../../../assets/activity/sendMail/view-bg.jpg);
    height: 100%;
    width: 100%;
  }

  .send-time-item {
    width: 60px;
    color: grey;
    height: 26px;
    line-height: 26px;
    text-align: center;
    margin-bottom: 8px;
    border-radius: 5px;
    border: 1px solid #ccc;
    background-color: transparent;
    margin-right: 8px;
  }
  .weui-icon_msg {
    color: #969494;
  }
  .weui-icon-success {
    color: #969494;
  }
  .send-time-item-selected {
    background-color: #969494;
    color: white;
  }
</style>
