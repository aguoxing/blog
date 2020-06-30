<template>
  <div>
    <el-form ref="form" :model="tag" label-width="80px" size="mini">
      <el-form-item label="标签名称">
        <el-input v-model="tag.name" size="mini" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" size="mini" @click="onSubmit">提交</el-button>
        <el-button size="mini" @click="cancel">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import tagApi from '@/api/tag'
export default {
  data() {
    return {
      tag: {}
    }
  },
  methods: {
    onSubmit() {
      tagApi.save(this.tag).then(res => {
        console.log(this.tag)
        this.$message.success(res.msg)
        // 关闭弹窗
        this.$emit('closeAddDialog')
        // 更新数据
        this.$emit('getTagList')
      })
    },
    cancel() {
      // 取消
      this.tag = {}
      this.$emit('closeAddDialog')
    }
  }
}
</script>
