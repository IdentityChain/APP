<template>
  <div class="">
    <group>
      <!--<cell title="公告">-->
      <!--<marquee>-->
      <!--<marquee-item v-for="i in 5" :key="i" @click.native="onClick(i)">{{ 'JavaScript is the best language'}}-->
      <!--{{i}}-->
      <!--</marquee-item>-->
      <!--</marquee>-->
      <!--</cell>-->
      <div style="height: 350px;">
        <index-panel></index-panel>
      </div>
      <cell title="公告">
        <marquee style="width: 80%;text-align: right">
          <marquee-item>xxxxxxxxxxxxxx111111111111122222222222222</marquee-item>
        </marquee>
      </cell>

      <swiper :list="demo01_list" v-model="demo01_index" @on-index-change="demo01_onIndexChange"></swiper>
    </group>
    <group title="开心夺宝">
      <img style="width: 100%" src="../../assets/swipper.png"/>
    </group>
  </div>
</template>

<script>
  import {Group, Cell, Swiper, Marquee, MarqueeItem} from 'vux'
  import IndexPanel from '../../components/IndexPanel'

  const baseList = [{
    url: 'javascript:',
    img: 'https://ww1.sinaimg.cn/large/663d3650gy1fq66vvsr72j20p00gogo2.jpg',
    title: '送你一朵fua'
  }, {
    url: 'javascript:',
    img: 'https://ww1.sinaimg.cn/large/663d3650gy1fq66vw1k2wj20p00goq7n.jpg',
    title: '送你一辆车'
  }, {
    url: 'javascript:',
    img: 'https://static.vux.li/demo/5.jpg', // 404
    title: '送你一次旅行',
    fallbackImg: 'https://ww1.sinaimg.cn/large/663d3650gy1fq66vw50iwj20ff0aaaci.jpg'
  }]

  export default {
    name: 'Home',
    components: {
      Group,
      Cell,
      Swiper,
      Marquee,
      MarqueeItem,
      IndexPanel
    },
    data () {
      return {
        demo01_index: 0,
        demo01_list: baseList
      }
    },
    methods: {
      onSwiperItemIndexChange (index) {
        console.log('demo item change', index)
      },
      demo01_onIndexChange (index) {
        this.demo01_index = index
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
