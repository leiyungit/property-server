package com.msb.controller;

import com.alibaba.fastjson.JSON;
import com.msb.bean.FcBuilding;
import com.msb.resultJson.ResultObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class EstateService {

    @Autowired
    private com.msb.service.EstateService service;

    @Test
    public void findBuildingByEstateCode(){
        String estateCode = "A";
        List<FcBuilding> fcBuildings = service.findBuildingByEstateCode(estateCode);
        System.out.println(fcBuildings);

    }
}
