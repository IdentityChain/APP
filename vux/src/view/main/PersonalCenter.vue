<template>
  <div style="height: 100%; width: 100%;background-color: white">
    <view-box body-padding-top="46px" body-padding-bottom="55px">
      <div class="header-css" slot="header"
           style="position: absolute;height: calc(40px + env(safe-area-inset-top));width:100%;left: 0;top: 0;background-color: rgb(50,112,222);z-index: 100">
        <x-header :left-options="{showBack: true, backText: '',preventGoBack: true}"
                  style="position: absolute;left:0;top: env(safe-area-inset-top);width: 100%;z-index: 100;background-color: rgb(50,112,222);"
                  @on-click-back="backHome">个人中心
        </x-header>
      </div>
      <div :class="platform === 'android' ? 'body-header-android' : 'body-header'">
          <div :class="platform === 'android' ? 'person-head-android' : 'person-head'" >
            <img src="../../assets/login/touxiang.png"/>
            <p>{{person.account}}</p>
          </div>
      </div>
      <div :class="platform === 'android' ? 'menu-android' : 'menu'">
        <cell title="个人信息" :border-intent=false is-link link="/my/resetSetting">
          <img slot="icon" width="20" style="display:block;margin-right:5px;" src="../../assets/wakuang/person-head.png">
        </cell>
        <cell title="邀请好友" :border-intent=false  is-link link="/invite">
          <img slot="icon" width="20" style="display:block;margin-right:5px;" src="../../assets/wakuang/icon-yaoqing1.png">
        </cell>
        <cell title="邀请好友列表" :border-intent=false  is-link link="/inviteList">
          <img slot="icon" width="20" style="display:block;margin-right:5px;" src="../../assets/wakuang/icon-yaoqing1.png">
        </cell>
        <br>
        <!--<cell title="用户反馈" :border-intent=true is-link link="">-->
          <!--<img slot="icon" width="20" style="display:block;margin-right:5px;" src="../../assets/wakuang/icon-fankui.png">-->
        <!--</cell>-->
        <cell title="系统设置" :border-intent=true is-link link="/my/systemSetting">
          <img slot="icon" width="20" style="display:block;margin-right:5px;" src="../../assets/wakuang/icon-setting.png">
        </cell>
        <cell title="退出登录" :border-intent=true is-link @click.native="logout">
          <img slot="icon" width="20" style="display:block;margin-right:5px;" src="../../assets/my/logout.png">
        </cell>
      </div>
    </view-box>
  </div>
</template>

<script>
  import {ViewBox, XHeader, Group, Cell, Divider} from 'vux'

  export default {
    name: 'PersonalCenter',
    components: {
      ViewBox,
      XHeader,
      Group,
      Divider,
      Cell
    },
    data () {
      return {
        platform: 'web',
        person: {
          account: ''
        }
      }
    },
    created: function () {
      this.platform = window.cordova === undefined ? 'web' : window.cordova.platformId
      this.person.account = this.$db.get('User').account
    },
    methods: {
      backHome () {
        this.$emit('close-center')
      },
      logout () {
        if (this.$appConfig.deployAPP) {
          let message = '确定退出登录?'
          let title = '提示'
          let buttonLabels = '取消,确定'
          const _that = this
          navigator.notification.confirm(message, (buttonText) => {
            console.log(buttonText)
            if (buttonText === 2) {
              console.log('点击了确定')
              window.localStorage.clear()
              window.sessionStorage.clear()
              _that.$router.replace({'name': 'login'})
            }
          }, title, buttonLabels)
        } else {
          window.localStorage.clear()
          window.sessionStorage.clear()
          this.$router.replace({'name': 'login'})
        }
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
    height: calc(5vh + 15vw + env(safe-area-inset-top));
    width: 100vw;
    background-color: rgb(50,112,222);
  }

  .body-header-android {
    position: absolute;
    top: 45px;
    height: calc(5vh + 15vw);
    width: 100vw;
    background-color: rgb(50,112,222);
  }
  .person-head {
    position: absolute;
    top: calc(5vh + env(safe-area-inset-top));
    left: 35vw;
    background-color: white;
    height: 30vw;
    width: 30vw;
    border-radius: 15vw;
    text-align: center;
    padding-top: 2vw;
  }

  .person-head-android {
    position: absolute;
    top: calc(5vh);
    left: 35vw;
    background-color: white;
    height: 30vw;
    width: 30vw;
    border-radius: 15vw;
    text-align: center;
    padding-top: 2vw;
  }
  .person-head img {
    height: 26vw;
    width: 26vw;
  }
  .menu {
    position: absolute;
    top: calc(46px + 5vh + 30vw + 60px + env(safe-area-inset-top));
    width: 80vw;
    left: 10vw;
  }

  .menu-android {
    position: absolute;
    top: calc(46px + 5vh + 30vw + 60px);
    width: 80vw;
    left: 10vw;
  }
</style>
