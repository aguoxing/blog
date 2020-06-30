import request from '@/utils/request'
var group_name = 'tag'
export default {
  save(tags) {
    return request({
      url: `/${group_name}/save`,
      method: 'post',
      data: tags
    })
  },
  deleteById(id) {
    return request({
      url: `/${group_name}/delete/${id}`,
      method: 'put'
    })
  },
  update(tag) {
    return request({
      url: `/${group_name}/update`,
      method: 'put',
      data: tag
    })
  },
  getById(id) {
    return request({
      url: `/${group_name}/${id}`,
      method: 'get'
    })
  },
  listTag(page) {
    return request({
      url: `/${group_name}/list`,
      method: 'post',
      data: page
    })
  }
}
