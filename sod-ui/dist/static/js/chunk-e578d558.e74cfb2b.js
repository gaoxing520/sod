(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-e578d558"],{"1e4b":function(t,e,a){"use strict";a.r(e);var s=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"home"},[a("section",[a("el-carousel",{attrs:{height:"420px",trigger:"click"}},t._l(t.bannerList,(function(e,s){return a("el-carousel-item",{key:s},[a("img",{attrs:{src:e.src},on:{click:function(e){return t.bannerGoPage(s)}}})])})),1)],1),t._v(" "),a("section",[a("el-row",{staticClass:"row-bg",attrs:{justify:"space-between",type:"flex",gutter:20}},[a("el-col",{attrs:{span:16}},[a("div",{staticClass:"colBox"},[a("span",{staticClass:"homeTitle"},[t._v("资料分类统计")]),t._v(" "),a("div",{staticClass:"handleChart"},[a("el-button",{attrs:{type:t.classType,size:"small"},on:{click:t.classBarInit}},[t._v("按资料分类")]),t._v(" "),a("el-button",{attrs:{type:t.DBType,size:"small"},on:{click:t.DBBarInit}},[t._v("按数据库分类")])],1),t._v(" "),a("div",{staticClass:"chartsBox",attrs:{id:"dataCLassfication"}})])]),t._v(" "),a("el-col",{attrs:{span:8,id:"unCheckBox"}},[a("div",{staticClass:"colBox"},[a("span",{staticClass:"homeTitle"},[t._v("待办提醒")]),t._v(" "),a("div",{staticClass:"eventBox"},t._l(t.eventList,(function(e,s){return a("div",{key:s,staticClass:"eventList",on:{click:function(a){return t.goPageUrl(e.name)}}},[a("i",{staticClass:"el-icon-price-tag"}),t._v(" "),a("span",{staticClass:"name"},[t._v(t._s(e.name))]),t._v("，待办\n              "),a("span",{staticClass:"todoNum"},[t._v(t._s(e.uncheck))]),t._v("条，已办理\n              "),a("span",{staticClass:"haveTodoNum"},[t._v(t._s(e.checked))]),t._v("条\n            ")])})),0)])])],1)],1),t._v(" "),a("section",{staticStyle:{background:"#fff","padding-bottom":"20px"}},[a("span",{staticClass:"homeTitle"},[t._v("存储管理")]),t._v(" "),a("el-row",{staticClass:"storageRow",attrs:{gutter:20}},t._l(t.pieBox,(function(e,s){return a("el-col",{key:s,attrs:{span:8}},[a("el-card",{staticClass:"box-card"},[a("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[a("span",[t._v(t._s(e.LOGIC_NAME))])]),t._v(" "),a("div",{staticClass:"cardBody"},[a("div",{staticClass:"pieBox",attrs:{id:t.pieId(s)}}),t._v(" "),a("div",{staticClass:"progressBox"},t._l(e.DB_INFO,(function(e,s){return a("div",{key:s},[a("span",{staticClass:"name"},[t._v(t._s(e.DATABASE_NAME))]),t._v(" "),a("el-progress",{attrs:{percentage:Number((s/10*100).toFixed(2)),"stroke-width":16,"text-inside":!0,color:"#ffc739"}})],1)})),0)]),t._v(" "),a("div",{staticClass:"cardFooter"},[a("div",{staticClass:"storageInfo"},[a("i",{staticClass:"el-icon-receiving"}),t._v(" "),a("p",{staticClass:"name"},[t._v("总容量")]),t._v(" "),a("span",{staticClass:"num"},[t._v("\n                "+t._s(void 0==e.TOTAL_CAPACITY?"0":e.TOTAL_CAPACITY)+"TB\n              ")])]),t._v(" "),a("div",{staticClass:"storageInfo"},[a("i",{staticClass:"el-icon-bangzhu"}),t._v(" "),a("p",{staticClass:"name"},[t._v("已使用")]),t._v(" "),a("span",{staticClass:"num"},[t._v("\n                "+t._s(void 0==e.USED_CAPACITY?"0":e.USED_CAPACITY)+"TB\n              ")])]),t._v(" "),a("div",{staticClass:"storageInfo"},[a("i",{staticClass:"el-icon-odometer"}),t._v(" "),a("p",{staticClass:"name"},[t._v("剩余")]),t._v(" "),a("span",{staticClass:"num"},[t._v("\n                "+t._s(void 0==e.TOTAL_CAPACITY?"0":e.TOTAL_CAPACITY-e.USED_CAPACITY)+"TB\n              ")])]),t._v(" "),a("div",{staticClass:"storageInfo"},[a("i",{staticClass:"el-icon-tickets"}),t._v(" "),a("p",{staticClass:"name"},[t._v("存储资料")]),t._v(" "),a("span",{staticClass:"num"},[t._v(t._s(void 0==e.NUM?"0":e.NUM)+"种")])])])])],1)})),1)],1),t._v(" "),a("section",{staticStyle:{background:"#fff"}},[a("span",{staticClass:"homeTitle"},[t._v("专题库")]),t._v(" "),a("el-row",{staticClass:"projiectRow",attrs:{gutter:20}},t._l(t.projectLibraryList,(function(e,s){return a("el-col",{key:s,attrs:{span:6}},[a("div",{class:"project"+s},[a("span",[t._v(t._s("信息中心"))]),t._v(" "),a("p",[t._v(t._s(e.SDB_NAME))])])])})),1)],1),t._v(" "),a("section",[a("el-row",{staticClass:"row-bg",attrs:{gutter:20}},[a("el-col",{attrs:{span:16}},[a("div",{staticClass:"colBox"},[a("span",{staticClass:"homeTitle"},[t._v("资料种类统计")]),t._v(" "),a("div",{staticClass:"chartsBox",attrs:{id:"lineChart"}})])]),t._v(" "),a("el-col",{attrs:{span:8}},[a("div",{staticClass:"colBox",staticStyle:{height:"390px"}},[a("span",{staticClass:"homeTitle"},[t._v("帮助文档")]),t._v(" "),a("div",{staticClass:"documentBox"},t._l(t.documentList,(function(e,s){return a("div",{key:s,staticClass:"documentList"},[a("span",{staticClass:"round"}),t._v(" "),a("span",{staticClass:"name",attrs:{title:e.FILE_NAME}},[t._v(t._s("dev"==e.FILE_TYPE?"[开发文档] ":"[运维文档] ")+t._s(e.FILE_NAME))]),t._v(" "),a("el-link",{attrs:{type:"primary"},on:{click:function(a){return t.downloadWord(e.FILE_STOR_PATH+"/"+e.FILE_STOR_NAME)}}},[t._v("[下载]")])],1)})),0)])])],1)],1),t._v(" "),t._m(0)])},i=[function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"footer"},[a("p",[a("span",{staticStyle:{"font-weight":"bold"}},[t._v("相关链接：")]),t._v(" "),a("a",{attrs:{href:"http://www.cma.gov.cn/",target:"_blank"}},[t._v("中国气象局政府网")]),t._v(" |\n      "),a("a",{attrs:{href:"http://10.1.65.66/pub/cmahead/nmsy/index.html",target:"_blank"}},[t._v("综合管理信息系统")]),t._v("\n      |\n      "),a("a",{attrs:{href:"http://data.cma.cn/",target:"_blank"}},[t._v("中国气象数据网")]),t._v(" |\n      "),a("a",{attrs:{href:"http://www.weather.com.cn/",target:"_blank"}},[t._v("中国天气网")]),t._v(" |\n      "),a("a",{attrs:{href:"http://www.cmastd.cn/",target:"_blank"}},[t._v("中国气象标准化网")])]),t._v(" "),a("p",[t._v("版权所有 © 2018-2020 国家气象局信息中心")])])}],n=(a("7514"),a("ac6a"),a("365c")),c=a("313e"),o={name:"home",components:{},data:function(){return{pageFlag:this.$route.params.pageFlag,classType:"primary",DBType:"",bannerList:[{index:1,src:a("f761")},{index:2,src:a("8669")},{index:3,src:a("5a27")},{index:4,src:a("f0b3")}],eventList:[{name:"新增资料审核",checked:0,uncheck:0},{name:"数据授权审核",checked:0,uncheck:0},{name:"数据库账户审核",checked:0,uncheck:0},{name:"业务专题库审核",checked:0,uncheck:0},{name:"云数据库审核",checked:0,uncheck:0}],pieBox:[],projectLibraryList:[],documentList:[],interfaceData:[]}},mounted:function(){this.drawBarChartClass(),this.drawLineChart(),this.pageFlag&&document.querySelector("#"+this.pageFlag).scrollIntoView(!0)},created:function(){this.getEventList(),this.getHelpDocument(),this.getPieBox(),this.queryCheckList()},watch:{pieBox:function(){this.$nextTick((function(){this.drawPies()}))}},methods:{bannerGoPage:function(t){this.$router.push({name:"业务场景说明",params:{pageFlag:"pageFlag"+t}})},getEventList:function(){var t=this;Object(n["g"])().then((function(e){var a=e.data;t.eventList[0].checked=a.xzzl.checked,t.eventList[0].uncheck=a.xzzl.uncheck,t.eventList[1].checked=a.sjsq.checked,t.eventList[1].uncheck=a.sjsq.uncheck,t.eventList[2].checked=a.sjkzh.checked,t.eventList[2].uncheck=a.sjkzh.uncheck,t.eventList[3].checked=a.ywztk.checked,t.eventList[3].uncheck=a.ywztk.uncheck,t.eventList[4].checked=a.ysjk.checked,t.eventList[4].uncheck=a.ysjk.uncheck}))},goPageUrl:function(t){"新增资料审核"==t?this.$router.push("/authorityAudit/DRegistration"):"数据授权审核"==t?this.$router.push("/authorityAudit/materialPower"):"数据库账户审核"==t?this.$router.push("/authorityAudit/DBaccount"):"业务专题库审核"==t?this.$router.push("/authorityAudit/topicLibraryAudit"):"云数据库审核"==t&&this.$router.push("/authorityAudit/cloudDBaudit")},drawBarChartClass:function(){Object(n["a"])().then((function(t){var e=t.data,a=[],s=[];e.forEach((function(t){a.push(t.NUM),s.push(t.CLASS_NAME)}));var i=c.init(document.getElementById("dataCLassfication"));i.setOption({title:{text:"气象资料种类统计",left:"center",textStyle:{fontSize:14,fontWeight:"normal"}},tooltip:{trigger:"axis"},color:["#058ae5"],calculable:!0,yAxis:[{name:"单位: 种",nameRotate:90,nameGap:50,nameLocation:"middle",type:"value",axisLabel:{formatter:"{value}"},boundaryGap:[0,.01]}],xAxis:[{type:"category",data:s,axisLabel:{interval:0,rotate:20}}],series:[{name:"总计",type:"bar",barWidth:20,data:a,itemStyle:{normal:{color:new c.graphic.LinearGradient(0,0,0,1,[{offset:0,color:"#21bd69"},{offset:1,color:"#1561d1"}])}},label:{normal:{show:!1}}}]})}))},drawDBchart:function(){Object(n["d"])().then((function(t){var e=t.data,a=[],s=[];e.forEach((function(t){a.push(t.LOGICNAME),s.push(t.NUM)}));var i=c.init(document.getElementById("dataCLassfication"));i.setOption({title:{text:"数据库资料种数统计",left:"center",textStyle:{fontSize:14,fontWeight:"normal"}},tooltip:{trigger:"axis"},color:["#1fbf86"],calculable:!0,yAxis:[{name:"单位: 种",nameRotate:90,nameLocation:"middle",nameGap:50,type:"value",axisLabel:{formatter:"{value}"},boundaryGap:[0,.01]}],xAxis:[{type:"category",data:a,nameRotate:90,axisLabel:{interval:0,rotate:40,textStyle:{fontSize:10}}}],series:[{type:"bar",barWidth:20,data:s,itemStyle:{normal:{color:new c.graphic.LinearGradient(0,0,0,1,[{offset:0,color:"#21bd69"},{offset:1,color:"#1561d1"}])}},label:{normal:{show:!1}}}]})}))},classBarInit:function(){"primary"!=this.classType&&(this.classType="primary",this.DBType="",this.drawBarChartClass())},DBBarInit:function(){"primary"!=this.DBType&&(this.classType="",this.DBType="primary",this.drawDBchart())},pieId:function(t){return"pieId"+t},getPieBox:function(){var t=this;Object(n["e"])().then((function(e){t.pieBox=e.data,console.log(t.pieBox)}))},drawPies:function(){for(var t={tooltip:{trigger:"item",formatter:function(t){var e=t;return(e.name="")?"数值 <br/>非结构化数据库 : "+e.value+"TB("+e.percent+"%)":"数值 <br/>结构化数据库 : "+e.value+"TB("+e.percent+"%)"},position:function(t,e,a,s,i){var n=t[0],c=t[1],o=(i.viewSize[0],i.viewSize[1],i.contentSize[0]),r=i.contentSize[1],l=0,u=0;return l=n<o?5:n-o,u=c<r?5:c-r,[l,u]}},grid:{width:"auto",height:"auto"},color:["#ffc739","#22bfcb","#76da77"],series:[{name:"数值",type:"pie",center:["40%","50%"],radius:["50%","70%"],data:[{value:310,name:"DRC"},{value:635,name:"NAS"}],itemStyle:{emphasis:{shadowBlur:10,shadowOffsetX:0,shadowColor:"rgba(0, 0, 0, 0.5)"}},label:{normal:{show:!1,position:"inner",formatter:"{b}"}}}]},e=0;e<this.pieBox.length;e++){var a=document.getElementById("pieId"+e),s=c.init(a),i=[];this.pieBox[e].database_name2?i.push({value:this.pieBox[e].TOTAL_CAPACITY,name:this.pieBox[e].LOGIC_NAME},{value:this.pieBox[e].total_capacity2,name:this.pieBox[e].logic_name2}):i.push({value:void 0==this.pieBox[e].TOTAL_CAPACITY?0:this.pieBox[e].TOTAL_CAPACITY,name:this.pieBox[e].LOGIC_NAME}),t.series[0].data=i,s.setOption(t,!0)}},queryCheckList:function(){var t=this;Object(n["f"])().then((function(e){t.projectLibraryList=e.data}))},drawLineChart:function(){Object(n["b"])().then((function(t){var e=t.data.numData,a=t.data.monthData,s=c.init(document.getElementById("lineChart"));s.setOption({title:{text:"存储管理资料总数月统计",left:"center",textStyle:{fontSize:14,fontWeight:"normal"}},tooltip:{trigger:"axis"},xAxis:{type:"category",boundaryGap:!1,axisLabel:{color:"#50697a"},axisLine:{show:!1},splitLine:{show:!1},axisTick:{show:!1},data:a},yAxis:{name:"单位: 种",nameRotate:90,nameLocation:"middle",nameGap:50,axisLabel:{color:"#50697a"},axisLine:{show:!1},axisTick:{show:!1},type:"value"},series:[{name:"总计",type:"line",color:"#21cecf",data:e,label:{normal:{show:!1}},lineStyle:{color:"#21cecf"},smooth:!0,areaStyle:{normal:{color:new c.graphic.LinearGradient(0,0,0,1,[{offset:0,color:"#80e2e2"},{offset:1,color:"#ffe"}])}}}]})}))},getHelpDocument:function(){var t=this;Object(n["c"])().then((function(e){t.documentList=e.data}))},downloadWord:function(t){},getInterfaceInfo:function(t){var e="";return this.interfaceData.find((function(a){a.interfaceid==t&&(e=a.interfacename)})),e}}},r=o,l=(a("da7f"),a("2877")),u=Object(l["a"])(r,s,i,!1,null,"6e86b782",null);e["default"]=u.exports},4718:function(t,e,a){},"5a27":function(t,e,a){t.exports=a.p+"static/img/banner3.c9861372.gif"},7514:function(t,e,a){"use strict";var s=a("5ca1"),i=a("0a49")(5),n="find",c=!0;n in[]&&Array(1)[n]((function(){c=!1})),s(s.P+s.F*c,"Array",{find:function(t){return i(this,t,arguments.length>1?arguments[1]:void 0)}}),a("9c6c")(n)},8669:function(t,e,a){t.exports=a.p+"static/img/banner2.660401be.gif"},da7f:function(t,e,a){"use strict";var s=a("4718"),i=a.n(s);i.a},f0b3:function(t,e,a){t.exports=a.p+"static/img/banner4.5d82695d.gif"},f761:function(t,e,a){t.exports=a.p+"static/img/banner1.4b4c3102.gif"}}]);