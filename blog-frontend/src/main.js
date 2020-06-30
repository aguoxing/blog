import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import '@/permission'

import * as filters from './filters' // 全局过滤器

import iView from 'iview'
import 'iview/dist/styles/iview.css'

import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
// use
Vue.use(mavonEditor)

Vue.use(iView)
Vue.config.productionTip = false

Object.keys(filters).forEach(key => {
  Vue.filter(key, filters[key])
})

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
