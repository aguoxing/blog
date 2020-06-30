<template>
  <div>
    <div class="button-container">
      <div class="nav-logo">
        <div class="logo-text">{{ name }}</div>
      </div>
      <div class="nav">
        <Menu mode="horizontal" active-name="create_time" @on-select="changeSort">
          <MenuItem name="create_time">
            最新
          </MenuItem>
          <MenuItem name="like_count">
            点赞最多
          </MenuItem>
          <MenuItem name="views">
            阅读最多
          </MenuItem>
        </Menu>
      </div>
    </div>
    <!-- 内容列表 -->
    <div class="list-container">
      <Card v-for="item in page.list" :key="item.id" class="content-card">
        <div class="content-main">
          <div v-if="item.cover" class="blog-image">
            <img :src="item.cover" class="blog-cover">
          </div>
          <router-link :to="'/article/details/'+item.id" :class="item.cover ? 'img-content-container' : 'content-container'">
            <div class="blog-title">{{ item.title }}</div>
            <div class="blog-summary">{{ item.summary }}</div>
            <div class="blog-bottom">
              <div class="blog-tag">
                <Tag v-if="item.isTop" color="error">置顶</Tag>
                <Tag v-for="tag in item.tagList" :key="tag.id" color="cyan">{{ tag.name }}</Tag>
              </div>
              <div class="blog-meta">
                <div class="blog-icon"><Icon type="md-time" size="18" />
                  {{ item.createTime | formatTime }}
                </div>
                <div class="blog-icon"><Icon type="ios-eye" size="18" />
                  {{ item.views }}
                </div>
                <div class="blog-icon"><Icon type="ios-text" size="18" />
                  {{ item.commentCount }}
                </div>
                <div class="blog-icon"><Icon type="md-thumbs-up" size="18" />
                  {{ item.likeCount }}
                </div>
              </div>
            </div>
          </router-link>
        </div>
      </Card>
    </div>
    <!-- 分页按钮 -->
    <div class="blog-pagination">
      <Page :current="page.currPage" :total="page.totalPage" simple />
    </div>
  </div>
</template>

<script>
import articleApi from '@/api/article'
export default {
  props: {
    name: {
      type: String,
      default: '文章'
    }
  },
  data() {
    return {
      page: {
        totalCount: 0,
        pageSize: 10,
        totalPage: 0,
        currPage: 1,
        list: [],
        params: { isEnable: true },
        sortColumn: 'create_time'
      }
    }
  },
  created() {
    this.getArticleList()
  },
  methods: {
    getArticleList() {
      articleApi.listArticle(this.page).then(res => {
        this.page = res.data
      })
    },
    changeSort(e) {
      this.page.sortColumn = e
      this.page.params = { isEnable: true }
      this.getArticleList()
    }
  }
}
</script>
<style scoped>
.button-container{
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
}
.nav-logo{
  display: flex;
  flex-direction: row;
  align-items: center;
  font-size: 16px;
}
.logo-img{
  widows: 180px;
  height: 60px;
}
.nav{
  display: flex;
  flex-direction: row;
  align-items: center;
}
.ivu-menu-horizontal{
  background: none !important;
}
.ivu-menu{
  background: none !important;
}

a{
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    color: #3e4149;
}
.content-container{
  height: 100px;
    width: 100%;
}
.img-content-container{
    width: 650px;
}
.blog-title{
    font-size: 16px;
    margin-bottom: 10px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}
.blog-summary{
    font-size: 14px;
    color: #9c9ea8;
    max-height: 50px;
    overflow: hidden;
    text-overflow: ellipsis;
    line-height: 20px;
}
.blog-bottom{
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
}
.blog-meta{
    display: flex;
    color: #3e4149;
    font-size: 14px;
}
.blog-image{
    width: 150px;
    height: 100px;
    margin-right: 20px;
}
.blog-cover{
    width: 100%;
    height: 100%;
}
.content-main{
    display: flex;
    flex-direction: row;
}
.content-card{
    margin-bottom: 8px;
}
.blog-icon{
  color: gray;
  display: flex;
  margin-right: 6px;
}
</style>
