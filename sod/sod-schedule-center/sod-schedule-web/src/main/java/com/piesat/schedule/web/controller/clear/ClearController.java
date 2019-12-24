package com.piesat.schedule.web.controller.clear;

import com.piesat.schedule.rpc.api.clear.ClearService;
import com.piesat.schedule.rpc.dto.clear.ClearDto;
import com.piesat.sso.client.annotation.Log;
import com.piesat.sso.client.enums.BusinessType;
import com.piesat.util.ResultT;
import com.piesat.util.page.PageBean;
import com.piesat.util.page.PageForm;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: sod
 * @description:
 * @author: zzj
 * @create: 2019-12-24 11:40
 **/
@RestController
@RequestMapping("/schedule/clear")
public class ClearController {
    @Autowired
    private ClearService clearService;

    @RequiresPermissions("schedule:clear:list")
    @GetMapping("/list")
    public ResultT<PageBean> list(ClearDto clear, int pageNum, int pageSize)
    {
        ResultT<PageBean> resultT=new ResultT<>();
        PageForm<ClearDto> pageForm=new PageForm<>(pageNum,pageSize,clear);
        PageBean pageBean=clearService.selectClearList(pageForm);
        resultT.setData(pageBean);
        return resultT;
    }

    @RequiresPermissions("schedule:clear:query")
    @GetMapping(value = "/{clearId}")
    public ResultT<ClearDto> getInfo(@PathVariable String clearId)
    {
        ResultT<ClearDto> resultT=new ResultT<>();
        ClearDto clearDto= clearService.findClearById(clearId);
        resultT.setData(clearDto);
        return resultT;
    }
    @RequiresPermissions("schedule:clear:add")
    @Log(title = "清除任务管理", businessType = BusinessType.INSERT)
    @PostMapping
    public ResultT<String> add(@RequestBody ClearDto clear)
    {
        ResultT<String> resultT=new ResultT<>();
        clearService.saveClear(clear);
        return resultT;
    }

    @RequiresPermissions("schedule:clear:edit")
    @Log(title = "清除任务管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResultT<String> edit(@RequestBody ClearDto clear)
    {
        ResultT<String> resultT=new ResultT<>();
        clearService.updateClear(clear);
        return resultT;
    }
    /**
     * 删除用户
     */
    @RequiresPermissions("schedule:clear:remove")
    @Log(title = "清除任务管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{clearIds}")
    public  ResultT<String> remove(@PathVariable String[] clearIds)
    {
        ResultT<String> resultT=new ResultT<>();
        clearService.deleteClearByIds(clearIds);
        return resultT;
    }
}
