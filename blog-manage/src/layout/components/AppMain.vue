<template>
  <section class="app-main">
    <transition name="fade-transform" mode="out-in">
      <router-view :key="key" />
    </transition>
  </section>
</template>

<script>
import tagApi from '@/api/tag'
import categoryApi from '@/api/category'
export default {
  name: 'AppMain',
  data() {
    return {
      page: {
        totalCount: 0,
        pageSize: 20,
        totalPage: 0,
        currPage: 1,
        list: [],
        params: {},
        sortColumn: ''
      }
    }
  },
  computed: {
    key() {
      return this.$route.path
    }
  },
  created() {
    this.getTagList()
    this.getCategoryList()
  },
  methods: {
    getTagList() {
      tagApi.listTag(this.page).then(res => {
        this.$store.commit('global/SET_TAG', res.data.list)
      })
    },
    getCategoryList() {
      categoryApi.listCategory(this.page).then(res => {
        this.$store.commit('global/SET_CATEGORY', res.data.list)
      })
    }
  }
}
</script>

<style scoped>
.app-main {
  /*50 = navbar  */
  min-height: calc(100vh - 50px);
  width: 100%;
  position: relative;
  overflow: hidden;
  margin: 15px;
}
.fixed-header+.app-main {
  padding-top: 50px;
}
</style>

<style lang="scss">
// fix css style bug in open el-dialog
.el-popup-parent--hidden {
  .fixed-header {
    padding-right: 15px;
  }
}
</style>
