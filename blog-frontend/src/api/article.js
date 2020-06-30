import request from '@/utils/request'
var group_name = 'article'
export default {
  readById(id) {
    return request({
      url: `/${group_name}/details/${id}`,
      method: 'get'
    })
  },
  listArticle(page) {
    return request({
      url: `/${group_name}/list`,
      method: 'post',
      data: page
    })
  },
  listRecommend() {
    return request({
      url: `/${group_name}/recommend/list`,
      method: 'get'
    })
  },
  listArchive() {
    return request({
      url: `/${group_name}/archive/list`,
      method: 'get'
    })
  }
}
