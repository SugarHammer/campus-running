import request from './request.js'

export function register(data) {
  return request({
    url: '/applet/register',
    method: 'post',
    data: data
  })
}