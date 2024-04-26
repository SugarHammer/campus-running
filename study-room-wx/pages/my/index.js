import pages from "../../pages";
import { outLogin } from "../../api/my";
import { userInfo } from "../../api/information";
// pages/my/index.js
Page({
  /**
   * 页面的初始数据
   */
  data: {
    cardFlag: false,
    userInformation: {},
  },
  // 登录
  login() {
    wx.navigateTo({
      url: pages.Login,
    });
  },
  // 个人信息
  fillIn() {
    wx.navigateTo({
      url: pages.Information,
    });
  },
  handMessage() {
    wx.navigateTo({
      url: pages.Message,
    });
  },
  leaveMessage() {
    wx.navigateTo({
      url: pages.leave,
    });
  },
  announce() {
    wx.navigateTo({
      url: pages.Announce,
    });
  },
  nameList() {
    wx.navigateTo({
      url: pages.NameList,
    });
  },
  //退出登录
  logOut() {
    let that = this
    wx.showModal({
      content: "确认退出登录？",
      success(res) {
        if (res.confirm) {
          outLogin().then((info) => {
            if (info.code == 0) {
              wx.clearStorageSync("userInfo");
              that.setData({
                cardFlag: false,
                userInformation: {},
              });
              wx.switchTab({
                url: pages.Home
              })
            }
          });
        } else if (res.cancel) {
          console.log("取消退出登录");
        }
      },
    });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {},

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {
    setTimeout(() =>{
      userInfo().then((info) =>{
        if (info.code == 0) {
          this.setData({
            userInformation: info.data,
            cardFlag: true,
          })
        }
      })
    }, 550)
    
    // setTimeout((res) => {
    //   if (wx.getStorageSync("userInfo")) {
    //     this.setData({
    //       userInformation: wx.getStorageSync("userInfo") || {},
    //       cardFlag: true,
    //     });
    //   } else {
    //     this.setData({
    //       cardFlag: false,
    //     });
    //   }
    // }, 600);

    if (typeof this.getTabBar === "function" && this.getTabBar()) {
      this.getTabBar().setData({
        //唯一标识（其它设置不同的整数）
        selected: 2,
      });
    }
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {},

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {},

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {},

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {},

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {},
});
