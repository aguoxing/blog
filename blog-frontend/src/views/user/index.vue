<template>
  <div>
    <div class="uinfo-container">
      <div class="uheader-container">
        <div class="user-avatar">
          <img :src="userInfo.avatar" class="my-avatar">
          <Upload
            action="uploadUrl"
            :on-success="uploadSuccess"
            :headers="headers"
          >
            <Button icon="ios-cloud-upload-outline">点我修改</Button>
          </Upload>
        </div>
        <div class="user-info">
          <p>ID：{{ userInfo.id }}</p>
          <p>用户名：{{ userInfo.username }}</p>
          <p>密码：******</p>
          <p>性别：<span v-if="userInfo.gender==1">男</span> <span v-else-if="userInfo.gender==0">女</span> <span v-else>未知</span> </p>
          <p>注册时间：{{ userInfo.userCreateTime }}</p>
        </div>
      </div>
      <Divider>完善信息</Divider>
      <div class="meta-container">
        <div class="user-form">
          <Form ref="userInfo" :model="userInfo" :rules="ruleValidate" :label-width="80">
            <FormItem label="账号" prop="id">
              <Input v-model="userInfo.id" disabled placeholder="账号" />
            </FormItem>
            <FormItem label="用户名" prop="username">
              <Input v-model="userInfo.username" placeholder="用户名" />
            </FormItem>
            <FormItem label="密码" prop="password">
              <a href="javascript:void(0)" @click="openUpdateDialog">点我修改</a>
            </FormItem>
            <FormItem label="性别" prop="gender">
              <RadioGroup v-model="userInfo.gender">
                <Radio label="1">男</Radio>
                <Radio label="0">女</Radio>
              </RadioGroup>
            </FormItem>
            <FormItem label="邮箱" prop="email">
              <Input v-model="userInfo.email" placeholder="邮箱" />
            </FormItem>
            <FormItem label="号码" prop="tel">
              <Input v-model="userInfo.tel" placeholder="号码" />
            </FormItem>
            <FormItem label="生日" prop="birthday">
              <DatePicker v-model="userInfo.birthday" type="date" placeholder="选择日期" />
            </FormItem>
            <FormItem>
              <Button type="primary" @click="updateUser">提交</Button>
              <Button style="margin-left: 8px" @click="handleReset('userInfo')">重置</Button>
            </FormItem>
          </Form>
        </div>
      </div>

      <Modal
        v-model="dialog"
        title="修改密码"
        @on-ok="toResetPwd"
      >
        <Form ref="resetPwd" :model="resetPwd" :rules="ruleValidate" :label-width="80">
          <FormItem label="新密码" prop="password">
            <Input v-model="resetPwd.onePwd" type="password" placeholder="新密码" />
          </FormItem>
          <FormItem label="确认密码" prop="password">
            <Input v-model="resetPwd.twoPwd" type="password" placeholder="确认密码" />
          </FormItem>
        </Form>
      </Modal>

    </div>
  </div>
</template>
<script>
import userApi from '@/api/user'
import { mapGetters } from 'vuex'
import { getToken } from '@/utils/auth'
export default {
  data() {
    return {
      userInfo: {},
      resetPwd: {
        onePwd: '',
        twoPwd: '',
        headers: { // 上传文件的请求头
          Authorization: getToken()
        }
      },
      uploadUrl: process.env.VUE_APP_UPLOAD_URL, // 上传路径
      dialog: false,
      ruleValidate: {
        username: [
          { required: true, message: '用户名不能为空', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '邮箱不能为空', trigger: 'blur' },
          { type: 'email', message: '请输入正确的邮箱格式', trigger: 'blur' }
        ],
        gender: [
          { required: true, message: '请选择性别', trigger: 'change' }
        ]
      }
    }
  },
  computed: {
    ...mapGetters(['userId'])
  },
  created() {
    this.getUserInfo(this.userId)
  },
  methods: {
    getUserInfo(id) {
      userApi.getMyInfo(id).then(res => {
        this.userInfo = res.data
      })
    },
    updateUser() {
      userApi.updateUserInfo(this.userInfo).then(res => {
        this.$Message.success(res.msg)
        this.getUserInfo(this.userId)
      })
    },
    uploadSuccess(res, file) {
      this.$Message.success(res.msg)
      this.userInfo.avatar = res.data
      this.updateUser()
    },
    toResetPwd() {
      if (this.resetPwd.onePwd === this.resetPwd.twoPwd) {
        this.userInfo.password = this.resetPwd.twoPwd
        userApi.updateUserInfo(this.userInfo).then(res => {
          this.$Message.success(res.msg)
          this.getUserInfo(this.userId)
        })
      } else {
        this.$Message.error('两次输入的密码不一致')
      }
    },
    handleReset(name) {
      this.$refs[name].resetFields()
    },
    openUpdateDialog() {
      this.dialog = true
    },
    closeUpdateDialog() {
      this.dialog = false
    },
    uploadAvatar() {

    }
  }
}
</script>
<style scoped>
.uinfo-container {
  background-color: #ffffff;
}
.uheader-container {
  display: flex;
  flex-direction: row;
}
.user-avatar{
    text-align: center;
}
.my-avatar {
  width: 100px;
  height: 100px;
  border: 5px solid #e5e5e5;
  border-radius: 50%;
  display: block;
  margin: 15px;
}
.user-info {
    margin-top: 15px;
    width: 100%;
}
.meta-container {
    margin: 15px;
    padding-bottom: 15px;
}
</style>
