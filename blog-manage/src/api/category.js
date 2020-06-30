import request from '@/utils/request'
var group_name = 'category'
export default {
  save(category) {
    return request({
      url: `/${group_name}/save`,
      method: 'post',
      data: category
    })
  },
  deleteById(id) {
    return request({
      url: `/${group_name}/delete/${id}`,
      method: 'put'
    })
  },
  update(category) {
    return request({
      url: `/${group_name}/update`,
      method: 'put',
      data: category
    })
  },
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
