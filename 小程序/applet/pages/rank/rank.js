// pages/rank/rank.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    rankText: ["周排行", "日排行", "月排行"],
    rankTextStyle: [
      { fontSize: 32, color: 'rgb(217,235,255)' },
      { fontSize: 40, color: 'white' },
      { fontSize: 32, color: 'rgb(217,235,255)' }
    ],
    myRankInfo:{hours:22,minutes:22,image:'../../images/head.jpg',rankNum: 1},//我的等级的信息
    listInfo:[
      { hours: 22, minutes: 22, image: '../../images/head.jpg', name:"张三"},
      { hours: 22, minutes: 22, image: '../../images/head.jpg', name:"张三"}, 
      { hours: 22, minutes: 22, image: '../../images/head.jpg', name:"张三"},
      { hours: 22, minutes: 22, image: '../../images/head.jpg', name:"张三"},
      { hours: 22, minutes: 22, image: '../../images/head.jpg', name:"张三"},
    ],//排名表各个用户的信息

    cupImages: ["../../images/first.png", "../../images/second.png", "../../images/third.png"],//奖杯的信息
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.showTabBar();
  },
  clickListener: function(e){
    var index = e.currentTarget.dataset.index;
    // 改变字体的样式
    var temp = [];
    for(var i =0 ; i<this.data.rankText.length ; i++){
      temp[i] = { fontSize: 32, color: 'rgb(217,235,255)'};
    }
    temp[index] = { fontSize: 40, color: 'white' };
    this.setData({
      rankTextStyle:temp,
    });
    var temp= [];
    if (index==0){//周排行
      temp = [
        { hours: 11, minutes: 11, image: '../../images/head.jpg', name: "李四" },
        { hours: 11, minutes: 11, image: '../../images/head.jpg', name: "李四" },
        { hours: 11, minutes: 11, image: '../../images/head.jpg', name: "李四" },
        { hours: 11, minutes: 11, image: '../../images/head.jpg', name: "李四" },
        { hours: 11, minutes: 11, image: '../../images/head.jpg', name: "李四" },
      ]
    }else if(index==1){//日排行
      temp = [
        { hours: 22, minutes: 22, image: '../../images/head.jpg', name: "张三" },
        { hours: 22, minutes: 22, image: '../../images/head.jpg', name: "张三" },
        { hours: 22, minutes: 22, image: '../../images/head.jpg', name: "张三" },
        { hours: 22, minutes: 22, image: '../../images/head.jpg', name: "张三" },
        { hours: 22, minutes: 22, image: '../../images/head.jpg', name: "张三" },
      ];
    }else if(index==2){//月排行
      temp = [
        { hours: 33, minutes: 33, image: '../../images/head.jpg', name: "王五" },
        { hours: 33, minutes: 33, image: '../../images/head.jpg', name: "王五" },
        { hours: 33, minutes: 33, image: '../../images/head.jpg', name: "王五" },
        { hours: 33, minutes: 33, image: '../../images/head.jpg', name: "王五" },
        { hours: 33, minutes: 33, image: '../../images/head.jpg', name: "王五" },
      ];
    }
    this.setData({
      listInfo: temp
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