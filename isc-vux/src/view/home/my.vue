<template>
  <div>
    <group>
      <panel :list="owner" type="1">

      </panel>
    </group>

    <group>
      <card :header="{title: '我的数据'}">
        <div slot="content" class="card-demo-flex card-demo-content01">
          <div class="vux-1px-r">
            ￥<span>11111</span>
            <br/>
            奖金
          </div>
          <div class="vux-1px-r">
            ￥<span>11111</span>
            <br/>
            报单币
          </div>
          <div class="vux-1px-r">
            ￥<span>1111</span>
            <br/>
            产品积分
          </div>
        </div>
      </card>

    </group>

    <group>
      <cell title='奖金转换报单币' is-link @click.native="goTo('convertConsumeCoin')">
        <img slot="icon" width="20" style="display:block;margin-right:10px;" src="../../assets/icon/tel.png">
      </cell>
      <cell title='报单币转账' is-link @click.native="goTo('transferConsumeCoin')">
        <img slot="icon" width="20" style="display:block;margin-right:10px;" src="../../assets/icon/tel.png">
      </cell>
      <cell title='重复消费' is-link @click.native="goTo('reConsume')">
        <img slot="icon" width="20" style="display:block;margin-right:10px;"
             src="../../assets/icon/tel.png">
      </cell>
      <cell title='订单查询' is-link @click.native="goTo('orderView')">
        <img slot="icon" width="20" style="display:block;margin-right:10px;" src="../../assets/icon/tel.png">
      </cell>
      <cell title='修改登录密码' is-link @click.native="goTo('resetPassword')">
        <img slot="icon" width="20" style="display:block;margin-right:10px;" src="../../assets/icon/tel.png">
      </cell>
    </group>
  </div>
</template>

<script>
  import {Group, Panel, Cell, Card} from 'vux'

  export default {
    name: 'Home',
    components: {
      Group,
      Panel,
      Cell,
      Card
    },
    data () {
      return {
        owner: [{
          src: 'http://somedomain.somdomain/x.jpg',
          fallbackSrc: 'http://placeholder.qiniudn.com/60x60/3cc51f/ffffff',
          title: '昵称',
          desc: '第xxxxx位用户'
        }]
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .card-demo-flex {
    display: flex;
  }
  .card-demo-content01 {
    padding: 10px 0;
  }
  .card-padding {
    padding: 15px;
  }
  .card-demo-flex > div {
    flex: 1;
    text-align: center;
    font-size: 12px;
  }
  .card-demo-flex span {
    color: #f74c31;
  }
</style>
