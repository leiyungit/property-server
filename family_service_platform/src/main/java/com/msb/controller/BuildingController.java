package com.msb.controller;

import com.alibaba.fastjson.JSON;
import com.msb.bean.FcBuilding;
import com.msb.resultJson.ResultObject;
import com.msb.service.EstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estate")
@CrossOrigin(origins = "*",allowedHeaders = "*",methods = {},allowCredentials = "true")
public class BuildingController {

    @Autowired
    private EstateService service;

    /**
     * 添加住宅信息第二步，传入楼宇数量，页面初始化时新生成楼宇行记录，并返回
     * @param buildingNumber
     * @param estateCode
     * @return
     */
    @RequestMapping("/selectBuilding")
    public String selectInsertBuilding(Integer buildingNumber,  String estateCode){
        System.out.println("estate selectBuilding");
        List<FcBuilding> fcBuildings = service.selectInsertBuilding(buildingNumber, estateCode);
        System.out.println(fcBuildings);
        return JSON.toJSONString(new ResultObject(fcBuildings));
    }

    @RequestMapping("/findBuildingByEstateCode")
    public String findBuildingByEstateCode(String estateCode){
        System.out.println("estate findBuildingByEstateCode     " + estateCode);
        List<FcBuilding> fcBuildings = service.findBuildingByEstateCode(estateCode);
        System.out.println(fcBuildings);
        return JSON.toJSONString(new ResultObject(fcBuildings));
    }

    @RequestMapping("/findSelectBuildingByEstateCode")
    public String findSelectBuildingByEstateCode(String estateCode){
        System.out.println("estate findSelectBuildingByEstateCode     " + estateCode);
        List<FcBuilding> fcBuildings = service.findSelectBuildingByEstateCode(estateCode);
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
        //return JSON.toJSONString(new ResultObject(0));
        return JSON.toJSONString(new ResultObject(service.updateBatchBuilding(fcBuildings)));
    }

}
