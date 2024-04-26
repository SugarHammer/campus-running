const API_BASE_URL = "http://localhost:8080/self-study"

import pages from "../pages"
const request = (options) => {
  
  return new Promise((resolve,reject) => {
    wx.showLoading({
      title: '加载中',
    })
      options.url = API_BASE_URL + options.url
      wx.request({
          ...options,
          header: {
              'token': wx.getStorageSync('userInfo')?.token || ""                             
          },
          
          success: function (res) {
              let { code, msg } = res.data
              if (code == 401) {
                wx.navigateTo({
                  url: pages.Login,
                });
                wx.hideLoading()
                setTimeout(() =>{
                  wx.showToast({
                    title: "请先登录",
                    icon: "none",
                    duration: 2000,
                  });
                }, 500)
                
                return 
              }
              if (code == 0) {
                // 取消加载
                wx.hideLoading()
              } else {
                wx.hideLoading()
                wx.showToast({
                  title: msg,
                  icon: 'error',
                  duration: 2000
                })
              }
              // 返回成功信息
              resolve(res.data,)    
          },
          fail: function (error) {
              console.log("network-err=>", error);
              // 返回错误信息
              reject(error)
          }
      })
  })
}
export default request