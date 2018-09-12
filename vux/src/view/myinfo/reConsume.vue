<template>
  <div>
      {{msg}}
  </div>
</template>

<script>
export default {
  name: 'reConsume',
  data () {
    return {
      msg: 'reConsume page'
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
