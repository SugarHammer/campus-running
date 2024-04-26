import request from './request.js'

export function getFloorList(params) {
  return request({
    url: '/applet/getAllFloor',
    method: 'get',
    params
  })
}

export function getRoomList(params) {
  return request({
    url: '/applet/getRoomByFloor?id=' + params,
    method: 'get'
  })
}

export function getSeatList(params) {
  return request({
    url: '/applet/getSeatByRoom?id=' + params,
    method: 'get'
  })
}

export function addSeat(data) {
  return request({
    url: '/applet/appointment',
    method: 'post',
    data: data
  })
}