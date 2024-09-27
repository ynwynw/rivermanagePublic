<template>
  <div>
    <div class="forget_view">
      <div class="outTitle_view">
        <div class="outTilte">{{ projectName }}注册</div>
      </div>
      <el-form :model="forgetForm" class="forget_box">
        <div class="tab_view">
          <div class="tab" :class="pageType == 1 ? 'tab_active' : ''">
            找回密码
          </div>
          <div
            class="tab"
            :class="pageType == 2 ? 'tab_active tab_active1' : ''"
          >
            输入密保
          </div>
          <div
            class="tab"
            :class="pageType == 3 ? 'tab_active tab_active1 tab_active2' : ''"
          >
            重置密码
          </div>
        </div>
      </el-form>
    </div>
  </div>
</template>
<script setup>
import { ref, getCurrentInstance, nextTick } from 'vue'
const context = getCurrentInstance()?.appContext.config.globalProperties
const pageType = ref(1)
const forgetForm = ref({})
const userForm = ref({})
//返回登录
const close = () => {
  context?.$router.push({
    path: '/login',
  })
}
</script>

<style lang="scss" scoped>
.forget_view {
  background-repeat: no-repeat;
  flex-direction: column;
  background-size: cover;
  background: url(http://localhost:8080/rivermanage/file/35644becdb81459c99e81cb9cd43e9c6.png);
  display: flex;
  min-height: 100vh;
  justify-content: center;
  align-items: center;
  position: relative;
  background-position: center center;
  // 标题盒子
  .outTitle_view {
    padding: 0 0 0px;
    display: flex;
    align-items: center;
    .outTilte {
      border: 0px solid #fff;
      border-radius: 0px;
      padding: 10px 20px;
      color: #333;
      font-size: 30px;
    }
  }
  // 表单盒子
  .forget_box {
    border-radius: 0px;
    padding: 50px;
    margin: 20px 0;
    background: rgba(255, 255, 255, 0.96);
    display: block;
    width: 35vw;
    min-height: 500px;
    justify-content: center;
    flex-wrap: wrap;
    // tab
    // 盒子
    .tab_view {
      padding: 0 10% 50px;
      display: flex;
      width: 100%;
      align-items: center;
      // 默认样式
      .tab {
        padding: 12px 0;
        width: calc(100% / 3);
        font-size: 14px;
        box-sizing: border-box;
        text-align: center;
      }
      // 选中样式一
      .tab_active {
        clip-path: polygon(00% 0%, 92% 00%, 100% 50%, 92% 100%, 0 100%, 8% 50%);
        color: #fff;
        background: #cc000090;
      }
      // 选中样式二
      .tab_active1 {
        color: #fff;
        background: #cc000099;
      }
      // 选中样式三
      .tab_active2 {
        color: #fff;
        background: #cc0000;
      }
    }
    // item
    .list_item {
      margin: 10px auto 20px;
      display: flex;
      width: 80%;
      justify-content: flex-start;
      align-items: center;
      // label
      .item_label {
        width: 120px;
        font-size: 14px;
        box-sizing: border-box;
        text-align: right;
      }
      // 输入框
      :deep(.list_inp) {
        border: 1px solid #ddd;
        border-radius: 0px;
        padding: 0 10px;
        width: calc(100% - 230px);
        line-height: 36px;
        box-sizing: border-box;
        height: 36px;
        //去掉默认样式
        .el-input__wrapper {
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
      //下拉框样式
      :deep(.list_sel) {
        border: 1px solid #ddd;
        border-radius: 4px;
        padding: 0 10px;
        background: #fff;
        width: calc(100% - 230px);
        line-height: 36px;
        box-sizing: border-box;
        //去掉默认样式
        .select-trigger {
          height: 100%;
          .el-input {
            height: 100%;
            .el-input__wrapper {
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
    // 按钮盒子
    .list_btn {
      margin: 40px 0 0;
      display: flex;
      width: 100%;
      justify-content: center;
      align-items: center;
      flex-wrap: wrap;
      // 获取密保
      :deep(.el-button--success) {
        border: 0;
        cursor: pointer;
        border-radius: 0px;
        padding: 0 30px;
        margin: 0 10px 0 0;
        outline: none;
        color: #fff;
        background: #cc0000;
        width: auto;
        font-size: 14px;
        min-width: 150px;
        height: 36px;
      }
      // 获取密保悬浮
      :deep(.el-button--success:hover) {
        opacity: 0.8;
      }
      // 确认密保
      :deep(.el-button--primary) {
        border: 0;
        cursor: pointer;
        border-radius: 0px;
        padding: 0 30px;
        margin: 0 10px 0 0;
        outline: none;
        color: #fff;
        background: #cc0000;
        width: auto;
        font-size: 14px;
        min-width: 150px;
        height: 36px;
      }
      // 确认密保悬浮
      :deep(.el-button--primary:hover) {
      }
      // 重置密码
      :deep(.el-button--warning) {
        border: 0;
        cursor: pointer;
        border-radius: 0px;
        padding: 0 30px;
        margin: 0 10px 0 0;
        outline: none;
        color: #fff;
        background: #cc0000;
        width: auto;
        font-size: 14px;
        min-width: 150px;
        height: 36px;
      }
      // 重置密码悬浮
      :deep(.el-button--warning:hover) {
      }
      .r-login {
        cursor: pointer;
        padding: 10px 10% 0;
        color: #999;
        width: 100%;
        font-size: 14px;
        text-align: left;
      }
    }
  }
}
</style>
