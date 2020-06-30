<template>
  <div>
    <el-form ref="form" :model="tag" label-width="80px" size="mini">
      <el-form-item label="标签名称">
        <el-input v-model="tag.name" />
      </el-form-item>
      <el-form-item label="启用">
        <el-switch
          v-model="tag.isEnable"
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
import tagApi from '@/api/tag'
export default {
  props: {
    tag: {
      type: Object,
      default: null
    }
  },
  methods: {
    onSubmit() {
      tagApi.update(this.tag).then(res => {
        this.$message.success(res.msg)
        // 关闭弹窗
        this.$emit('closeUpdateDialog')
        // 更新数据
        this.$emit('getTagList')
      })
    },
    cancel() {
      this.$emit('closeUpdateDialog')
    }
  }
}
</script>
