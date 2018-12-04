<template>
  <div class="hello">
    <p><strong>图片自身transform</strong></p>
    <div class="overflow">
      <img src="mm1.jpg" class="transform" />
    </div>
    <p><strong>overflow容器transform</strong></p>
    <div class="overflow transform">
      <img src="mm1.jpg" />
    </div>
    <p><strong>overflow和图片之间内嵌元素transform</strong></p>
    <div class="overflow">
      <div class="transform">
        <img src="mm1.jpg" />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
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
