import request from '@/utils/request'
var group_name = 'log'
export default {
  listLog(page) {
    return request({
      url: `/${group_name}/list`,
      method: 'post',
      data: page
    })
  }
}
