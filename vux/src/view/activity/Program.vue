<template>
  <div style="height: 100%;background-color: #EDEAF3">
    <view-box body-padding-top="0px" body-padding-bottom="55px">
      <div slot="header"
           style="position: absolute;height: calc(40px + env(safe-area-inset-top));width:100%;left: 0;top: 0;background-color: rgb(50,112,222);z-index: 100">
        <x-header :left-options="{showBack: false}"
                  style="position: absolute;left:0;background-color: rgb(50,112,222);top: env(safe-area-inset-top);width: 100%;z-index: 100;"><p style="color: white;font-weight: bold;font-size: 13pt;line-height: 46px;">{{titleStr}}</p>
          <div class="program" slot="right">
            <img @click="showAbout"  src="../../assets/activity/more.png" style="height: 20px;width: 18px;float: left;margin-top: 4px;margin-left: 10px;z-index: 1999"/>
            <p style="color: #b2b2b2;float: left;line-height: 28px;">&nbsp;&nbsp;|&nbsp;&nbsp;</p>
            <img @click="closeProgram"  src="../../assets/activity/circle.png" style="height: 20px;width: 20px;margin-top: 4px;margin-right: 8px;"/>
          </div>
        </x-header>
      </div>
      <!--</div>-->

      <component :is="program" :show-title="showTitle" @change-title="changeTitle" ref="refProgram" @close-about="closeAbout"></component>
      <div v-transfer-dom>
        <actionsheet v-model="viewAbout" :menus="aboutMenus" @on-click-menu-menu1="clickAbout" show-cancel></actionsheet>
        <!--<popup v-model="viewAbout" :popup-style="styleObj" :hide-on-blur=false :show-mask=false>-->
          <!--<cell @click.native="clickAbout" :title="'关与' + programName" style="text-align: center;background-color: white"></cell>-->
          <!--<cell @click.native="closeAbout" title="取消" style="text-align: center;background-color: white;margin-top: 5px;"></cell>-->
        <!--</popup>-->
      </div>
    </view-box>
  </div>
</template>

<script>
  import {ViewBox, TransferDom, Popup, Cell, XHeader, Actionsheet} from 'vux'
  import ProgramHeader from './components/ProgramHeader'
  export default {
    props: {
      program: Object,
      showTitle: Boolean,
      programName: String
    },
    directives: {
      TransferDom
    },
    components: {
      Actionsheet,
      ViewBox,
      Popup,
      XHeader,
      Cell,
      ProgramHeader
    },
    data () {
      return {
        viewAbout: false,
        titleStr: this.programName,
        styleObj: {
          'z-index': 201
        },
        aboutMenus: {
          menu1: '关于' + this.programName
        }
      }
    },
    computed: {
      title () {
        return this.titleStr
      }
    },
    mounted () {
    },
    methods: {
      changeTitle (newStr) {
        this.titleStr = newStr
        // this.title = newStr
      },
      closeProgram () {
        this.viewAbout = false
        this.$emit('close-program')
        let that = this
        this.$nextTick(() => {
          that.$refs.refProgram.closeWindow()
        })
      },
      showAbout () {
        this.viewAbout = true
      },
      closeAbout () {
        this.viewAbout = false
      },
      openAbout () {
        // console.log(this.$refs.program1)
        // console.log('get click event')
        this.viewAbout = true
      },
      clickAbout () {
        let that = this
        this.$nextTick(() => {
          that.$refs.refProgram.openAbout()
        })
      }
    }
  }
</script>

<style scoped>
  .program {
    border-radius: 15px;
    height: 28px;
    width: 80px;
    z-index: 1999;
    background:rgba(2,2,2,0.1);
    position: fixed;
    top: calc(9px + env(safe-area-inset-top));
    right: 10px;
    border: #cccccc 1px solid;
  }

</style>
