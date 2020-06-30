<template>
  <div>
    <div class="time-line">
      <Timeline v-for="(item,index) in articleList" :key="index">
        <h1>{{ item.year }}</h1>
        <TimelineItem v-for="data in item.articles" :key="data.id">
          <p class="time">{{ data.createTime }}</p>
          <router-link :to="'/article/details/'+data.id" class="content">
            {{ data.title }}
          </router-link>
        </TimelineItem>
      </Timeline>
    </div>
  </div>
</template>

<script>
import articleApi from '@/api/article'
export default {
  data() {
    return {
      articleList: {}
    }
  },
  created() {
    this.getArchiveList()
  },
  methods: {
    getArchiveList() {
      articleApi.listArchive().then(res => {
        this.articleList = res.data
      })
    }
  }
}
</script>

<style scoped>
.time-line{
    background: #ffffff;
    padding: 30px 20px;
}
.time{
    font-size: 14px;
    font-weight: bold;
}
.content{
    padding-left: 5px;
}
</style>
