<template>
  <div>
    <coin v-for="(coinItem,index) in coins"
          :key="index"
          v-if="coinItem.status === '可采集'"
          :addISC="coinItem.addISC"
          :isclID="coinItem.isclId"
          @getCoin="doGetCoin"
          class="coin"
          :style="{'top': (Math.random() * 42 ) + 'vw', 'left': (Math.random() * 42) + 'vw'}">
    </coin>
  </div>
</template>

<script>
  import coin from './GetCoin'
  export default {
    name: 'Coins',
    props: ['coins'],
    components: {
      coin
    },
    methods: {
      doGetCoin (iscLID, addISC) {
        console.log('获取币ID: ' + iscLID)
        this.$api.calculateApi.checkCalculate(iscLID).then(result => {
          if (result.data.success) {
            this.$emit('add-event', addISC)
          } else {
            this.$vux.toast.show({
              type: 'text',
              text: result.data.message
            })
          }
        })
      }
    }
  }
</script>

<style scoped>
  .coin {
    position: absolute;
  }
</style>
