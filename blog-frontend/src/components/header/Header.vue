<template>
  <div>
    <div class="menu-container">
      <div class="nav-logo">
        <img src="@/assets/logo.png" class="logo-img">
      </div>
      <div class="nav">
        <Input suffix="ios-search" clearable="true" placeholder="这里可以搜到奇怪的东西哦..." style="width: auto" />
        <Menu mode="horizontal" active-name="1">
          <MenuItem name="1">
            <router-link to="/">
              首页
            </router-link>
          </MenuItem>
          <MenuItem name="2">
            <router-link to="/category">
              分类
            </router-link>
          </MenuItem>
          <MenuItem name="3">
            <router-link to="/archive">
              归档
            </router-link>
          </MenuItem>
          <MenuItem name="4">
            <router-link to="/about">
              关于
            </router-link>
          </MenuItem>
        </Menu>

        <div class="demo-avatar">
          <Dropdown>
            <Avatar icon="ios-person" :src="avatar" />
            <DropdownMenu v-if="name" slot="list">
              <DropdownItem>
                {{ name }}
              </DropdownItem>
              <DropdownItem>
                <router-link to="/user/info">
                  个人中心
                </router-link>
              </DropdownItem>
              <DropdownItem divided @click.native="logout">
                退出登录
              </DropdownItem>
            </DropdownMenu>
            <DropdownMenu v-else slot="list">
              <DropdownItem>
                <router-link to="/login">
                  登录/注册
                </router-link>
              </DropdownItem>
            </DropdownMenu>
          </Dropdown>

        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  data() {
    return {
      value: ''
    }
  },
  computed: {
    ...mapGetters(['name']),
    ...mapGetters(['avatar'])
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true
    }
  },
  methods: {
    async logout() {
      await this.$store.dispatch('user/logout')
      this.$Message.success('退出成功')
    }
  }
}
</script>

<style scoped>
.menu-container{
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  padding: 0 60px;
  background-color: #ffffff;
  margin-bottom: 10px;
}
.nav-logo{
  display: flex;
  flex-direction: row;
  align-items: center;
}
.logo-img{
  widows: 180px;
  height: 60px;
}
.nav{
  display: flex;
  flex-direction: row;
  align-items: center;
  margin-right: 40px;
}
.ivu-menu-horizontal {
  border-bottom: none !important;
}
.ivu-menu-horizontal.ivu-menu-light:after{
  background: none;
}
.ivu-menu-horizontal a{
color: black;
}
</style>
