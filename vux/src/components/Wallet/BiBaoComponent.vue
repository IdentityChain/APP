<template>
  <div class="bodydiv">
    <div class="div1">
      <div style="color: #BC0200">
        <p style="display: inline">+</p><p style="display: inline;font-size: xx-large">{{Math.floor(expectIncrease)}}</p><p style="display: inline">.{{floatNum}}%</p>
      </div>
      <p style="font-size: xx-small;color: gray">预期增长率</p>
    </div>
    <div style="height: 60px;margin-top: 15px; width: 63%;margin-left: 45%;">
      <p style="font-size: large;color: #1272BC">余币宝-ISC</p>
      <p style="font-size: xx-small;color: grey">管理期限{{expectDay}}天</p>
    </div>
  </div>
</template>

<script>
    export default {
      name: 'BiBao',
      props: {
        expectDay: Number,
        expectIncrease: Number
      },
      computed: {
        floatNum: function () {
          var num = this.expectIncrease
          var strNum = num.toFixed(2).toString()
          return strNum.substring(strNum.indexOf('.') + 1, strNum.indexOf('.') + 3)
        }
      }
    }
</script>

<style scoped>
  .bodydiv {
    height: 80px;
    width: 100vw;
    background-color: white;
    border-top: 1px solid lightgray;
    border-bottom: 1px solid lightgray;
    margin-bottom: 15px;
  }

  .div1 {
    height: 60px;
    width: 35%;
    border-right: 1px solid lightgray;
    float: left;
    text-align: center
  }
</style>
