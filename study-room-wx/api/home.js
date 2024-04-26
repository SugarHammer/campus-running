import request from './request.js'

export function listHome(params) {
  return request({
    url: '/serHelpCategory/getHelpCate',
    methods: 'GET',
    params
  })
}