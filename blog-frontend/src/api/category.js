import request from '@/utils/request'
var group_name = 'category'
export default {
  getById(id) {
    return request({
      url: `/${group_name}/${id}`,
      method: 'get'
    })
  },
  listCategory(page) {
    return request({
      url: `/${group_name}/list`,
      method: 'post',
      data: page
    })
  }
}
