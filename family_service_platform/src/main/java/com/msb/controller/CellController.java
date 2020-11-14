package com.msb.controller;

import com.alibaba.fastjson.JSON;
import com.msb.bean.FcCell;
import com.msb.bean.FcUnit;
import com.msb.resultJson.ResultObject;
import com.msb.service.EstateService;
import com.msb.vo.CellMessage;
import com.msb.vo.UnitMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estate")
@CrossOrigin(origins = "*",allowedHeaders = "*",methods = {},allowCredentials = "true")
public class CellController {

    @Autowired
    private EstateService service;

    @PostMapping("/selectCell")
    public String insertSelectCell(@RequestBody CellMessage[] cellMessages){
        /*System.out.println("selectCell.......");
        for (CellMessage cellMessage : cellMessages) {
            System.out.println(cellMessage);
        }*/
        return JSON.toJSONString(new ResultObject(service.insertSelectCell(cellMessages)));
    }

    @PostMapping("/findCellByUnitCode")
    public String findCellByUnitCode(String unitCode){
        return JSON.toJSONString(new ResultObject(service.findCellByUnitCode(unitCode)));
    }

    @PostMapping("/updateBatchCell")
    public String updateBatchCell(@RequestBody FcCell[] fcCells){
        System.out.println("updateBatchCell.........");
        return JSON.toJSONString(new ResultObject(service.updateBatchCell(fcCells)));
    }

    @PostMapping("/updateCell")
    public String updateCell(FcCell fcCell){
        System.out.println("updateCell..........");
        return JSON.toJSONString(new ResultObject(service.updateCell(fcCell)));
    }

}
