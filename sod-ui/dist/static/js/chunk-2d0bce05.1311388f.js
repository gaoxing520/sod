(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d0bce05"],{"2a33":function(s,t,e){"use strict";e.r(t);var l=function(){var s=this,t=s.$createElement,e=s._self._c||t;return e("div",{staticClass:"app-container"},[e("el-row",[e("el-col",{staticClass:"card-box",attrs:{span:12}},[e("el-card",[e("div",{attrs:{slot:"header"},slot:"header"},[e("span",[s._v("CPU")])]),s._v(" "),e("div",{staticClass:"el-table el-table--enable-row-hover el-table--medium"},[e("table",{staticStyle:{width:"100%"},attrs:{cellspacing:"0"}},[e("thead",[e("tr",[e("th",{staticClass:"is-leaf"},[e("div",{staticClass:"cell"},[s._v("属性")])]),s._v(" "),e("th",{staticClass:"is-leaf"},[e("div",{staticClass:"cell"},[s._v("值")])])])]),s._v(" "),e("tbody",[e("tr",[e("td",[e("div",{staticClass:"cell"},[s._v("核心数")])]),s._v(" "),e("td",[s.server.cpu?e("div",{staticClass:"cell"},[s._v(s._s(s.server.cpu.cpuNum))]):s._e()])]),s._v(" "),e("tr",[e("td",[e("div",{staticClass:"cell"},[s._v("用户使用率")])]),s._v(" "),e("td",[s.server.cpu?e("div",{staticClass:"cell"},[s._v(s._s(s.server.cpu.used)+"%")]):s._e()])]),s._v(" "),e("tr",[e("td",[e("div",{staticClass:"cell"},[s._v("系统使用率")])]),s._v(" "),e("td",[s.server.cpu?e("div",{staticClass:"cell"},[s._v(s._s(s.server.cpu.sys)+"%")]):s._e()])]),s._v(" "),e("tr",[e("td",[e("div",{staticClass:"cell"},[s._v("当前空闲率")])]),s._v(" "),e("td",[s.server.cpu?e("div",{staticClass:"cell"},[s._v(s._s(s.server.cpu.free)+"%")]):s._e()])])])])])])],1),s._v(" "),e("el-col",{staticClass:"card-box",attrs:{span:12}},[e("el-card",[e("div",{attrs:{slot:"header"},slot:"header"},[e("span",[s._v("内存")])]),s._v(" "),e("div",{staticClass:"el-table el-table--enable-row-hover el-table--medium"},[e("table",{staticStyle:{width:"100%"},attrs:{cellspacing:"0"}},[e("thead",[e("tr",[e("th",{staticClass:"is-leaf"},[e("div",{staticClass:"cell"},[s._v("属性")])]),s._v(" "),e("th",{staticClass:"is-leaf"},[e("div",{staticClass:"cell"},[s._v("内存")])]),s._v(" "),e("th",{staticClass:"is-leaf"},[e("div",{staticClass:"cell"},[s._v("JVM")])])])]),s._v(" "),e("tbody",[e("tr",[e("td",[e("div",{staticClass:"cell"},[s._v("总内存")])]),s._v(" "),e("td",[s.server.mem?e("div",{staticClass:"cell"},[s._v(s._s(s.server.mem.total)+"G")]):s._e()]),s._v(" "),e("td",[s.server.jvm?e("div",{staticClass:"cell"},[s._v(s._s(s.server.jvm.total)+"M")]):s._e()])]),s._v(" "),e("tr",[e("td",[e("div",{staticClass:"cell"},[s._v("已用内存")])]),s._v(" "),e("td",[s.server.mem?e("div",{staticClass:"cell"},[s._v(s._s(s.server.mem.used)+"G")]):s._e()]),s._v(" "),e("td",[s.server.jvm?e("div",{staticClass:"cell"},[s._v(s._s(s.server.jvm.used)+"M")]):s._e()])]),s._v(" "),e("tr",[e("td",[e("div",{staticClass:"cell"},[s._v("剩余内存")])]),s._v(" "),e("td",[s.server.mem?e("div",{staticClass:"cell"},[s._v(s._s(s.server.mem.free)+"G")]):s._e()]),s._v(" "),e("td",[s.server.jvm?e("div",{staticClass:"cell"},[s._v(s._s(s.server.jvm.free)+"M")]):s._e()])]),s._v(" "),e("tr",[e("td",[e("div",{staticClass:"cell"},[s._v("使用率")])]),s._v(" "),e("td",[s.server.mem?e("div",{staticClass:"cell",class:{"text-danger":s.server.mem.usage>80}},[s._v(s._s(s.server.mem.usage)+"%")]):s._e()]),s._v(" "),e("td",[s.server.jvm?e("div",{staticClass:"cell",class:{"text-danger":s.server.jvm.usage>80}},[s._v(s._s(s.server.jvm.usage)+"%")]):s._e()])])])])])])],1),s._v(" "),e("el-col",{staticClass:"card-box",attrs:{span:24}},[e("el-card",[e("div",{attrs:{slot:"header"},slot:"header"},[e("span",[s._v("服务器信息")])]),s._v(" "),e("div",{staticClass:"el-table el-table--enable-row-hover el-table--medium"},[e("table",{staticStyle:{width:"100%"},attrs:{cellspacing:"0"}},[e("tbody",[e("tr",[e("td",[e("div",{staticClass:"cell"},[s._v("服务器名称")])]),s._v(" "),e("td",[s.server.sys?e("div",{staticClass:"cell"},[s._v(s._s(s.server.sys.computerName))]):s._e()]),s._v(" "),e("td",[e("div",{staticClass:"cell"},[s._v("操作系统")])]),s._v(" "),e("td",[s.server.sys?e("div",{staticClass:"cell"},[s._v(s._s(s.server.sys.osName))]):s._e()])]),s._v(" "),e("tr",[e("td",[e("div",{staticClass:"cell"},[s._v("服务器IP")])]),s._v(" "),e("td",[s.server.sys?e("div",{staticClass:"cell"},[s._v(s._s(s.server.sys.computerIp))]):s._e()]),s._v(" "),e("td",[e("div",{staticClass:"cell"},[s._v("系统架构")])]),s._v(" "),e("td",[s.server.sys?e("div",{staticClass:"cell"},[s._v(s._s(s.server.sys.osArch))]):s._e()])])])])])])],1),s._v(" "),e("el-col",{staticClass:"card-box",attrs:{span:24}},[e("el-card",[e("div",{attrs:{slot:"header"},slot:"header"},[e("span",[s._v("Java虚拟机信息")])]),s._v(" "),e("div",{staticClass:"el-table el-table--enable-row-hover el-table--medium"},[e("table",{staticStyle:{width:"100%"},attrs:{cellspacing:"0"}},[e("tbody",[e("tr",[e("td",[e("div",{staticClass:"cell"},[s._v("Java名称")])]),s._v(" "),e("td",[s.server.jvm?e("div",{staticClass:"cell"},[s._v(s._s(s.server.jvm.name))]):s._e()]),s._v(" "),e("td",[e("div",{staticClass:"cell"},[s._v("Java版本")])]),s._v(" "),e("td",[s.server.jvm?e("div",{staticClass:"cell"},[s._v(s._s(s.server.jvm.version))]):s._e()])]),s._v(" "),e("tr",[e("td",[e("div",{staticClass:"cell"},[s._v("启动时间")])]),s._v(" "),e("td",[s.server.jvm?e("div",{staticClass:"cell"},[s._v(s._s(s.server.jvm.startTime))]):s._e()]),s._v(" "),e("td",[e("div",{staticClass:"cell"},[s._v("运行时长")])]),s._v(" "),e("td",[s.server.jvm?e("div",{staticClass:"cell"},[s._v(s._s(s.server.jvm.runTime))]):s._e()])]),s._v(" "),e("tr",[e("td",{attrs:{colspan:"1"}},[e("div",{staticClass:"cell"},[s._v("安装路径")])]),s._v(" "),e("td",{attrs:{colspan:"3"}},[s.server.jvm?e("div",{staticClass:"cell"},[s._v(s._s(s.server.jvm.home))]):s._e()])]),s._v(" "),e("tr",[e("td",{attrs:{colspan:"1"}},[e("div",{staticClass:"cell"},[s._v("项目路径")])]),s._v(" "),e("td",{attrs:{colspan:"3"}},[s.server.sys?e("div",{staticClass:"cell"},[s._v(s._s(s.server.sys.userDir))]):s._e()])])])])])])],1),s._v(" "),e("el-col",{staticClass:"card-box",attrs:{span:24}},[e("el-card",[e("div",{attrs:{slot:"header"},slot:"header"},[e("span",[s._v("磁盘状态")])]),s._v(" "),e("div",{staticClass:"el-table el-table--enable-row-hover el-table--medium"},[e("table",{staticStyle:{width:"100%"},attrs:{cellspacing:"0"}},[e("thead",[e("tr",[e("th",{staticClass:"is-leaf"},[e("div",{staticClass:"cell"},[s._v("盘符路径")])]),s._v(" "),e("th",{staticClass:"is-leaf"},[e("div",{staticClass:"cell"},[s._v("文件系统")])]),s._v(" "),e("th",{staticClass:"is-leaf"},[e("div",{staticClass:"cell"},[s._v("盘符类型")])]),s._v(" "),e("th",{staticClass:"is-leaf"},[e("div",{staticClass:"cell"},[s._v("总大小")])]),s._v(" "),e("th",{staticClass:"is-leaf"},[e("div",{staticClass:"cell"},[s._v("可用大小")])]),s._v(" "),e("th",{staticClass:"is-leaf"},[e("div",{staticClass:"cell"},[s._v("已用大小")])]),s._v(" "),e("th",{staticClass:"is-leaf"},[e("div",{staticClass:"cell"},[s._v("已用百分比")])])])]),s._v(" "),s.server.sysFiles?e("tbody",s._l(s.server.sysFiles,(function(t){return e("tr",[e("td",[e("div",{staticClass:"cell"},[s._v(s._s(t.dirName))])]),s._v(" "),e("td",[e("div",{staticClass:"cell"},[s._v(s._s(t.sysTypeName))])]),s._v(" "),e("td",[e("div",{staticClass:"cell"},[s._v(s._s(t.typeName))])]),s._v(" "),e("td",[e("div",{staticClass:"cell"},[s._v(s._s(t.total))])]),s._v(" "),e("td",[e("div",{staticClass:"cell"},[s._v(s._s(t.free))])]),s._v(" "),e("td",[e("div",{staticClass:"cell"},[s._v(s._s(t.used))])]),s._v(" "),e("td",[e("div",{staticClass:"cell",class:{"text-danger":t.usage>80}},[s._v(s._s(t.usage)+"%")])])])})),0):s._e()])])])],1)],1)],1)},a=[],v=e("b775"),i="http://10.40.79.18:8005";function c(){return Object(v["a"])({url:i+"/monitor/server",method:"get"})}var r={data:function(){return{loading:[],server:[]}},created:function(){this.getList(),this.openLoading()},methods:{getList:function(){var s=this;c().then((function(t){s.server=t.data,s.loading.close()}))},openLoading:function(){this.loading=this.$loading({lock:!0,text:"拼命读取中",spinner:"el-icon-loading",background:"rgba(0, 0, 0, 0.7)"})}}},_=r,d=e("2877"),C=Object(d["a"])(_,l,a,!1,null,null,null);t["default"]=C.exports}}]);