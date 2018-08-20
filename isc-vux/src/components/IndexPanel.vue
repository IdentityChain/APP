<template>
  <div class="" style="height: 350px;position: relative">
    <img style="height: 100%;width: 100%;z-index: 10;position: absolute;top: 0px; left: 0px;" src="../assets/bg.jpeg"/>
    <div style="height: 300px;z-index: 20;position: relative;top: 0px; left: 0px;">
      <button style="position: relative;left: 10%;top: 200px;">5</button>
      <button style="position: relative;left: 20%;top: 50px;">3</button>
      <button style="position: relative;left: 50%;top: 120px;">1</button>
      <button style="position: relative;left: 60%;top: 180px;">4</button>
      <button style="position: relative;left: 30%;top: 60px;">2</button>
    </div>
    <div style="height: 50px;margin-left: 5px;margin-right: 5px;z-index: 21;position: absolute;top: 300px; left: 0px;">
      <div style="text-align: left;float: left">
        <button>钱包</button>
        <button>提升算力</button>
      </div>
      <div style="text-align: right">
        <button style="">邀请</button>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'Home',
    components: {},
    data () {
      return {}
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  /*.bg {*/
  /*background:url('../assets/bg.jpeg') no-repeat;*/
  /*!*height: 100%;*!*/
  /*!*position: fixed;*!*/
  /*!*width: 100%;*!*/
  /*}*/
</style>
