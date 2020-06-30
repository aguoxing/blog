<template>
  <div>
    <!-- 搜索栏 -->
    <el-form :inline="true" :model="page" class="demo-form-inline" size="mini">
      <el-form-item label="标签名称">
        <el-input v-model="page.params.name" placeholder="分类名称" clearable />
      </el-form-item>
      <el-form-item label="是否启用">
        <el-select v-model="page.params.isEnable" placeholder="是否启用" clearable filterable>
          <el-option label="是" value="1" />
          <el-option label="否" value="0" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" sizi="mini" @click="getCategoryList">查询</el-button>
      </el-form-item>
    </el-form>
    <el-divider />
    <!-- 添加按钮 -->
    <el-button type="primary" size="small" class="add-btn" @click="openAddDialog">添加</el-button>
    <!-- 分类列表 -->
    <div class="category-list">
      <el-table :data="page.list" border style="width: 100%">
        <el-table-column prop="id" label="ID" />
        <el-table-column prop="categoryName" label="分类名称" />
        <el-table-column prop="parentCategoryId" label="父分类">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.isTop == -1" type="info">{{ parentCategoryId }}</el-tag>
            <el-tag v-else type="info">暂无</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="isEnable" label="状态">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.isEnable == 1">启用</el-tag>
            <el-tag v-else type="info">未启用</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="articleCount" label="该分类下的文章数" />
        <el-table-column prop="edit" label="操作">
          <template slot-scope="scope">
            <el-dropdown>
              <el-button type="primary" size="mini">
                操作<i class="el-icon-arrow-down el-icon--right" />
              </el-button>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>
                  <el-button size="mini" @click="handleEdit(scope.row.id)">修改</el-button>
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
    <el-dialog title="添加标签" :visible.sync="addDialog">
      <category-add @getCategoryList="getCategoryList" @closeAddDialog="closeAddDialog" />
    </el-dialog>
    <!-- 修改弹窗 -->
    <el-dialog title="修改标签" :visible.sync="updateDialog">
      <category-update :category="category" @getCategoryList="getCategoryList" @closeUpdateDialog="closeUpdateDialog" />
    </el-dialog>
  </div>
</template>
<script>
import categoryApi from '@/api/category'
import categoryAdd from './category-add'
import categoryUpdate from './category-update'
export default {
  components: {
    categoryAdd,
    categoryUpdate
  },
  data() {
    return {
      category: {},
      addDialog: false, // 控制弹窗展示
      updateDialog: false, // 控制弹窗展示
      page: {
        totalCount: 0,
        pageSize: 10,
        totalPage: 0,
        currPage: 1,
        list: [],
        params: {},
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
        this.page = res.data
      })
    },
    handleEdit(id) {
      categoryApi.getById(id).then(res => {
        this.category = res.data
        this.updateDialog = true
      })
    },
    handleDelete(id) {
      this.$confirm('删除该分类, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        categoryApi.deleteById(id).then(res => {
          this.$message.success(res.msg)
          this.getCategoryList()
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
      this.getCategoryList()
    },
    handleCurrentChange(val) {
      this.page.currPage = val
      this.getCategoryList()
    }
  }
}
</script>
<style scoped>
.add-btn{
  margin-bottom: 15px;
}
</style>
