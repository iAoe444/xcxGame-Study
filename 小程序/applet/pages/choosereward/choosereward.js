// pages/choosereward/choosereward.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    rewards:{
      type:Array,
      value: [],
      
    },
    canvasWidth: 0,//画布的宽度
    canvasHeight: 0,//画布的高度
    awardArr: ["", "", "", "", ""],//奖励列表
  },
  // 开始学习
  startStudy(){
    wx.switchTab({
      url: '/pages/index/index',
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    const that = this;
    // 绘制顶部的类似半圆的圆角
    wx.getSystemInfo({
      success: function (res) {
        that.setData({
          canvasWidth: res.windowWidth,
          canvasHeight: res.windowHeight * 0.3
        })
        console.log(res.windowWidth);
        const context = wx.createCanvasContext('firstCanvas')

        context.setFillStyle('#7BBAFF');
        context.arc(res.windowWidth / 2, 0, res.windowWidth / 1.5, 0, 2 * Math.PI, true);
        context.translate(0, -120);
        context.fill();
        context.draw();
      },
    });
  },
  onInputEvent:function(event){
    var that = this;
    var rewards = that.data.rewards;
    var value = event.detail.value;
    var id = event.target.dataset.id;
    rewards[id-1] = value;
    that.setData({
      rewards:rewards
    });
    // console.log(that.data.rewards);
  }
})