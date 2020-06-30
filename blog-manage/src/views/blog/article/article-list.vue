<template>
  <div>
    <!-- 搜索栏 -->
    <el-form :inline="true" :model="page" class="demo-form-inline" size="mini">
      <el-form-item label="博客标题">
        <el-input v-model="page.params.title" placeholder="博客标题" clearable />
      </el-form-item>
      <el-form-item label="推荐阅读">
        <el-select v-model="page.params.isRecommend" placeholder="推荐阅读" clearable filterable>
          <el-option label="是" value="1" />
          <el-option label="否" value="0" />
        </el-select>
      </el-form-item>
      <el-form-item label="状态">
        <el-select v-model="page.params.isEnable" placeholder="状态" clearable filterable>
          <el-option label="草稿" value="0" />
          <el-option label="正常" value="1" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" sizi="mini" @click="getArticleList">查询</el-button>
      </el-form-item>
    </el-form>
    <el-divider />
    <!-- 添加按钮 -->
    <el-button type="primary" size="small" class="add-btn" @click="openAddDialog">添加</el-button>
    <!-- 博客列表-->
    <div class="article-list">
      <el-table :data="page.list" border style="width: 100%">
        <el-table-column prop="id" label="id" show-overflow-tooltip />
        <el-table-column prop="title" label="标题" show-overflow-tooltip />
        <el-table-column prop="summary" label="摘要" show-overflow-tooltip />
        <el-table-column prop="category.categoryName" label="分类" show-overflow-tooltip width="100" />
        <el-table-column prop="views" label="浏览量" width="70" />
        <el-table-column prop="commentCount" label="评论数" width="70" />
        <el-table-column prop="likeCount" label="点赞数" width="70" />
        <el-table-column prop="isTop" label="置顶" width="80">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.isTop == 1" type="danger">置顶</el-tag>
            <el-tag v-else type="info">否</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="isRecommend" label="推荐阅读" width="60">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.isRecommend == 1" type="success">是</el-tag>
            <el-tag v-else type="info">否</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="isEnable" label="状态" width="80">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.isEnable == 1">正常</el-tag>
            <el-tag v-else type="info">草稿</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="edit" label="操作">
          <template slot-scope="scope">
            <el-dropdown>
              <el-button type="primary" size="mini">
                操作
                <i class="el-icon-arrow-down el-icon--right" />
              </el-button>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>
                  <el-button size="mini" @click="readArticle(scope.row.id)">预览</el-button>
                </el-dropdown-item>
                <el-dropdown-item>
                  <el-button size="mini" @click="updateArticle(scope.row.id)">修改</el-button>
                </el-dropdown-item>
                <el-dropdown-item>
                  <el-button size="mini" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
                </el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </template>
        </el-table-column>
      </el-table>
      <!-- 分页 -->
      <el-pagination
        class="pagination"
        :current-page="page.currPage"
        :page-sizes="[10,15,20]"
        :page-size="page.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="page.totalCount"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      />
    </div>
    <!-- 添加弹窗 -->
    <el-dialog title="添加文章" :visible.sync="addDialog" width="80%">
      <article-add @getArticleList="getArticleList" @closeAddDialog="closeAddDialog" />
    </el-dialog>
    <!-- 阅读弹窗 -->
    <el-dialog title="阅读文章" :visible.sync="readDialog">
      <no-ssr>
        <mavon-editor
          v-model="article.content"
          :subfield="false"
          :box-shadow="false"
          default-open="preview"
          :toolbars-flag="false"
        />
      </no-ssr>
    </el-dialog>
    <!-- 修改弹窗 -->
    <el-dialog title="修改文章" :visible.sync="updateDialog" width="80%">
      <article-update :article="article" @getArticleList="getArticleList" @closeUpdateDialog="closeUpdateDialog" />
    </el-dialog>
  </div>
</template>
<script>
import articleApi from '@/api/article'
import articleAdd from './article-add'
import articleUpdate from './article-update'
export default {
  components: {
    articleAdd,
    articleUpdate
  },
  data() {
    return {
      article: {},
      page: {
        totalCount: 0,
        pageSize: 10,
        totalPage: 0,
        currPage: 1,
        list: [],
        params: {},
        sortColumn: 'create_time'
      },
      tagList: this.$store.getters.tagList,
      categoryList: this.$store.getters.categoryList,
      addDialog: false, // 控制弹窗展示
      readDialog: false, // 阅读
      updateDialog: false // 控制弹窗展示
    }
  },
  created() {
    this.getArticleList()
    setTimeout(() => {
      this.tagList = this.$store.getters.tagList
      this.categoryList = this.$store.getters.categoryList
    }, 500)
  },
  methods: {
    getArticleList() {
      articleApi.listArticle(this.page).then(res => {
        this.page = res.data
      })
    },
    readArticle(id) {
      // 预览
      articleApi.getById(id).then(res => {
        this.article = res.data
        this.readDialog = true
      })
    },
    updateArticle(id) {
      // 修改
      articleApi.getById(id).then(res => {
        this.article = res.data
        this.updateDialog = true
      })
    },
    handleDelete(id) {
      this.$confirm('删除该文章, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        articleApi.deleteById(id).then(res => {
          this.$message.success(res.msg)
          this.getArticleList()
        })
      })
    },
    openAddDialog() {
      // 打开添加弹窗
      this.addDialog = true
    },
    closeAddDialog() {
      // 关闭添加弹窗
      this.addDialog = false
    },
    closeUpdateDialog() {
      // 关闭更新弹窗
      this.updateDialog = false
    },
    handleSizeChange(val) {
      this.page.pageSize = val
      this.getArticleList()
    },
    handleCurrentChange(val) {
      this.page.currPage = val
      this.getArticleList()
    }
  }
}
</script>
<style scoped>
.add-btn{
  margin-bottom: 15px;
}
.pagination{
  margin-top: 15px;
}
</style>
