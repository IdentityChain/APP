<template>
    <div style="width: 100%;padding-left: 20px;">
      <div style="margin-top: 0px;">
        <img src="../../assets/wallet/point.png" width="20px" height="48px" style="float: left;margin-top: 15px;margin-left: -20px;display:block;"/>
        <div style="height: 52px;width: 100%">
          <div style="float: left;margin-left: 10px;display: inline">
            <p style="font-size: small;line-height: 20px;">{{transType}}&nbsp;&nbsp;&nbsp; {{account}}</p>
            <P style="font-size: xx-small;color: grey;margin-top: 4px;">{{transTime}}</P>
          </div>
          <div style="text-align: right; display: inline;line-height: 45px;">
            <p style="font-size: small">{{transNumber}} ISC &nbsp;&nbsp;&nbsp;&nbsp;</p>
          </div>
        </div>
      </div>

    </div>
</template>

<script>
    export default {
      name: 'MyFlowState',
      props: {
        transType: String,
        account: String,
        transTime: String,
        transNumber: Number
      }
    }
</script>

<style scoped>

</style>
