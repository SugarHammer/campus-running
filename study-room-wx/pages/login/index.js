import pages from "../../pages";
import { wxLogin, Login } from "../../api/login"
Page({

  /**
   * 页面的初始数据
   */
  data: {
    // 账号密码表单
   dataAccountFrom: {
    account: "",
    password: ""
   },
   userData: {
    token: ""
   }
  },
  onChange(e) {
    if (e.currentTarget.dataset.sign == "account") { 
      this.setData({
        "dataAccountFrom.account": e.detail
      })
    }
    if (e.currentTarget.dataset.sign == "password") { 
      this.setData({
        "dataAccountFrom.password": e.detail
      })
    }
  },
  // 跳转注册
  jumpRegister() {
    wx.navigateTo({
      url: pages.Register,
    });
  },
  // 账号登录
  accountLogin() {
    // console.log(this.data.dataAccountFrom)
    Login(this.data.dataAccountFrom).then((info) =>{
      console.log(info)
      if (info.code == 0) {
        wx.showToast({
          title: '登录成功',
          icon: 'success',
          duration: 1500
        })
        this.setData({
          userData: info
        })
        wx.setStorageSync('userInfo', this.data.userData)
        setTimeout(() => {
          wx.switchTab({
            url: pages.Home,
          });
        }, 500);
      }
    })
  },  
  // 微信登录
  login() {
    wx.getUserProfile({
      desc: "登录",
      success: (res) =>{
        this.setData({
          userData: res.userInfo
        })
        wx.login({
          success: (row) =>{
            wxLogin({
              code: row.code,
              avatarUrl: res.userInfo.avatarUrl,
              nickName: res.userInfo.nickName
            }).then((info) =>{
              if (info.code == 0) {
                wx.showToast({
                  title: '登录成功',
                  icon: 'success',
                  duration: 2000
                })
                this.setData({
                  "userData.token": info.token
                })
                wx.setStorageSync('userInfo', this.data.userData)
              }
            })
            wx.switchTab({
              url: pages.Home
            })
          }
        })
      }
    })
    
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})