(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-0c4fbcdc"],{"00fa":function(e,t,a){e.exports=a.p+"static/img/h3.42d0f93a.png"},1344:function(e,t,a){e.exports=a.p+"static/img/h5.c2a04329.png"},"2ad3":function(e,t,a){e.exports=a.p+"static/img/h15.ec5226c0.png"},"31b0":function(e,t,a){"use strict";var l=a("9eb9"),s=a.n(l);s.a},"51e5":function(e,t,a){e.exports=a.p+"static/img/h8.c07ce382.png"},"58a2":function(e,t,a){e.exports=a.p+"static/img/h14.d6dfbbee.png"},"6d36":function(e,t,a){e.exports=a.p+"static/img/h12.9bc1d8f2.png"},"6f2b":function(e,t,a){e.exports=a.p+"static/img/h9.ffc51429.png"},"7ac9":function(e,t,a){e.exports=a.p+"static/img/h11.f105f083.png"},"84d7":function(e,t,a){"use strict";a.d(t,"i",(function(){return o})),a.d(t,"g",(function(){return r})),a.d(t,"f",(function(){return i})),a.d(t,"h",(function(){return n})),a.d(t,"a",(function(){return c})),a.d(t,"b",(function(){return m})),a.d(t,"c",(function(){return u})),a.d(t,"d",(function(){return p})),a.d(t,"j",(function(){return b})),a.d(t,"e",(function(){return d}));var l=a("b775"),s="http://10.40.79.18:8005";function o(e){return Object(l["a"])({url:s+"/dm/databaseSpecial/page",method:"get",params:e})}function r(e){return Object(l["a"])({url:s+"/dm/databaseSpecial/getById",method:"get",params:e})}function i(e){return Object(l["a"])({url:s+"/dm/databaseSpecial/getAuthorityBySdbId",method:"get",params:e})}function n(e){return Object(l["a"])({url:s+"/dm/databaseSpecial/getSpecialDataList",method:"get",params:e})}function c(e){return Object(l["a"])({url:s+"/dm/databaseSpecial/delete",method:"delete",params:e})}function m(e){return Object(l["a"])({url:s+"/dm/databaseSpecial/empowerDataBatch",method:"post",data:e})}function u(e){return Object(l["a"])({url:s+"/dm/databaseSpecial/empowerDataOne",method:"post",data:e})}function p(e){return Object(l["a"])({url:s+"/dm/databaseSpecial/empowerDatabaseSpecial",method:"post",data:e})}function b(e){return Object(l["a"])({url:s+"/dm/databaseSpecial/save",method:"post",data:e})}function d(e){return Object(l["a"])({url:s+"/dm/fileUpDown/download",method:"get",params:e,responseType:"arraybuffer"})}},"89e9":function(e,t,a){e.exports=a.p+"static/img/h10.a63814ad.png"},9311:function(e,t,a){e.exports=a.p+"static/img/h7.b30eade7.png"},"9eb9":function(e,t,a){},afa1:function(e,t,a){e.exports=a.p+"static/img/h2.ab690d33.png"},ba76:function(e,t,a){e.exports=a.p+"static/img/h13.c347a4c2.png"},c559:function(e,t,a){"use strict";a.r(t);var l=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("section",{staticClass:"handleLiberyDialog"},[a("el-tabs",{attrs:{type:"border-card"}},[a("el-tab-pane",{attrs:{label:"基本信息"}},[a("el-form",{ref:"ruleForm",attrs:{model:e.msgFormDialog,"label-width":"100px"}},[e.handleObj.pageName?e._e():a("el-form-item",{attrs:{label:"图标"}},[e.sdbImg?a("img",{staticStyle:{width:"40px"},attrs:{src:e.sdbImg,alt:""}}):e._e()]),e._v(" "),a("el-form-item",{attrs:{label:"专题库名称"}},[a("el-input",{attrs:{size:"small"},model:{value:e.msgFormDialog.sdbName,callback:function(t){e.$set(e.msgFormDialog,"sdbName",t)},expression:"msgFormDialog.sdbName"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"用途"}},[a("el-input",{attrs:{size:"small"},model:{value:e.msgFormDialog.uses,callback:function(t){e.$set(e.msgFormDialog,"uses",t)},expression:"msgFormDialog.uses"}})],1),e._v(" "),e.handleObj.pageName?e._e():a("el-form-item",{attrs:{label:"创建人"}},[a("el-input",{attrs:{size:"small",disabled:!0},model:{value:e.msgFormDialog.userId,callback:function(t){e.$set(e.msgFormDialog,"userId",t)},expression:"msgFormDialog.userId"}})],1),e._v(" "),e.handleObj.pageName?e._e():a("el-form-item",{attrs:{label:"机构"}},[a("el-input",{attrs:{size:"small",disabled:!0},model:{value:e.msgFormDialog.department,callback:function(t){e.$set(e.msgFormDialog,"department",t)},expression:"msgFormDialog.department"}})],1),e._v(" "),e.handleObj.pageName?e._e():a("el-form-item",{attrs:{label:"联系方式"}},[a("el-input",{attrs:{size:"small",disabled:!0},model:{value:e.msgFormDialog.phoneNum,callback:function(t){e.$set(e.msgFormDialog,"phoneNum",t)},expression:"msgFormDialog.phoneNum"}})],1),e._v(" "),e.handleObj.pageName?e._e():a("el-form-item",{attrs:{label:"申请材料"}},[a("el-button",{attrs:{disabled:!e.msgFormDialog.applyMaterial,size:"small",type:"success",icon:"el-icon-document"},on:{click:e.handleExport}},[e._v("下载")])],1),e._v(" "),a("el-form-item",{attrs:{label:"排序"}},[a("el-input",{attrs:{size:"small"},model:{value:e.msgFormDialog.sortNo,callback:function(t){e.$set(e.msgFormDialog,"sortNo",t)},expression:"msgFormDialog.sortNo"}})],1),e._v(" "),a("div",{staticClass:"dialog-footer"},[a("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.trueDialog("ruleForm")}}},[e._v("保 存")])],1)],1)],1),e._v(" "),a("el-tab-pane",{attrs:{label:"数据库授权"}},[a("el-form",[a("el-row",{staticClass:"center"},[a("el-col",{attrs:{span:24}},[a("span",{staticStyle:{"font-weight":"bold"}},[e._v(e._s(this.msgFormDialog.sdbName))])])],1),e._v(" "),a("el-row",{staticClass:"center"},[a("el-col",{attrs:{span:24}},[a("span",[e._v("专题库英文简称:")]),e._v(" "),a("span",[e._v(e._s(this.msgFormDialog.simpleName))])])],1),e._v(" "),a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.databaseList,border:"",stripe:""}},[a("el-table-column",{attrs:{prop:"databaseId",label:"数据库"}}),e._v(" "),a("el-table-column",{attrs:{label:"权限"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-checkbox-group",{model:{value:t.row.checkList,callback:function(a){e.$set(t.row,"checkList",a)},expression:"scope.row.checkList"}},[a("el-checkbox",{attrs:{label:"createTable"}},[e._v("创建")]),e._v(" "),a("el-checkbox",{attrs:{label:"deleteTable"}},[e._v("删除")]),e._v(" "),a("el-checkbox",{attrs:{label:"tableDataAccess"}},[e._v("读写")])],1)]}}])})],1),e._v(" "),a("el-row",[a("el-col",{staticStyle:{"text-align":"center","margin-top":"10px"}},[a("el-button",{attrs:{type:"primary",size:"small",icon:"el-icon-thumb"},on:{click:e.addPhysicsDefine}},[e._v("数据库授权")])],1)],1)],1)],1),e._v(" "),e.handleObj.pageName?e._e():a("el-tab-pane",{attrs:{label:"专题库资料"}},[a("section",{staticClass:"searchCon"},[a("el-form",{ref:"modelForm",staticClass:"funLoad selSearchCon",attrs:{inline:!0,model:e.searchLibraryObj}},[a("el-form-item",{attrs:{label:"关键字查询"}},[a("el-select",{attrs:{size:"small"},model:{value:e.searchLibraryObj.key,callback:function(t){e.$set(e.searchLibraryObj,"key",t)},expression:"searchLibraryObj.key"}},[a("el-option",{attrs:{label:"资料分类",value:"typeName"}}),e._v(" "),a("el-option",{attrs:{label:"资料名称",value:"dataName"}}),e._v(" "),a("el-option",{attrs:{label:"表名称",value:"tableName"}})],1)],1),e._v(" "),a("el-form-item",{attrs:{label:""}},[a("el-input",{attrs:{size:"small",type:"text"},model:{value:e.searchLibraryObj.valueText,callback:function(t){e.$set(e.searchLibraryObj,"valueText",t)},expression:"searchLibraryObj.valueText"}})],1),e._v(" "),a("el-form-item",{staticClass:"handleSearchBtn"},[a("el-button",{attrs:{size:"small",type:"primary",icon:"el-icon-search"},on:{click:e.searchLibraryFun}},[e._v("查询")])],1)],1)],1),e._v(" "),a("section",{staticClass:"loadTable"},[a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableLibraryData,border:""}},[a("el-table-column",{attrs:{type:"index",width:"50"}}),e._v(" "),a("el-table-column",{attrs:{prop:"typeName",label:"资料分类","show-overflow-tooltip":!0}}),e._v(" "),a("el-table-column",{attrs:{prop:"dataName",label:"资料名称","show-overflow-tooltip":!0}}),e._v(" "),a("el-table-column",{attrs:{prop:"ddataId",label:"四级编码","show-overflow-tooltip":!0}}),e._v(" "),a("el-table-column",{attrs:{prop:"tableName",label:"表名称","show-overflow-tooltip":!0}}),e._v(" "),a("el-table-column",{attrs:{prop:"databaseName",label:"数据库","show-overflow-tooltip":!0}}),e._v(" "),a("el-table-column",{attrs:{prop:"applyAuthority",label:"申请权限",width:"80",formatter:e.applyAuthFormatter}})],1)],1)]),e._v(" "),e.handleObj.pageName?e._e():a("el-tab-pane",{attrs:{label:"基础库资料"}},[a("section",{staticClass:"searchCon"},[a("el-form",{ref:"modelForm",staticClass:"funLoad selSearchCon",attrs:{inline:!0,model:e.searchBaseLibraryObj}},[a("el-form-item",{attrs:{label:"关键字查询"}},[a("el-select",{attrs:{size:"small"},model:{value:e.searchBaseLibraryObj.key,callback:function(t){e.$set(e.searchBaseLibraryObj,"key",t)},expression:"searchBaseLibraryObj.key"}},[a("el-option",{attrs:{label:"资料分类",value:"typeName"}}),e._v(" "),a("el-option",{attrs:{label:"资料名称",value:"dataName"}}),e._v(" "),a("el-option",{attrs:{label:"表名称",value:"tableName"}}),e._v(" "),a("el-option",{attrs:{label:"申请权限",value:"applyAuthority"}}),e._v(" "),a("el-option",{attrs:{label:"审核状态",value:"empowerAuthority"}})],1)],1),e._v(" "),a("el-form-item",{attrs:{label:""}},[a("el-input",{attrs:{size:"small",type:"text"},model:{value:e.searchBaseLibraryObj.valueText,callback:function(t){e.$set(e.searchBaseLibraryObj,"valueText",t)},expression:"searchBaseLibraryObj.valueText"}})],1),e._v(" "),a("el-form-item",{staticClass:"handleSearchBtn"},[a("el-button",{attrs:{size:"small",type:"primary",icon:"el-icon-search"},on:{click:e.loadReadList}},[e._v("查询")]),e._v(" "),a("el-button",{attrs:{type:"primary",size:"small",icon:"el-icon-thumb"},on:{click:function(t){return e.batchUpdatePower("1")}}},[e._v("授权")]),e._v(" "),a("el-button",{attrs:{type:"danger",size:"small",icon:"el-icon-close"},on:{click:function(t){return e.batchUpdatePower("2")}}},[e._v("拒绝")])],1)],1)],1),e._v(" "),a("section",{staticClass:"loadTable"},[a("el-table",{attrs:{data:e.baseTableLibraryData,border:""},on:{"selection-change":e.handleSelectionChange}},[a("el-table-column",{attrs:{type:"selection",width:"45"}}),e._v(" "),a("el-table-column",{attrs:{prop:"typeName",label:"资料分类","show-overflow-tooltip":!0}}),e._v(" "),a("el-table-column",{attrs:{prop:"dataName",label:"资料名称","show-overflow-tooltip":!0}}),e._v(" "),a("el-table-column",{attrs:{prop:"ddataId",label:"四级编码","show-overflow-tooltip":!0}}),e._v(" "),a("el-table-column",{attrs:{prop:"tableName",label:"表名称","show-overflow-tooltip":!0}}),e._v(" "),a("el-table-column",{attrs:{prop:"databaseName",label:"数据库","show-overflow-tooltip":!0}}),e._v(" "),a("el-table-column",{attrs:{prop:"applyAuthority",width:"80px",label:"申请权限",formatter:e.applyAuthFormatter}}),e._v(" "),a("el-table-column",{attrs:{prop:"empowerAuthority",label:"审核状态"},scopedSlots:e._u([{key:"default",fn:function(t){return["1"==t.row.empowerAuthority?a("el-link",{attrs:{underline:!1,size:"small",type:"success",icon:"el-icon-check"}},[e._v("已授权")]):a("el-link",{attrs:{underline:!1,size:"small",type:"danger",icon:"el-icon-close"}},[e._v("已撤销")])]}}],null,!1,2569904908)}),e._v(" "),a("el-table-column",{attrs:{prop:"examine_status",label:"备注"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-popover",{attrs:{trigger:"hover",placement:"top"}},[a("p",[e._v(e._s(t.row.failureReason))]),e._v(" "),a("div",{staticClass:"name-wrapper",attrs:{slot:"reference"},slot:"reference"},[a("el-tag",{attrs:{size:"medium"}},[e._v("查看")])],1)])]}}],null,!1,3453803811)}),e._v(" "),a("el-table-column",{attrs:{label:"操作",width:"240px"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{plain:"",type:"primary",size:"mini",icon:"el-icon-thumb"},on:{click:function(a){return e.updatePower(t.row,"1")}}},[e._v("授权")]),e._v(" "),a("el-button",{attrs:{plain:"",type:"danger",size:"mini",icon:"el-icon-close"},on:{click:function(a){return e.updatePower(t.row,"2")}}},[e._v("拒绝")])]}}],null,!1,1638602640)})],1)],1)])],1)],1)},s=[],o=(a("ac6a"),a("84d7")),r=(a("e09d"),{name:"handleLiberyDialog",props:{handleObj:{type:Object}},data:function(){return{msgFormDialog:{},searchLibraryObj:{key:"typeName",valueText:""},searchBaseLibraryObj:{key:"typeName",valueText:""},baseTableLibraryData:[],tableLibraryData:[],databaseList:[],multipleSelection:[],paramList:[],refuseReasonDialog:!1,refusereason:"",single:!1,sdbImg:"",dialogImageUrlData:[a("dde0"),a("afa1"),a("00fa"),a("d4c8"),a("1344"),a("d0e7"),a("9311"),a("51e5"),a("6f2b"),a("89e9"),a("7ac9"),a("6d36"),a("ba76"),a("58a2"),a("2ad3")]}},created:function(){this.handleObj.id&&(console.log(this.handleObj),this.initDetail()),this.searchLibraryFun(),this.loadReadList()},methods:{applyAuthFormatter:function(e,t){return"1"==e.applyAuthority?"只读":"2"==e.applyAuthority?"读写":void 0},handleExport:function(){var e=this;Object(o["e"])({filePath:this.msgFormDialog.applyMaterial}).then((function(t){t.code?e.$message({type:"warning",message:t.msg}):e.downloadfileCommon(t)}))},initDetail:function(){var e=this;Object(o["g"])({id:this.handleObj.id}).then((function(t){200==t.code&&(e.msgFormDialog=t.data,e.msgFormDialog.applyMaterial||e.handleObj.pageName||e.$message({type:"danger",message:"文件不存在"}))})),Object(o["f"])({sdbId:this.handleObj.id}).then((function(t){200==t.code&&t.data&&t.data.length>0&&(e.databaseList=t.data,e.databaseList.forEach((function(t,a){e.$set(t,"checkList",[]),"2"==t.createTable&&t.checkList.push("createTable"),"2"==t.deleteTable&&t.checkList.push("deleteTable"),"2"==t.tableDataAccess&&t.checkList.push("tableDataAccess")})))}))},trueDialog:function(){var e=this;Object(o["j"])(this.msgFormDialog).then((function(t){200==t.code?(e.$emit("closedialog"),e.$message({type:"success",message:"保存成功"})):e.$message({type:"error",message:t.msg})}))},searchLibraryFun:function(){var e=this,t={dataType:1,sdbId:this.handleObj.id};this.searchLibraryObj.valueText&&(t[this.searchLibraryObj.key]=this.searchLibraryObj.valueText),console.log(t),Object(o["h"])(t).then((function(t){e.tableLibraryData=t.data}))},loadReadList:function(){var e=this,t={dataType:2,sdbId:this.handleObj.id};this.searchBaseLibraryObj.valueText&&(t[this.searchBaseLibraryObj.key]=this.searchBaseLibraryObj.valueText),console.log(t),Object(o["h"])(t).then((function(t){e.baseTableLibraryData=t.data}))},addPhysicsDefine:function(){var e=this,t={},a=this.databaseList;a.forEach((function(e){var t=e.checkList;t.forEach((function(t){e[t]&&(e[t]=2)}))})),t.databaseSpecialAuthorityList=a,t.databaseId=this.msgFormDialog.databaseId,t.sdbId=this.msgFormDialog.id,t.sdbName=this.msgFormDialog.sdbName,t.simpleName=this.msgFormDialog.simpleName,t.userId=this.msgFormDialog.userId,console.log(t),Object(o["d"])(t).then((function(t){200==t.code&&(e.$message({type:"success",message:"授权成功"}),e.databaseList=[],e.initDetail(),e.searchLibraryFun(),e.loadReadList())}))},handleSelectionChange:function(e){this.multipleSelection=e},updatePower:function(e,t){var a=this;e.examineStatus=t,"2"==t?this.$prompt("请输入拒绝原因","提示",{confirmButtonText:"确定",cancelButtonText:"取消"}).then((function(t){var l=t.value;e.failureReason=l,a.editPowerOne(e)})).catch((function(){})):this.editPowerOne(e)},editPowerOne:function(e){var t=this;console.log(e),Object(o["c"])(e).then((function(e){200==e.code?(t.$message({type:"success",message:"授权成功"}),t.loadReadList()):t.$message({type:"error",message:"授权失败"})}))},batchUpdatePower:function(e){var t=this;this.multipleSelection.length>0?"2"==e?this.$prompt("请输入拒绝原因","提示",{confirmButtonText:"确定",cancelButtonText:"取消"}).then((function(e){var a=e.value;t.multipleSelection.forEach((function(e){e.failureReason=a,e.examineStatus=2})),t.editPowerBath()})).catch((function(){})):(this.multipleSelection.forEach((function(e){e.examineStatus=1})),this.editPowerBath()):this.$message({type:"warning",message:"请勾选需要操作的资料"})},editPowerBath:function(){var e=this;console.log(this.multipleSelection),Object(o["b"])(this.multipleSelection).then((function(t){200==t.code?(e.$message({type:"success",message:"授权成功"}),e.loadReadList()):e.$message({type:"error",message:"授权失败"})}))}}}),i=r,n=(a("31b0"),a("2877")),c=Object(n["a"])(i,l,s,!1,null,null,null);t["default"]=c.exports},d0e7:function(e,t,a){e.exports=a.p+"static/img/h6.622ca2de.png"},d4c8:function(e,t,a){e.exports=a.p+"static/img/h4.f54d5416.png"},dde0:function(e,t,a){e.exports=a.p+"static/img/h1.1bc196c8.png"},e09d:function(e,t,a){"use strict";a.d(t,"b",(function(){return o})),a.d(t,"a",(function(){return r}));var l=a("b775"),s="http://10.40.79.18:8005";function o(e){return Object(l["a"])({url:s+"/system/user/gatAllBiz",method:"get",params:e})}function r(e){return Object(l["a"])({url:s+"/dm/databaseSpecial/findByUserId",method:"get",params:e})}}}]);