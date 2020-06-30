<template>
  <div>
    <el-form ref="form" :model="article" label-width="80px" size="mini">
      <el-form-item label="作者">
        <el-input v-model="article.author" />
      </el-form-item>
      <el-form-item label="标题">
        <el-input v-model="article.title" />
      </el-form-item>
      <el-form-item label="摘要">
        <el-input v-model="article.summary" />
      </el-form-item>
      <el-form-item label="置顶">
        <el-switch
          v-model="article.isTop"
          active-text="是"
          inactive-text="否"
        />
      </el-form-item>
      <el-form-item label="推荐">
        <el-switch
          v-model="article.isRecommend"
          active-text="是"
          inactive-text="否"
        />
      </el-form-item>
      <el-form-item label="发布">
        <el-switch
          v-model="article.isEnable"
          active-text="是"
          inactive-text="否"
        />
      </el-form-item>
      <el-form-item label="分类">
        <el-select
          v-model="article.category"
          value-key="id"
          clearable
          filterable
          placeholder="请选择"
          style="width: 100%"
        >
          <el-option
            v-for="category in categoryList"
            :key="category.id"
            :label="category.categoryName"
            :value="category"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="标签">
        <el-select
          v-model="article.tagList"
          value-key="id"
          multiple
          filterable
          allow-create
          default-first-option
          placeholder="请选择文章标签"
        >
          <el-option v-for="tag in tagList" :key="tag.id" :label="tag.name" :value="tag" />
        </el-select>
      </el-form-item>
      <el-form-item label="内容">
        <mavon-editor v-model="article.content" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" size="mini" @click="onSubmit">提交</el-button>
        <el-button size="mini" @click="cancel">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import articleApi from '@/api/article'
export default {
  props: {
    article: {
      type: Object,
      default: null
    }
  },
  data() {
    return {
      tagList: this.$store.getters.tagList,
      categoryList: this.$store.getters.categoryList
    }
  },
  methods: {
    onSubmit() {
      articleApi.update(this.article).then(res => {
        console.log(this.article)
        this.$message.success(res.msg)
        // 关闭弹窗
        this.$emit('closeUpdateDialog')
        // 更新数据
        this.$emit('getArticleList')
      })
    },
    cancel() {
      this.$emit('closeUpdateDialog')
    }
  }
}
</script>
