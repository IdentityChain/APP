<template>
  <div>
    <group>
      <panel :list="owner" type="1">

      </panel>
    </group>

    <!--<Box gap="10px 10px">-->


      <group>
        <card>
          <div slot="content" class="card-demo-flex card-demo-content01">
            <div class="vux-1px-r">
              <span><img style="height: 30px;width: 30px" src="../../assets/icon/tel.png"/></span>
              <br/>
              奖金
            </div>
            <div class="vux-1px-r">
              <span><img style="height: 30px;width: 30px" src="../../assets/icon/tel.png"/></span>
              <br/>
              奖金
            </div>
            <div class="vux-1px-r">
              <span><img style="height: 30px;width: 30px" src="../../assets/icon/tel.png"/></span>
              <br/>
              奖金
            </div>
          </div>
          <div slot="content" class="card-demo-flex card-demo-content01">
            <div class="vux-1px-r">
              <span><img style="height: 30px;width: 30px" src="../../assets/icon/tel.png"/></span>
              <br/>
              奖金
            </div>
            <div class="vux-1px-r">
              <span><img style="height: 30px;width: 30px" src="../../assets/icon/tel.png"/></span>
              <br/>
              奖金
            </div>
            <div class="vux-1px-r">
              <span><img style="height: 30px;width: 30px" src="../../assets/icon/tel.png"/></span>
              <br/>
              奖金
            </div>
          </div>
        </card>

      </group>
    <!--</Box>-->

    <group>
      <cell title='联系人' is-link @click.native="goTo('convertConsumeCoin')">
        <img slot="icon" width="20" style="display:block;margin-right:10px;" src="../../assets/icon/tel.png">
      </cell>
      <cell title='加入社群' is-link @click.native="goTo('transferConsumeCoin')">
        <img slot="icon" width="20" style="display:block;margin-right:10px;" src="../../assets/icon/tel.png">
      </cell>
      <cell title='帮助中心' is-link @click.native="goTo('reConsume')">
        <img slot="icon" width="20" style="display:block;margin-right:10px;"
             src="../../assets/icon/tel.png">
      </cell>
    </group>
    <group>
      <cell title='关于我们' is-link @click.native="goTo('orderView')">
        <img slot="icon" width="20" style="display:block;margin-right:10px;" src="../../assets/icon/tel.png">
      </cell>
      <cell title='系统设置' is-link @click.native="goTo('resetPassword')">
        <img slot="icon" width="20" style="display:block;margin-right:10px;" src="../../assets/icon/tel.png">
      </cell>
    </group>
    <br>
    <br>
  </div>
</template>

<script>
  import {Group, Panel, Cell, Card, Box} from 'vux'

  export default {
    name: 'Home',
    components: {
      Group,
      Panel,
      Cell,
      Card,
      Box
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
