<template>
  <div style="width: 100%; background-color: white">
    <router-link to="/" >home</router-link>
    <router-link to="/login" >login</router-link>
    <router-link to="/resetPasswd" >ResetPassword</router-link>
    <router-link to="/register" >Register</router-link>
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
