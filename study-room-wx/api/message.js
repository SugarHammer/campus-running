import request from './request.js'

export function addMessage(data) {
  return request({
    url: '/applet/userMessage',
    method: 'post',
    data: data
  })
}