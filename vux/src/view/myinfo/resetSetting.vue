<template>
  <div>
      {{msg}}
  </div>
</template>

<script>
export default {
  name: 'resetSetting',
  data () {
    return {
      msg: 'resetSetting page'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .rel { position: relative; }
  .fixed { position: fixed; }
  .transform { transform: scale(1); }
</style>
