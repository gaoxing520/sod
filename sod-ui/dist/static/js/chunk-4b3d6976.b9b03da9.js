(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-4b3d6976"],{"70e3":function(e,t,a){"use strict";a.r(t);var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("section",{staticClass:"handleMoveDialog"},[a("el-form",{ref:"ruleForm",attrs:{model:e.msgFormDialog,rules:e.rules,"label-width":"140px"}},[a("el-row",[a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"物理库",prop:"databaseId"}},[a("el-select",{staticStyle:{width:"100%"},attrs:{filterable:"",placeholder:"请选择物理库"},on:{change:function(t){return e.selectByDatabaseIds(t,"")}},model:{value:e.msgFormDialog.databaseId,callback:function(t){e.$set(e.msgFormDialog,"databaseId",t)},expression:"msgFormDialog.databaseId"}},e._l(e.databaseOptions,(function(e){return a("el-option",{key:e.KEY,attrs:{label:e.VALUE,value:e.KEY}})})),1)],1)],1),e._v(" "),a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"资料名称",prop:"dataClassId"}},[a("el-select",{staticStyle:{width:"100%"},attrs:{filterable:"",placeholder:"请选择资料"},on:{change:e.selectTable},model:{value:e.msgFormDialog.dataClassId,callback:function(t){e.$set(e.msgFormDialog,"dataClassId",t)},expression:"msgFormDialog.dataClassId"}},e._l(e.dataClassIdOptions,(function(e){return a("el-option",{key:e.KEY,attrs:{label:e.VALUE,value:e.KEY}})})),1)],1)],1),e._v(" "),a("el-col",{attrs:{span:24}},[a("el-form-item",{attrs:{label:"迁移条件",prop:"conditions"}},[a("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择迁移条件",filterable:""},model:{value:e.msgFormDialog.conditions,callback:function(t){e.$set(e.msgFormDialog,"conditions",t)},expression:"msgFormDialog.conditions"}},e._l(e.conditionsOptions,(function(e){return a("el-option",{key:e.dictValue,attrs:{label:e.dictLabel,value:e.dictValue}})})),1)],1)],1),e._v(" "),a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"迁移源目录",prop:"sourceDirectory"}},[a("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择"},model:{value:e.msgFormDialog.sourceDirectory,callback:function(t){e.$set(e.msgFormDialog,"sourceDirectory",t)},expression:"msgFormDialog.sourceDirectory"}},e._l(e.sourceDirectoryOptions,(function(e){return a("el-option",{key:e.dictValue,attrs:{label:e.dictLabel,value:e.dictValue}})})),1)],1)],1),e._v(" "),a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"迁移目标目录",prop:"targetDirectory"}},[a("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择"},model:{value:e.msgFormDialog.targetDirectory,callback:function(t){e.$set(e.msgFormDialog,"targetDirectory",t)},expression:"msgFormDialog.targetDirectory"}},e._l(e.targetDirectoryOptions,(function(e){return a("el-option",{key:e.dictValue,attrs:{label:e.dictLabel,value:e.dictValue}})})),1)],1)],1),e._v(" "),a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"表名",prop:"tableName"}},[a("el-input",{attrs:{disabled:""},model:{value:e.msgFormDialog.tableName,callback:function(t){e.$set(e.msgFormDialog,"tableName",t)},expression:"msgFormDialog.tableName"}})],1)],1),e._v(" "),a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"四级编码",prop:"ddataId"}},[a("el-input",{attrs:{disabled:""},model:{value:e.msgFormDialog.ddataId,callback:function(t){e.$set(e.msgFormDialog,"ddataId",t)},expression:"msgFormDialog.ddataId"}})],1)],1),e._v(" "),a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"迁移限制频率",prop:"moveLimit"}},[a("el-input",{attrs:{placeholder:"请输入迁移限制频率单位为秒"},model:{value:e.msgFormDialog.moveLimit,callback:function(t){e.$set(e.msgFormDialog,"moveLimit",t)},expression:"msgFormDialog.moveLimit"}})],1)],1),e._v(" "),a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"超时时间",prop:"executorTimeout"}},[a("el-input",{attrs:{placeholder:"请输入超时时间单位为分钟"},model:{value:e.msgFormDialog.executorTimeout,callback:function(t){e.$set(e.msgFormDialog,"executorTimeout",t)},expression:"msgFormDialog.executorTimeout"}})],1)],1),e._v(" "),a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"是否告警",prop:"isAlarm"}},[a("el-radio-group",{model:{value:e.msgFormDialog.isAlarm,callback:function(t){e.$set(e.msgFormDialog,"isAlarm",t)},expression:"msgFormDialog.isAlarm"}},e._l(e.alarmOptions,(function(t){return a("el-radio",{key:t.dictValue,attrs:{label:t.dictValue}},[e._v(e._s(t.dictLabel))])})),1)],1)],1),e._v(" "),a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"是否清除",prop:"isClear"}},[a("el-radio-group",{model:{value:e.msgFormDialog.isClear,callback:function(t){e.$set(e.msgFormDialog,"isClear",t)},expression:"msgFormDialog.isClear"}},e._l(e.isClearOptions,(function(t){return a("el-radio",{key:t.dictValue,attrs:{label:t.dictValue}},[e._v(e._s(t.dictLabel))])})),1)],1)],1),e._v(" "),a("el-col",{attrs:{span:20}},[a("el-form-item",{attrs:{label:"执行策略",prop:"jobCron"}},[a("el-input",{attrs:{placeholder:"请输入执行策略"},model:{value:e.msgFormDialog.jobCron,callback:function(t){e.$set(e.msgFormDialog,"jobCron",t)},expression:"msgFormDialog.jobCron"}})],1)],1),e._v(" "),a("el-col",{attrs:{span:4}},[a("el-button",{staticStyle:{"margin-left":"10px"},attrs:{size:"small",type:"primary"},on:{click:function(t){e.cronDialogVisible=!0}}},[e._v("执行策略")])],1),e._v(" "),a("el-col",{attrs:{span:24}},["0"!=e.msgFormDialog.isClear?a("el-form-item",{attrs:{label:"二级nas清除条件",prop:"clearConditions"}},[a("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择二级nas清除条件",filterable:""},model:{value:e.msgFormDialog.clearConditions,callback:function(t){e.$set(e.msgFormDialog,"clearConditions",t)},expression:"msgFormDialog.clearConditions"}},e._l(e.clearConditionsOptions,(function(e){return a("el-option",{key:e.dictValue,attrs:{label:e.dictLabel,value:e.dictValue}})})),1)],1):e._e()],1),e._v(" "),a("el-col",{attrs:{span:24}},[a("el-form-item",{attrs:{label:"备注"}},[a("el-input",{attrs:{type:"textarea",placeholder:"请输入内容"},model:{value:e.msgFormDialog.jobDesc,callback:function(t){e.$set(e.msgFormDialog,"jobDesc",t)},expression:"msgFormDialog.jobDesc"}})],1)],1)],1)],1),e._v(" "),"数据注册审核"!=e.handleObj.pageName?a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.trueDialog("ruleForm")}}},[e._v("确 定")]),e._v(" "),a("el-button",{on:{click:function(t){return e.cancelDialog("ruleForm")}}},[e._v("取 消")])],1):e._e(),e._v(" "),e.cronDialogVisible?a("Cron",{attrs:{"append-to-body":"",cronDialogVisible:e.cronDialogVisible},on:{closeCron:e.closeCron,setCron:e.setCron}}):e._e()],1)},r=[],l=(a("96cf"),a("3b8d")),s=a("3d09"),i=a("f651"),n={name:"handleMoveDialog",props:{handleObj:{type:Object}},components:{Cron:s["a"]},data:function(){return{targetDirectoryOptions:[],sourceDirectoryOptions:[],isClearOptions:[],alarmOptions:[],databaseOptions:[],dataClassIdOptions:[],conditionsOptions:[],clearConditionsOptions:[],msgFormDialog:{databaseId:"",dataClassId:"",conditions:"",tableName:"",ddataId:"",clearLimit:"",jobCron:"",isAlarm:"",executorTimeout:"",jobDesc:""},rules:{databaseId:[{required:!0,message:"物理库不能为空",trigger:"blur"}],dataClassId:[{required:!0,message:"资料名称不能为空",trigger:"blur"}],jobCron:[{required:!0,message:"执行策略不能为空",trigger:"blur"}],executorTimeout:[{required:!0,message:"超时时间不能为空",trigger:"blur"}],moveLimit:[{required:!0,message:"限制条数不能为空",trigger:"blur"}],sourceDirectory:[{required:!0,message:"迁移源目录不能为空",trigger:"blur"}],targetDirectory:[{required:!0,message:"迁移目标目录不能为空",trigger:"blur"}]},cronDialogVisible:!1}},created:function(){var e=Object(l["a"])(regeneratorRuntime.mark((function e(){var t=this;return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return this.getDicts("job_is_alarm").then((function(e){t.alarmOptions=e.data})),e.next=3,Object(i["e"])().then((function(e){t.databaseOptions=e.data}));case 3:return e.next=5,this.getDicts("move_is_clear").then((function(e){t.isClearOptions=e.data}));case 5:return e.next=7,this.getDicts("move_source_directory").then((function(e){t.sourceDirectoryOptions=e.data}));case 7:return e.next=9,this.getDicts("move_target_directory").then((function(e){t.targetDirectoryOptions=e.data}));case 9:return e.next=11,this.getDicts("database_move_1_where").then((function(e){t.conditionsOptions=e.data}));case 11:return e.next=13,this.getDicts("database_move_2_where").then((function(e){t.clearConditionsOptions=e.data}));case 13:if("资料存储策略"!=this.handleObj.pageName&&"存储结构概览"!=this.handleObj.pageName&&"数据注册审核"!=this.handleObj.pageName){e.next=19;break}return this.msgFormDialog.databaseId=this.handleObj.databaseId,e.next=17,this.selectByDatabaseIds(this.handleObj.databaseId,this.handleObj.dataClassId);case 17:return e.next=19,this.selectTable(this.handleObj.dataClassId);case 19:if(!this.handleObj.id){e.next=22;break}return e.next=22,Object(i["h"])(this.handleObj.id).then((function(e){t.selectByDatabaseIds(e.data.databaseId,e.data.dataClassId),t.msgFormDialog=e.data}));case 22:case"end":return e.stop()}}),e,this)})));function t(){return e.apply(this,arguments)}return t}(),methods:{selectTable:function(e){this.msgFormDialog.tableName="",this.findTable(this.msgFormDialog.databaseId,this.msgFormDialog.dataClassId)},findTable:function(e,t){var a=this;Object(i["g"])(e,t).then((function(e){console.log(e),a.msgFormDialog.ddataId=e.data.ddataId,a.msgFormDialog.tableName=e.data.tableName,a.msgFormDialog.vTableName=e.data.vTableName}))},selectByDatabaseIds:function(){var e=Object(l["a"])(regeneratorRuntime.mark((function e(t,a){var o=this;return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.next=2,Object(i["f"])(t,a).then((function(e){o.dataClassIdOptions=e.data,o.msgFormDialog.dataClassId=a}));case 2:case"end":return e.stop()}}),e)})));function t(t,a){return e.apply(this,arguments)}return t}(),trueDialog:function(e){var t=this;this.msgFormDialog.triggerStatus=1,console.log(this.msgFormDialog),this.$refs[e].validate((function(e){e&&(void 0!=t.msgFormDialog.id?Object(i["l"])(t.msgFormDialog).then((function(e){200===e.code?(t.msgSuccess("修改成功"),t.$emit("cancelHandle")):t.msgError(e.msg)})):Object(i["a"])(t.msgFormDialog).then((function(e){200===e.code?(t.msgSuccess("新增成功"),t.$emit("cancelHandle")):t.msgError(e.msg)})))}))},cancelDialog:function(e){this.$emit("cancelHandle")},setCron:function(e){this.msgFormDialog.jobCron=e,this.cronDialogVisible=!1},closeCron:function(){this.cronDialogVisible=!1}}},c=n,d=(a("9188"),a("2877")),m=Object(d["a"])(c,o,r,!1,null,null,null);t["default"]=m.exports},9188:function(e,t,a){"use strict";var o=a("eba0"),r=a.n(o);r.a},eba0:function(e,t,a){},f651:function(e,t,a){"use strict";a.d(t,"i",(function(){return l})),a.d(t,"h",(function(){return s})),a.d(t,"a",(function(){return i})),a.d(t,"l",(function(){return n})),a.d(t,"b",(function(){return c})),a.d(t,"e",(function(){return d})),a.d(t,"f",(function(){return m})),a.d(t,"g",(function(){return u})),a.d(t,"j",(function(){return g})),a.d(t,"k",(function(){return b})),a.d(t,"c",(function(){return p})),a.d(t,"d",(function(){return f}));var o=a("b775"),r="http://10.40.79.18:8005";function l(e){return Object(o["a"])({url:r+"/schedule/move/list",method:"get",params:e})}function s(e){return Object(o["a"])({url:r+"/schedule/move/"+e,method:"get"})}function i(e){return Object(o["a"])({url:r+"/schedule/move",method:"post",data:e})}function n(e){return Object(o["a"])({url:r+"/schedule/move",method:"put",data:e})}function c(e){return Object(o["a"])({url:r+"/schedule/move/"+e,method:"delete"})}function d(){return Object(o["a"])({url:r+"/schedule/move/findDatabase",method:"get"})}function m(e,t){return Object(o["a"])({url:r+"/schedule/move/findDataClassId",method:"get",params:{databaseId:e,dataClassId:t}})}function u(e,t){return Object(o["a"])({url:r+"/schedule/job/getByDatabaseIdAndClassId",method:"get",async:!0,params:{databaseId:e,dataClassId:t}})}function g(e){return Object(o["a"])({url:r+"/schedule/job/startById",method:"get",params:{id:e}})}function b(e){return Object(o["a"])({url:r+"/schedule/job/stop",method:"get",params:{id:e}})}function p(e){return Object(o["a"])({url:r+"/schedule/job/execute",method:"get",params:{id:e}})}function f(e){return Object(o["a"])({url:r+"/schedule/move/export",method:"get",params:e,responseType:"arraybuffer"})}}}]);