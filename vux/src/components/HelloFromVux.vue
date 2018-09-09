<template>
  <div style="height: 100%; width:100%; position: fixed">
    <div class="vux-demo">
      <img class="logo" src="../assets/vux_logo.png">
      <h1> Home</h1>
    </div>
    <group title="cell demo">
      <cell title="VUX" value="cool" is-link></cell>
      <x-button  @click.native="back">back</x-button>
      <x-button @click.native="gotoPage1">Page1</x-button>
      <x-button @click.native="gotoPage2">Page2</x-button>
      <x-button @click.native="gotoPage3">Page3</x-button>
    </group>

  </div>
</template>

<script>
import { Group, Cell, XButton } from 'vux'

export default {
  components: {
    Group,
    Cell,
    XButton
  },
  data () {
    return {
      // note: changing this line won't causes changes
      // with hot-reload because the reloaded component
      // preserves its current state and we are modifying
      // its initial state.
      msg: 'Hello World!'
    }
  },
  methods: {
    back () {
      // this.$store.commit('setTransition', 'turn-off')
      this.$router.back(-1)
    },
    gotoHome () {
      this.$router.push({name: 'home'})
      // this.$store.commit('setTransition', 'turn-on')
    },
    gotoPage1 () {
      this.$router.push({name: 'page1'})
      // this.$store.commit('setTransition', 'turn-on')
    },
    gotoPage2 () {
      this.$router.push({name: 'page2'})
      // this.$store.commit('setTransition', 'turn-on')
    },
    gotoPage3 () {
      this.$router.push({name: 'page3'})
      // this.$store.commit('setTransition', 'turn-on')
    }
  }
}
</script>

<style>
.vux-demo {
  text-align: center;
}
.logo {
  width: 100px;
  height: 100px
}
</style>
