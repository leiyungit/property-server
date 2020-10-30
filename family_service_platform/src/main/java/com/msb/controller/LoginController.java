package com.msb.controller;

import com.alibaba.fastjson.JSON;
import com.msb.bean.TblUserRecord;
import com.msb.resultJson.Permission;
import com.msb.resultJson.Permissions;
import com.msb.resultJson.ResultObject;
import com.msb.resultJson.UserInfo;
import com.msb.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController // 等于  @Controller+@ResponseBody
@CrossOrigin(origins = "*",allowedHeaders = "*",methods = {},allowCredentials = "true")
public class LoginController {

    @Autowired
    private LoginService service;

    @RequestMapping("/auth/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session){
        //System.out.println("login2...");
        System.out.println(username +" ---- "+password);
        TblUserRecord userRecord = service.login(username, password);
        userRecord.setToken(userRecord.getUserName());
        //System.out.println(userRecord);
        session.setAttribute("userRecord",userRecord);
        ResultObject result = new ResultObject(userRecord);
        return JSON.toJSONString(result);
    }

    @RequestMapping("/user/info")
    public String getinfo(HttpSession session){
        // 取session
        TblUserRecord userRecord = (TblUserRecord)session.getAttribute("userRecord");
        // 角色id集合，拆分数组
        String[] roles = userRecord.getTblRole().getRolePrivileges().split("-");
        // 前端框架要求的权限管理要求的格式
        List<Permission> permissionList = new ArrayList<>();
        for (String r : roles) {
            //System.out.println(r);
            Permission permission = new Permission(r);
            permissionList.add(permission);
        }
        Permissions permissions = new Permissions();
        permissions.setPermissions(permissionList);
        UserInfo userInfo = new UserInfo(userRecord.getUserName(), permissions);
        //
        ResultObject result = new ResultObject(userInfo);
        System.out.println(result);
        return JSON.toJSONString(result);
    }

    @RequestMapping("/auth/logout")
    public String logout(HttpSession session){
        return JSON.toJSONString(new ResultObject(""));
    }

    @RequestMapping("/auth/2step-code")
    public Boolean login(){
        System.out.println("前端框架自带验证规则，写不写无所谓");
        return true;
    }
}
