<template>
	<div>
		<el-scrollbar wrap-class="scrollbar-wrapper" class="menu_scrollbar">
			<el-menu :default-openeds="[]" :unique-opened="false" default-active="0" class="menu_view"
				:collapse="collapse">
				<el-sub-menu :index="0" @click="menuHandler('')">
					<template #title>
						<i class="iconfont icon-zhuye2" v-if="collapse?true:true"></i>
						<span>首页</span>
					</template>
				</el-sub-menu>
				<el-sub-menu v-for=" (menu,index) in menuList.backMenu" :key="menu.menu" :index="index+2+''">
					<template #title>
						<i class="iconfont" :class="menu.fontClass" v-if="collapse?true:true"></i>
						<span>{{ menu.menu }}</span>
					</template>
					<el-menu-item class="menu_item_view" v-for=" (child,sort) in menu.child" :key="sort"
						:index="(index+2)+'-'+sort" @click="menuHandler(child.tableName,child.menuJump)">{{ child.menu }}
					</el-menu-item>
				</el-sub-menu>
			</el-menu>
		</el-scrollbar>
	</div>
</template>

<script setup>
	import menu from '@/utils/menu'
	import {
		ref,
		toRefs,
		getCurrentInstance,
		nextTick
	} from 'vue';
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	//props
	const props = defineProps({
		collapse: Boolean
	})
	const {
		collapse
	} = toRefs(props)
	//data
	const menuList = ref([])
	const role = ref('')
	const styleChange = () => {
		nextTick(() => {
			document.querySelectorAll('.el-menu-vertical-demo .el-sub-menu .el-menu').forEach(el => {
				el.removeAttribute('style')
				const icon = {
					"border": "none",
					"padding": "0",
					"margin": "10px auto 0",
					"borderRadius": "0px",
					"background": "none",
					"display": "none",
					"width": "100%"
				}
				Object.keys(icon).forEach((key) => {
					el.style[key] = icon[key]
				})
			})
		})
	}
	//权限验证
	const btnAuth = (e,a)=>{
		return context?.$toolUtil.isAuth(e,a)
	}
	const init = () => {
		const menus = menu.list()
		if (menus) {
			menuList.value = menus
		}
		role.value = context?.$toolUtil.storageGet('role')

		for (let i = 0; i < menuList.value.length; i++) {
			if (menuList.value[i].roleName == role.value) {
				menuList.value = menuList.value[i];
				break;
			}
		}
		// styleChange()
	}
	const menuHandler = (name,menuJump) => {
		if(name == 'center'){
			name = `${role.value}Center`
		}
		if(name == 'storeup'){
			name = `storeup?type=${menuJump}`
		}
		if(name == 'exampaper' && menuJump == '12'){
			name = 'exampaperlist'
		}
		if(name == 'examrecord' && menuJump == '22'){
			name = 'examfailrecord'
		}
		let router = context?.$router
		name = '/' + name
		router.push(name)
	}
	init()
</script>

<style lang="scss" scoped>
	// 总盒子
	:deep(.menu_scrollbar) {

		// 菜单盒子-展开样式
		.menu_view {
			padding: 20px 0 60px;
			color: #333;
			background: none;
			height: 100%;

			// 无二级菜单
			.el-menu-item {
				padding: 10px 10px;
				flex-direction: column;
				color: #666;
				background: none;
				display: block;
				width: auto;
				clear: both;
				line-height: 1.5;
				flex-wrap: wrap;
				text-align: center;
				height: auto;
				.iconfont {
					border-radius: 4px;
					margin: 0 auto;
					color: #3b87d5;
					background: #fff;
					display: flex;
					vertical-align: middle;
					width: 42px;
					font-size: 32px;
					justify-content: center;
					align-items: center;
					text-align: center;
					height: 42px;
				}
			}

			// 无二级悬浮
			.el-menu-item:hover {
				padding: 10px 10px;
				color: #666;
				background: linear-gradient(30deg, rgba(254,247,236,1) 0%, rgba(244,220,182,1) 100%),#FEF7EC;
				line-height: 1.5;
				text-align: center;
				height: auto;
			}

			// 无二级选中
			.el-menu-item.is-active {
				padding: 10px 10pxx;
				color: #666;
				background: linear-gradient(30deg, rgba(254,247,236,1) 0%, rgba(244,220,182,1) 100%),#FEF7EC;
				line-height: 1.5;
				text-align: center;
				height: auto;
			}

			// 有二级盒子
			.el-sub-menu {
				cursor: pointer;
				padding: 0 0;
				color: #333;
				white-space: nowrap;
				background: none;
				width: 100%;
				position: relative;

				// 有二级item
				.el-sub-menu__title {
					padding: 10px 10px;
					flex-direction: column;
					color: #666;
					background: none;
					display: block;
					width: auto;
					clear: both;
					line-height: 1.5;
					flex-wrap: wrap;
					text-align: center;
					height: auto;
					.iconfont {
						border-radius: 4px;
						margin: 0 auto;
						color: #3b87d5;
						background: #fff;
						display: flex;
						vertical-align: middle;
						width: 42px;
						font-size: 32px;
						justify-content: center;
						align-items: center;
						text-align: center;
						height: 42px;
					}
					.el-sub-menu__icon-arrow{
						margin: -3px 0 0 8px;
						color: inherit;
						vertical-align: middle;
						font-size: 13px;
						position: absolute;
						right: 20px;
					}
				}

				// 有二级item悬浮
				.el-sub-menu__title:hover {
					padding: 10px 10px;
					color: #666;
					background: linear-gradient(30deg, rgba(254,247,236,1) 0%, rgba(244,220,182,1) 100%),#FEF7EC;
					line-height: 1.5;
					text-align: center;
					height: auto;
				}
			}
			//二级选中
			.is-active {
				.el-sub-menu__title {
					padding: 10px 10pxx;
					color: #666;
					background: linear-gradient(30deg, rgba(254,247,236,1) 0%, rgba(244,220,182,1) 100%),#FEF7EC;
					line-height: 1.5;
					text-align: center;
					height: auto;
				}
			}
			// 二级盒子
			.el-menu--inline {
				border: none;
				padding: 0;
				background: none;
				// 二级菜单
				.menu_item_view {
					padding: 0 10px;
					color: #666;
					background: #fff;
					line-height: 40px;
					text-align: center;
					height: 40px;
				}
				// 二级悬浮
				.menu_item_view:hover {
					padding: 0 10px;
					color: #666;
					background: linear-gradient(30deg, rgba(254,247,236,1) 0%, rgba(244,220,182,1) 100%),#FEF7EC;
					line-height: 40px;
					height: 40px;
				}
				// 二级选中
				.is-active.menu_item_view {
					padding: 0 10px;
					color: #666;
					background: linear-gradient(30deg, rgba(254,247,236,1) 0%, rgba(244,220,182,1) 100%),#FEF7EC;
					line-height: 40px;
					text-align: center;
					height: 40px;
				}
			}
		}
		// 菜单盒子-关闭样式
		.el-menu--collapse {
			padding: 0;
			color: #333;
			background: none;
			height: 100%;

			// 无二级菜单
			.el-menu-item {
				padding: 0 10px;
				color: #666;
				background: none;
				line-height: 50px;
				height: 50px;
				.iconfont {
					margin: 0 5px 0 0;
					color: #3b87d5;
					width: 40px;
					vertical-align: middle;
					font-size: 32px;
					text-align: center;
				}
			}

			// 无二级悬浮
			.el-menu-item:hover {
				padding: 0 10px;
				color: #fff;
				background: linear-gradient(30deg, rgba(254,247,236,1) 0%, rgba(244,220,182,1) 100%),#FEF7EC;
				line-height: 50px;
				height: 50px;
			}

			// 无二级选中
			.el-menu-item.is-active {
				padding: 0 10px;
				color: #fff;
				background: linear-gradient(30deg, rgba(254,247,236,1) 0%, rgba(244,220,182,1) 100%),#FEF7EC;
				line-height: 50px;
				height: 50px;
			}

			// 有二级盒子
			.el-sub-menu {
				cursor: pointer;
				padding: 0 0;
				color: #333;
				white-space: nowrap;
				background: none;
				position: relative;

				// 有二级item
				.el-sub-menu__title {
					padding: 0 10px;
					color: #666;
					background: none;
					line-height: 50px;
					height: 50px;
					.iconfont {
						margin: 0 5px 0 0;
						color: #3b87d5;
						width: 40px;
						vertical-align: middle;
						font-size: 32px;
						text-align: center;
					}
					.el-sub-menu__icon-arrow{
						margin: -3px 0 0 8px;
						color: inherit;
						vertical-align: middle;
						font-size: 12px;
						position: static;
					}
				}

				// 有二级item悬浮
				.el-sub-menu__title:hover {
					padding: 0 10px;
					color: #fff;
					background: linear-gradient(30deg, rgba(254,247,236,1) 0%, rgba(244,220,182,1) 100%),#FEF7EC;
					line-height: 50px;
					height: 50px;
				}
			}
			//二级选中
			.is-active {
				.el-sub-menu__title {
					padding: 0 10px;
					color: #fff;
					background: linear-gradient(30deg, rgba(254,247,236,1) 0%, rgba(244,220,182,1) 100%),#FEF7EC;
					line-height: 50px;
					height: 50px;
				}
			}
			// 二级盒子
			.el-menu--inline {
				border: none;
				padding: 0px;
				background: none;
				// 二级菜单
				.menu_item_view {
					padding: 0 40px;
					color: #666;
					background: #fff;
					line-height: 40px;
					height: 40px;
				}
				// 二级悬浮
				.menu_item_view:hover {
					padding: 0 40px;
					color: #666;
					background: linear-gradient(30deg, rgba(254,247,236,1) 0%, rgba(244,220,182,1) 100%),#FEF7EC;
					line-height: 40px;
					height: 40px;
				}
				// 二级选中
				.is-active.menu_item_view {
					padding: 0 40px;
					color: #666;
					background: linear-gradient(30deg, rgba(254,247,236,1) 0%, rgba(244,220,182,1) 100%),#FEF7EC;
					line-height: 40px;
					height: 40px;
				}
			}
		}
	}
</style>
<style lang="scss">
	.el-popper{
		.el-menu--popup-container {
			.el-menu--popup{
				border: none;
				padding: 0px;
				background: none;
				// 二级菜单
				.menu_item_view {
					padding: 0 40px;
					color: #666;
					background: #fff;
					line-height: 40px;
					height: 40px;
				}
				// 二级悬浮
				.menu_item_view:hover {
					padding: 0 40px;
					color: #666;
					background: linear-gradient(30deg, rgba(254,247,236,1) 0%, rgba(244,220,182,1) 100%),#FEF7EC;
					line-height: 40px;
					height: 40px;
				}
				// 二级选中
				.is-active.menu_item_view {
					padding: 0 40px;
					color: #666;
					background: linear-gradient(30deg, rgba(254,247,236,1) 0%, rgba(244,220,182,1) 100%),#FEF7EC;
					line-height: 40px;
					height: 40px;
				}
			}
		}
	}
</style>
