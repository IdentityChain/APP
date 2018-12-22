<template>
  <div style="width: 100%;">
    <div
      style="width: 90%;margin-left: 5%;height: 60px;background: transparent; border: 1px solid lightgray;border-radius: 10px;margin-top: 10px;display: flex;align-items:Center;flex-direction: row">
      <div style="height: 40px;width: 40px;margin-left: 8px;">
        <img :src="'/static/image/power/task/' + (taskObj.status === 'running' ? taskObj.imgUrl : taskObj.imgUrlDone)"
             width="40px" height="40px"/>
      </div>
      <div style="height: 50px;width: auto;margin-left: 10px;line-height: 50px;">
        <p v-if="taskObj.status === 'running'" style="color: rgb(50, 112, 222);font-size: small">{{taskObj.title}}</p>
        <p v-if="taskObj.status === 'done'" style="color: grey;font-size: small">{{taskObj.title}}</p>
      </div>
      <div style="margin-left: 5px;line-height: 10px;">
        <img v-if="taskObj.status === 'running'" src="../../assets/power/task/image/help.png" height="20px" width="20px" @click="showAlert"/>
        <img v-if="taskObj.status === 'done'" src="../../assets/power/task/image/help-done.png" height="20px" width="20px" @click="showAlert"/>
      </div>
      <div style="height: 50px;margin-left: auto;display: flex;align-items:Center;">
        <div v-if="taskObj.status === 'done'">
          <p style="color: grey;margin-right: 8px;">已完成</p>
        </div>
        <div v-else>
          <p v-show="taskObj.toolType === 'text'" style="color: rgb(50, 112, 222);margin-right: 8px;">
            {{taskObj.toolText}}</p>
          <x-button v-show="taskObj.toolType === 'button'" mini @click.native="sendEvent"
                    style="color: white;margin-right: 8px;background: rgb(50, 112, 222);">{{taskObj.toolText}}
          </x-button>
        </div>
      </div>
    </div>
    <div v-transfer-dom>
      <x-dialog v-model="showHelp" hide-on-blur :dialog-style="{'max-width': '100%', width: '100%', height: '50%', 'background-color': 'transparent'}">
        <p style="color:#fff;text-align:center;" @click="showHelp = false">
          <span style="font-size: large">{{taskObj.describe}}</span>
          <br>
          <br>
          <x-icon type="ios-close-outline" style="fill:#fff;"></x-icon>
        </p>
      </x-dialog>
    </div>
  </div>
</template>

<script>
  import {XButton, XDialog, TransferDomDirective as TransferDom} from 'vux'

  export default {
    name: 'TaskList',
    props: ['taskObj'],
    directives: {
      TransferDom
    },
    components: {
      XButton,
      XDialog
    },
    data () {
      return {
        showHelp: false
      }
    },
    methods: {
      showAlert () {
        this.showHelp = true
      },
      sendEvent () {
        this.$emit('do-task', this.taskObj.toolEvent)
      }
    }
  }
</script>

<style scoped>

</style>
