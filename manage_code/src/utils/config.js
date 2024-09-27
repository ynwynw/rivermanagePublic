const config = {
  get() {
    return {
      url:
        process.env.VUE_APP_BASE_API_URL + process.env.VUE_APP_BASE_API + '/',
      name: process.env.VUE_APP_BASE_API,
      // 退出到首页链接
      indexUrl: 'http://localhost:8080/rivermanage/front/index.html',
    }
  },
  getProjectName() {
    return {
      projectName: '河道污染治理成果展示平台的设计与实现',
    }
  },
}
export default config
