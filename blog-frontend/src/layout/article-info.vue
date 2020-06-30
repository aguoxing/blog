<template>
  <div id="app" class="main">
    <transition name="menu">
      <Header v-show="show" class="nav-container" />
    </transition>
    <div class="main-container">
      <div class="left-container">
        <router-view />
      </div>
      <article-info />
    </div>
    <Footer />
    <BackTop />
  </div>
</template>

<script>
import Header from '@/components/header/Header'
import ArticleInfo from '@/components/right/article-info'
import Footer from '@/components/footer/Footer'
export default {
  components: {
    Header,
    ArticleInfo,
    Footer
  },
  data() {
    return {
      show: true
    }
  },
  created() {
    this.mouseScroll()
  },
  methods: {
    mouseScroll() {
      // 给页面绑定滑轮滚动事件
      if (document.addEventListener) { // firefox
        document.addEventListener('DOMMouseScroll', this.watchScroll, false)
      }
      // 滚动滑轮触发scrollFunc方法  //ie 谷歌
      window.onmousewheel = document.onmousewheel = this.watchScroll
    },
    initLive2d() {
      setTimeout(() => {
        window.L2Dwidget.init({
          pluginRootPath: 'live2dw/',
          pluginJsPath: 'lib/',
          pluginModelPath: 'live2d-widget-model-shizuku/assets/',
          tagMode: false,
          debug: false,
          model: { jsonPath: '../live2dw/live2d-widget-model-shizuku/assets/shizuku.model.json' },
          display: { position: 'left', width: 240, height: 480 },
          mobile: { show: true },
          log: false
        })
      }, 1000)
    },
    watchScroll(e) {
      e = e || window.event
      if (e.wheelDelta) {
        if (e.wheelDelta > 0 && this.show === false) {
          // 当滑轮向上滚动
          this.show = true
        }
        if (e.wheelDelta < 0 && this.show === true) {
          // 当滑轮向下滚动
          this.show = false
        }
      } else if (e.detail) {
        if (e.detail < 0 && this.show === false) {
          // 当滑轮向上滚动
          this.show = true
        }
        if (e.detail > 0 && this.show === true) {
          // 当滑轮向下滚动
          this.show = false
        }
      }
    }
  }
}
</script>

<style scoped>
.main{
  background-color: #f5f5f5;
}
.main-container{
  width: 1200px;
  margin: auto;
  display: flex;
  flex-direction: row;
}
.left-container{
  width: 850px;
  padding: 0 5px;
}
.nav-container{
  position: sticky;
  top: 0;
  z-index: 10;
  width: 100%;
}

/* 可以设置不同的进入和离开动画 */
/* 设置持续时间和动画函数 */
.menu-enter-active,
.menu-leave-active {
  transition: all .8s ease;
}
.menu-enter, .menu-leave-to
/* .menu-leave-active for below version 2.1.8 */ {
  transform: translateY(-70px);
  opacity: 0;
}
</style>
