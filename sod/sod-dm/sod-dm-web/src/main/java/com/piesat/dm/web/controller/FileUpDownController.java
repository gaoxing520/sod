package com.piesat.dm.web.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import com.piesat.common.utils.UUID;
import com.piesat.dm.rpc.service.ImportData;
import com.piesat.util.ResultT;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 文件上传下载接口
 * @author wulei
 * @date 2020年04月21日 10:12:08
 */
@Api(tags = "公用上传下载接口")
@RequestMapping("/dm/fileUpDown")
@RestController
public class FileUpDownController {

    @Value("${fileUpload.httpPath}")
    private String uploadPath;

    @ApiOperation(value = "单文件上传")
    @PostMapping(value = "/uploadOne")
    public ResultT uploadOne(@RequestParam(value = "file") MultipartFile multipartFile) {
        try {
            String yyyyMMdd = uploadPath + File.separator + new SimpleDateFormat("yyyyMMdd").format(new Date()) + File.separator;
            if (!FileUtil.exist(yyyyMMdd)) {
                FileUtil.mkdir(yyyyMMdd);
            }
            String fileName = System.currentTimeMillis() + "@" + multipartFile.getOriginalFilename();
            String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1);
            File fileObj = FileUtil.writeBytes(multipartFile.getBytes(), yyyyMMdd + fileName);
            List<Map<String, String>> pathList = new ArrayList<>();
            if (fileObj.length() > 0) {
                Map<String, String> map = new HashMap<>();
                map.put("fileName", fileName);
                map.put("filePath", yyyyMMdd.replaceAll("\\\\", "/"));
                map.put("suffix", suffix);
                pathList.add(map);
            }
            return ResultT.success(pathList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultT.failed(e.getMessage());
        }
    }

    @ApiOperation(value = "多文件上传")
    @PostMapping("/uploadMany")
    public ResultT uploadMany(@RequestParam(value = "file") MultipartFile[] file) {
        try {
            String yyyyMMdd = uploadPath + File.separator + new SimpleDateFormat("yyyyMMdd").format(new Date()) + File.separator;
            if (!FileUtil.exist(yyyyMMdd)) {
                FileUtil.mkdir(yyyyMMdd);
            }
            List<Map<String, String>> pathList = new ArrayList<>();
            if (file.length > 0) {
                for (MultipartFile multipartFile : file) {
                    String fileName = System.currentTimeMillis() + "@" + multipartFile.getOriginalFilename();
                    String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1);
                    File fileObj = FileUtil.writeBytes(multipartFile.getBytes(), yyyyMMdd + fileName);
                    if (fileObj.length() > 0) {
                        Map<String, String> map = new HashMap<>();
                        map.put("fileName", fileName);
                        map.put("filePath", yyyyMMdd.replaceAll("\\\\", "/"));
                        map.put("suffix", suffix);
                        pathList.add(map);
                    }
                }
            }
            return ResultT.success(pathList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultT.failed(e.getMessage());
        }
    }

    @ApiOperation(value = "文件下载")
    @GetMapping("/download")
    public ResultT download(HttpServletResponse response, String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return ResultT.failed("文件不存在");
            }
            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("application/force-download");
            //设置编码，避免文件名中文乱码
            String fileName = file.getName();
            response.setHeader("Content-Disposition", "attachment;filename="
                    + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
            outputStream.write(FileUtil.readBytes(filePath));
            IoUtil.close(outputStream);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultT.failed(e.getMessage());
        }
    }
}
