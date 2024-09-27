<template>
  <div>
    <el-dialog
      v-model="formVisible"
      :title="formTitle"
      width="80%"
      destroy-on-close
      :fullscreen="false"
    >
      <el-form
        class="formModel_form"
        ref="formRef"
        :model="form"
        label-width="$template2.back.add.form.base.labelWidth"
        :rules="rules"
      >
        <el-row>
          <el-col :span="24">
            <el-form-item label="治理名称" prop="zhilimingcheng">
              <el-input
                class="list_inp"
                v-model="form.zhilimingcheng"
                placeholder="治理名称"
                type="text"
                :readonly="!isAdd || disabledForm.zhilimingcheng ? true : false"
              />
            </el-form-item>
          </el-col>

          <el-col :span="24">
            <el-form-item label="河道区域" prop="hedaoquyu">
              <el-select
                class="list_sel"
                :disabled="!isAdd || disabledForm.hedaoquyu ? true : false"
                v-model="form.hedaoquyu"
                placeholder="请选择河道区域"
              >
                <el-option
                  v-for="(item, index) in hedaoquyuLists"
                  :label="item"
                  :value="item"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="图片" prop="tupian">
              <uploads
                :disabled="!isAdd || disabledForm.tupian ? true : false"
                action="file/upload"
                tip="请上传图片"
                :limit="3"
                style="width: 100%; text-align: left"
                :fileUrls="form.tupian ? form.tupian : ''"
                @change="tupianUploadSuccess"
              >
              </uploads>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="说明附件" prop="shuomingfujian">
              <uploads
                :disabled="!isAdd || disabledForm.shuomingfujian ? true : false"
                type="file"
                action="file/upload"
                tip="请上传说明附件"
                :limit="1"
                style="width: 100%; text-align: left"
                :fileUrls="form.shuomingfujian ? form.shuomingfujian : ''"
                @change="shuomingfujianUploadSuccess"
              >
              </uploads>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="工作人员账号" prop="gongzuorenyuanzhanghao">
              <el-input
                class="list_inp"
                v-model="form.gongzuorenyuanzhanghao"
                placeholder="工作人员账号"
                type="text"
                :readonly="
                  !isAdd || disabledForm.gongzuorenyuanzhanghao ? true : false
                "
              />
            </el-form-item>
          </el-col>

          <el-col :span="24">
            <el-form-item label="工作人员姓名" prop="gongzuorenyuanxingming">
              <el-input
                class="list_inp"
                v-model="form.gongzuorenyuanxingming"
                placeholder="工作人员姓名"
                type="text"
                :readonly="
                  !isAdd || disabledForm.gongzuorenyuanxingming ? true : false
                "
              />
            </el-form-item>
          </el-col>

          <el-col :span="24">
            <el-form-item label="计划措施" prop="jihuacuoshi">
              <el-input
                v-model="form.jihuacuoshi"
                placeholder="计划措施"
                type="textarea"
                :readonly="!isAdd || disabledForm.jihuacuoshi ? true : false"
              />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="详情信息" prop="xiangqingxinxi">
              <editor
                :value="form.xiangqingxinxi"
                placeholder="请输入详情信息"
                :readonly="!isAdd || disabledForm.xiangqingxinxi ? true : false"
                class="list_editor"
                @change="(e) => editorChange(e, 'xiangqingxinxi')"
              ></editor>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <template #footer v-if="isAdd || type == 'logistics' || type == 'reply'">
        <span class="formModel_btn_box">
          <el-button class="formModel_cancel" @click="closeClick"
            >取消</el-button
          >
          <el-button class="formModel_confirm" type="primary" @click="save">
            提交
          </el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>
<script setup>
import {
  reactive,
  ref,
  getCurrentInstance,
  nextTick,
  computed,
  defineEmits,
} from 'vue'
const context = getCurrentInstance()?.appContext.config.globalProperties
const emit = defineEmits(['formModelChange'])
//基础信息
const tableName = 'zhilixinxi'
const formName = '治理信息'
//基础信息
//form表单
const form = ref({})
const disabledForm = ref({
  zhilimingcheng: false,
  hedaoquyu: false,
  tupian: false,
  jihuacuoshi: false,
  xiangqingxinxi: false,
  shuomingfujian: false,
  sfsh: false,
  shhf: false,
  gongzuorenyuanzhanghao: false,
  gongzuorenyuanxingming: false,
  thumbsupnum: false,
  crazilynum: false,
})
const formVisible = ref(false)
const isAdd = ref(false)
const formTitle = ref('')
//表单验证
//匹配整数
const validateIntNumber = (rule, value, callback) => {
  if (!value) {
    callback()
  } else if (!context?.$toolUtil.isIntNumer(value)) {
    callback(new Error('请输入整数'))
  } else {
    callback()
  }
}
//匹配数字
const validateNumber = (rule, value, callback) => {
  if (!value) {
    callback()
  } else if (!context?.$toolUtil.isNumber(value)) {
    callback(new Error('请输入数字'))
  } else {
    callback()
  }
}
//匹配手机号码
const validateMobile = (rule, value, callback) => {
  if (!value) {
    callback()
  } else if (!context?.$toolUtil.isMobile(value)) {
    callback(new Error('请输入正确的手机号码'))
  } else {
    callback()
  }
}
//匹配电话号码
const validatePhone = (rule, value, callback) => {
  if (!value) {
    callback()
  } else if (!context?.$toolUtil.isPhone(value)) {
    callback(new Error('请输入正确的电话号码'))
  } else {
    callback()
  }
}
//匹配邮箱
const validateEmail = (rule, value, callback) => {
  if (!value) {
    callback()
  } else if (!context?.$toolUtil.isEmail(value)) {
    callback(new Error('请输入正确的邮箱地址'))
  } else {
    callback()
  }
}
//匹配身份证
const validateIdCard = (rule, value, callback) => {
  if (!value) {
    callback()
  } else if (!context?.$toolUtil.checkIdCard(value)) {
    callback(new Error('请输入正确的身份证号码'))
  } else {
    callback()
  }
}
//匹配网站地址
const validateUrl = (rule, value, callback) => {
  if (!value) {
    callback()
  } else if (!context?.$toolUtil.isURL(value)) {
    callback(new Error('请输入正确的URL地址'))
  } else {
    callback()
  }
}
const rules = ref({
  zhilimingcheng: [],
  hedaoquyu: [],
  tupian: [],
  jihuacuoshi: [],
  xiangqingxinxi: [],
  shuomingfujian: [],
  sfsh: [],
  shhf: [],
  gongzuorenyuanzhanghao: [],
  gongzuorenyuanxingming: [],
  thumbsupnum: [{ validator: validateIntNumber, trigger: 'blur' }],
  crazilynum: [{ validator: validateIntNumber, trigger: 'blur' }],
})
//表单验证

const formRef = ref(null)
const id = ref(0)
const type = ref('')
//河道区域列表
const hedaoquyuLists = ref([])
//图片上传回调
const tupianUploadSuccess = (e) => {
  form.value.tupian = e
}
//说明附件上传回调
const shuomingfujianUploadSuccess = (e) => {
  form.value.shuomingfujian = e
}
//methods

//获取唯一标识
const getUUID = () => {
  return new Date().getTime()
}
//重置
const resetForm = () => {
  form.value = {
    zhilimingcheng: '',
    hedaoquyu: '',
    tupian: '',
    jihuacuoshi: '',
    xiangqingxinxi: '',
    shuomingfujian: '',
    sfsh: '待审核',
    shhf: '',
    gongzuorenyuanzhanghao: '',
    gongzuorenyuanxingming: '',
    thumbsupnum: '0',
    crazilynum: '0',
  }
}
//获取info
const getInfo = () => {
  context
    ?.$http({
      url: `${tableName}/info/${id.value}`,
      method: 'get',
    })
    .then((res) => {
      let reg = new RegExp('../../../file', 'g')
      res.data.data.xiangqingxinxi = res.data.data.xiangqingxinxi
        ? res.data.data.xiangqingxinxi.replace(reg, '../../../rivermanage/file')
        : ''
      form.value = res.data.data
      formVisible.value = true
    })
}
const crossRow = ref('')
const crossTable = ref('')
const crossTips = ref('')
const crossColumnName = ref('')
const crossColumnValue = ref('')
//初始化
const init = (
  formId = null,
  formType = 'add',
  formNames = '',
  row = null,
  table = null,
  statusColumnName = null,
  tips = null,
  statusColumnValue = null
) => {
  resetForm()
  if (formId) {
    id.value = formId
    type.value = formType
  }
  if (formType == 'add') {
    isAdd.value = true
    formTitle.value = '新增' + formName
    formVisible.value = true
  } else if (formType == 'info') {
    isAdd.value = false
    formTitle.value = '查看' + formName
    getInfo()
  } else if (formType == 'edit') {
    isAdd.value = true
    formTitle.value = '修改' + formName
    getInfo()
  } else if (formType == 'logistics') {
    isAdd.value = false
    formTitle.value = '修改物流信息'
    getInfo()
  } else if (formType == 'reply') {
    type.value = formType
    isAdd.value = true
    disabledForm.value.cpicture = true
    disabledForm.value.content = true
    formTitle.value = '回复'
    getInfo()
  } else if (formType == 'cross') {
    isAdd.value = true
    formTitle.value = formNames
    // getInfo()
    for (let x in row) {
      if (x == 'zhilimingcheng') {
        form.value.zhilimingcheng = row[x]
        disabledForm.value.zhilimingcheng = true
        continue
      }
      if (x == 'hedaoquyu') {
        form.value.hedaoquyu = row[x]
        disabledForm.value.hedaoquyu = true
        continue
      }
      if (x == 'tupian') {
        form.value.tupian = row[x]
        disabledForm.value.tupian = true
        continue
      }
      if (x == 'jihuacuoshi') {
        form.value.jihuacuoshi = row[x]
        disabledForm.value.jihuacuoshi = true
        continue
      }
      if (x == 'xiangqingxinxi') {
        form.value.xiangqingxinxi = row[x]
        disabledForm.value.xiangqingxinxi = true
        continue
      }
      if (x == 'shuomingfujian') {
        form.value.shuomingfujian = row[x]
        disabledForm.value.shuomingfujian = true
        continue
      }
      if (x == 'gongzuorenyuanzhanghao') {
        form.value.gongzuorenyuanzhanghao = row[x]
        disabledForm.value.gongzuorenyuanzhanghao = true
        continue
      }
      if (x == 'gongzuorenyuanxingming') {
        form.value.gongzuorenyuanxingming = row[x]
        disabledForm.value.gongzuorenyuanxingming = true
        continue
      }
      if (x == 'thumbsupnum') {
        form.value.thumbsupnum = row[x]
        disabledForm.value.thumbsupnum = true
        continue
      }
      if (x == 'crazilynum') {
        form.value.crazilynum = row[x]
        disabledForm.value.crazilynum = true
        continue
      }
    }
    if (row) {
      crossRow.value = row
    }
    if (table) {
      crossTable.value = table
    }
    if (tips) {
      crossTips.value = tips
    }
    if (statusColumnName) {
      crossColumnName.value = statusColumnName
    }
    if (statusColumnValue) {
      crossColumnValue.value = statusColumnValue
    }
    form.value.sfsh = '待审核'
    form.value.thumbsupnum = '0'
    form.value.crazilynum = '0'
    formVisible.value = true
  }

  context
    ?.$http({
      url: `${context?.$toolUtil.storageGet('sessionTable')}/session`,
      method: 'get',
    })
    .then((res) => {
      var json = res.data.data
      if (
        json.hasOwnProperty('gongzuorenyuanzhanghao') &&
        context?.$toolUtil.storageGet('role') != '管理员'
      ) {
        form.value.gongzuorenyuanzhanghao = json.gongzuorenyuanzhanghao
        disabledForm.value.gongzuorenyuanzhanghao = true
      }
      if (
        json.hasOwnProperty('gongzuorenyuanxingming') &&
        context?.$toolUtil.storageGet('role') != '管理员'
      ) {
        form.value.gongzuorenyuanxingming = json.gongzuorenyuanxingming
        disabledForm.value.gongzuorenyuanxingming = true
      }
    })
  context
    ?.$http({
      url: `option/hedaoquyu/hedaoquyu`,
      method: 'get',
    })
    .then((res) => {
      hedaoquyuLists.value = res.data.data
    })
}
//初始化
//声明父级调用
defineExpose({
  init,
})
//关闭
const closeClick = () => {
  formVisible.value = false
}
//富文本
const editorChange = (e, name) => {
  form.value[name] = e
}
//提交
const save = () => {
  if (form.value.tupian != null) {
    form.value.tupian = form.value.tupian.replace(
      new RegExp(context?.$config.url, 'g'),
      ''
    )
  }
  if (form.value.shuomingfujian != null) {
    form.value.shuomingfujian = form.value.shuomingfujian.replace(
      new RegExp(context?.$config.url, 'g'),
      ''
    )
  }
  var table = crossTable.value
  var objcross = JSON.parse(JSON.stringify(crossRow.value))
  let crossUserId = ''
  let crossRefId = ''
  let crossOptNum = ''
  if (type.value == 'cross') {
    if (crossColumnName.value != '') {
      if (!crossColumnName.value.startsWith('[')) {
        for (let o in objcross) {
          if (o == crossColumnName.value) {
            objcross[o] = crossColumnValue.value
          }
        }
        //修改跨表数据
        changeCrossData(objcross)
      } else {
        crossUserId = context?.$toolUtil.storageGet('userid')
        crossRefId = objcross['id']
        crossOptNum = crossColumnName.value.replace(/\[/, '').replace(/\]/, '')
      }
    }
  }
  formRef.value.validate((valid) => {
    if (valid) {
      if (crossUserId && crossRefId) {
        form.value.crossuserid = crossUserId
        form.value.crossrefid = crossRefId
        let params = {
          page: 1,
          limit: 1000,
          crossuserid: form.value.crossuserid,
          crossrefid: form.value.crossrefid,
        }
        context
          ?.$http({
            url: `${tableName}/page`,
            method: 'get',
            params: params,
          })
          .then((res) => {
            if (res.data.data.total >= crossOptNum) {
              context?.$toolUtil.message(`${crossTips.value}`, 'error')
              return false
            } else {
              context
                ?.$http({
                  url: `${tableName}/${!form.value.id ? 'save' : 'update'}`,
                  method: 'post',
                  data: form.value,
                })
                .then((res) => {
                  context?.$toolUtil.message(`操作成功`, 'success', () => {
                    formVisible.value = false
                    emit('formModelChange')
                  })
                })
            }
          })
      } else {
        context
          ?.$http({
            url: `${tableName}/${!form.value.id ? 'save' : 'update'}`,
            method: 'post',
            data: form.value,
          })
          .then((res) => {
            context?.$toolUtil.message(`操作成功`, 'success', () => {
              formVisible.value = false
              emit('formModelChange')
            })
          })
      }
    }
  })
}
//修改跨表数据
const changeCrossData = (row) => {
  context
    ?.$http({
      url: `${crossTable.value}/update`,
      method: 'post',
      data: row,
    })
    .then((res) => {})
}
</script>
<style lang="scss" scoped>
// 表单
.formModel_form {
  border: 1px solid #ddd;
  border-radius: 4px;
  padding: 30px;
  margin: 0;
  background: #fff;
  // form item
  :deep(.el-form-item) {
    margin: 0 0 20px 0;
    display: flex;
    //label
    .el-form-item__label {
      background: none;
      display: block;
      width: 150px;
      min-width: 150px;
      text-align: right;
    }
    // 内容盒子
    .el-form-item__content {
      display: flex;
      width: calc(100% - 120px);
      justify-content: flex-start;
      align-items: center;
      flex-wrap: wrap;
      // 输入框
      .list_inp {
        border: 1px solid #ddd;
        border-radius: 4px;
        padding: 0 10px;
        width: auto;
        line-height: 36px;
        box-sizing: border-box;
        min-width: 350px;
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
      // 下拉框
      .list_sel {
        border: 1px solid #ddd;
        border-radius: 4px;
        padding: 0 10px;
        width: auto;
        line-height: 36px;
        box-sizing: border-box;
        min-width: 350px;
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
      // 富文本
      .list_editor {
        background-color: #fff;
        border-radius: 0;
        padding: 0;
        margin: 0;
        width: auto;
        border-color: #ccc;
        border-width: 0;
        border-style: solid;
        min-width: 800px;
        height: auto;
      }
      // 长文本
      .el-textarea__inner {
        border: 1px solid #ddd;
        padding: 12px;
        outline: none;
        color: #333;
        width: auto;
        font-size: 14px;
        min-height: 120px;
        outline-offset: 4px;
        min-width: 550px;
      }
      //图片上传样式
      .el-upload-list {
        //提示语
        .el-upload__tip {
          margin: 7px 0 0;
          color: #999;
          display: flex;
          font-size: 14px;
          justify-content: flex-start;
          align-items: center;
        }
        //外部盒子
        .el-upload--picture-card {
          border: 1px solid #ddd;
          cursor: pointer;
          background-color: #fff;
          border-radius: 4px;
          width: 80px;
          line-height: 80px;
          text-align: center;
          height: 80px;
          //图标
          .el-icon {
            color: #999;
            font-size: 32px;
            line-height: 80px;
          }
        }
        .el-upload-list__item {
          border: 1px solid #ddd;
          cursor: pointer;
          background-color: #fff;
          border-radius: 4px;
          width: 80px;
          line-height: 80px;
          text-align: center;
          height: 80px;
        }
      }
      //文件上传样式
      .upload-demo {
        width: 100%;
        //外部盒子
        .el-upload-dragger {
          border: 1px solid #ddd;
          cursor: pointer;
          background-color: #fff;
          border-radius: 4px;
          overflow: hidden;
          width: 360px;
          box-sizing: border-box;
          text-align: center;
          height: auto;
        }
        //图标
        .el-icon--upload {
          margin: 0;
          color: #d09d7d;
          font-size: 48px;
          line-height: 48px;
        }
        //提示文字
        .el-upload__text {
          margin: 0px 0 0;
          color: #9e9e9e;
          font-size: 14px;
          em {
            color: #d09d7d;
          }
        }
        //提示文字
        .el-upload__tip {
          margin: 0px 0 0;
          color: #9e9e9e;
          font-size: 14px;
        }
      }
    }
  }
}
// 按钮盒子
.formModel_btn_box {
  display: flex;
  width: 100%;
  justify-content: center;
  align-items: center;
  .formModel_cancel {
    border: 0;
    cursor: pointer;
    border-radius: 4px;
    padding: 0 24px;
    margin: 0 20px 0 0;
    outline: none;
    color: #313131;
    background: #eed7b3;
    width: auto;
    font-size: 14px;
    min-width: 100px;
    height: 36px;
  }
  .formModel_cancel:hover {
    color: #fff;
    background: #d09d7d;
  }

  .formModel_confirm {
    border: 0;
    cursor: pointer;
    border-radius: 4px;
    padding: 0 24px;
    margin: 0 20px 0 0;
    outline: none;
    color: #313131;
    background: #eed7b3;
    width: auto;
    font-size: 14px;
    min-width: 100px;
    height: 36px;
  }
  .formModel_confirm:hover {
    color: #fff;
    background: #d09d7d;
  }
}
</style>
