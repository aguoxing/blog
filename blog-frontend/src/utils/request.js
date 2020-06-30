import axios from 'axios'
import { getToken } from '@/utils/auth'
import store from '@/store'
const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API,
  timeout: 5000 // request timeout
})

// request interceptor
service.interceptors.request.use(
  config => {
    // const token = getToken()
    if (store.getters.token) {
      config.headers['Authorization'] = getToken()
    }
    return config
  },
  error => {
    return Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(
  response => {
    const res = response.data

    if (res.code && res.code !== 20000) {
      if (res.code === 40003) {
        // 未登录
        this.$Message.error(res.msg)
        store.commit('SET_USER', {})
      }
      if (res.code === 50008 || res.code === 50012 || res.code === 50014) {
        // to re-login
        this.$Message.error('重新登录')
          .then(() => {
            store.dispatch('user/resetToken').then(() => {
              location.reload()
            })
          })
      }
      if (res.code === 400) {
        // to re-login
        this.$Message.error('用户名或密码错误')
          .then(() => {
            store.dispatch('user/resetToken').then(() => {
              location.reload()
            })
          })
      }
      return Promise.reject(new Error(res.msg || 'Error'))
    } else {
      return res
    }
  },
  error => {
    console.log('err-=>' + error) // for debug
    return Promise.reject(error)
  }
)

export default service
