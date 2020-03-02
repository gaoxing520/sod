import request from "@/utils/request";
const baseUrl = process.env.VUE_APP_DB_API;
// 查询字典分组
export function findMenu(query) {
  return request({
    url: baseUrl + "/restApi/dicmgn/findMenu",
    method: "get",
    params: query
  });
}

//查询表格
export function getTableData(query) {
  return request({
    url: baseUrl + "/restApi/dicmgn/page",
    method: "get",
    params: query
  });
}
//删除
export function deleteByIds(query) {
  return request({
    url: baseUrl + "/restApi/dicmgn/deleteByIds",
    method: "delete",
    params: query
  });
}
//新增
export function addType(query) {
  return request({
    url: baseUrl + "/restApi/dicmgn/addType",
    method: "post",
    params: query
  });
}
// 编辑
export function updateById(query) {
  return request({
    url: baseUrl + "/restApi/dicmgn/updateById",
    method: "put",
    params: query
  });
}