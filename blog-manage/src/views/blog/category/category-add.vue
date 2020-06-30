<template>
  <div>
    <el-form ref="form" :model="category" label-width="80px" size="mini">
      <el-form-item label="分类名称">
        <el-input v-model="category.categoryName" size="mini" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" size="mini" @click="onSubmit">提交</el-button>
        <el-button size="mini" @click="cancel">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import categoryApi from '@/api/category'
export default {
  data() {
    return {
      category: {}
    }
  },
  methods: {
    onSubmit() {
      categoryApi.save(this.category).then(res => {
        this.$message.success(res.msg)
        // 关闭弹窗
        this.$emit('closeAddDialog')
        // 更新数据
        this.$emit('getCategoryList')
      })
    },
    cancel() {
      // 取消
      this.category = {}
      this.$emit('closeAddDialog')
    }
  }
}
</script>
