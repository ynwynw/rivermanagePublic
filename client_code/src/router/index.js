import { createRouter, createWebHashHistory } from 'vue-router'
import index from '../views'
import home from '../views/pages/home.vue'
import login from '../views/pages/login.vue'
import yonghuList from '@/views/pages/yonghu/list'
import yonghuDetail from '@/views/pages/yonghu/formModel'
import yonghuAdd from '@/views/pages/yonghu/formAdd'
import yonghuRegister from '@/views/pages/yonghu/register'
import yonghuCenter from '@/views/pages/yonghu/center'
import gongzuorenyuanList from '@/views/pages/gongzuorenyuan/list'
import gongzuorenyuanDetail from '@/views/pages/gongzuorenyuan/formModel'
import gongzuorenyuanAdd from '@/views/pages/gongzuorenyuan/formAdd'
import hedaoquyuList from '@/views/pages/hedaoquyu/list'
import hedaoquyuDetail from '@/views/pages/hedaoquyu/formModel'
import hedaoquyuAdd from '@/views/pages/hedaoquyu/formAdd'
import zhilixinxiList from '@/views/pages/zhilixinxi/list'
import zhilixinxiDetail from '@/views/pages/zhilixinxi/formModel'
import zhilixinxiAdd from '@/views/pages/zhilixinxi/formAdd'
import manyidudiaochaList from '@/views/pages/manyidudiaocha/list'
import manyidudiaochaDetail from '@/views/pages/manyidudiaocha/formModel'
import manyidudiaochaAdd from '@/views/pages/manyidudiaocha/formAdd'
import newsList from '@/views/pages/news/list'
import storeupList from '@/views/pages/storeup/list'
import menuList from '@/views/pages/menu/list'
import menuDetail from '@/views/pages/menu/formModel'
import menuAdd from '@/views/pages/menu/formAdd'
import discusszhilixinxiList from '@/views/pages/discusszhilixinxi/list'
import discusszhilixinxiDetail from '@/views/pages/discusszhilixinxi/formModel'
import discusszhilixinxiAdd from '@/views/pages/discusszhilixinxi/formAdd'

const routes = [{
		path: '/',
		redirect: '/index/home'
	},
	{
		path: '/index',
		component: index,
		children: [{
			path: 'home',
			component: home
		}
		, {
			path: 'yonghuList',
			component: yonghuList
		}, {
			path: 'yonghuDetail',
			component: yonghuDetail
		}, {
			path: 'yonghuAdd',
			component: yonghuAdd
		}
		, {
			path: 'yonghuCenter',
			component: yonghuCenter
		}
		, {
			path: 'gongzuorenyuanList',
			component: gongzuorenyuanList
		}, {
			path: 'gongzuorenyuanDetail',
			component: gongzuorenyuanDetail
		}, {
			path: 'gongzuorenyuanAdd',
			component: gongzuorenyuanAdd
		}
		, {
			path: 'hedaoquyuList',
			component: hedaoquyuList
		}, {
			path: 'hedaoquyuDetail',
			component: hedaoquyuDetail
		}, {
			path: 'hedaoquyuAdd',
			component: hedaoquyuAdd
		}
		, {
			path: 'zhilixinxiList',
			component: zhilixinxiList
		}, {
			path: 'zhilixinxiDetail',
			component: zhilixinxiDetail
		}, {
			path: 'zhilixinxiAdd',
			component: zhilixinxiAdd
		}
		, {
			path: 'manyidudiaochaList',
			component: manyidudiaochaList
		}, {
			path: 'manyidudiaochaDetail',
			component: manyidudiaochaDetail
		}, {
			path: 'manyidudiaochaAdd',
			component: manyidudiaochaAdd
		}
		, {
			path: 'newsList',
			component: newsList
		}
		, {
			path: 'storeupList',
			component: storeupList
		}
		, {
			path: 'menuList',
			component: menuList
		}, {
			path: 'menuDetail',
			component: menuDetail
		}, {
			path: 'menuAdd',
			component: menuAdd
		}
		, {
			path: 'discusszhilixinxiList',
			component: discusszhilixinxiList
		}, {
			path: 'discusszhilixinxiDetail',
			component: discusszhilixinxiDetail
		}, {
			path: 'discusszhilixinxiAdd',
			component: discusszhilixinxiAdd
		}
		]
	},
	{
		path: '/login',
		component: login
	}
	,{
		path: '/yonghuRegister',
		component: yonghuRegister
	}
]

const router = createRouter({
  history: createWebHashHistory(process.env.BASE_URL),
  routes
})

export default router
