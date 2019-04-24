// pages/taskStart/taskStart.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    btnChange:true,//播放和暂停按钮的切换
    minutes:0,//分钟
    seconds:10,//秒钟
    secondsSum: 0,//总的秒数
    angleSpeed:0,//canvas每秒钟绘制的角度
    angle:0,//绘制的角度的累计
    timer:null,//定时器
    task:"",//任务说明
    circleWidth:0,//画布的宽度
    circleHeight:0,//画布的高度
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    // 获取路径条状传过来的值
    // console.log(options.task);
    this.setData({
      task: options.task
    });

    // 获取画布的宽度和高度
    const that = this;
    wx.createSelectorQuery().selectAll('.canvasStyle').boundingClientRect(function (circle) {


      //  绘制底部的圆环
      const grayCircle = wx.createCanvasContext('grayCurcle');
      grayCircle.setStrokeStyle("#AED4FF");
      grayCircle.arc(circle[0].width / 2, circle[0].height / 2, 50, 0, Math.PI * 2, true);
      grayCircle.setLineWidth(20);
      grayCircle.stroke();
      grayCircle.draw();
      grayCircle.closePath();

      that.setData({
        circleWidth: circle[0].width/2,
        circleHeight: circle[0].height/2,
      })
      that.drawCircle(0);
    }).exec();

    // 获取总的秒数
    this.setData({
      secondsSum: this.data.minutes * 60 + this.data.seconds
    },function(){
      this.setData({
        angleSpeed: 1 / this.data.secondsSum
      });
      
    }.bind(this));

   
  },
  // 绘制圆环
  drawCircle: function(angle){
    var  context = wx.createCanvasContext('circleCanvas');
    
    // 91C5FE
    context.setStrokeStyle('#91C5FE');
    context.arc(this.data.circleWidth, this.data.circleHeight, 50, -Math.PI * 1 / 2,angle - Math.PI *1 / 2, false);
    context.setLineWidth(20);
    context.stroke();
    context.draw();
  },
  // 按钮的切换
  btnToggle: function(){
    var temp = this.data.btnChange;
    this.setData({
      btnChange:!temp
    });
    if(temp == true){
      this.timePlay();
    }else{
      this.timeStop();
    }
  },
  // 开启定时器
  timePlay: function(){
    clearInterval(this.data.timer);
    this.data.timer = setInterval(function(){
      var tempSeconds = this.data.seconds;
      var tempMinutes = this.data.minutes;
      if (tempSeconds % 60 == 0) {

        tempMinutes--;
        tempSeconds = 60
      }
      var tempAngle = this.data.angle + this.data.angleSpeed;
      this.setData({
        angle: tempAngle,
      })
      this.drawCircle(tempAngle * Math.PI * 2);

      // 时间到了，这里要有别的操作
      if(tempMinutes<0){
        tempSeconds = 1;
        tempMinutes = 0;
        clearInterval(this.data.timer);

        // 跳转到首页
        wx.showModal({
          title: '提示',
          content: '恭喜你，你坚持下来了',
          showCancel:false,
          success: function(){
            wx.switchTab({
              url: '/pages/index/index',
              // complete: function(res){
              //   console.log(res);
              // }
            })
          }
        })
      }

      tempSeconds--;
      this.setData({
        seconds: tempSeconds,
        minutes: tempMinutes
      })
    }.bind(this),1000);
  },
  // 停止定时器
  timeStop(){
    clearInterval(this.data.timer);

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
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})