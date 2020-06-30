<template>
  <div>
    <div class="right-container">
      <Recommend />
      <tag-list />
      <article-toc />
    </div>
  </div>
</template>

<script>
import Recommend from '@/components/right/recommend'
import tagList from '@/components/right/tag-list'
import articleToc from '@/components/right/article-toc'
export default {
  components: {
    Recommend,
    tagList,
    articleToc
  }
}
</script>
<style>
.right-container{
  width: 350px;
  padding: 0 5px;
}
.ivu-card-head{
  border-left: 2px solid skyblue;
}
.ivu-card{
  border-radius: 0 !important;
  margin-bottom: 2px;
}
</style>
