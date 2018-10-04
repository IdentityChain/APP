<template>
  <div style="height: 100%;width: 100%;position: fixed; top: 0;left: 0;">
      <div class="program-header">
        <program-button slot="probtn" :name="name" @close-pro="backList"  @open-about="openAbout"></program-button>
      </div>
      <div>
        <!--<slot name="program"></slot>-->
        <component :is="program" ref="program1"></component>
      </div>

  </div>
</template>

<script>
  import ProgramButton from './components/ProgramHeader'
  import {WriteMail} from './index'
  export default {
    name: 'Program',
    props: ['name', 'program'],
    components: {
      ProgramButton,
      WriteMail
    },
    data () {
      return {}
    },
    methods: {
      backList () {
        console.log('get go-back event, go on push')
        this.$emit('back-list')
      },
      openAbout () {
        this.$refs.program1.openAbout()
      }
    }
  }
</script>

<style scoped>

</style>
