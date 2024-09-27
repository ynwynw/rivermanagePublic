const config = {
  get() {
    return {
      url:
        process.env.VUE_APP_BASE_API_URL + process.env.VUE_APP_BASE_API + '/',
      name: process.env.VUE_APP_BASE_API,
      menuList: [
        {
          name: '新闻资讯管理',
          icon: '${frontMenu.fontClass}',
          child: [
            {
              name: '治理政策',
              url: '/index/newsList',
            },
          ],
        },
        {
          name: '治理信息管理',
          icon: '${frontMenu.fontClass}',
          child: [
            {
              name: '治理信息',
              url: '/index/zhilixinxiList',
            },
          ],
        },
      ],
    }
  },
  getProjectName() {
    return {
      projectName: '河道污染治理成果展示平台的设计与实现',
    }
  },
}
export default config
