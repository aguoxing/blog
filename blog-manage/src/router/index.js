import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },
  {
    path: '/',
    component: Layout,
    redirect: '/index',
    children: [{
      path: 'index',
      name: '首页',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '首页', icon: 'dashboard' }
    }]
  },
  {
    path: '/blog',
    component: Layout,
    redirect: '/blog',
    name: '博客管理',
    meta: { title: '博客管理', icon: 'article-fill' },
    children: [
      {
        path: 'article',
        name: 'Article',
        component: () => import('@/views/blog/article/article-list'),
        meta: { title: '文章列表', icon: 'article-fill' }
      },
      {
        path: 'category',
        name: 'Category',
        component: () => import('@/views/blog/category/category-list'),
        meta: { title: '分类列表', icon: 'category' }
      },
      {
        path: 'tag',
        name: 'Tag',
        component: () => import('@/views/blog/tag/tag-list'),
        meta: { title: '标签列表', icon: 'tag' }
      },
      {
        path: 'link',
        name: 'Link',
        component: () => import('@/views/blog/link/link-list'),
        meta: { title: '友链列表', icon: 'users' }
      }
    ]
  },
  {
    path: '/user',
    component: Layout,
    redirect: '/user',
    name: '用户管理',
    meta: { title: '用户管理', icon: 'user' },
    children: [
      {
        path: 'user',
        name: 'user',
        component: () => import('@/views/user/user-list'),
        meta: { title: '用户列表', icon: 'users' }
      },
      {
        path: 'role',
        name: 'role',
        component: () => import('@/views/user/role/role-list'),
        meta: { title: '角色列表', icon: 'role' }
      },
      {
        path: 'perms',
        name: 'perms',
        component: () => import('@/views/user/perms/perms-list'),
        meta: { title: '权限列表', icon: 'permission' }
      }
    ]
  },
  {
    path: '/sys',
    component: Layout,
    redirect: '/sys',
    name: '系统管理',
    meta: { title: '系统管理', icon: 'setting' },
    children: [
      {
        path: 'log',
        name: 'Log',
        component: () => import('@/views/sys/log/index'),
        meta: { title: '系统日志', icon: 'log' }
      },
      {
        path: 'index',
        name: 'Index',
        component: () => import('@/views/sys/sys-setting'),
        meta: { title: '系统管理', icon: 'setting' }
      }
    ]
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
