import request from '@/utils/request'
var group_name = 'comment'
export default {
  toComment(data) {
    return request({
      url: `/${group_name}/save`,
      method: 'post',
      data: data
    })
  },
  listComment(id) {
    return request({
      url: `/${group_name}/list/${id}`,
      method: 'get'
    })
  }
}
