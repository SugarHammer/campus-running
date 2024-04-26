import request from './request.js'
// 用户信息
export function userInfo() {
  return request({
    url: '/applet/getUser',
    method: 'get'
  })
}

// 修改
export function userUpdate(data) {
  return request({
    url: '/applet/userInfo',
    method: 'post',
    data: data
  })
}