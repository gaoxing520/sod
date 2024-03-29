package com.piesat.portal.web.controller;

import com.piesat.common.constant.FileTypesConstant;
import com.piesat.portal.rpc.api.SdkManageService;
import com.piesat.portal.rpc.dto.SdkManageDto;
import com.piesat.sso.client.annotation.Log;
import com.piesat.sso.client.enums.BusinessType;
import com.piesat.util.ResultT;
import com.piesat.util.page.PageBean;
import com.piesat.util.page.PageForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * sdk管理
 */
@Api(value="SDK管理",tags= {"SDK管理"})
@RequestMapping("/portal/sdkManage")
@RestController
public class SdkManageController {

    @Autowired
    private SdkManageService sdkManageService;

    @Value("${serverfile.sdkfile}")
    private String fileAddress;

    @GetMapping("/list")
    @ApiOperation(value = "条件分页查询", notes = "条件分页查询")
    public ResultT<PageBean<SdkManageDto>> list(SdkManageDto sdkManageDto, int pageNum, int pageSize) {
        ResultT<PageBean<SdkManageDto>> resultT = new ResultT<>();
        PageForm<SdkManageDto> pageForm = new PageForm<>(pageNum, pageSize, sdkManageDto);
        PageBean pageBean = sdkManageService.selectPageList(pageForm);
        resultT.setData(pageBean);
        return resultT;
    }

    @ApiOperation(value = "根据id删除")
    @Log(title = "业务动态管理", businessType = BusinessType.DELETE)
    @DeleteMapping(value = "/del")
    public ResultT del(String id) {
        try {
            this.sdkManageService.delete(id);
            return ResultT.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultT.failed(e.getMessage());
        }
    }

    @ApiOperation(value = "根据id查询")
    @GetMapping(value = "/getById")
    public ResultT get(String id) {
        try {
            SdkManageDto sdkManageDto = this.sdkManageService.getDotById(id);
            return ResultT.success(sdkManageDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultT.failed(e.getMessage());
        }
    }

    @Log(title = "SDK管理", businessType = BusinessType.INSERT)
    @PostMapping(value="/saveSdkManage")
    @ApiOperation(value="SDK管理（portal）",notes="SDK管理（portal）")
    public ResultT saveSdkManage(@RequestBody SdkManageDto sdkManageDto){
        try {
            SdkManageDto save = this.sdkManageService.saveDto(sdkManageDto);
            return ResultT.success(save);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultT.failed(e.getMessage());
        }
    }

    @PutMapping("/edit")
    @ApiOperation(value = "编辑", notes = "编辑")
    public ResultT<SdkManageDto> edit(@RequestBody SdkManageDto sdkManageDto)
    {
        ResultT<SdkManageDto> resultT=new ResultT<>();
        sdkManageDto = this.sdkManageService.updateDto(sdkManageDto);
        resultT.setData(sdkManageDto);
        return resultT;
    }

    @ApiOperation(value = "SDK文件上传接口")
    @PostMapping(value = "/uploadSdk")
    public ResultT uploadFile(MultipartHttpServletRequest request, HttpServletResponse response) {
        try {
            // 获取文件存储路径 , 如果没有 , 创建
            if (!Paths.get(fileAddress).toFile().exists()) {
                Paths.get(fileAddress).toFile().mkdirs();
            }
            List<MultipartFile> fileList = request.getFiles("fileName");
            MultipartFile mf = fileList.get(0);
            //文件路径
            String filePath = fileAddress + "/" + mf.getOriginalFilename();
            String fileSuffix = FilenameUtils.getExtension(mf.getOriginalFilename());
            boolean b = Arrays.stream(FileTypesConstant.PORTAL_FILE_SDK_TYPES).anyMatch(e -> e.equalsIgnoreCase(fileSuffix));
            if (!b) {
                response.setStatus(404);
                return ResultT.failed("文件格式不支持！");
            }
            //上传文件到服务器指定路径
            FileCopyUtils.copy(mf.getBytes(), new File(filePath));

            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("fileName", mf.getOriginalFilename());
            resultMap.put("filePath", filePath);
            resultMap.put("fileSuffix", fileSuffix);
            return ResultT.success(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultT.failed(e.getMessage());
        }
    }

    @ApiOperation(value = "SDK文档上传接口")
    @PostMapping(value = "/uploadSdkDoc")
    public ResultT uploadSdkWord(MultipartHttpServletRequest request, HttpServletResponse response) {
        try {
            // 获取文件存储路径 , 如果没有 , 创建
            if (!Paths.get(fileAddress).toFile().exists()) {
                Paths.get(fileAddress).toFile().mkdirs();
            }
            List<MultipartFile> fileList = request.getFiles("fileName");
            MultipartFile mf = fileList.get(0);
            //文件路径
            String filePath = fileAddress + "/" + mf.getOriginalFilename();
            String fileSuffix = FilenameUtils.getExtension(mf.getOriginalFilename());
            boolean b = Arrays.stream(FileTypesConstant.PORTAL_FILE_SDK_DOC_TYPES).anyMatch(e -> e.equalsIgnoreCase(fileSuffix));
            if (!b) {
                response.setStatus(404);
                return ResultT.failed("文件格式不支持！");
            }
            //上传文件到服务器指定路径
            FileCopyUtils.copy(mf.getBytes(), new File(filePath));

            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("fileName", mf.getOriginalFilename());
            resultMap.put("filePath", filePath);
            resultMap.put("fileSuffix", fileSuffix);
            return ResultT.success(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultT.failed(e.getMessage());
        }
    }


    @ApiOperation(value="根据id下载文件",notes="根据id下载文件")
    @GetMapping(value="/downloadById")
    public void downloadById(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String type = request.getParameter("type");
        SdkManageDto sdkManageDto = this.sdkManageService.getDotById(id);
        String fullPath = "";
        if("jar".equalsIgnoreCase(type)){
            fullPath = sdkManageDto.getSdkJarUrl();
        }else{
            fullPath = sdkManageDto.getSdkDocUrl();
        }
        File file = new File(fullPath);
        String fileName = file.getName();

        if(file.exists()){
            try {
                String userAgent = request.getHeader("User-Agent");
                if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
                    //IE浏览器处理
                    fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
                } else {
                    fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
                    // 非IE浏览器的处理：
//                    fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
                }
                // 以流的形式下载文件。
                InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
                byte[] buffer = new byte[inputStream.available()];
                inputStream.read(buffer);
                inputStream.close();
                // 清空response
                //response.reset();
                // 设置response的Header
                response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName));
                response.addHeader("Content-Length", "" + file.length());
                OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
                response.setContentType("application/octet-stream");
                outputStream.write(buffer);
                outputStream.flush();
                outputStream.close();
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        }else{
            try {
                response.setContentType("text/html; charset=UTF-8"); //转码
                PrintWriter out = response.getWriter();
                out.flush();
                out.println("<script defer='defer' type='text/javascript'>");
                out.println("alert('文件不存在或已经被删除！');");
//                out.println("window.location='/AnnualStatistics/downloadList';");
                out.println("</script>");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




}
