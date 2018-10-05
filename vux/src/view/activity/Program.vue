<template>
  <div style="height: 100%;">
    <view-box :style="{'margin-top': showTitle ? '46px' : '0px'}">
      <program-header :title="title" @close-program="closeProgram" @open-about="openAbout" :show-title="showTitle"></program-header>
      <component :is="program" :show-title="showTitle" @change-title="changeTitle" ref="refProgram" @close-about="closeAbout"></component>
      <div v-transfer-dom>
        <popup v-model="viewAbout" :popup-style="styleObj" :hide-on-blur=false :show-mask=false>
          <cell @click.native="clickAbout" :title="'关与' + programName" style="text-align: center;background-color: white"></cell>
          <cell @click.native="closeAbout" title="取消" style="text-align: center;background-color: white;margin-top: 5px;"></cell>
        </popup>
      </div>
    </view-box>
  </div>
</template>

<script>
  import {ViewBox, TransferDom, Popup, Cell} from 'vux'
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
      ViewBox,
      Popup,
      Cell,
      ProgramHeader
    },
    data () {
      return {
        viewAbout: false,
        titleStr: this.programName,
        styleObj: {
          'z-index': 800
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

</style>
