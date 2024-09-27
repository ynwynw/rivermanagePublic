
<template>
	<div>
		<div class="app-contain">
			<div class="list_search_view">
				<el-form :model="searchQuery" class="search_form" >
					<div class="search_view">
						<div class="search_label">
							治理名称：
						</div>
						<div class="search_box">
							<el-input class="search_inp" v-model="searchQuery.zhilimingcheng" placeholder="治理名称"
								clearable>
							</el-input>
						</div>
					</div>
					<div class="search_view">
						<div class="search_label">
							河道区域：
						</div>
						<div class="search_box">
							<el-select
								class="search_sel"
								clearable
								v-model="searchQuery.hedaoquyu" 
								placeholder="河道区域"
								>
								<el-option v-for="item in hedaoquyuLists" :label="item" :value="item"></el-option>
							</el-select>
						</div>
					</div>
					<div class="search_view">
						<div class="search_label">
							审核状态：
						</div>
						<div class="search_box">
							<el-select
								class="search_sel"
								clearable
								v-model="searchQuery.sfsh" 
								placeholder="审核状态"
								>
								<el-option v-for="item in approvalLists" :label="item" :value="item"></el-option>
							</el-select>
						</div>
					</div>
					<div class="search_btn_view">
						<el-button class="search_btn" type="primary" @click="searchClick()" size="small">搜索</el-button>
					</div>
				</el-form>
				<br>
				<div class="btn_view">
					<el-button type="success" @click="addClick" v-if="btnAuth('zhilixinxi','新增')">新增</el-button>
					<el-button  v-if=" btnAuth('zhilixinxi','查看')" type="info"  :disabled="selRows.length==1?false:true" @click="infoClick(null)">详情</el-button>
					<el-button type="primary" :disabled="selRows.length==1?false:true" @click="editClick" v-if=" btnAuth('zhilixinxi','修改')">修改</el-button>
					<el-button type="danger" :disabled="selRows.length?false:true" @click="delClick(null)"  v-if="btnAuth('zhilixinxi','删除')">删除</el-button>
				</div>
			</div>
			<br>
			<el-table
				v-loading="listLoading"
				border 
				:stripe='false'
				@selection-change="handleSelectionChange" 
				ref="table"
				v-if="btnAuth('zhilixinxi','查看')"
				:data="list"
				@row-click="listChange">
				<el-table-column :resizable='true' align="left" header-align="left" type="selection" width="55" />
				<el-table-column label="序号" width="70" :resizable='true' :sortable='true' align="left" header-align="left">
					<template #default="scope">{{ scope.$index + 1}}</template>
				</el-table-column>
				<el-table-column
					 :resizable='true' 
					 :sortable='true' 
					 align="left" 
					 header-align="left"
					label="治理名称">
					<template #default="scope">
						{{scope.row.zhilimingcheng}}
					</template>
				</el-table-column>
				<el-table-column
					 :resizable='true' 
					 :sortable='true' 
					 align="left" 
					 header-align="left"
					label="河道区域">
					<template #default="scope">
						{{scope.row.hedaoquyu}}
					</template>
				</el-table-column>
				<el-table-column label="图片" width="120" :resizable='true' :sortable='true' align="left" header-align="left">
					<template #default="scope">
						<div v-if="scope.row.tupian">
							<el-image v-if="scope.row.tupian.substring(0,4)=='http'" preview-teleported
								:preview-src-list="[scope.row.tupian.split(',')[0]]"
								:src="scope.row.tupian.split(',')[0]" style="width:100px;height:100px"></el-image>
							<el-image v-else preview-teleported
								:preview-src-list="[$config.url+scope.row.tupian.split(',')[0]]"
								:src="$config.url+scope.row.tupian.split(',')[0]" style="width:100px;height:100px">
							</el-image>
						</div>
						<div v-else>无图片</div>
					</template>
				</el-table-column>
				<el-table-column label="说明附件" :resizable='true' :sortable='true' align="left" header-align="left">
					<template #default="scope">
						<el-button v-if="scope.row.shuomingfujian" type="text" size="small" @click="download(scope.row.shuomingfujian)">下载</el-button>
						<el-button v-else disabled type="text" size="small">无</el-button>
					</template>
				</el-table-column>
				<el-table-column
					 :resizable='true' 
					 :sortable='true' 
					 align="left" 
					 header-align="left"
					label="工作人员账号">
					<template #default="scope">
						{{scope.row.gongzuorenyuanzhanghao}}
					</template>
				</el-table-column>
				<el-table-column
					 :resizable='true' 
					 :sortable='true' 
					 align="left" 
					 header-align="left"
					label="工作人员姓名">
					<template #default="scope">
						{{scope.row.gongzuorenyuanxingming}}
					</template>
				</el-table-column>
				<el-table-column label="审核回复" :resizable='true' :sortable='true' align="left" header-align="left">
					<template #default="scope">
						{{scope.row.shhf}}
					</template>
				</el-table-column>
				<el-table-column label="审核状态" :resizable='true' :sortable='true' align="left" header-align="left">
					<template #default="scope">
						<el-tag type="success" v-if="scope.row.sfsh=='是'">通过</el-tag>
						<el-tag type="danger" v-else-if="scope.row.sfsh=='否'">未通过</el-tag>
						<el-tag type="warning" v-else>待审核</el-tag>
					</template>
				</el-table-column>
				<el-table-column label="审核" v-if="btnAuth('zhilixinxi','审核')" :resizable='true' :sortable='true' align="left" header-align="left">
					<template #default="scope">
						<el-button type="text" @click="approvalClick(scope.row)">审核</el-button>
					</template>
				</el-table-column>
				<el-table-column label="操作" width="300" :resizable='true' :sortable='true' align="left" header-align="left">
					<template #default="scope">
						<el-button type="info" v-if=" btnAuth('zhilixinxi','查看')" @click="infoClick(scope.row.id)">详情</el-button>
						<el-button v-if="btnAuth('zhilixinxi','投票')" type="success" @click="manyidudiaochaCrossAddOrUpdateHandler(scope.row,'cross','是','[1]','已投票')">投票</el-button>
						<el-button v-if="btnAuth('zhilixinxi','查看评论')" type="warning" @click="commentClick(scope.row.id)">查看评论</el-button>
					</template>
				</el-table-column>
			</el-table>
			<el-pagination 
				background
				:layout="layouts.join(',')"
				:total="total" 
				:page-size="listQuery.limit"
				prev-text="<"
				next-text=">"
				:hide-on-single-page="true"
				:style='{"width":"100%","padding":"0","margin":"20px 0 0","whiteSpace":"nowrap","color":"#333","fontWeight":"500"}'
				@size-change="sizeChange"
				@current-change="currentChange" 
				@prev-click="prevClick"
				@next-click="nextClick"  />
		</div>
		<formModel ref="formRef" @formModelChange="formModelChange"></formModel>
		<Approval ref="approvalRef" :tableName="tableName" @shChange="searchClick()"></Approval>
		<manyidudiaochaFormModel ref="manyidudiaochaFormModelRef" @formModelChange="formModelChange"></manyidudiaochaFormModel>
	</div>
</template>
<script setup>
	import axios from 'axios'
	import {
		reactive,
		ref,
		getCurrentInstance,
		nextTick,
		onMounted,
		watch,
	} from 'vue'
	import {
		useRoute,
		useRouter
	} from 'vue-router'
	import {
		ElMessageBox
	} from 'element-plus'
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	import formModel from './formModel.vue'
	
	//基础信息
	const tableName = 'zhilixinxi'
	const formName = '治理信息'
	const route = useRoute()
	//基础信息
	onMounted(()=>{
	})
	//列表数据
	const list = ref(null)
	const table = ref(null)
	const listQuery = ref({
		page: 1,
		limit: 20,
		sort: 'id',
		order: 'desc'
	})
	const searchQuery = ref({})
	const selRows = ref([])
	const listLoading = ref(false)
	const listChange = (row) =>{
		nextTick(()=>{
			table.value.clearSelection()
			table.value.toggleRowSelection(row)
		})
	}
	//列表
	const getList = () => {
		listLoading.value = true
		let params = JSON.parse(JSON.stringify(listQuery.value))
		params['sort'] = 'id'
		params['order'] = 'desc'
		if(searchQuery.value.zhilimingcheng&&searchQuery.value.zhilimingcheng!=''){
			params['zhilimingcheng'] = '%' + searchQuery.value.zhilimingcheng + '%'
		}
          if(searchQuery.value.sfsh && searchQuery.value.sfsh!=''){
            params['sfsh'] = searchQuery.value.sfsh
          }
		if(searchQuery.value.hedaoquyu&&searchQuery.value.hedaoquyu!=''){
			params['hedaoquyu'] = searchQuery.value.hedaoquyu
		}
          if(searchQuery.value.sfsh && searchQuery.value.sfsh!=''){
            params['sfsh'] = searchQuery.value.sfsh
          }
          if(searchQuery.value.sfsh && searchQuery.value.sfsh!=''){
            params['sfsh'] = searchQuery.value.sfsh
          }
          if(searchQuery.value.sfsh && searchQuery.value.sfsh!=''){
            params['sfsh'] = searchQuery.value.sfsh
          }
          if(searchQuery.value.sfsh && searchQuery.value.sfsh!=''){
            params['sfsh'] = searchQuery.value.sfsh
          }
          if(searchQuery.value.sfsh && searchQuery.value.sfsh!=''){
            params['sfsh'] = searchQuery.value.sfsh
          }
          if(searchQuery.value.sfsh && searchQuery.value.sfsh!=''){
            params['sfsh'] = searchQuery.value.sfsh
          }
          if(searchQuery.value.sfsh && searchQuery.value.sfsh!=''){
            params['sfsh'] = searchQuery.value.sfsh
          }
          if(searchQuery.value.sfsh && searchQuery.value.sfsh!=''){
            params['sfsh'] = searchQuery.value.sfsh
          }
          if(searchQuery.value.sfsh && searchQuery.value.sfsh!=''){
            params['sfsh'] = searchQuery.value.sfsh
          }
          if(searchQuery.value.sfsh && searchQuery.value.sfsh!=''){
            params['sfsh'] = searchQuery.value.sfsh
          }
          if(searchQuery.value.sfsh && searchQuery.value.sfsh!=''){
            params['sfsh'] = searchQuery.value.sfsh
          }
		context?.$http({
			url: `${tableName}/page`,
			method: 'get',
			params: params
		}).then(res => {
			listLoading.value = false
			list.value = res.data.data.list
			total.value = Number(res.data.data.total)
		})
	}
	//删
	const delClick = (id) => {
		let ids = ref([])
		if (id) {
			ids.value = [id]
		} else {
			if (selRows.value.length) {
				for (let x in selRows.value) {
					ids.value.push(selRows.value[x].id)
				}
			} else {
				return false
			}
		}
		ElMessageBox.confirm(`是否删除选中${formName}`, '提示', {
			confirmButtonText: '是',
			cancelButtonText: '否',
			type: 'warning',
		}).then(() => {
			context?.$http({
				url: `${tableName}/delete`,
				method: 'post',
				data: ids.value
			}).then(res => {
				context?.$toolUtil.message('删除成功', 'success',()=>{
					getList()
				})
			})
		})
	}
	//多选
	const handleSelectionChange = (e) => {
		selRows.value = e
	}
	//列表数据
	//分页
	const total = ref(0)
	const layouts = ref(["total","prev","pager","next","sizes","jumper"])
	const sizeChange = (size) => {
		listQuery.value.limit = size
		getList()
	}
	const currentChange = (page) => {
		listQuery.value.page = page
		getList()
	}
	const prevClick = () => {
		listQuery.value.page = listQuery.value.page - 1
		getList()
	}
	const nextClick = () => {
		listQuery.value.page = listQuery.value.page + 1
		getList()
	}
	//分页
	//权限验证
	const btnAuth = (e,a)=>{
		return context?.$toolUtil.isAuth(e,a)
	}
	//搜索
	const hedaoquyuLists = ref([])
	const gethedaoquyuLists = () => {
		context?.$http({
			url: 'option/hedaoquyu/hedaoquyu',
			method:'get'
		}).then(res=>{
			hedaoquyuLists.value = res.data.data
		})
	}
	gethedaoquyuLists()
	const searchClick = () => {
		listQuery.value.page = 1
		getList()
	}
	//表单
	const formRef = ref(null)
	const formModelChange=()=>{
		searchClick()
	}
	const addClick = ()=>{
		formRef.value.init()
	}
	const editClick = ()=>{
		if(selRows.value.length){
			formRef.value.init(selRows.value[0].id,'edit')
		}
	}
	
	const infoClick = (id=null)=>{
		if(id){
			formRef.value.init(id,'info')
		}
		else if(selRows.value.length){
			formRef.value.init(selRows.value[0].id,'info')
		}
	}
	// 表单
	// 预览文件
	const preClick = (file) =>{
		if(!file){
			context?.$toolUtil.message('文件不存在','error')
		}
		window.open(context?.$config.url + file)
		// const a = document.createElement('a');
		// a.style.display = 'none';
		// a.setAttribute('target', '_blank');
		// file && a.setAttribute('download', file);
		// a.href = context?.$config.url + file;
		// document.body.appendChild(a);
		// a.click();
		// document.body.removeChild(a);
	}
	// 下载文件
	const download = (file) => {
		if(!file){
			context?.$toolUtil.message('文件不存在','error')
		}
		let arr = file.replace(new RegExp('file/', "g"), "")
		axios.get((location.href.split(context?.$config.name).length>1 ? location.href.split(context?.$config.name)[0] :'') + context?.$config.name + '/file/download?fileName=' + arr, {
			headers: {
				token: context?.$toolUtil.storageGet('Token')
			},
			responseType: "blob"
		}).then(({
			data
		}) => {
			const binaryData = [];
			binaryData.push(data);
			const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
				type: 'application/pdf;chartset=UTF-8'
			}))
			const a = document.createElement('a')
			a.href = objectUrl
			a.download = arr
			// a.click()
			// 下面这个写法兼容火狐
			a.dispatchEvent(new MouseEvent('click', {
				bubbles: true,
				cancelable: true,
				view: window
			}))
			window.URL.revokeObjectURL(data)
		})
	}

	//审核
	import Approval from '@/components/common/approval.vue'
	const approvalRef = ref(null)
	const approvalClick = (row) => {
		let params = {
			id:row.id,
			zhilimingcheng: row.zhilimingcheng,
			hedaoquyu: row.hedaoquyu,
			tupian: row.tupian,
			jihuacuoshi: row.jihuacuoshi,
			xiangqingxinxi: row.xiangqingxinxi,
			shuomingfujian: row.shuomingfujian,
			sfsh: row.sfsh,
			shhf: row.shhf,
			gongzuorenyuanzhanghao: row.gongzuorenyuanzhanghao,
			gongzuorenyuanxingming: row.gongzuorenyuanxingming,
			thumbsupnum: row.thumbsupnum,
			crazilynum: row.crazilynum,
		}
		nextTick(() => {
			approvalRef.value.approvalClick(params )
		})
	}

    // 查看评论
	const commentClick=(id)=>{
		context?.$router.push('/discusszhilixinxi?refid=' + id)
	}
	import manyidudiaochaFormModel from '@/views/manyidudiaocha/formModel'
	const manyidudiaochaFormModelRef = ref(null)
    const manyidudiaochaCrossAddOrUpdateHandler = (row,type,crossOptAudit,statusColumnName,tips,statusColumnValue) => {
		if(crossOptAudit=='是'&&row.sfsh!='是') {
			context?.$toolUtil.message('请审核通过后再操作！','error')
			return
		}
		if(statusColumnName!=''&&!statusColumnName.startsWith("[")) {
			var obj = row
			for (var o in obj){
				if(o==statusColumnName && obj[o]==statusColumnValue){
					context?.$toolUtil.message(tips,'error')
					return;
				}
			}
		}
		nextTick(()=>{
			manyidudiaochaFormModelRef.value.init(row.id,'cross','投票',row,'zhilixinxi',statusColumnName,tips,statusColumnValue)
		})
    }
	//查询审核状态列表
	const approvalLists = ref([])
	//初始化
	const init = () => {
        approvalLists.value = "是,否,待审核".split(',');
		getList()
	}
	init()
</script>
<style lang="scss" scoped>
	
	// 操作盒子
	.list_search_view {
		margin: 0 0 20px;
		// 搜索盒子
		.search_form {
			display: flex;
			align-items: center;
			// 子盒子
			.search_view {
				margin: 0 10px 0 0;
				display: flex;
				align-items: center;
				// 搜索label
				.search_label {
					margin: 0 10px 0 0;
					color: #666;
					font-weight: 500;
					display: inline-block;
					font-size: 14px;
					line-height: 40px;
					height: 40px;
				}
				// 搜索item
				.search_box {
					width: auto;
					// 输入框
					:deep(.search_inp) {
						border: 1px solid #ddd;
						border-radius: 4px;
						padding: 0 10px;
						background: #fff;
						width: 100%;
						line-height: 34px;
						box-sizing: border-box;
						//去掉默认样式
						.el-input__wrapper{
							border: none;
							box-shadow: none;
							background: none;
							border-radius: 0;
							height: 100%;
							padding: 0;
						}
						.is-focus {
							box-shadow: none !important;
						}
					}
					// 下拉框
					:deep(.search_sel) {
						border: 1px solid #ddd;
						border-radius: 4px;
						padding: 0 10px;
						background: #fff;
						width: 100%;
						line-height: 34px;
						box-sizing: border-box;
						//去掉默认样式
						.select-trigger{
							height: 100%;
							.el-input{
								height: 100%;
								.el-input__wrapper{
									border: none;
									box-shadow: none;
									background: none;
									border-radius: 0;
									height: 100%;
									padding: 0;
								}
								.is-focus {
									box-shadow: none !important;
								}
							}
						}
					}
				}
			}
			// 搜索按钮盒子
			.search_btn_view {
				width: 20%;
				display: flex;
				padding: 0 20px;
				// 搜索按钮
				.search_btn {
					border: 0;
					cursor: pointer;
					border-radius: 4px;
					padding: 0 24px;
					outline: none;
					color: #fff;
					background: #D09D7D;
					width: auto;
					font-size: 14px;
					height: 32px;
				}
				// 搜索按钮-悬浮
				.search_btn:hover {
					background: #1c9c9390;
				}
			}
		}
		//头部按钮盒子
		.btn_view {
			margin: 20px 0;
			display: flex;
			// 其他
			:deep(.el-button--default){
				border: 0;
				cursor: pointer;
				border-radius: 4px;
				padding: 0 24px;
				margin: 0 10px 0 0;
				outline: none;
				color: #313131;
				background: #EED7B3;
				width: auto;
				font-size: 14px;
				height: 36px;
			}
			// 其他-悬浮
			:deep(.el-button--default:hover){
				color: #fff;
				background: #D09D7D;
			}
			// 新增
			:deep(.el-button--success){
				border: 0;
				cursor: pointer;
				border-radius: 4px;
				padding: 0 24px;
				margin: 0 10px 0 0;
				outline: none;
				color: #313131;
				background: #EED7B3;
				width: auto;
				font-size: 14px;
				height: 36px;
			}
			// 新增-悬浮
			:deep(.el-button--success:hover){
				color: #fff;
				background: #D09D7D;
			}
			// 修改
			:deep(.el-button--primary){
				border: 0;
				cursor: pointer;
				border-radius: 4px;
				padding: 0 24px;
				margin: 0 10px 0 0;
				outline: none;
				color: #313131;
				background: #EED7B3;
				width: auto;
				font-size: 14px;
				height: 36px;
			}
			// 修改-悬浮
			:deep(.el-button--primary:hover){
				color: #fff;
				background: #D09D7D;
			}
			// 详情
			:deep(.el-button--info){
				border: 0;
				cursor: pointer;
				border-radius: 4px;
				padding: 0 24px;
				margin: 0 10px 0 0;
				outline: none;
				color: #313131;
				background: #EED7B3;
				width: auto;
				font-size: 14px;
				height: 36px;
			}
			// 详情-悬浮
			:deep(.el-button--info:hover){
				color: #fff;
				background: #D09D7D;
			}
			// 删除
			:deep(.el-button--danger){
				border: 0;
				cursor: pointer;
				border-radius: 4px;
				padding: 0 24px;
				margin: 0 10px 0 0;
				outline: none;
				color: #313131;
				background: #EED7B3;
				width: auto;
				font-size: 14px;
				height: 36px;
			}
			// 删除-悬浮
			:deep(.el-button--danger:hover){
				color: #fff;
				background: #D09D7D;
			}
			// 统计
			:deep(.el-button--warning){
				border: 0;
				cursor: pointer;
				border-radius: 4px;
				padding: 0 24px;
				margin: 0 10px 0 0;
				outline: none;
				color: #313131;
				background: #EED7B3;
				width: auto;
				font-size: 14px;
				height: 36px;
			}
			// 统计-悬浮
			:deep(.el-button--warning:hover){
				color: #fff;
				background: #D09D7D;
			}
		}
	}
	// 表格样式
	.el-table {
		border-radius: 4px;
		padding: 0;
		background: #fff;
		width: 100%;
		border-color: #eee;
		border-width: 1px 0 0 1px;
		border-style: solid;
		:deep(.el-table__header-wrapper) {
			thead {
				color: #999;
				font-weight: 500;
				width: 100%;
				tr {
					background: #D09D7D;
					th {
						background-color: #D09D7D;
						padding: 12px 0;
						color: #fff;
						border-color: #eee;
						border-width: 0 1px 1px 0;
						border-style: solid;
						text-align: left;
						.cell {
							padding: 0 10px;
							word-wrap: normal;
							color: #fff;
							word-break: break-all;
							white-space: normal;
							font-weight: bold;
							display: inline-block;
							vertical-align: middle;
							width: 100%;
							line-height: 24px;
							position: relative;
							text-overflow: ellipsis;
						}
					}
				}
			}
		}
		:deep(.el-table__body-wrapper) {
			tbody {
				width: 100%;
				tr {
					background: #fff;
					height: 60px;
					td {
						padding: 0;
						color: #999;
						background: #fff;
						border-color: #eee;
						border-width: 0 1px 1px 0;
						box-sizing: border-box;
						border-style: solid;
						text-align: left;
						height: 60px;
						.cell {
							padding: 0 10px;
							overflow: hidden;
							word-break: break-all;
							white-space: normal;
							line-height: 24px;
							text-overflow: ellipsis;
							// 编辑
							.el-button--primary {
								border: 0;
								cursor: pointer;
								border-radius: 4px;
								padding: 0 10px;
								box-shadow: 0px 3px 3px -2px #ccc;
								margin: 0 6px 6px 0;
								outline: none;
								color: #2C2C2C;
								background: #EFE6D8;
								width: auto;
								font-size: 14px;
								height: 24px;
							}
							// 编辑-悬浮
							.el-button--primary:hover {
							}
							// 详情
							.el-button--info {
								border: 0;
								cursor: pointer;
								border-radius: 4px;
								padding: 0 10px;
								box-shadow: 0px 3px 3px -2px #ccc;
								margin: 0 6px 6px 0;
								outline: none;
								color: #2C2C2C;
								background: #EFE6D8;
								width: auto;
								font-size: 14px;
								height: 24px;
							}
							// 详情-悬浮
							.el-button--info:hover {
							}
							// 删除
							.el-button--danger {
								border: 0;
								cursor: pointer;
								border-radius: 4px;
								padding: 0 10px;
								box-shadow: 0px 3px 3px -2px #ccc;
								margin: 0 6px 6px 0;
								outline: none;
								color: #2C2C2C;
								background: #EFE6D8;
								width: auto;
								font-size: 14px;
								height: 24px;
							}
							// 删除-悬浮
							.el-button--danger:hover {
							}
							// 跨表
							.el-button--success {
								border: 0;
								cursor: pointer;
								border-radius: 4px;
								padding: 0 10px;
								box-shadow: 0px 3px 3px -2px #ccc;
								margin: 0 6px 6px 0;
								outline: none;
								color: #2C2C2C;
								background: #EFE6D8;
								width: auto;
								font-size: 14px;
								height: 24px;
							}
							// 跨表-悬浮
							.el-button--success:hover {
							}
							// 操作
							.el-button--warning {
								border: 0;
								cursor: pointer;
								border-radius: 4px;
								padding: 0 10px;
								box-shadow: 0px 3px 3px -2px #ccc;
								margin: 0 6px 6px 0;
								outline: none;
								color: #2C2C2C;
								background: #EFE6D8;
								width: auto;
								font-size: 14px;
								height: 24px;
							}
							// 操作-悬浮
							.el-button--warning:hover {
							}
						}
					}
				}
				tr:hover {
					td {
						color: #555;
						background: rgba(200, 200, 200, 0.1);
						border-color: #eee;
						border-width: 0 1px 1px 0;
						border-style: solid;
						text-align: left;
					}
				}
			}
		}
	}
	// 分页器
	.el-pagination {
		// 总页码
		:deep(.el-pagination__total) {
			margin: 0 10px 0 0;
			color: #666;
			font-weight: 400;
			display: inline-block;
			vertical-align: top;
			font-size: 13px;
			line-height: 28px;
			height: 28px;
		}
		// 上一页
		:deep(.btn-prev) {
			border: none;
			border-radius: 2px;
			padding: 0;
			margin: 0 5px;
			color: #666;
			background: #f4f4f5;
			display: inline-block;
			vertical-align: top;
			font-size: 13px;
			line-height: 28px;
			min-width: 35px;
			height: 28px;
		}
		// 下一页
		:deep(.btn-next) {
			border: none;
			border-radius: 2px;
			padding: 0;
			margin: 0 5px;
			color: #666;
			background: #f4f4f5;
			display: inline-block;
			vertical-align: top;
			font-size: 13px;
			line-height: 28px;
			min-width: 35px;
			height: 28px;
		}
		// 上一页禁用
		:deep(.btn-prev:disabled) {
			border: none;
			cursor: not-allowed;
			border-radius: 2px;
			padding: 0;
			margin: 0 5px;
			color: #C0C4CC;
			background: #f4f4f5;
			display: inline-block;
			vertical-align: top;
			font-size: 13px;
			line-height: 28px;
			height: 28px;
		}
		// 下一页禁用
		:deep(.btn-next:disabled) {
			border: none;
			cursor: not-allowed;
			border-radius: 2px;
			padding: 0;
			margin: 0 5px;
			color: #C0C4CC;
			background: #f4f4f5;
			display: inline-block;
			vertical-align: top;
			font-size: 13px;
			line-height: 28px;
			height: 28px;
		}
		// 页码
		:deep(.el-pager) {
			padding: 0;
			margin: 0;
			display: inline-block;
			vertical-align: top;
			// 数字
			.number {
				cursor: pointer;
				padding: 0 4px;
				margin: 0 5px;
				color: #666;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				border-radius: 2px;
				background: #f4f4f5;
				text-align: center;
				min-width: 30px;
				height: 28px;
			}
			// 数字悬浮
			.number:hover {
				cursor: pointer;
				padding: 0 4px;
				margin: 0 5px;
				color: #D09D7D;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				border-radius: 2px;
				background: #f4f4f5;
				text-align: center;
				min-width: 30px;
				height: 28px;
			}
			// 选中
			.number.is-active {
				cursor: default;
				padding: 0 4px;
				margin: 0 5px;
				color: #FFF;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				border-radius: 2px;
				background: #D09D7D;
				text-align: center;
				min-width: 30px;
				height: 28px;
			}
		}
		// sizes
		:deep(.el-pagination__sizes) {
			display: inline-block;
			vertical-align: top;
			font-size: 13px;
			line-height: 28px;
			height: 28px;
			.el-select {
				border: 1px solid #DCDFE6;
				cursor: pointer;
				padding: 0;
				color: #606266;
				display: inline-block;
				font-size: 13px;
				line-height: 28px;
				border-radius: 3px;
				outline: 0;
				background: #FFF;
				width: 100%;
				text-align: center;
				height: 28px;
			}
		}
		// 跳页
		:deep(.el-pagination__jump) {
			margin: 0 0 0 24px;
			color: #606266;
			display: inline-block;
			vertical-align: top;
			font-size: 13px;
			line-height: 28px;
			height: 28px;
			// 输入框
			.el-input {
				border: 1px solid #DCDFE6;
				cursor: pointer;
				padding: 0 3px;
				color: #606266;
				display: inline-block;
				font-size: 14px;
				line-height: 28px;
				border-radius: 3px;
				outline: 0;
				background: #FFF;
				width: 100%;
				text-align: center;
				height: 28px;
				//去掉默认样式
				.el-input__wrapper{
					border: none;
					box-shadow: none;
					background: none;
					border-radius: 0;
					height: 100%;
					padding: 0;
				}
				.is-focus {
					box-shadow: none !important;
				}
			}
		}
	}
</style>
