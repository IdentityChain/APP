<template>
  <div>
    <div class="program" style="z-index: 999">
      <img v-on:click.stop="about" src="../../../assets/activity/more.png" style="height: 20px;width: 18px;float: left;margin-top: 5px;margin-left: 10px;"/>
      <p style="color: #b2b2b2;float: left;line-height: 28px;">&nbsp;&nbsp;|&nbsp;&nbsp;</p>
      <img @click="backHome" src="../../../assets/activity/circle.png" style="height: 20px;width: 20px;margin-top: 5px;margin-right: 8px;"/>
    </div>
    <!--<div v-transfer-dom>-->
      <popup v-model="viewAbout">
        <cell @click.native="openAbout" :title="'关与' + name" style="text-align: center;background-color: white"></cell>
        <cell @click.native="closeAbout" title="取消" style="text-align: center;background-color: white;margin-top: 5px;"></cell>
      </popup>
    <!--</div>-->
  </div>
</template>

<script>
  import {Popup, Group, Cell, TransferDom} from 'vux'
  export default {
    props: ['name'],
    directives: {
      TransferDom
    },
    components: {
      Popup,
      Group,
      Cell
    },
    mounted: function () {
    },
    data () {
      return {
        viewAbout: false
      }
    },
    methods: {
      backHome () {
        console.log('first send go-back event')
        this.$emit('close-program')
      },
      about () {
        this.viewAbout = true
      },
      openAbout () {
        this.viewAbout = false
        this.$emit('open-about')
      },
      closeAbout () {
        this.viewAbout = false
      }
    }
  }
</script>

<style scoped>
  .program {
    border-radius: 15px;
    height: 30px;
    width: 80px;
    background:rgba(2,2,2,0.1);
    position: fixed;
    top: 10px;
    right: 20px;
    border: #cccccc 1px solid;
  }
</style>
