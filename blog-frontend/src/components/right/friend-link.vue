<template>
  <div>
    <Card :dis-hover="true">
      <p slot="title">友链</p>
      <a v-for="item in linkList" :key="item.id" target="_blank" class="link-container" :href="item.url">
        <!-- <img src="@/assets/h1.jpg" class="link-avatar"> -->
        <Avatar icon="ios-person" />
        <div class="link-info">
          <h3>{{ item.nickname }}</h3>
          <h5>{{ item.url }}</h5>
        </div>
      </a>
      <a slot="extra" href="javascript:void(0)" @click="openAddDialog">
        申请
      </a>
    </Card>
    <!-- 添加弹窗 -->
    <Modal
      v-model="addDialog"
      title="申请友链"
      class-name="vertical-center-modal"
      ok-text="提交"
      @on-ok="handleSubmit('applyLink')"
    >
      <Form ref="applyLink" :model="applyLink" :rules="ruleInline">
        <FormItem prop="nickname">
          <Input v-model="applyLink.nickname" type="text" placeholder="名称" />
        </FormItem>
        <FormItem prop="url">
          <Input v-model="applyLink.url" type="text" placeholder="链接" />
        </FormItem>
      </Form>
    </Modal>
  </div>
</template>

<script>
import linkApi from '@/api/friendLink'
export default {
  data() {
    return {
      linkList: [],
      applyLink: {},
      ruleInline: {
        nickname: [
          { required: true, message: '名称不能为空...', trigger: 'blur' }
        ],
        url: [
          { required: true, type: 'url', message: '请输入合法的url', trigger: 'blur' }
        ]
      },
      addDialog: false // 控制弹窗展示
    }
  },
  created() {
    this.getLinkList()
  },
  methods: {
    getLinkList() {
      linkApi.getList().then(res => {
        this.linkList = res.data
      })
    },
    handleSubmit(name) {
      this.$refs[name].validate((valid) => {
        if (valid) {
          linkApi.save(this.applyLink).then(res => {
            this.$Message.success('提交成功，等待博主审核...')
            this.applyLink = {}
          })
        } else {
          this.$Message.error('提交失败')
        }
      })
    },
    openAddDialog() {
      // 打开添加弹窗
      this.addDialog = true
    },
    closeAddDialog() {
      // 关闭添加弹窗
      this.addDialog = false
    }
  }
}
</script>

<style>
.link-container{
  display: flex;
  flex-direction: row;
  margin-bottom: 2px;
}
.link-avatar{
  width: 50px;
  height: 50px;
}
.link-info{
  padding-left: 10px;
}
</style>
