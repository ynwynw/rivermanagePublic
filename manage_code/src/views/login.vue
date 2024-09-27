<template>
  <div>
    <div class="login_view">
      <el-form :model="loginForm" class="login_form">
        <div class="title_view">河道污染治理成果展示平台的设计与实现登录</div>
        <div class="tabView" v-if="userList.length > 1">
          <div
            class="tab"
            :style="{ width: `calc(100% / ${userList.length})` }"
            :class="loginForm.role == item.roleName ? 'tabActive' : ''"
            v-for="(item, index) in userList"
            :key="index"
            @click="tabClick(item.roleName)"
          >
            {{ item.roleName }}
          </div>
        </div>
        <div class="list_item" v-if="loginType == 1">
          <input
            class="list_inp"
            v-model="loginForm.username"
            placeholder="请输入账号"
          />
        </div>
        <div class="list_item" v-if="loginType == 1">
          <input
            class="list_inp"
            v-model="loginForm.password"
            type="password"
            placeholder="请输入密码"
            @keydown.enter.native="handleLogin"
          />
        </div>
        <div class="btn_view">
          <el-button
            class="login"
            v-if="loginType == 1"
            type="success"
            @click="handleLogin"
            >登录</el-button
          >
          <el-button
            class="register"
            type="primary"
            @click="handleRegister('gongzuorenyuan')"
            >注册工作人员</el-button
          >
        </div>
      </el-form>
    </div>
  </div>
</template>
<script setup>
import { ref, getCurrentInstance, nextTick, onMounted } from 'vue'
const userList = ref([])
const menus = ref([])
const loginForm = ref({
  role: '',
  username: '',
  password: '',
})
const tableName = ref('')
const loginType = ref(1)
const context = getCurrentInstance()?.appContext.config.globalProperties
//注册
const handleRegister = (tableName) => {
  context?.$router.push(`/${tableName}Register`)
}
//登录用户tab切换
const tabClick = (role) => {
  loginForm.value.role = role
}
const handleLogin = () => {
  if (!loginForm.value.username) {
    context?.$toolUtil.message('请输入用户名', 'error')

    return
  }
  if (!loginForm.value.password) {
    context?.$toolUtil.message('请输入密码', 'error')

    return
  }
  if (userList.value.length > 1) {
    if (!loginForm.value.role) {
      context?.$toolUtil.message('请选择角色', 'error')
      verifySlider.value.reset()
      return
    }
    for (let i = 0; i < menus.value.length; i++) {
      if (menus.value[i].roleName == loginForm.value.role) {
        tableName.value = menus.value[i].tableName
      }
    }
  } else {
    tableName.value = userList.value[0].tableName
    loginForm.value.role = userList.value[0].roleName
  }
  login()
}
const login = () => {
  context
    ?.$http({
      url: `${tableName.value}/login?username=${loginForm.value.username}&password=${loginForm.value.password}`,
      method: 'post',
    })
    .then(
      (res) => {
        context?.$toolUtil.storageSet('Token', res.data.token)
        context?.$toolUtil.storageSet('role', loginForm.value.role)
        context?.$toolUtil.storageSet('sessionTable', tableName.value)
        context?.$toolUtil.storageSet('adminName', loginForm.value.username)
        context?.$router.push('/')
      },
      (err) => {}
    )
}
//获取菜单
const getMenu = () => {
  let params = {
    page: 1,
    limit: 1,
    sort: 'id',
  }

  context
    ?.$http({
      url: 'menu/list',
      method: 'get',
      params: params,
    })
    .then((res) => {
      menus.value = JSON.parse(res.data.data.list[0].menujson)
      for (let i = 0; i < menus.value.length; i++) {
        if (menus.value[i].hasBackLogin == '是') {
          userList.value.push(menus.value[i])
        }
      }
      loginForm.value.role = userList.value[0].roleName
      context?.$toolUtil.storageSet('menus', JSON.stringify(menus.value))
    })
}
//初始化
const init = () => {
  getMenu()
}
onMounted(() => {
  init()
})
</script>

<style lang="scss" scoped>
.login_view {
  background-repeat: no-repeat;
  flex-direction: column;
  background-size: 100%100%;
  display: flex;
  min-height: 100vh;
  justify-content: center;
  position: relative;
  background-image: url(http://localhost:8080/rivermanage/file/88e54ac25f9d4aa883e39f7d74b4c859.png);
  // 表单盒子
  .login_form {
    padding: 30px 20px;
    box-shadow: 0px 4px 20px 0px #ffb589;
    margin: 0px 0 0px 58vw;
    flex-direction: column;
    max-width: 500px;
    background: #fff;
    display: flex;
    width: 34.8vw;
    min-height: 500px;
    box-sizing: border-box;
    height: 66vh;
  }
  .title_view {
    padding: 0 0 30px;
    color: #5d5b5c;
    font-weight: 600;
    width: 100%;
    font-size: 26px;
    text-align: center;
  }
  // item盒子
  .list_item {
    margin: 20px auto 0;
    display: flex;
    width: 80%;
    justify-content: flex-start;
    align-items: center;
    // 输入框
    .list_inp {
      border: 2px solid #f4f4f4;
      border-radius: 4px;
      padding: 0 10px;
      color: #666;
      width: 100%;
      line-height: 32px;
      height: 32px;
    }
  }
  // 用户类型样式
  .tabView {
    padding: 0 10%;
    display: flex;
    width: 100%;
    margin-bottom: 10px;
    // 默认样式
    .tab {
      cursor: pointer;
      color: #000;
      background: #fff;
      width: auto;
      line-height: 40px;
      text-align: center;
      height: 40px;
    }
    // 选中样式
    .tabActive {
      cursor: pointer;
      color: #d09d7d;
      font-weight: 700;
      width: auto;
      line-height: 40px;
      border-bottom: 3px solid #d09d7d;
      text-align: center;
    }
  }
  // 按钮盒子
  .btn_view {
    padding: 0;
    margin: 30px auto 0;
    display: flex;
    width: 80%;
    justify-content: flex-start;
    align-items: center;
    flex-wrap: wrap;
    order: 5;
    // 登录
    .login {
      border: 0;
      cursor: pointer;
      border-radius: 0;
      padding: 0 24px;
      margin: 0 auto 10px;
      outline: none;
      color: #fff;
      background: #d09d7d;
      font-weight: 700;
      width: 100%;
      font-size: 18px;
      height: 40px;
    }
    // 注册
    .register {
      border: 0;
      cursor: pointer;
      border-radius: 4px;
      padding: 0 10px;
      margin: 0 6px 10px 0;
      outline: none;
      color: #b8b8b8;
      background: none;
      width: auto;
      font-size: 14px;
      height: 32px;
    }
  }
}
</style>
