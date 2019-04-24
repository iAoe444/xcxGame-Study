
Page({
  data: {
    place: ["广师图书馆", "6栋宿舍", "棠下图书馆", "其他"],
    target: ["考研", "期末不挂科", "英语六级", "其他"],
    canvasWidth: 0, //画布的宽度
    canvasHeight: 0, //画布的高度
    color: [
      { bgColor: '', textColor: '' },
      { bgColor: '', textColor: '' },
      { bgColor: '', textColor: '' },
      { bgColor: '', textColor: '' }
    ],//设置学习目标背景颜色
    color2: [
      { bgColor: '', textColor: '' },
      { bgColor: '', textColor: '' },
      { bgColor: '', textColor: '' },
      { bgColor: '', textColor: '' }
    ],//设置学习地方背景颜色

  },
 
  onLoad: function() {
    // wx.login({
    //   success: function(res){
    //     console.log(res);
    //   }
    // })
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
        context.arc(res.windowWidth / 2, 0, res.windowWidth / 1.5, 0, 2 * Math.PI, true);
        context.translate(0, -120);
        context.fill();
        context.draw();

      },
    })
  },
  // 设置所选标签的背景色
  setColor( ev ){
    // console.log(ev.currentTarget.dataset.index);
    var index = ev.currentTarget.dataset.index;
    var temp = [];
    for(var i = 0 ;i < this.data.color.length ; i++){
      temp[i] = { bgColor: '', textColor: '' };
    }
    temp[index] = { bgColor: 'rgb(123,186,255)', textColor: 'white' }
    this.setData({
      color: temp
    });
  },
  // 设置所选标签的背景色
  setColor2(ev) {
    // console.log(ev.currentTarget.dataset.index);
    var index = ev.currentTarget.dataset.index;
    var temp = [];
    for (var i = 0; i < this.data.color2.length; i++) {
      temp[i] = { bgColor: '', textColor: '' };
    }
    temp[index] = { bgColor: 'rgb(123,186,255)', textColor: 'white' }
    this.setData({
      color2: temp
    });
  },
  // 跳转到下个页面
  nextPage() {
    console.log("test");
    wx.createSelectorQuery().selectAll('.targetChoice').boundingClientRect(function (dom) {
      console.log(dom);
    }).exec();
    // return;
    wx.redirectTo({
      url: '/pages/choosereward/choosereward',
    })
  },
})