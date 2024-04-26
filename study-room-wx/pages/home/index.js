// import {listHome} from "../../api/home/index"
import pages from "../../pages";
Page({
  /**
   * 页面的初始数据
   */
  data: {
    backgroundSwiper: [
      "https://ts1.cn.mm.bing.net/th/id/R-C.5bd758eb44bf5157cee868b73764c652?rik=6Pi3nJotAR2Zsw&riu=http%3a%2f%2fu1.0xiao.cn%2f3cfoodcn%2finfo%2fimage%2f20191014%2f5da44166ebdcc.jpg&ehk=IyccVPzJmNtrrCBrThn2SlQg9d5UW23uKog%2fW%2biaTcI%3d&risl=&pid=ImgRaw&r=0",
      "https://5b0988e595225.cdn.sohucs.com/images/20190428/eebb7638973642a4866ad9f8f2e09750.jpeg",
      "https://ts1.cn.mm.bing.net/th/id/R-C.0e279f83ecc3d75d0a7c9ae4188900a8?rik=asg4baYTa5PKmA&riu=http%3a%2f%2fpic.qqtf.com%2fup%2f2019-8%2f201988818101847.jpg&ehk=VL5lSJcOP2LWj5tOg6AfFcQ2qPgqnNN2ALGJ0VrM19g%3d&risl=&pid=ImgRaw&r=0",
      "https://ts1.cn.mm.bing.net/th/id/R-C.815cf2d15d82ce670f84cfec9e521eba?rik=57ujtEN3EkWFdg&riu=http%3a%2f%2fbpic.588ku.com%2fback_pic%2f05%2f73%2f20%2f425bc54c4fed1db.jpg!%2ffh%2f300%2fquality%2f90%2funsharp%2ftrue%2fcompress%2ftrue&ehk=20em81gGH4plcS2Ldld6VEiPZb1uc5EABHgXzbCXIRo%3d&risl=&pid=ImgRaw&r=0",
    ],
    // 轮播配置
    indicatorDots: true,
    vertical: false,
    autoplay: true,
    interval: 2000,
    duration: 500,
    // 场地介绍
    journalismList: [
      {
        text: "（一）服务预约：学员可通过小程序自行查看课表安排，预约想要参加的健身服务。  ",
      },
      {
        text: "（二）上课提醒&通知：支持线上、线下两种教学模式，在预约服务开始前，会发送上课备忘提醒，如果是线上课，健身教练可通过小程序发送线上课的链接及密码等。",
      }
      // ,
      // {
      //   text: "按功能布局分为比赛区、赛事管理区、运动员及随队官员区、新闻媒体区、裁判员区、观众区、后勤办公服务区、设备及辅助等区域。",
      // }
      // ,
      // {
      //   text: "独立隔断式书桌，可充电;",
      // },
      // {
      //   text: "提前线上预约，无需抢座;",
      // },
      // {
      //   text: "护眼台灯，人体工学座椅;",
      // },
      // {
      //   text: "配备冰箱、微波炉、饮水机;",
      // },
      // {
      //   text: "提供茶水、咖啡、文具、零食等。",
      // }
    ],
    matter: [
      {
        text: "1．确定服务范围和收费标准：明确哪些服务可以提供，如购物、快递、代缴水电费等，并制定合理的收费标准。"
      },
      {
        text: "2．建立预约和接单机制：居民通过电话、APP等方式预约服务，由校园工作人员接单，明确双方责任和服务时间。"
      },
      {
        text: "3.制定安全保障措施：要求校园工作人员持有效证件，并与居民签订服务协议，保障服务过程中的安全问题。"
      },
      {
        text: "4.设立财务管理制度：规定资金来源和管理方式，保障资金安全和透明。"
      },
      {
        text: "5.完善服务评价机制：建立反馈渠道，及时收集用户意见并改进服务。"
      },
      {
        text: "6.做好员工培训和管理：加强对员工的培训，提高服务质量，同时建立考核机制，评估员工绩效。"
      }
      // ,
      // {
      //   text: "（三）游泳池边严禁跳水，常因水浅，造成颈椎受伤而终生瘫痪。"
      // }     
    ]
  },
  regionCheck() {
    wx.navigateTo({
      url: pages.Region
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {},
  /**
   * 生命周期函数--监听页面显示
   */
  async onShow() {
    if (typeof this.getTabBar === "function" && this.getTabBar()) {
      this.getTabBar().setData({
        //唯一标识(防止tab点击两次才生效)
        selected: 0,
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
});
