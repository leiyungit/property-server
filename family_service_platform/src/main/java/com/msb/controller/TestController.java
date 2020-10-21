package com.msb.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController // 等于  @Controller+@ResponseBody
// 设置允许跨域请求
//@CrossOrigin(origins = "*",allowedHeaders = "*",methods = {},allowCredentials = "true")
public class TestController {

    @RequestMapping("/test")
    public String toTest(){
        System.out.println("test ....");
        return "test:测试页面输出通过";
    }

    @RequestMapping("/auth/login")
    public String login(@RequestBody Map<String,Object> map){
        //System.out.println(String.format("username={0},password={1}",map.get("username"),map.get("password")));
        System.out.println(map);
        return "test:测试页面输出通过";
    }

    @RequestMapping("/auth/2step-code")
    public Boolean login(){
        System.out.println("前端框架自带验证规则，写不写无所谓");
        return true;
    }
}
