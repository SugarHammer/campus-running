import request from './request.js'

export function getLicationList(data) {
  return request({
    url: '/applet/myAppointment',
    method: 'post',
    data:data
  })
}
// 结束学习
export function cancelLication(data) {
  return request({
    url: '/applet/over?id=' + data,
    method: 'post',
  })
}

export function overLication(data) {
  return request({
    url: '/applet/cancel?id=' + data,
    method: 'post',
  })
}