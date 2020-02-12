package com.piesat.dm.web.controller;

import com.piesat.dm.rpc.api.DataServerConfigService;
import com.piesat.dm.rpc.dto.DataServerConfigDto;
import com.piesat.sso.client.annotation.Log;
import com.piesat.sso.client.enums.BusinessType;
import com.piesat.util.ResultT;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 服务信息配置
 *
 * @author cwh
 * @date 2020年 02月12日 16:01:07
 */
@Api(tags = "服务基础信息")
@RequestMapping("/dm/dataserverconfig")
@RestController
public class DataServerConfigController {
    @Autowired
    private DataServerConfigService dataServerConfigService;

    @ApiOperation(value = "新增")
    @RequiresPermissions("dm:dataserverbaseinfo:add")
    @Log(title = "服务基础信息管理", businessType = BusinessType.INSERT)
    @PostMapping(value = "/save")
    public ResultT save(@RequestBody DataServerConfigDto dataServerConfigDto) {
        try {
            DataServerConfigDto save = this.dataServerConfigService.saveDto(dataServerConfigDto);
            return ResultT.success(save);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultT.failed(e.getMessage());
        }
    }

    @ApiOperation(value = "根据id查询")
    @RequiresPermissions("dm:dataserverbaseinfo:get")
    @GetMapping(value = "/get")
    public ResultT get(String id) {
        try {
            DataServerConfigDto dataServerConfigDto = this.dataServerConfigService.getDotById(id);
            return ResultT.success(dataServerConfigDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultT.failed(e.getMessage());
        }
    }

    @ApiOperation(value = "根据id删除")
    @RequiresPermissions("dm:dataserverbaseinfo:del")
    @Log(title = "服务基础信息管理", businessType = BusinessType.DELETE)
    @DeleteMapping(value = "/del")
    public ResultT del(String id) {
        try {
            this.dataServerConfigService.delete(id);
            return ResultT.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultT.failed(e.getMessage());
        }
    }

    @ApiOperation(value = "查询所有")
    @RequiresPermissions("dm:dataserverbaseinfo:all")
    @GetMapping(value = "/all")
    public ResultT all() {
        try {
            List<DataServerConfigDto> all = this.dataServerConfigService.all();
            return ResultT.success(all);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultT.failed(e.getMessage());
        }
    }
}
