<template>
  <div>
    <panel :footer="footer" :list="list" :type="type" @on-img-error="onImgError"></panel>
    <load-more :show-loading="false" :tip="msg" background-color="#fbf9fe"></load-more>
  </div>
</template>

<i18n>
  Switch the type:
  zh-CN: 切换样式
  List of content with image:
  zh-CN: 图文组合列表
  More:
  zh-CN: 查看更多
</i18n>

<script>
  import {Panel, Group, LoadMore} from 'vux'

  export default {
    components: {
      Panel,
      Group,
      LoadMore
    },
    methods: {
      onImgError (item, $event) {
        console.log(item, $event)
      }
    },
    data () {
      return {
        msg: '更多活动敬请期待',
        type: '1',
        list: [{
          src: 'http://placeholder.qiniudn.com/60x60/',
          fallbackSrc: 'http://placeholder.qiniudn.com/60x60/3cc51f/ffffff',
          title: '写给未来的一封信',
          desc: '将你对未来的期望写入到区块链，让他永远的存在下去，未来你打开这个信息，希望你过的比现在好。',
          url: '/my/convertConsumeCoin'
        }, {
          src: 'http://placeholder.qiniudn.com/60x60/3cc51f/ffffff',
          title: '开心夺宝',
          desc: '开心夺宝',
          url: {
            path: '/my/convertConsumeCoin',
            replace: false
          },
          meta: {
            source: '来源信息',
            date: '时间',
            other: '其他信息'
          }
        }]
      }
    }
  }
</script>
