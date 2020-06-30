import request from '@/utils/request'
var group_name = 'link'
export default {
  save(link) { // 添加
    return request({
      url: `/${group_name}/save`,
      method: 'post',
      data: link
    })
  },
  getList() { // 查询
    return request({
      url: `/${group_name}/list/enable`,
      method: 'get'
    })
  }
}
