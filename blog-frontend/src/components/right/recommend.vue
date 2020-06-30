<template>
  <div>
    <Card :dis-hover="true">
      <p slot="title">推荐阅读</p>
      <router-link v-for="item in article" :key="item.id" :to="'/article/details/'+item.id" class="recommend-card">
        <div class="blog-title">
          {{ item.title }}
        </div>
        <div class="blog-tag">
          <Tag v-for="tag in item.tagList" :key="tag.id" color="cyan">{{ tag.name }}</Tag>
        </div>
        <div class="blog-bottom">
          <div class="blog-time">{{ item.createTime }}</div>
          <div class="blog-like">点赞-{{ item.likeCount }}</div>
        </div>
      </router-link>
    </Card>
  </div>
</template>

<script>
import articleApi from '@/api/article'
export default {
  data() {
    return {
      article: {}
    }
  },
  created() {
    this.getRecommendList()
  },
  methods: {
    getRecommendList() {
      articleApi.listRecommend().then(res => {
        this.article = res.data
      })
    }
  }
}
</script>

<style scoped>
.recommend-card{
  display: flex;
  flex-direction: column;
}
.blog-title{
  font-size: 16px;
  line-height: 25px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.blog-tag{
  line-height: 30px;
}
.blog-bottom{
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  font-size: 12px;
  color: #b8b8b8;
}
</style>
