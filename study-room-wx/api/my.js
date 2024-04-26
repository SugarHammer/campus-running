import request from './request.js'
// 退出登录
export function outLogin(data) {
  return request({
    url: '/applet/logOut',
    method: 'post',
    data: data
  })
}