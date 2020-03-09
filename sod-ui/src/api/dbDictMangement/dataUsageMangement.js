import request from '@/utils/request'
const baseUrl = process.env.VUE_APP_DM;
// 查询表格
export function queryDataBaseLogicAll(query) {
  return request({
    url: baseUrl + '/dm/logicDefine/list',
    method: 'get',
    params: query
  })
}
