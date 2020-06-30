<template>
  <div>
    <div class="right-container">
      <user-info />
      <Recommend />
      <tag-list />
      <friend-link />
    </div>
  </div>
</template>

<script>
import userInfo from '@/components/right/user-info'
import Recommend from '@/components/right/recommend'
import tagList from '@/components/right/tag-list'
import friendLink from '@/components/right/friend-link'
export default {
  components: {
    userInfo,
    Recommend,
    tagList,
    friendLink
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
