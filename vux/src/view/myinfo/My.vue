<template>
  <div style="background-color: #ECECEC">
    <group>
      <panel :list="owner" type="1">
      </panel>
    </group>
    <!--<Box gap="10px 10px">-->
    <group>
      <Cell title="身份标签"></Cell>
      <box gap="5px 10px">
        <x-button mini type="primary" style="background-color: purple;margin-right: 20px">ISC居民</x-button>
        <x-button mini type="primary" style="background-color: blue;margin-right: 20px">区块链爱好者</x-button>
        <!-- <x-button mini type="primary" style="margin-right: 20px"></x-button> -->
        <br>
        &nbsp;
      </box>
    </group>
    <!--</Box>-->
    <group>
      <cell title='联系人' is-link   link="/my/convertConsumeCoin">
        <!--<img slot="icon" width="20" style="display:block;margin-right:10px;" src="../../assets/icon/tel.png">-->
      </cell>
      <cell title='加入社群' is-link link="/my/transferConsumeCoin">
        <!--<img slot="icon" width="20" style="display:block;margin-right:10px;" src="../../assets/icon/tel.png">-->
      </cell>
      <cell title='帮助中心' is-link link="/my/reConsume">
        <!--<img slot="icon" width="20" style="display:block;margin-right:10px;"-->
             <!--src="../../assets/icon/tel.png">-->
      </cell>
    </group>
    <group>
      <cell title='关于我们' is-link link="/my/orderView">
        <!--<img slot="icon" width="20" style="display:block;margin-right:10px;" src="../../assets/icon/tel.png">-->
      </cell>
      <cell title='系统设置' is-link link="/my/systemSetting">
        <!--<img slot="icon" width="20" style="display:block;margin-right:10px;" src="../../assets/icon/tel.png">-->
      </cell>
    </group>
    <br>
    <br>
  </div>
</template>

<script>
  import {Group, Panel, Cell, Card, Box, CellFormPreview, XButton} from 'vux'

  export default {
    name: 'Home',
    components: {
      XButton,
      Group,
      Panel,
      Cell,
      Card,
      Box,
      CellFormPreview
    },
    mounted: function () {
      const userOjb = JSON.parse(window.localStorage.getItem('User'))
      this.user = userOjb
      this.owner[0].title = this.user.nickName
      // this.owner[0].desc = '第' + (this.user.userId + 1) + '位用户'
    },
    data () {
      return {
        user: '',
        owner: [{
          src: require('../../assets/my/people.png'),
          title: '昵称',
          desc: '',
          url: '/my/resetSetting'
        }]
      }
    },
    methods: {
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
