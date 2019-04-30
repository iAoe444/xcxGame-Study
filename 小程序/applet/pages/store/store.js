// pages/store/store.js
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  chooseImage: function () {
    var that = this;
    console.log('aaaaaaaaaaaaaaaaaaaa')

    wx.chooseImage({
      count: 1,//最多可以选择的图片总数
      success: function (res) {
        console.log('ssssssssssssssssssssssssss')
        //缓存下 
        wx.showToast({
          title: '正在上传...',
          icon: 'loading',
          mask: true,
          duration: 2000,
          success: function (ress) {
            console.log('成功加载动画');
          }
        })

        console.log(res)
        that.setData({
          imageList: res.tempFilePaths
        })
        //获取第一张图片地址 
        var filep = res.tempFilePaths[0]
        //向服务器端上传图片 
        // getApp().data.servsers,这是在app.js文件里定义的后端服务器地址 
        wx.uploadFile({
          url: 'http://localhost/laravel/public/store/addGoods',
          filePath: filep,
          name: 'img',
          formData: {
            openId:'12345',
            goods:'123123',
            coin:'12323'
          },
          success: function (res) {
            console.log('成功')
            console.log(res)
            // console.log(res.data)
            // var sss = JSON.parse(res.data)
            // var dizhi = sss.dizhi;
            // //输出图片地址 
            // console.log(dizhi);
            // that.setData({
            //   "dizhi": dizhi
            // })

            //do something  
          }, fail: function (err) {
            console.log(err)
          }
        });
      }
    })
  },
  previewImage: function (e) {
    var current = e.target.dataset.src

    wx.previewImage({

      current: current,
      urls: this.data.imageList
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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