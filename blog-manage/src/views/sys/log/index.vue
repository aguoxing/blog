<template>
  <div>
    <!-- 搜索栏 -->
    <div class="search">
      <el-form :inline="true" :model="page" class="demo-form-inline" size="mini">
        <el-form-item label="请求地址">
          <el-input v-model="page.params.url" placeholder="请求地址" clearable />
        </el-form-item>
        <el-form-item label="请求ip">
          <el-input v-model="page.params.ip" placeholder="请求ip" clearable />
        </el-form-item>
        <el-form-item label="请求状态">
          <el-select v-model="page.params.status" placeholder="请求状态" clearable filterable>
            <el-option label="正常" :value="1" />
            <el-option label="异常" :value="0" />
          </el-select>
        </el-form-item>
        <el-form-item label="请求方式">
          <el-select v-model="page.params.method" placeholder="请求方式" clearable filterable>
            <el-option label="GET" value="GET" />
            <el-option label="POST" value="POST" />
            <el-option label="PUT" value="PUT" />
            <el-option label="DELETE" value="DELETE" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" sizi="mini" @click="getLogList">查询</el-button>
        </el-form-item>
      </el-form>
    </div>
    <el-divider />
    <div class="btn">
      <el-button type="danger" size="mini" @click="deleteByIds">批量删除</el-button>
      <el-button type="primary" size="mini" @click="exportAll">全部导出</el-button>
    </div>
    <!-- 数据-->
    <div class="log-list">
      <el-table
        :data="page.list"
        border
        style="width: 100%"
        @selection-change="handleSelectionChange"
        @sort-change="changeSort"
      >
        <el-table-column type="selection" width="55" />
        <el-table-column prop="id" label="id" width="60" />
        <el-table-column prop="url" label="请求地址" width="100" show-overflow-tooltip />
        <el-table-column prop="method" label="请求方法" width="60" />
        <el-table-column prop="usedTime" label="耗时（毫秒）" width="120" sortable="custom" />
        <el-table-column prop="ip" label="请求ip" width="100" show-overflow-tooltip />
        <el-table-column prop="status" label="请求状态" width="80">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.status === 1" type="info">正常</el-tag>
            <el-tag v-else type="danger">异常</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="params" label="请求参数" show-overflow-tooltip />
        <el-table-column prop="result" label="返回值" show-overflow-tooltip />
        <el-table-column prop="createTime" label="请求时间" show-overflow-tooltip sortable="custom" />
        <el-table-column prop="edit" label="操作" width="120">
          <template slot-scope="scope">
            <el-dropdown>
              <el-button type="primary" size="mini">
                操作
                <i class="el-icon-arrow-down el-icon--right" />
              </el-button>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>
                  <el-button size="mini" @click="handleEdit(scope.row.id)">预览</el-button>
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
  </div>
</template>
<script>
import logApi from '@/api/log'
export default {
  data() {
    return {
      page: {
        totalCount: 0,
        pageSize: 10,
        totalPage: 0,
        currPage: 1,
        list: [],
        params: {},
        sortColumn: 'create_time'
      },
      addDialog: false, // 控制弹窗展示
      updateDialog: false, // 控制弹窗展示
      selectLogs: [] // 被选中的日志
    }
  },
  created() {
    this.getLogList()
  },
  methods: {
    getLogList() {
      logApi.listLog(this.page).then(res => {
        this.page = res.data
      })
    },
    handleEdit(id) {},
    handleDelete(id) {},
    openAddDialog() {
      // 打开添加弹窗
      this.addDialog = true
    },
    closeAddDialog() {
      // 关闭添加弹窗
      this.addDialog = false
    },
    handleSizeChange(val) {
      this.page.pageSize = val
      this.getLogList()
    },
    handleCurrentChange(val) {
      this.page.currPage = val
      this.getLogList()
    },
    handleSelectionChange(val) {
      this.selectLogs = val
    },
    changeSort(e) {
      if (e.order) {
        this.page.sortColumn = e.prop
        this.page.sortMethod = 'desc'
      } else {
        this.page.sortColumn = ''
        this.page.sortMethod = 'asc'
      }
      this.getLogList()
    }
  }
}
</script>
<style scoped>
.pagination{
  margin-top: 15px;
}
.btn{
  margin-bottom: 15px;
}
</style>
