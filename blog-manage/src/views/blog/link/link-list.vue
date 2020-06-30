<template>
  <div>
    <!-- 列表 -->
    <div class="link-list">
      <el-table :data="link" border style="width: 100%">
        <el-table-column prop="id" label="ID" />
        <el-table-column prop="nickname" label="名称" />
        <el-table-column prop="url" label="URL" />
        <el-table-column prop="isEnable" label="状态">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.isEnable == 1">启用</el-tag>
            <el-tag v-else type="info">未启用</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="edit" label="操作">
          <template slot-scope="scope">
            <el-dropdown>
              <el-button type="primary" size="mini">
                操作<i class="el-icon-arrow-down el-icon--right" />
              </el-button>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>
                  <el-button v-if="scope.row.isEnable == 0" size="mini" @click="enableLink(scope.row)">启用</el-button>
                </el-dropdown-item>
                <el-dropdown-item>
                  <el-button v-if="scope.row.isEnable == 1" size="mini" type="danger" @click="disableLink(scope.row)">禁用</el-button>
                </el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>
<script>
import linkApi from '@/api/link'

export default {
  data() {
    return {
      link: {} // 标签
    }
  },
  created() {
    this.getLinkList()
  },
  methods: {
    getLinkList() {
      linkApi.listLink().then(res => {
        this.link = res.data
      })
    },
    enableLink(link) {
      this.link = link
      this.link.isEnable = 1
      linkApi.update(this.link).then(res => {
        this.$message.success(res.msg)
        this.getLinkList()
      })
    },
    disableLink(link) {
      this.link = link
      this.link.isEnable = 0
      linkApi.update(this.link).then(res => {
        this.$message.success(res.msg)
        this.getLinkList()
      })
    }
  }
}
</script>
