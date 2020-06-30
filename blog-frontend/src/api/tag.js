import request from '@/utils/request'
var group_name = 'tag'
export default {
  listTag(page) {
    return request({
      url: `/${group_name}/list`,
      method: 'post',
      data: page
    })
  }
}
