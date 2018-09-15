<template>
  <div style="height: 100%;background-color: #ECECEC">
    <view-box body-padding-top="46px">
      <div v-if="true" slot="header"
           style="position: absolute;height: calc(40px + env(safe-area-inset-top));width:100%;left: 0;top: 0;background-color: mediumslateblue;z-index: 100">
        <x-header :left-options="{showBack: true}"
                  style="position: absolute;left:0;top: env(safe-area-inset-top);width: 100%;z-index: 100;">完善信息
        </x-header>
      </div>
      <div style="height: env(safe-area-inset-top)">
      </div>
      <group style="padding-top: 46px">
        <cell title="头像"><img src="../../assets/my/people.png" style="height: 50px;width: 50px"/></cell>
        <cell title="昵称" is-link @click.native="openEdit"> 昵称</cell>
      </group>

      <group>
        <cell title="真实姓名" is-link>xxx</cell>
        <cell title="身份证号码" is-link>410923198902015452</cell>
      </group>

      <group>
        <cell title="地区" is-link>北京 海淀</cell>
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
            <x-input v-if="editModel.currentEdit === 'nickName'" v-model="nickName"></x-input>
          </group>
        </popup>
      </div>
    </view-box>
  </div>
</template>

<script>
  import {XButton, Box, Divider, XHeader, Group, Cell, CellBox, PopupHeader, TransferDom, Popup, XInput} from 'vux'

  export default {
    name: 'resetSetting',
    directives: {
      TransferDom
    },
    components: {
      XInput,
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
    data () {
      return {
        msg: 'resetSetting page',
        showEdit: false,
        nickName: '李晓明',
        editModel: {
          title: '设置昵称',
          currentEdit: 'nickName'
        }
      }
    },
    methods: {
      openEdit () {
        this.showEdit = true
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
