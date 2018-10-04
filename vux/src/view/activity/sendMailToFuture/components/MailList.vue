<template>
  <div>
    <scroller lock-x scrollbar-y use-pullup height="-50px" @on-pullup-loading="load1" ref="demo1"
              :pullup-config="pullUpConfig" style="margin: 5px 10px 5px 10px;color: white;padding-bottom: 20px;">
      <div class="mail-list">
        <h1 @click="currentPage = 'home'">返回</h1>
        <div class="view-mail" v-for="item in publicMailList">
          <p style="margin-top: 8px; margin-bottom: 8px; color: gray">{{item.content}}</p>
          <br>
          <p style="text-align: right;font-size: 14px;color: #999999;margin-right: 10px;margin-bottom: 10px;">
            寄往 {{item.toTime}} 年<br>
            寄信人: {{item.sender}}
          </p>
        </div>
        <br>
      </div>
    </scroller>
  </div>
</template>

<script>
  import {Scroller} from 'vux'
  export default {
    props: ['mail-list'],
    components: {
      Scroller
    },
    name: 'MailList'
  }
</script>

<style scoped>

</style>
