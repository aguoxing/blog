<template>
  <div>
    <el-form ref="form" :model="category" label-width="80px" size="mini">
      <el-form-item label="分类名称">
        <el-input v-model="category.categoryName" />
      </el-form-item>
      <el-form-item label="启用">
        <el-switch
          v-model="category.isEnable"
          active-text="是"
          inactive-text="否"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" size="mini" @click="onSubmit">修改</el-button>
        <el-button size="mini" @click="cancel">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import categoryApi from '@/api/category'
export default {
  props: {
    category: {
      type: Object,
      default: null
    }
  },
  methods: {
    onSubmit() {
      categoryApi.update(this.category).then(res => {
        this.$message.success(res.msg)
        // 关闭弹窗
        this.$emit('closeUpdateDialog')
        // 更新数据
        this.$emit('getCategoryList')
      })
    },
    cancel() {
      this.$emit('closeUpdateDialog')
    }
  }
}
</script>
