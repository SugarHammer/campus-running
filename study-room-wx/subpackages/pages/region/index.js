// subpackages/pages/region/index.js
import pages from "../../../pages";
import {
  getFloorList,
  getRoomList,
  getSeatList,
  addSeat,
} from "../../../api/region";
Page({
  /**
   * 页面的初始数据
   */
  data: {
    roomName: "",
    roomTime: "",
    stateTime: "",
    endTime: "",
    // 轮播数据
    backgroundSwiper: [
      "https://5b0988e595225.cdn.sohucs.com/images/20190428/eebb7638973642a4866ad9f8f2e09750.jpeg",
      "https://ts1.cn.mm.bing.net/th/id/R-C.89a40c6da31957a27785185c7a8b401d?rik=saDmnO%2f1WiuF1g&riu=http%3a%2f%2fu1.0xiao.cn%2f3cfoodcn%2finfo%2fimage%2f20190614%2f5d0314c400f13.jpeg&ehk=xMpuACG1Su3TSpCXmQFwVrX%2fw6%2b%2f6QdfsST8ESOfHNk%3d&risl=&pid=ImgRaw&r=0",
      "https://ts1.cn.mm.bing.net/th/id/R-C.c50e05e97ce3ca8a8b4ada62e37b72a3?rik=mU1qRk7cGzRKGA&riu=http%3a%2f%2fwww.duoguan.com%2fd%2ffile%2fdnews%2fduoguandongtai%2f2018-05-14%2fde233e5ea821cf6d9fda40586aaad467.png&ehk=%2bk2GG0mOUGKKXvmi7fxhi7%2bUm36nRQtT%2bCpaa%2f0OJ4I%3d&risl=&pid=ImgRaw&r=0",
      "https://www.yunkuaimai.com/Public/home/evolution/images/functions/run.jpg",
    ],
    // 轮播配置
    indicatorDots: true,
    vertical: false,
    autoplay: true,
    interval: 2000,
    duration: 500,
    // 开始时间选择
    minHour: 10,
    maxHour: 20,
    minDate: new Date().getTime(),
    maxDate: new Date(2024, 1, 1).getTime(),
    stateCurrentDate: new Date().getTime(),
    endCurrentDate: new Date().getTime(),
    // 时间弹框状态
    show: false,
    flag: false,
    // 星期数据
    floor: [],
    floorIndex: 0,
    // 房间
    room: [],
    roomIndex: 0,
    // 座位
    seatList: [],
    seatIndex: -1,
    // 预约信息
    dataFrom: {
      seatName: "",
      seatPhone: "",
      seatClass: "",
      floorId: "",
      roomId: "",
      seatId: "",
      seatDay: "",
    },
  },

  onConfirm(event) {
    this.setData({
      stateCurrentDate: event.detail,
      stateTime: this.timestampToTime(event.detail),
      show: false,
    });
    console.log(this.data.stateTime);
  },
  onConfirmEnd(event) {
    this.setData({
      endCurrentDate: event.detail,
      endTime: this.timestampToTime(event.detail),
      flag: false,
    });
    console.log(this.data.endTime);
  },
  onClose() {
    this.setData({ show: false, flag: false });
  },
  showPopup() {
    this.setData({ show: true });
  },
  showPopupEnd() {
    this.setData({ flag: true });
  },
  // 时间转换
  timestampToTime(timestamp) {
    var date = new Date(timestamp); //时间戳为10位需*1000，时间戳为13位的话不需乘1000
    var Y = date.getFullYear() + "-";
    var M =
      (date.getMonth() + 1 < 10
        ? "0" + (date.getMonth() + 1)
        : date.getMonth() + 1) + "-";
    var D = (date.getDate() < 10 ? "0" + date.getDate() : date.getDate()) + "";
    var h =
      (date.getHours() < 10 ? "0" + date.getHours() : date.getHours()) + ":";
    var m =
      date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
    var s =
      date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
    return Y + M + D;
  },

  onChangeName(e) {
    this.setData({
      "dataFrom.seatName": e.detail,
    });
  },
  onChangePhone(e) {
    this.setData({
      "dataFrom.seatPhone": e.detail,
    });
  },
  onChangeMajor(e) {
    this.setData({
      "dataFrom.seatClass": e.detail,
    });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    let oneDay = this.data.stateCurrentDate + 1000 * 60 * 60 * 24;
    this.setData({
      stateTime: this.timestampToTime(this.data.stateCurrentDate),
      endTime: this.timestampToTime(oneDay),
    });
    this.getFloor();
  },

  // 选择星期
  reserveFloor(row) {
    console.log(row)
    this.setData({
      floorIndex: row.target.dataset.index,
      "dataFrom.floorId": row.target.dataset.id,
    });
    this.getRoom();
  },
  // 获取星期
  getFloor() {
    getFloorList().then((info) => {
      if (info.code == 0) {
        this.setData({
          floor: info.data,
          "dataFrom.floorId": info.data[0]?.id || "",
        });
        this.getRoom();
      }
    });
  },

  // 选择自习室
  reserveRoom(row) {
    console.log(row)
    this.setData({
      roomIndex: row.target.dataset.index,
      "dataFrom.roomId": row.target.dataset.id,
      roomName: row.target.dataset.item.roomName,
      roomTime: row.target.dataset.item.openingTime + '-  ' + row.target.dataset.item.closeTime
    });
    this.getSeat();
  },
  // 获取自习室
  getRoom() {
    getRoomList(this.data.dataFrom.floorId).then((info) => {
      if (info.code == 0) {
        this.setData({
          room: info.data,
          roomName: info.data[0]?.roomName,
          "dataFrom.roomId": info.data[0]?.roomId || "",
          roomTime: info.data[0]?.openingTime + '-' + info.data[0]?.closeTime
        });
        this.getSeat();
      }
    });
  },

  // 座位选择
  handSeat(row) {
    console.log(row);
    if (row.target.dataset.item.roomState == "1") {
      wx.showToast({
        title: "此预约已被锁定",
        icon: "none",
        duration: 2000,
      });
      return;
    }
    this.setData({
      seatIndex: row.target.dataset.index,
      "dataFrom.seatId": row.target.dataset.item.seatId,
    });
  },
  // 获取座位
  getSeat() {
    getSeatList(this.data.dataFrom.roomId).then((info) => {
      if (info.code == 0) {
        this.setData({
          seatList: info.data,
        });
      }
    });
    this.setData({
      seatIndex: -1,
    });
  },
  // 预定
  regionCheck() {
    let time =
      this.data.stateTime.substr(0, 4) +
      "年" +
      this.data.stateTime.substr(5, 2) +
      "月" +
      this.data.stateTime.substr(8, 2) +
      "日" +
      "-" +
      this.data.endTime.substr(0, 4) +
      "年" +
      this.data.endTime.substr(5, 2) +
      "月" +
      this.data.endTime.substr(8, 2) +
      "日";
    this.setData({
      "dataFrom.seatDay": time,
    });

    if (
      this.data.dataFrom.seatName == "" ||
      this.data.dataFrom.seatPhone == "" ||
      this.data.dataFrom.seatClass == "" ||
      this.data.dataFrom.seatId == ""
    ) {
      wx.showToast({
        title: "请完善预约信息",
        icon: "none",
        duration: 2000,
      });
    } else {
      addSeat(this.data.dataFrom).then((info) =>{
        if (info.code == 0) {
          wx.showToast({
            title: '预约成功',
            icon: 'success',
            duration: 1500
          })
          setTimeout(() => {
            wx.switchTab({
              url: pages.Application,
            });
          }, 500);
        }
      })
    }
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {},

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {},

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
