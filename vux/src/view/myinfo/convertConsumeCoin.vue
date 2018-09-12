<template>
  <div>
    <x-header title="联系人"></x-header>
    <load-more :show-loading="false" :tip="msg" background-color="#fbf9fe"></load-more>
  </div>
</template>

<script>
import { XButton,Box,Divider,XHeader,TransferDom,LoadMore } from 'vux'

export default {
  name: 'convertConsumeCoin',
  directives: {
    TransferDom
  },
  components: {
    XButton,
    XHeader,
    LoadMore
  },
  data () {
    return {
      msg: '功能建设中'
    }
  }
}
</script>

<style lang="less">
.custom-primary-red {
  border-radius: 99px!important;
  border-color: #CE3C39!important;
  color: #CE3C39!important;
  &:active {
    border-color: rgba(206, 60, 57, 0.6)!important;
    color: rgba(206, 60, 57, 0.6)!important;
    background-color: transparent;
  }

}
</style>