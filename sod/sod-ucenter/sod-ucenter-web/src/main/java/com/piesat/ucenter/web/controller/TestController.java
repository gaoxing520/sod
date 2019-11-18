package com.piesat.ucenter.web.controller;


import com.alibaba.fastjson.JSON;
import com.piesat.common.utils.AESUtil;
import com.piesat.common.vo.HttpReq;
import com.piesat.ucenter.rpc.api.TestService;
import com.piesat.ucenter.rpc.dto.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;

/**
 * @program: sod
 * @描述
 * @创建人 zzj
 * @创建时间 2019/11/12 15:33
 */
@RestController
@Api(value="用户controller",tags={"用户操作接口"})
public class TestController {
    @Autowired
    private TestService testService;
    @Autowired
    WebApplicationContext applicationContext;
    @PostMapping(value = "/api/test")
    @ApiOperation(value="获取用户列表", notes="获取用户列表")
    public User test(@RequestBody User user1){
        user1.setDate(new Date());
        User user=new User();
        System.out.println("11");
        testService.test();
        return user1;
   }
    @GetMapping(value = "/api/test1/{name}")
    //@DecryptRequest(false)
    @ApiOperation(value="获取用户列表", notes="获取用户列表")
    public User test1(@PathVariable String name,String bb, String age){
        return new User();
    }
    @PostMapping(value = "/api/test2")
    @ApiOperation(value="获取用户列表", notes="获取用户列表")
    public User test2( User user1){
        User user=new User();
        System.out.println("11");
        testService.test();
        return user1;
    }
   public static void main(String[] args){
       User user=new User();
       user.setAge(11);
       user.setName("z三生三世");
       Map map=new HashMap();
       map.put("112","s是是是");
       user.setMap(map);
       List<String> strings=new ArrayList<>();
       strings.add("asss");
       strings.add("asss");
       user.setList(strings);
       String ss="age=1&name=map";

       String aa=AESUtil.aesEncrypt(JSON.toJSONString(user));
       System.out.println(aa);
       //String aa=AESUtil.aesEncode(ss);
       HttpReq httpReq=new HttpReq();
       httpReq.setData(aa);
       System.out.println(JSON.toJSONString(httpReq));

   }
}
