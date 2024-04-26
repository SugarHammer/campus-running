// custom-tab-bar/index.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {},


  /**
   * 组件的初始数据
   */
  data: {
    selected: null,
    list: [
      {
        pagePath: "/pages/home/index",
        iconPath: "/static/image/home-off.png",
        selectedIconPath: "/static/image/home-no.png",
        text: "首页",
        name: "home"
      },
      {
        pagePath: "/pages/application/index",
        iconPath: "/static/image/seat-off.png",
        selectedIconPath: "/static/image/seat-no.png",
        text: "预约信息",
        name: "application"
      },
      // {
      //   pagePath: "/pages/backstage/index",
      //   iconPath: "/static/image/backstage-off.png",
      //   selectedIconPath: "/static/image/backstage-no.png",
      //   text: "预约信息",
      //   name: "backstage"
      // },
      {
        pagePath: "/pages/my/index",
        iconPath: "/static/image/my-off.png",
        selectedIconPath: "/static/image/my-no.png",
        text: "我的",
        name: "my"
      },
    ],
  },

  /**
   * 组件的方法列表
   */
  methods: {
    switchTab(e) {
      const data = e.currentTarget.dataset;
      const url = data.path;
      wx.switchTab({
        url,
      });
      this.setData({
        selected: data.index,
      });
    },
  },
});
