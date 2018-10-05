<template>
  <div>
    <div class="title" v-show="showTitle">
      <p class="title-css" style="">{{title}}</p>
    </div>
    <div class="program">
      <img v-on:click="about" src="../../../assets/activity/more.png" style="height: 20px;width: 18px;float: left;margin-top: 4px;margin-left: 10px;"/>
      <p style="color: #b2b2b2;float: left;line-height: 28px;">&nbsp;&nbsp;|&nbsp;&nbsp;</p>
      <img @click="backHome" src="../../../assets/activity/circle.png" style="height: 20px;width: 20px;margin-top: 4px;margin-right: 8px;"/>
    </div>
  </div>
</template>

<script>
  import {Popup, Group, Cell, TransferDom} from 'vux'
  export default {
    props: ['showTitle', 'title'],
    directives: {
      TransferDom
    },
    components: {
      Popup,
      Group,
      Cell
    },
    mounted: function () {
      this.$nextTick(function () {
        this.titleStatus = this.showTitle
      })
    },
    data () {
      return {
      }
    },
    methods: {
      backHome () {
        console.log('first send go-back event')
        this.$emit('close-program')
      },
      about () {
        console.log('click about')
        this.$emit('open-about')
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
    top: 9px;
    right: 10px;
    border: #cccccc 1px solid;
  }
  .title {
    position: fixed;
    top: 0px;
    text-align: center;
    background:rgba(255,255,255,1);
    width: 100%;
    height: 46px;
    line-height: 46px;
    z-index: 1999;
    color: black;
  }
  .title-css {
    font-size: 20px;
    font-weight: inherit;
  }
</style>
