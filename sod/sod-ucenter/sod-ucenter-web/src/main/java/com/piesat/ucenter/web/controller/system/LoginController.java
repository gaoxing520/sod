package com.piesat.ucenter.web.controller.system;

import com.alibaba.fastjson.JSON;
import com.piesat.common.constant.Constants;
import com.piesat.common.utils.AESUtil;
import com.piesat.common.utils.IdUtils;
import com.piesat.common.utils.ServletUtils;
import com.piesat.common.utils.VerifyCodeUtils;
import com.piesat.common.utils.ip.AddressUtils;
import com.piesat.common.utils.ip.IpUtils;
import com.piesat.common.utils.sign.Base64;
import com.piesat.sso.client.enums.OperatorType;
import com.piesat.ucenter.rpc.api.monitor.LoginInfoService;
import com.piesat.ucenter.rpc.api.system.MenuService;
import com.piesat.ucenter.rpc.api.system.RoleService;
import com.piesat.ucenter.rpc.api.system.UserService;
import com.piesat.ucenter.rpc.dto.monitor.LoginInfoDto;
import com.piesat.ucenter.rpc.dto.system.DeptDto;
import com.piesat.ucenter.rpc.dto.system.UserDto;
import com.piesat.ucenter.rpc.util.RouterVo;
import com.piesat.util.ResultT;
import com.piesat.util.ReturnCodeEnum;
import eu.bitwalker.useragentutils.UserAgent;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @program: sod
 * @描述
 * @创建人 zzj
 * @创建时间 2019/11/28 11:43
 */
@RestController
@Api(value="登录",tags={"用户登录"})
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private LoginInfoService loginInfoService;
    @Autowired
    private ExecutorService executorService;
    /**
     * 登录方法
     *
     * @param username 用户名
     * @param password 密码
     * @param
     * @param uuid 唯一标识
     * @return 结果
     */
    @PostMapping("/login")
    public ResultT<Map<String,Object>> login(String username, String password, String code, String uuid)
    {
        ResultT<Map<String,Object>> resultT=new ResultT<>();
        Map<String,Object> map=new HashMap<>();
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setLoginType("0");
            token.setRequest(ServletUtils.getRequest());
            token.setOperatorType(OperatorType.MANAGE.ordinal());
            subject.login(token);
            map.put("token", subject.getSession().getId());
            resultT.setData(map);
        } catch (LockedAccountException e) {
            resultT.setErrorMessage(ReturnCodeEnum.ReturnCodeEnum_402_ERROR);
        }catch (UnknownAccountException e){
            resultT.setErrorMessage(ReturnCodeEnum.ReturnCodeEnum_404_ERROR);
        }catch (IncorrectCredentialsException e){
            resultT.setErrorMessage(ReturnCodeEnum.ReturnCodeEnum_405_ERROR);
        }catch (AuthenticationException ex){
            resultT.setErrorMessage(ReturnCodeEnum.ReturnCodeEnum_405_ERROR);
        }finally {
            this.recordLogininfor(ServletUtils.getRequest(),username,resultT);
        }
        return resultT;
    }

    @PostMapping("/thirdLogin")
    public ResultT<Map<String,Object>> thirdLogin(@RequestBody Map<String,String> userDto)
    {
        ResultT<Map<String,Object>> resultT=new ResultT<>();
        Map<String,Object> map=new HashMap<>();
        String appId= userDto.get("appId");
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(appId, "");
            token.setLoginType("1");
            token.setRequest(ServletUtils.getRequest());
            token.setOperatorType(OperatorType.THRID.ordinal());
            token.setParam(JSON.toJSONString(userDto));
            subject.login(token);
            map.put("token", subject.getSession().getId());
            resultT.setData(map);
        } catch (LockedAccountException e) {
            resultT.setErrorMessage(ReturnCodeEnum.ReturnCodeEnum_402_ERROR);
        }catch (UnknownAccountException e){
            resultT.setErrorMessage(ReturnCodeEnum.ReturnCodeEnum_404_ERROR);
        }catch (IncorrectCredentialsException e){
            resultT.setErrorMessage(ReturnCodeEnum.ReturnCodeEnum_405_ERROR);
        }catch (AuthenticationException ex){
            resultT.setErrorMessage(ReturnCodeEnum.ReturnCodeEnum_405_ERROR);
        }finally {
            this.recordLogininfor(ServletUtils.getRequest(),appId,resultT);
        }
        return resultT;
    }
    /**
     * 校验token
     */
    @GetMapping(value = "checkToken/{token}")
    public ResultT<Map<String,Object>> checkToken(@PathVariable String token)
    {
        ResultT<Map<String,Object>> resultT=new ResultT<>();
        Map<String,Object> map=new HashMap<>();
        map.put("token", token);
        resultT.setData(map);
        return resultT;
    }
    @GetMapping("getInfo")
    public ResultT<Map<String,Object>> getInfo()
    {
        ResultT<Map<String,Object>> resultT=new ResultT<>();
        Map<String,Object> map=new HashMap<>();

        UserDto userDto = (UserDto)SecurityUtils.getSubject().getPrincipal();
        // 角色集合
        Set<String> roles = roleService.getRolePermission(userDto);
        Set<String> permissions = menuService.getMenuPermission(userDto);

        map.put("user", userDto);
        map.put("roles", roles);
        map.put("permissions", permissions);
        resultT.setData(map);
        return resultT;
    }
    @GetMapping("getRouters")
    public ResultT<List<RouterVo>>  getRouters()
    {
        ResultT<List<RouterVo>> resultT=new ResultT<>();
        UserDto userDto = (UserDto)SecurityUtils.getSubject().getPrincipal();
        List<RouterVo> routerVos=menuService.getRouters(userDto.getId());
        resultT.setData(routerVos);
        return resultT;
    }
 /*   @GetMapping(value = "/unauth")
    public ResultT<String> unauth() {
        ResultT<String> resultT=new ResultT<>();
        resultT.setErrorMessage(ReturnCodeEnum.ReturnCodeEnum_401_ERROR);
        return resultT;
    }
    @GetMapping(value = "/unauthorized")
    public ResultT<String> unauthorized() {
        ResultT<String> resultT=new ResultT<>();
        resultT.setErrorMessage(ReturnCodeEnum.ReturnCodeEnum_403_ERROR);
        return resultT;
    }*/

    /**
     * 生成验证码
     */
    @GetMapping("/captchaImage")
    public ResultT<Map<String,Object>> getCode(HttpServletResponse response) throws IOException
    {
        ResultT<Map<String,Object>> resultT = new ResultT();

        // 生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        // 唯一标识
        String uuid = IdUtils.simpleUUID();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;

        //redisCache.setCacheObject(verifyKey, verifyCode, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // 生成图片
        int w = 111, h = 36;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        VerifyCodeUtils.outputImage(w, h, stream, verifyCode);
        try
        {
            Map<String,Object> map=new HashMap<>();
            map.put("uuid", uuid);
            map.put("img", Base64.encode(stream.toByteArray()));
            resultT.setData(map);
            return resultT;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            resultT.setErrorMessage(e.getMessage());
            return resultT;
        }
        finally
        {
            stream.close();
        }
    }
    @PostMapping("/logout")
    public ResultT<String> logout(){
        ResultT<String> resultT=new ResultT<>();
        try {
            Subject currentUser = SecurityUtils.getSubject();//获取当前用户信息
            if(currentUser.isAuthenticated()){
                currentUser.logout();
                resultT.setMessage("退出成功");
            }
        } catch (Exception e) {
            resultT.setErrorMessage("退出失败");
        }
        return resultT;
    }
    public  static void main(String[] args ){
        Map<String,String> map=new HashMap<>();
        map.put("userId","11111111111");
        map.put("userName","222");
        try {
           String aa= AESUtil.aesEncrypt(JSON.toJSONString(map));
           System.out.println(aa);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void recordLogininfor(HttpServletRequest request,String userName,ResultT<Map<String,Object>> resultT){
        try {
            executorService.execute(
                    ()->{
                        final UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
                        final String ip = IpUtils.getIpAddr(request);
                        String address = AddressUtils.getRealAddressByIP(ip);
                        String os = userAgent.getOperatingSystem().getName();
                        // 获取客户端浏览器
                        String browser = userAgent.getBrowser().getName();
                        LoginInfoDto logininfor=new LoginInfoDto();
                        logininfor.setUserName(userName);
                        logininfor.setIpaddr(ip);
                        logininfor.setLoginLocation(address);
                        logininfor.setBrowser(browser);
                        logininfor.setOs(os);
                        logininfor.setMsg(resultT.getMsg());
                        if(resultT.isSuccess()){
                            logininfor.setStatus("0");
                        }else{
                            logininfor.setStatus("1");
                        }
                        logininfor.setLoginTime(new Date());
                        loginInfoService.insertLogininfor(logininfor);
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}