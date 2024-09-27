<template>
	<div class="home_view">
		<div class="projectTitle">欢迎使用 {{projectName}}</div>
		<div class="count_list">
			<el-collapse-transition v-if="btnAuth('manyidudiaocha','首页总数')">
				<el-card v-show="countTypeList.closemanyidudiaochaCountType" class="card_view">
					<template #header>
						<div class="index_card_head">
							<div class="card_head_title">
								满意度调查
							</div>
							<div class="card_head_right">
								<el-icon @click="countTypeClick('hiddenmanyidudiaochaCountType')" class="showIcons"
									:class="countTypeList.hiddenmanyidudiaochaCountType?'showIcons1':''">
									<ArrowUpBold />
								</el-icon>
								<el-icon @click="countTypeClick('closemanyidudiaochaCountType')" class="closeIcons">
									<CloseBold />
								</el-icon>
							</div>
							
						</div>
					</template>
					<el-collapse-transition>
						<div class="count_item" v-show="countTypeList.hiddenmanyidudiaochaCountType">
							<div class="count_title">满意度调查总数</div>
							<div class="count_num">{{manyidudiaochaCount}}</div>
						</div>
					</el-collapse-transition>
				</el-card>
			</el-collapse-transition>
		</div>
		<div class="card_list">
			<el-collapse-transition v-if="btnAuth('manyidudiaocha','首页统计')">
				<el-card v-show="cardTypeList.closemanyidudiaochaChartType1" class="card_view">
					<template #header>
						<div class="index_card_head">
							<div class="card_head_title">
								满意度调查
							</div>
							<div class="card_head_right">
								<el-icon @click="cardTypeClick('hiddenmanyidudiaochaChartType1')" class="showIcons"
									:class="cardTypeList.hiddenmanyidudiaochaChartType1?'showIcons1':''">
									<ArrowUpBold />
								</el-icon>
								<el-icon @click="cardTypeClick('closemanyidudiaochaChartType1')" class="closeIcons">
									<CloseBold />
								</el-icon>
							</div>
						</div>
					</template>
					<el-collapse-transition>
						<div class="card_item" v-show="cardTypeList.hiddenmanyidudiaochaChartType1">
							<div id="manyidudiaochazhilimingchengEchart1" style="width: 100%;height: 400px;"></div>
						</div>
					</el-collapse-transition>
				</el-card>
			</el-collapse-transition>
			<el-collapse-transition v-if="btnAuth('manyidudiaocha','首页统计')">
				<el-card v-show="cardTypeList.closemanyidudiaochaChartType2" class="card_view">
					<template #header>
						<div class="index_card_head">
							<div class="card_head_title">
								满意度调查
							</div>
							<div class="card_head_right">
								<el-icon @click="cardTypeClick('hiddenmanyidudiaochaChartType2')" class="showIcons"
									:class="cardTypeList.hiddenmanyidudiaochaChartType2?'showIcons1':''">
									<ArrowUpBold />
								</el-icon>
								<el-icon @click="cardTypeClick('closemanyidudiaochaChartType2')" class="closeIcons">
									<CloseBold />
								</el-icon>
							</div>
						</div>
					</template>
					<el-collapse-transition>
						<div class="card_item" v-show="cardTypeList.hiddenmanyidudiaochaChartType2">
							<div id="manyidudiaochahedaoquyuEchart2" style="width: 100%;height: 400px;"></div>
						</div>
					</el-collapse-transition>
				</el-card>
			</el-collapse-transition>
		</div>
	</div>
</template>

<script setup>
	import {
		inject,
		nextTick,
		ref,
		getCurrentInstance
	} from 'vue';
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const projectName = context.$project.projectName
	const countTypeList = ref({
	})
	const getCountList=()=>{
		countTypeList.value.closemanyidudiaochaCountType = true
		countTypeList.value.hiddenmanyidudiaochaCountType = true
		if(btnAuth('manyidudiaocha','首页总数')){
			getmanyidudiaochaCount()
		}
	}
	const manyidudiaochaCount = ref(0)
	const getmanyidudiaochaCount = () => {
		context?.$http({
			url:'manyidudiaocha/count',
			method: 'get'
		}).then(res=>{
			manyidudiaochaCount.value = res.data.data
		})
	}
	const countTypeClick = (e) => {
		countTypeList.value[e] = !countTypeList.value[e]
	}
	const init=()=>{
		getCountList()
		getCardList()
	}
	//权限验证
	const btnAuth = (e,a)=>{
		return context?.$toolUtil.isAuth(e,a)
	}
	let echarts = inject("echarts")
	const cardTypeClick = (e) =>{
		cardTypeList.value[e] = !cardTypeList.value[e]
		setTimeout(()=>{
			getCardList()
		},1000)
	}
	const cardTypeList = ref({
		closemanyidudiaochaChartType1: true,
		hiddenmanyidudiaochaChartType1: true,
		closemanyidudiaochaChartType2: true,
		hiddenmanyidudiaochaChartType2: true,
	})
	const getCardList = () => {
		if(btnAuth('manyidudiaocha','首页统计')){
			getmanyidudiaochaChart1()
		}
		if(btnAuth('manyidudiaocha','首页统计')){
			getmanyidudiaochaChart2()
		}
	}
	const getmanyidudiaochaChart1 = () => {
		nextTick(()=>{
			var zhilimingchengEchart1 = echarts.init(document.getElementById("manyidudiaochazhilimingchengEchart1"),'macarons');
			context?.$http({
				url: "manyidudiaocha/group/zhilimingcheng",
				method: "get",
			}).then(obj=>{
				let res = obj.data.data
				let xAxis = [];
				let yAxis = [];
				let pArray = []
				for(let i=0;i<res.length;i++){
				    xAxis.push(res[i].zhilimingcheng);
				    yAxis.push(parseFloat((res[i].total)));
				    pArray.push({
				        value: parseFloat((res[i].total)),
				        name: res[i].zhilimingcheng
				    })
				}
				var option = {};
                option = {
                    title: {
                        text: '投票结果统计',
                        left: 'center'
                    },
                    tooltip: {
                      trigger: 'item',
                      formatter: '{b} : {c}'
                    },
                    xAxis: {
                        type: 'category',
                        data: xAxis,
                        axisLabel: {
                            rotate: 40
                        }
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: yAxis,
                        type: 'bar'
                    }]
                };
				zhilimingchengEchart1.clear()
				// 使用刚指定的配置项和数据显示图表。
				zhilimingchengEchart1.setOption(option);
				//根据窗口的大小变动图表
				zhilimingchengEchart1.resize();
			})
		})
	}
	const getmanyidudiaochaChart2 = () => {
		nextTick(()=>{
			var hedaoquyuEchart2 = echarts.init(document.getElementById("manyidudiaochahedaoquyuEchart2"),'macarons');
			context?.$http({
				url: "manyidudiaocha/group/hedaoquyu",
				method: "get",
			}).then(obj=>{
				let res = obj.data.data
				let xAxis = [];
				let yAxis = [];
				let pArray = []
				for(let i=0;i<res.length;i++){
				    xAxis.push(res[i].hedaoquyu);
				    yAxis.push(parseFloat((res[i].total)));
				    pArray.push({
				        value: parseFloat((res[i].total)),
				        name: res[i].hedaoquyu
				    })
				}
				var option = {};
                option = {
                    title: {
                        text: '河道投票统计',
                        left: 'center'
                    },
                    tooltip: {
                      trigger: 'item',
                      formatter: '{b} : {c}'
                    },
                    xAxis: {
                        type: 'category',
                        data: xAxis,
                        axisLabel: {
                            rotate: 40
                        }
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: yAxis,
                        type: 'bar'
                    }]
                };
				// 使用刚指定的配置项和数据显示图表。
				hedaoquyuEchart2.clear()
				hedaoquyuEchart2.setOption(option);
				//根据窗口的大小变动图表
				hedaoquyuEchart2.resize();
			})
		})
	}
	init()
</script>
<style lang="scss">
	.projectTitle{
		padding: 20px 0;
		margin: 20px 0 20px;
		color: #4F1904;
		font-weight: bold;
		display: flex;
		width: 100%;
		font-size: 22px;
		justify-content: center;
		align-items: center;
		height: auto;
	}

	.showIcons {
		transition: transform 0.3s;
		margin-right: 10px;
	}

	.showIcons1 {
		transform: rotate(-180deg);
	}
	
	// 总数盒子
	.count_list{
		padding: 0 0 20px;
		grid-column-gap: 20px;
		display: flex;
		width: 100%;
		justify-content: center;
		align-items: flex-start;
		flex-wrap: wrap;
		// 总数card
		.card_view {
			border: 1px solid #ddd;
			box-shadow: 0px 0px 0px rgba(0,0,0,.12);
			padding-top: 60px;
			flex: 1;
			background: linear-gradient(90deg,#FFFAF2, #FDE9CB);
			width: 100%;
			position: relative;
			box-sizing: border-box;
			height: auto;
			// card头部
			.el-card__header {
				border-radius: 20px 0 0 20px;
				padding: 0 20px;
				top: 20px;
				background: #EED6B4;
				line-height: 40px;
				position: absolute;
				right: 0;
				border-bottom: none;
				// 头部盒子
				.index_card_head {
					display: flex;
					width: 100%;
					justify-content: space-between;
					align-items: center;
					// 标题
					.card_head_title {
						color: #313131;
						font-size: 14px;
						margin-right: 40px;
					}
					// 按钮盒子
					.card_head_right {
						display: flex;
						align-items: center;
						// 按钮
						.el-icon {
							cursor: pointer;
							color: #313131;
							font-weight: 700;
							font-size: 14px;
						}
					}
				}
			}
			// body
			.el-card__body {
				padding: 0;
				// body盒子
				.count_item{
					padding: 30px;
					text-align: center;
					// 总数标题
					.count_title{
						color: #9E9E9E;
						font-size: 18px;
						line-height: 2;
						text-align: left;
					}
					// 总数数字
					.count_num{
						color: #E7B668;
						font-weight: 700;
						font-size: 64px;
						line-height: 1;
						text-align: left;
					}
				}
			}
		}
	}
	// 首页盒子
	.home_view {
		padding: 0px 30px;
		margin: 126px 0 0;
		background: #F9F9F9;
		min-height: 100vh;
		height: auto;
	}
	// 统计图盒子
	.card_list {
		padding: 0 0 20px;
		display: flex;
		width: 100%;
		justify-content: space-between;
		align-items: flex-start;
		flex-wrap: wrap;
		// 统计图card
		.card_view {
			border: 1px solid #ddd;
			box-shadow: 0px 0px 0px rgba(0,0,0,.12);
			padding-top: 60px;
			margin: 0 1% 30px;
			width: 48%;
			position: relative;
			box-sizing: border-box;
			height: auto;
			// 头部
			.el-card__header {
				border-radius: 20px 0px 0px 20px;
				padding: 0px 20px;
				top: 20px;
				background: rgb(238, 214, 180);
				line-height: 40px;
				position: absolute;
				right: 0;
				border-bottom: none;
				// 头部盒子
				.index_card_head {
					display: flex;
					justify-content: space-between;
					align-items: center;
					// 标题
					.card_head_title {
						color: #313131;
						font-size: 14px;
					}
					// 按钮盒子
					.card_head_right {
						display: flex;
						align-items: center;
						// 按钮
						.el-icon{
							cursor: pointer;
							color: #313131;
							font-weight: 700;
							font-size: 14px;
						}
					}
				}
			}
			// body
			.el-card__body {
				padding: 0;
				// body盒子
				.card_item{
					padding: 30px;
					text-align: center;
				}
			}
		}
	}
</style>
