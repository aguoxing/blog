<template>
  <div>
    <div class="about-info">
      <h3>Hello World {{ name }}</h3>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  computed: {
    ...mapGetters(['name'])
  }
}
</script>
<style scoped>
.about-info{
    background: #ffffff;
    padding: 30px 20px;
}
</style>
