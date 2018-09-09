<template>
  <div style="width: 100%; background-color: white">
    <router-link to="/page1" >page1</router-link>
    <router-link to="/page2" >page2</router-link>
    <p v-for="i in 100">{{i}}</p>
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
