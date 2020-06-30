<template>
  <div>
    <div class="index-carousel">
      <Carousel v-model="value2" autoplay :autoplay-speed="6000" loop>
        <CarouselItem>
          <div class="carousel">
            <img src="@/assets/banner-1.png">
          </div>
        </CarouselItem>
        <CarouselItem>
          <div class="carousel">
            <img src="@/assets/banner-1.png">
          </div>
        </CarouselItem>
        <CarouselItem>
          <div class="carousel">
            <img src="@/assets/banner-1.png">
          </div>
        </CarouselItem>
      </Carousel>
    </div>
    <article-list :name="'文章'" />
  </div>
</template>

<script>
import ArticleList from '@/components/content/article-list'
export default {
  components: {
    ArticleList
  },
  data() {
    return {
      value2: 0
    }
  }
}
</script>

<style scoped>
.carousel{
  width: 840px;
  height: 196px;
  text-align: center;
  background-color: #ffffff;
}
</style>
