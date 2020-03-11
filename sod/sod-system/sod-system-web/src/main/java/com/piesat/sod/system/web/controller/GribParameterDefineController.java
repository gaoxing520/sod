package com.piesat.sod.system.web.controller;

import com.piesat.sod.system.rpc.api.GribParameterDefineService;
import com.piesat.sod.system.rpc.dto.GribParameterDefineDto;
import com.piesat.util.ResultT;
import com.piesat.util.page.PageBean;
import com.piesat.util.page.PageForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yaya
 * @description TODO
 * @date 2020/3/9 17:21
 */
@RestController
@RequestMapping("/system/gribParameterDefine")
@Api(value="服务代码管理",tags= {"服务代码管理接口"})
public class GribParameterDefineController {

    @Autowired
    private GribParameterDefineService gribParameterDefineService;

    @GetMapping("/list")
    @ApiOperation(value = "条件分页查询", notes = "条件分页查询")
    public ResultT<PageBean> list(GribParameterDefineDto gribParameterDefineDto, int pageNum, int pageSize) {
        ResultT<PageBean> resultT = new ResultT<>();
        PageForm<GribParameterDefineDto> pageForm = new PageForm<>(pageNum, pageSize, gribParameterDefineDto);
        PageBean pageBean = gribParameterDefineService.selectPageList(pageForm);
        resultT.setData(pageBean);
        return resultT;
    }

    @PostMapping(value = "/save")
    @ApiOperation(value = "添加", notes = "添加")
    public ResultT save(@RequestBody GribParameterDefineDto gribParameterDefineDto) {
        try {
            GribParameterDefineDto save = this.gribParameterDefineService.saveDto(gribParameterDefineDto);
            return ResultT.success(save);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultT.failed(e.getMessage());
        }
    }

    @PutMapping("/edit")
    @ApiOperation(value = "编辑", notes = "编辑")
    public ResultT<GribParameterDefineDto> edit(@RequestBody GribParameterDefineDto gribParameterDefineDto)
    {
        ResultT<GribParameterDefineDto> resultT=new ResultT<>();
        gribParameterDefineDto= this.gribParameterDefineService.updateDto(gribParameterDefineDto);
        resultT.setData(gribParameterDefineDto);
        return resultT;
    }

    @DeleteMapping("/deleteByIds/{ids}")
    @ApiOperation(value = "批量删除", notes = "批量删除")
    public ResultT<String> remove(@PathVariable String[] ids)
    {
        ResultT<String> resultT=new ResultT<>();
        List<String> list=new ArrayList();
        if(ids.length>0){
            list= Arrays.asList(ids);
            this.gribParameterDefineService.deleteRecordByIds(list);
        }
        return resultT;
    }
}