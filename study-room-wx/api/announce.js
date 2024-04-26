import request from './request.js'

export function getAnnounceList() {
  return request({
    url: '/applet/listNotice',
    method: 'get'
  })
}

export function getForbanList() {
  return request({
    url: '/applet/listForBan',
    method: 'get'
  })
}

export function getMessageList() {
  return request({
    url: '/applet/MessageList',
    method: 'get'
  })
}