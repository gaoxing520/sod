import request from "@/utils/request";
const baseUrl = process.env.VUE_APP_DB_API;
// 查询表格
export function getpage(query) {
  return request({
    url: baseUrl + "/api/dbfile/getpage",
    method: "get",
    params: query
  });
}
// 删除
export function deleteByIds(ids) {
  return request({
    url: baseUrl + "/api/dbfile/deleteByIds?ids=" + ids,
    method: "delete"
  });
}
export function upload() {
  return request({
    url: baseUrl + "/api/dbfile/upload",
    method: "post"
  });
}
