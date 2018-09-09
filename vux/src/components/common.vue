<template>
  <div class="pad" style="height: 100%; width:100%; position: fixed">
    <router-link to="/page1" >page1</router-link>
    <router-link to="/page2" >page2</router-link>
    <router-link to="/page3" >page3</router-link>
  </div>
</template>

<script>
  export default {
    data () {
      return {}
    },
    methods: {
    }
  }
</script>

<style scoped>
  .pad {
    padding-top: env(safe-area-inset-top);
    padding-bottom: env(safe-area-inset-bottom);
  }
</style>
