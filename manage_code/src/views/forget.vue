<template>
  <div>
    <div class="forget_view">
      <el-form :model="forgetForm" class="forget_box">
        <div class="forget_title">
          河道污染治理成果展示平台的设计与实现{{
            pageType == 1 ? '找回密码' : pageType == 2 ? '输入密保' : '重置密码'
          }}
        </div>
        <div class="tab_line">
          <div class="line"></div>
          <div class="num_line">
            <div
              class="line_number"
              :class="
                (pageType == 1 ? 'line_number1' : '',
                pageType > 1 ? 'line_number2' : '')
              "
            >
              <div class="number" v-if="pageType < 2">1</div>
              <el-icon v-else><Check /></el-icon>
            </div>
            <div
              class="line_number"
              :class="
                (pageType == 2 ? 'line_number1' : '',
                pageType > 2 ? 'line_number2' : '')
              "
            >
              <div class="number" v-if="pageType < 3">2</div>
              <el-icon v-else><Check /></el-icon>
            </div>
            <div
              class="line_number"
              :class="pageType == 3 ? 'line_number1' : ''"
            >
              <div class="number">3</div>
            </div>
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
  background-size: 100% 100%;
  display: flex;
  min-height: 100vh;
  justify-content: center;
  position: relative;
  background-image: url('http://localhost:8080/rivermanage/file/88e54ac25f9d4aa883e39f7d74b4c859.png');
  // 表单盒子
  .forget_box {
    border-radius: 0px;
    padding: 30px 20px;
    box-shadow: rgb(255, 181, 137) 0px 4px 20px 0px;
    margin: 0px 0px 0px 58vw;
    flex-direction: column;
    max-width: 500px;
    background: #fff;
    display: flex;
    width: 34.8vw;
    justify-content: center;
    box-sizing: border-box;
    height: 66vh;
    // 标题
    .forget_title {
      padding: 0px;
      margin: 0 auto 20px;
      color: #5d5b5c;
      font-weight: 600;
      width: 80%;
      font-size: 26px;
      text-align: center;
    }
    // tab
    // 盒子
    .tab_line {
      padding: 30px 0;
      flex-direction: column;
      display: flex;
      width: 100%;
      align-items: center;
      // 中间线
      .line {
        background: #ccc;
        width: 80%;
        height: 2px;
      }
      // item盒子
      .num_line {
        margin: -20px 0 0;
        display: flex;
        width: 80%;
        justify-content: space-between;
        align-items: center;
        height: 40px;
        // 默认样式
        .line_number {
          border: 4px solid #ddd;
          border-radius: 50%;
          color: #aaa;
          background: #eee;
          display: flex;
          width: 40px;
          font-size: 18px;
          justify-content: center;
          align-items: center;
          height: 40px;
        }
        // 选中样式
        .line_number1 {
          color: #d09d7d;
          font-size: 22px;
          border-color: #d09d7d;
        }
        // 完成样式
        .line_number2 {
          color: rgba(0, 170, 0, 1);
          border-color: rgba(0, 170, 0, 1);
        }
      }
    }
    // item
    .list_item {
      margin: 10px auto;
      display: flex;
      width: 80%;
      align-items: center;
      // label
      .item_label {
        flex-shrink: 0;
        white-space: nowrap;
        width: 70px;
      }
      // 输入框
      :deep(.list_inp) {
        border: 1px solid #ddd;
        border-radius: 4px;
        padding: 0 10px;
        color: #666;
        flex: 1;
        line-height: 32px;
        box-sizing: border-box;
        height: 32px;
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
        color: #666;
        flex: 1;
        line-height: 32px;
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
      margin: 20px auto 0;
      display: flex;
      width: 80%;
      align-items: center;
      flex-wrap: wrap;
      // 获取密保
      :deep(.el-button--success) {
        border: 0;
        cursor: pointer;
        border-radius: 4px;
        padding: 0 24px;
        margin: 0 10px 0 0;
        outline: none;
        color: #fff;
        background: #d09d7d;
        flex: 1;
        font-weight: 700;
        font-size: 18px;
        height: 40px;
      }
      // 获取密保悬浮
      :deep(.el-button--success:hover) {
      }
      // 确认密保
      :deep(.el-button--primary) {
        border: 0;
        cursor: pointer;
        padding: 0 24px;
        margin: 0 10px 0 0;
        color: #fff;
        font-weight: 700;
        font-size: 18px;
        border-radius: 4px;
        outline: none;
        background: #d09d7d;
        flex: 1;
        width: auto;
        height: 40px;
      }
      // 确认密保悬浮
      :deep(.el-button--primary:hover) {
      }
      // 重置密码
      :deep(.el-button--warning) {
        border: 0;
        cursor: pointer;
        padding: 0 24px;
        margin: 0 10px 0 0;
        color: #fff;
        font-weight: 700;
        font-size: 18px;
        border-radius: 4px;
        outline: none;
        background: #d09d7d;
        flex: 1;
        width: auto;
        height: 40px;
      }
      // 重置密码悬浮
      :deep(.el-button--warning:hover) {
      }
      .r-login {
        cursor: pointer;
        padding: 10px 0 0;
        color: #999;
        width: 100%;
        font-size: 14px;
        text-align: center;
      }
    }
  }
}
</style>
