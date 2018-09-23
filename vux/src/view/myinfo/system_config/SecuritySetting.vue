<template>
  <div style="height: 100%;background-color: #ECECEC">
    <view-box body-padding-top="46px">
      <div v-if="true" slot="header"
           style="position: absolute;height: calc(40px + env(safe-area-inset-top));width:100%;left: 0;top: 0;background-color: mediumslateblue;z-index: 100">
        <x-header :left-options="{showBack: true}"
                  style="position: absolute;left:0;top: env(safe-area-inset-top);width: 100%;z-index: 100;">安全设置
        </x-header>
      </div>

      <group style="padding-top: env(safe-area-inset-top);">
        <cell title="手机号码" is-link></cell>
      </group>

      <group>
        <cell title="登陆密码" is-link></cell>
        <cell title="交易密码" is-link></cell>
      </group>

      <div v-transfer-dom>
        <popup v-model="showEdit" height="100%">
          <!-- group already has a top border, so we need to hide header's bottom border-->
          <popup-header
            left-text="取消"
            right-text="完成"
            :title="editModel.title"
            :show-bottom-border="false"
            @on-click-left="showEdit = false"
            @on-click-right="showEdit = false"
            style="background-color: mediumslateblue;color: white;padding-top: env(safe-area-inset-top)"></popup-header>
          <group >
            <!--修改昵称-->
            <x-input @on-focus="nickNameInputFocus" v-if="editModel.currentEdit === 'nickName'" ref="nickNameInput" v-model="currentUser.nickName"></x-input>
          </group>
        </popup>
      </div>
    </view-box>
  </div>
</template>

<script>
  import {XButton, Box, Divider, XHeader, Group, Cell, CellBox, PopupHeader, TransferDom, Popup, XInput, ViewBox} from 'vux'

  export default {
    name: 'systemSetting',
    directives: {
      TransferDom
    },
    components: {
      XInput,
      ViewBox,
      XButton,
      XHeader,
      Box,
      Divider,
      Group,
      Cell,
      CellBox,
      PopupHeader,
      Popup
    },
    mounted: function () {
      console.log(this.currentUser.id)
      let userObj = JSON.parse(window.localStorage.getItem('User'))
      this.currentUser.nickName = userObj.nickName
    },
    data () {
      return {
        msg: 'resetSetting page',
        showEdit: false,
        nickName: '李晓明',
        currentUser: {
          nickName: ''
        },
        editModel: {
          title: '设置昵称',
          currentEdit: 'nickName'
        }
      }
    },
    methods: {
      logout () {
        window.localStorage.clear()
        this.$router.push({'name': 'login'})
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .rel {
    position: relative;
  }

  .fixed {
    position: fixed;
  }

  .transform {
    transform: scale(1);
  }
</style>
