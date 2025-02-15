const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  tagList: state => state.global.tagList,
  categoryList: state => state.global.categoryList
}
export default getters
