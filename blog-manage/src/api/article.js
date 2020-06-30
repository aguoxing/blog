import request from '@/utils/request'
var group_name = 'article'
export default {
  save(article) {
    return request({
      url: `/${group_name}/save`,
      method: 'post',
      data: article
    })
  },
  update(article) {
    return request({
      url: `/${group_name}/update`,
      method: 'put',
      data: article
    })
  },
  deleteById(id) {
    return request({
      url: `/${group_name}/delete/${id}`,
      method: 'put',
      data: id
    })
  },
  listArticle(page) {
    return request({
      url: `/${group_name}/list`,
      method: 'post',
      data: page
    })
  },
  getById(id) {
    return request({
      url: `/${group_name}/${id}`,
      method: 'get',
      data: id
    })
  }
}
