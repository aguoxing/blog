<template>
  <div>
    <div class="category-container">
      <div class="category-title">分类</div>
      <div class="category-count">共计{{ categoryList.length }}个分类</div>
      <div class="category-content">
        <div v-for="item in categoryList" :key="item.id" class="category-item">
          {{ item.categoryName }} [{{ item.articleCount }}]
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import categoryApi from '@/api/category'
export default {
  data() {
    return {
      categoryList: {},
      page: {
        totalCount: 0,
        pageSize: 20,
        totalPage: 0,
        currPage: 1,
        list: [],
        params: { isEnable: 1 },
        sortColumn: ''
      }
    }
  },
  created() {
    this.getCategoryList()
  },
  methods: {
    getCategoryList() {
      categoryApi.listCategory(this.page).then(res => {
        this.categoryList = res.data.list
      })
    }
  }
}
</script>

<style scoped>
.category-container{
    background: #ffffff;
    padding: 30px 20px;
}
</style>
