// pages/start/start.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    tasks: [],

    canvasWidth: 0,//画布的宽度
    canvasHeight: 0,//画布的高度
    inputText: '',//输入框的值
  },


  // 监听输入框的焦点
  inputBlur: function (event){
    if(event.detail.value == ""){
      return;
    }
    this.setData({
      inputText:""
    })
    // 添加任务
    var temp = this.data.tasks;
    temp.push(event.detail.value);
    this.setData({
      tasks: temp
    });
  },
  // 任务开始
  startTask: function(event){

    // console.log(event.currentTarget.dataset.task);
    // 跳转页面附带任务数据
    wx.reLaunch({
      url: '/pages/taskStart/taskStart?task='+event.currentTarget.dataset.task,
    });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    const that = this;

    // 绘制顶部的类似半圆的圆角
    wx.getSystemInfo({
      success: function(res) {
        that.setData({
          canvasWidth: res.windowWidth,
          canvasHeight: res.windowHeight * 0.3
        })
        console.log(res.windowWidth);
        const context = wx.createCanvasContext('firstCanvas')

        context.setFillStyle('#7BBAFF');
        context.arc(res.windowWidth / 2, 0, res.windowWidth / 1.5 , 0, 2 * Math.PI, true);
        context.translate(0, -120);
        context.fill();
        context.draw();
      },
    })
   
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})