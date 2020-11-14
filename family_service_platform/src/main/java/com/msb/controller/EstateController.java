package com.msb.controller;

import com.alibaba.fastjson.JSON;
import com.msb.bean.FcEstate;
import com.msb.resultJson.ResultObject;
import com.msb.service.EstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/estate")
@CrossOrigin(origins = "*",allowedHeaders = "*",methods = {},allowCredentials = "true")
public class EstateController {

    @Autowired
    private EstateService service;

    @RequestMapping("/findCompany")
    public String findCompany(){
        return JSON.toJSONString(new ResultObject(service.findCompay()));
    }

    @RequestMapping("/insertEstate") // @RequestBody Map<String,Object> fcBuilding
    public String insertSelectEstate(FcEstate fcEstate){
        System.out.println("insert estate ");
        System.out.println(fcEstate);
        //return JSON.toJSONString(new ResultObject("房产添加成功",1));
        Integer result = service.insertSelectEstate(fcEstate);
        ResultObject ro;
        if(result == 0){
            ro = new ResultObject("房产编码已存在，操作失败",0);
        }else{
            ro = new ResultObject("房产添加成功",1);
        }
        return JSON.toJSONString(ro);
    }

    @RequestMapping("/findEstate") // @RequestBody Map<String,Object> fcBuilding
    public String findEstate(){
        return JSON.toJSONString(new ResultObject(service.findEstate()));
    }


    @RequestMapping("/selectEstateByCompany") // @RequestBody Map<String,Object> fcBuilding
    public String selectEstateByCompany(String company){
        System.out.println("selectEstateByCompany------------------->"+company);
        return JSON.toJSONString(new ResultObject(service.selectEstateByCompany(company)));
    }




}
