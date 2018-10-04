<template>
  <div style="height: 100%">
    <view-box style="height: calc(100% + 55px)">
      <program-header @close-program="closeProgram" @open-about="openAbout"></program-header>
      <slot name="program">
      </slot>
    </view-box>
  </div>
</template>

<script>
  import {ViewBox} from 'vux'
  import ProgramHeader from './components/ProgramHeader'
  export default {
    components: {
      ViewBox,
      ProgramHeader
    },
    data () {
      return {
      }
    },
    mounted () {
    },
    methods: {
      closeProgram () {
        this.$emit('close-program')
      },
      openAbout () {
        console.log(this.$refs.program1)
        // this.$refs.program.openAbout()
      }
    }
  }
</script>

<style scoped>

</style>
