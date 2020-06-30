<template>
  <div>
    <div class="login-container">
      <Card class="form-card">
        <div class="login-title">欢迎来到xing'blog</div>
        <div class="tab-container">
          <Tabs value="login">
            <TabPane label="登录" name="login">
              <div class="login-form">
                <Form ref="loginForm" :model="loginForm" :rules="ruleValidate" :label-width="80">
                  <FormItem label="用户名" prop="username">
                    <Input v-model="loginForm.username" placeholder="请输入用户名..." />
                  </FormItem>
                  <FormItem label="密码" prop="password">
                    <Input v-model="loginForm.password" type="password" placeholder="请输入密码..." />
                  </FormItem>
                  <FormItem>
                    <Button type="primary" @click.native.prevent="login">登录</Button>
                    <Button :loading="loading" style="margin-left: 8px" @click="handleReset('loginForm')">重置</Button>
                  </FormItem>
                </Form>
                <Divider>第三方登录</Divider>
                <div class="social-container">
                  <div class="social-item">
                    <a href="">
                      <img src="@/assets/social_wechat.png" class="social-icon">
                    </a>
                    <a :href="githubUrl">
                      <img src="@/assets/social_github.png" class="social-icon">
                    </a>
                    <a :href="qqUrl">
                      <img src="@/assets/social_qq.png" class="social-icon">
                    </a>
                  </div>
                </div>
              </div>
            </TabPane>
            <TabPane label="注册" name="register">
              <div class="register-form">
                <Form ref="registerForm" :model="registerForm" :rules="ruleValidate" :label-width="80">
                  <FormItem label="用户名" prop="username">
                    <Input v-model="registerForm.username" placeholder="请输入用户名..." />
                  </FormItem>
                  <FormItem label="密码" prop="password">
                    <Input v-model="registerForm.password" type="password" placeholder="请输入密码..." />
                  </FormItem>
                  <FormItem label="邮箱" prop="email">
                    <Input v-model="registerForm.email" placeholder="请输入邮箱..." />
                  </FormItem>
                  <FormItem label="性别" prop="gender">
                    <RadioGroup v-model="registerForm.gender">
                      <Radio label="1">男</Radio>
                      <Radio label="0">女</Radio>
                    </RadioGroup>
                  </FormItem>
                  <FormItem>
                    <Button type="primary" @click.native.prevent="register">注册</Button>
                    <Button style="margin-left: 8px" @click="handleReset('registerForm')">重置</Button>
                  </FormItem>
                </Form>
              </div>
            </TabPane>
          </Tabs>
        </div>
      </Card>
    </div>
  </div>
</template>

<script>
import userApi from '@/api/user'
export default {
  data() {
    return {
      loading: false,
      githubUrl: '',
      qqUrl: '',
      loginForm: {
        username: '',
        password: ''
      },
      registerForm: {
        username: '',
        password: '',
        email: ''
      },
      ruleValidate: {
        username: [
          { required: true, message: '用户名不能为空！', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '密码不能为空！', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '邮箱不能为空！', trigger: 'blur' },
          { type: 'email', message: '请输入正确的邮箱格式！', trigger: 'blur' }
        ]
      },
      redirect: undefined
    }
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true
    }
  },
  created() {
    this.loginWithGithub()
    this.loginWithQq()
  },
  methods: {
    login() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          this.$store.dispatch('user/login', this.loginForm).then(() => {
            this.$router.push({ path: this.redirect || '/' })
            this.loading = false
          }).catch(() => {
            this.loading = false
          })
        } else {
          return false
        }
      })
    },
    loginWithGithub() {
      userApi.loginWithGithub().then(res => {
        this.githubUrl = res.msg
      })
    },
    loginWithQq() {
      userApi.loginWithQq().then(res => {
        this.qqUrl = res.msg
      })
    },
    register() {
      this.$refs.registerForm.validate(valid => {
        if (valid) {
          this.loading = true
          this.$store.dispatch('user/register', this.registerForm).then(() => {
            this.$Message.success('注册成功')
            this.loading = false
          }).catch(() => {
            this.loading = false
          })
        } else {
          return false
        }
      })
    },
    handleReset(name) {
      this.$refs[name].resetFields()
    }
  }
}
</script>

<style>
.login-container{
  width: 100%;
  height: 100%;
  position: fixed;
  background-image: url('/bg.jpg');
}
.form-card{
  width: 450px;
  min-height: 350px;
  margin: 150px auto;
}
.login-title{
  font-size: 24px;
  font-weight: bold;
  text-align: center;
  margin-bottom: 20px;
}
.ivu-tabs-nav{
  width: 100%;
}
.ivu-tabs-tab{
  width: 50%;
  text-align: center;
}
.social-container{
  width: 100%;
  display: flex;
  flex-direction: row;
  align-items: center;
}
.social-item{
  width: 200px;
  margin: auto;
  display: flex;
  flex-direction: row;
  justify-content: center;
}
.social-icon{
  width: 40px;
  height: 40px;
}
</style>
