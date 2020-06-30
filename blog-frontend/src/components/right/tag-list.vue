<template>
  <div>
    <Card :dis-hover="true">
      <p slot="title">标签云</p>
      <Tag v-for="(item,index) in tagList" :key="index" type="dot" :color="index | mapTagColor">{{ item.name }} [{{ item.articleCount }}]</Tag>
    </Card>
  </div>
</template>

<script>
import tagApi from '@/api/tag'
export default {
  filters: {
    mapTagColor(index) {
      switch (index % 4) {
        case 0:
          return 'error'
        case 1:
          return 'primary'
        case 2:
          return 'success'
        case 3:
          return 'warning'
      }
    }
  },
  data() {
    return {
      tagList: [],
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
    this.getTagList()
  },
  methods: {
    getTagList() {
      tagApi.listTag(this.page).then(res => {
        this.tagList = res.data.list
      })
    }
  }
}
</script>

