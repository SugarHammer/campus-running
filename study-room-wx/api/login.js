import request from './request.js'

export function wxLogin(data) {
  return request({
    url: '/applet/wxLogin',
    method: 'post',
    data: data
  })
}

export function Login(data) {
  return request({
    url: '/applet/login',
    method: 'post',
    data: data
  })
}