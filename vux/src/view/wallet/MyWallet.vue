<template>
  <div style="height: 100%; width: 100%;background-color: white">
    <view-box body-padding-top="46px" body-padding-bottom="55px">
      <div class="header-css" slot="header"
           style="position: fixed;height: calc(40px + env(safe-area-inset-top));width:100%;left: 0;top: 0;background-color: rgb(50,112,222);z-index: 100">
        <x-header :left-options="{showBack: true, backText: ''}"
                  style="position: fixed;left:0;top: env(safe-area-inset-top);width: 100%;z-index: 100;background-color: rgb(50,112,222);">钱包
        </x-header>
      </div>
      <div class="body-header">
        <div class="body-content">
          <group>
            <cell title="交易记录" is-link :link="{name:'transRecord'}">
              <img slot="icon" width="20" style="display:block;margin-right:5px;" src="../../assets/wallet/a.png">
            </cell>
            <cell title="总资产">
              <img slot="icon" width="20" style="display:block;margin-right:5px;" src="../../assets/wallet/b.png">
              <p style="font-size: small;margin-right: 14px;">{{balance.total}} ISC</p>
            </cell>
            <!--<cell title="余额" is-link link="/myWallet/balanceDetail">-->
            <cell title="余额">
              <img slot="icon" width="20" style="display:block;margin-right:5px;" src="../../assets/wallet/c.png">
              <p style="font-size: small;margin-right: 14px;">{{balance.balance}}&nbsp;ISC</p>
            </cell>
            <cell title="余币宝" is-link link="/myWallet/yuBiBao">
              <img slot="icon" width="20" style="display:block;margin-right:5px;" src="../../assets/wallet/d.png">
            </cell>
          </group>
        </div>
      </div>
    </view-box>
  </div>
</template>

<script>
  import {ViewBox, XHeader, Cell, Group} from 'vux'

  export default {
    name: 'MyWallet',
    components: {
      ViewBox,
      XHeader,
      Cell,
      Group
    },
    created: function () {
      this.init()
    },
    data () {
      return {
        paihangList: [],
        balance: {
          total: 0,
          balance: 0
        }
      }
    },
    methods: {
      init () {
        this.getBalance()
      },
      getBalance () {
        let user = this.$db.get('User')
        let userId = user.userId
        let ethAddress = user.ethAddress
        this.$api.walletApi.getBalance(userId, ethAddress).then(result => {
          if (result.data.success) {
            let num = parseInt(result.data.data)
            this.balance.total = num.toFixed(2)
            this.balance.balance = num.toFixed(2)
          }
        })
      }
    }
  }
</script>

<style scoped>
  .header-css /deep/ .vux-header-title {
    color: white;
  }

  .header-css /deep/ .vux-header .vux-header-left .left-arrow:before {
    border: 1px solid white;
    border-width: 1px 0 0 1px;
  }

  .body-header {
    position: absolute;
    top: 45px;
    height: 0;
    padding-top: env(safe-area-inset-top);
    width: 100vw;
    background-color: rgb(50, 112, 222);
  }

  .body-content {
    margin-top: 0;
  }

</style>
