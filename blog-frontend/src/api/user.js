import request from '@/utils/request'

export function login(username, password) {
  return request({
    url: '/user/login',
    method: 'post',
    params: { username, password }
  })
}

export function register(user) {
  return request({
    url: '/user/register',
    method: 'post',
    data: user
  })
}

export default {
  getMyInfo(id) {
    return request({
      url: `/user/myinfo/${id}`,
      method: 'get'
    })
  },
  updateUserInfo(data) {
    return request({
      url: '/user/update',
      method: 'put',
      data: data
    })
  },
  loginWithGithub() {
    return request({
      url: '/user/login/github',
      method: 'get'
    })
  },
  loginWithQq() {
    request({
      url: '/user/login/qq',
      method: 'get'
    })
  }
}

export function getInfo() {
  return request({
    url: '/user/info',
    method: 'get'
  })
}

export function logout() {
  return request({
    url: '/user/logout',
    method: 'post'
  })
}
