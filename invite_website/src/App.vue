<template>
  <div id="app">
      <transition
        @after-enter="$vux.bus && $vux.bus.$emit('vux:after-view-enter')"
        :name="viewTransition" :css="!!direction">
        <router-view class="router-view"></router-view>
      </transition>
  </div>
</template>

<script>
  import {mapState} from 'vuex'
  import { XHeader, Tabbar, TabbarItem, ViewBox } from 'vux'

  export default {
    name: 'app',
    components: {
      XHeader,
      Tabbar,
      TabbarItem,
      ViewBox
    },
    computed: {
      ...mapState({
        direction: state => state.vux.direction
      }),
      viewTransition () {
        if (!this.direction) return ''
        return 'vux-pop-' + (this.direction === 'forward' ? 'in' : 'out')
      }
    },
    methods: {},
    data () {
      return {
      }
    }
  }
</script>

<style lang="less">
  @import '~vux/src/styles/reset.less';
  @import '~vux/src/styles/1px.less';
  @import '~vux/src/styles/tap.less';

  .router-view {
    width: 100%;
    /*top: 46px;*/
  }

  html, body {
    height: 100%;
    width: 100%;
    overflow-x: hidden;
  }
  #app {
    height: 100%;
  }
  .vux-pop-out-enter-active,
  .vux-pop-out-leave-active,
  .vux-pop-in-enter-active,
  .vux-pop-in-leave-active {
    will-change: transform;
    transition: all 500ms;
    height: 100%;
    /*top: 46px;*/
    position: absolute;
    backface-visibility: hidden;
    perspective: 1000;
  }

  .vux-pop-out-enter {
    opacity: 0;
    transform: translate3d(-100%, 0, 0);
  }

  .vux-pop-out-leave-active {
    opacity: 0;
    transform: translate3d(100%, 0, 0);
  }

  .vux-pop-in-enter {
    opacity: 0;
    transform: translate3d(100%, 0, 0);
  }

  .vux-pop-in-leave-active {
    opacity: 0;
    transform: translate3d(-100%, 0, 0);
  }
</style>
