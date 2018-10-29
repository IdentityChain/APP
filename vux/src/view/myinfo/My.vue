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
      </box>
    </group>
    <!--</Box>-->
    <group>
      <cell title='加入社群' is-link link="/my/transferConsumeCoin">
        <!--<img slot="icon" width="20" style="display:block;margin-right:10px;" src="../../assets/icon/tel.png">-->
      </cell>
      <cell title='帮助中心' is-link link="/template">
        <!--<img slot="icon" width="20" style="display:block;margin-right:10px;"-->
             <!--src="../../assets/icon/tel.png">-->
      </cell>
    </group>
    <group>
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
    },
    data () {
      return {
      }
    },
    methods: {
    },
    computed: {
      owner: function () {
        return [{
          src: require('../../assets/my/people.png'),
          title: this.$db.get('User').nickName,
          desc: '',
          url: '/my/resetSetting'
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
