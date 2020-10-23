package com.msb.controller;

import com.alibaba.fastjson.JSON;
import com.msb.bean.TblUserRecord;
import com.msb.resultJson.ResultObject;
import com.msb.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController // 等于  @Controller+@ResponseBody
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
        ResultObject result = new ResultObject(userRecord);
        session.setAttribute("userRecord",result);
        return JSON.toJSONString(result);
    }

    @RequestMapping("/user/info")
    public String getinfo(HttpSession session){
        Object userRecord = session.getAttribute("userRecord");
        return JSON.toJSONString(userRecord);
    }

    @RequestMapping("/auth/logout")
    public String logout(HttpSession session){
        return JSON.toJSONString(new ResultObject(""));
    }
}
