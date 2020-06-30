const state = {
  tagList: [],
  categoryList: {}
}

const mutations = {
  SET_TAG: (state, tagList) => {
    state.tagList = tagList
  },
  SET_CATEGORY: (state, categoryList) => {
    state.categoryList = categoryList
  }
}

export default {
  namespaced: true,
  state,
  mutations
}
