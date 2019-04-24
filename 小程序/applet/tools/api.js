const baseUrl = 'http://yun.iaoe.xyz';

// 数据请求
function apiRequest(option) {
  console.log(option);
  wx.request({
    url: baseUrl +　'/user/userOrCreate',
    method: 'post',
    data: option.data,
    success: function(res){
      option.callback(res);
    }
  });
}

function getPeopleInfo(option, callback) {
  option.callback = callback;
  apiRequest(option);
  // callback('test');
}
module.exports = {
  getPeopleInfo,
} 