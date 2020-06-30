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
      <el-form-item label="封面">
        <el-upload
          class="avatar-uploader"
          :action="uploadUrl"
          :show-file-list="false"
          :on-success="uploadSuccess"
          :headers="headers"
        >
          <img v-if="imageUrl" :src="imageUrl" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon" />
        </el-upload>
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
import { getToken } from '@/utils/auth'
export default {
  data() {
    return {
      article: {
        author: '',
        title: '',
        content: '',
        summary: '',
        category: {},
        tagList: []
      },
      headers: { // 上传文件的请求头
        Authorization: getToken()
      },
      imageUrl: null, // 上传回显
      uploadUrl: process.env.VUE_APP_UPLOAD_URL, // 上传路径
      tagList: this.$store.getters.tagList,
      categoryList: this.$store.getters.categoryList
    }
  },
  methods: {
    onSubmit() {
      articleApi.save(this.article).then(res => {
        this.$message.success(res.msg)
        // 关闭弹窗
        this.$emit('closeAddDialog')
        // 更新数据
        this.$emit('getArticleList')
      })
    },
    uploadSuccess(res, file) {
      this.$message.success(res.msg)
      this.imageUrl = res.data
      this.article.cover = res.data
    },
    cancel() {
      // 取消
      this.article = {}
      this.$emit('closeAddDialog')
    }
  }
}
</script>

<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>

