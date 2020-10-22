package com.msb.controller;

import com.msb.bean.TblUserRecord;
import com.msb.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // 等于  @Controller+@ResponseBody
public class LoginController {

    @Autowired
    private LoginService service;

    @RequestMapping("/auth/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password){
        System.out.println("login2...");
        System.out.println(username +" ---- "+password);
        TblUserRecord userRecord = service.login(username, password);
        System.out.println(userRecord);
        return userRecord.toString();
    }
}
