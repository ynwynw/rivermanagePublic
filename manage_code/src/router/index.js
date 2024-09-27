	import {
		createRouter,
		createWebHashHistory
	} from 'vue-router'
	import news from '@/views/news/list'
	import manyidudiaocha from '@/views/manyidudiaocha/list'
	import discusszhilixinxi from '@/views/discusszhilixinxi/list'
	import zhilixinxi from '@/views/zhilixinxi/list'
	import gongzuorenyuan from '@/views/gongzuorenyuan/list'
	import yonghu from '@/views/yonghu/list'
	import config from '@/views/config/list'
	import hedaoquyu from '@/views/hedaoquyu/list'
	import gongzuorenyuanRegister from '@/views/gongzuorenyuan/register'
	import gongzuorenyuanCenter from '@/views/gongzuorenyuan/center'

export const routes = [{
		path: '/login',
		name: 'login',
		component: () => import('../views/login.vue')
	},{
		path: '/',
		name: '首页',
		component: () => import('../views/index'),
		children: [{
			path: '/',
			name: '首页',
			component: () => import('../views/HomeView.vue'),
			meta: {
				affix: true
			}
		}, {
			path: '/updatepassword',
			name: '修改密码',
			component: () => import('../views/updatepassword.vue')
		}
		
		,{
			path: '/gongzuorenyuanCenter',
			name: '工作人员个人中心',
			component: gongzuorenyuanCenter
		}
		,{
			path: '/news',
			name: '治理政策',
			component: news
		}
		,{
			path: '/manyidudiaocha',
			name: '满意度调查',
			component: manyidudiaocha
		}
		,{
			path: '/discusszhilixinxi',
			name: '治理信息评论',
			component: discusszhilixinxi
		}
		,{
			path: '/zhilixinxi',
			name: '治理信息',
			component: zhilixinxi
		}
		,{
			path: '/gongzuorenyuan',
			name: '工作人员',
			component: gongzuorenyuan
		}
		,{
			path: '/yonghu',
			name: '用户',
			component: yonghu
		}
		,{
			path: '/config',
			name: '轮播图',
			component: config
		}
		,{
			path: '/hedaoquyu',
			name: '河道区域',
			component: hedaoquyu
		}
		]
	},
	{
		path: '/gongzuorenyuanRegister',
		name: '工作人员注册',
		component: gongzuorenyuanRegister
	},
]

const router = createRouter({
	history: createWebHashHistory(process.env.BASE_URL),
	routes
})

export default router
