package com.msb.controller;

import com.alibaba.fastjson.JSON;
import com.msb.bean.FcBuilding;
import com.msb.bean.FcUnit;
import com.msb.resultJson.ResultObject;
import com.msb.service.EstateService;
import com.msb.vo.UnitMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estate")
@CrossOrigin(origins = "*",allowedHeaders = "*",methods = {},allowCredentials = "true")
public class UnitController {

    @Autowired
    private EstateService service;

    @RequestMapping("/selectUnit")
    public String selectUnit(@RequestBody UnitMessage[] unitMessages){
        System.out.println("selectUnit..........");
        return JSON.toJSONString(new ResultObject(service.selectUnit(unitMessages)));
    }

    @RequestMapping("/findUnitByBuildingCode")
    public String findUnitByBuildingCode(String buildingCode){
        System.out.println("findUnitByBuildingCode..........");
        return JSON.toJSONString(new ResultObject(service.findUnitByBuildingCode(buildingCode)));
    }


    @PostMapping("/updateBatchUnit")
    public String updateBatchUnit(@RequestBody FcUnit[] fcUnits){
        System.out.println("updateBatchUnit..........");
        return JSON.toJSONString(new ResultObject(service.updateBatchUnit(fcUnits)));
    }

    @PostMapping("/updateUnit")
    public String updateUnit(FcUnit fcUnit){
        System.out.println("updateUnit..........");
        return JSON.toJSONString(new ResultObject(service.updateUnit(fcUnit)));
    }

}
