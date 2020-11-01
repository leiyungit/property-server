package com.msb.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.msb.bean.FcBuilding;
import com.msb.bean.FcEstate;
import com.msb.resultJson.ResultObject;
import com.msb.service.EstateService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String insertEstate(FcEstate fcEstate){
        System.out.println("insert estate ");
        System.out.println(fcEstate);
        //return JSON.toJSONString(new ResultObject("房产添加成功",1));
        Integer result = service.insertEstate(fcEstate);
        ResultObject ro;
        if(result == 0){
            ro = new ResultObject("房产编码已存在，操作失败",0);
        }else{
            ro = new ResultObject("房产添加成功",1);
        }
        return JSON.toJSONString(ro);
    }

    /**
     * 添加住宅信息第二步，传入楼宇数量，页面初始化时新生成楼宇行记录，并返回
     * @param buildingNumber
     * @param estateCode
     * @return
     */
    @RequestMapping("/selectBuilding")
    public String selectBuilding(Integer buildingNumber,  String estateCode){
        System.out.println("estate selectBuilding");
        List<FcBuilding> fcBuildings = service.selectBuilding(buildingNumber, estateCode);
        System.out.println(fcBuildings);
        return JSON.toJSONString(new ResultObject(fcBuildings));
    }

    @RequestMapping("/updateBuilding")
    public String updateBuilding(FcBuilding fcBuilding){
        System.out.println(fcBuilding);
        return JSON.toJSONString(new ResultObject(service.updateBuilding(fcBuilding)));
    }

    @RequestMapping("/updateBatchBuilding")
    public String updateBatchBuilding(@RequestBody FcBuilding[] fcBuildings){
        System.out.println("updateBatchBuilding ... ");
        // System.out.println(fcBuildings);
        for (FcBuilding fcBuilding : fcBuildings) {
            System.out.println(fcBuilding);
        }
        return JSON.toJSONString(new ResultObject(0));
        //return JSON.toJSONString(new ResultObject(service.updateBatchBuilding(fcBuildings)));
    }

}
