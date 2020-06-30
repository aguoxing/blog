import request from '@/utils/request'
var group_name = 'link'
export default {
  listLink() {
    return request({
      url: `/${group_name}/list`,
      method: 'get'
    })
  },
  update(data) {
    return request({
      url: `/${group_name}/update`,
      method: 'put',
      data: data
    })
  }
}
