package com.msb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.msb.bean.FcBuilding;
import com.msb.bean.FcEstate;
import com.msb.bean.FcUnit;
import com.msb.bean.TblCompany;
import com.msb.mapper.FcBuildingMapper;
import com.msb.mapper.FcEstateMapper;
import com.msb.mapper.FcUnitMapper;
import com.msb.mapper.TblCompanyMapper;
import com.msb.vo.UnitMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstateService {

    @Autowired
    private TblCompanyMapper tblCompanyMapper;

    @Autowired
    private FcEstateMapper fcEstateMapper;

    @Autowired
    private FcBuildingMapper fcBuildingMapper;

    @Autowired
    private FcUnitMapper fcUnitMapper;

    public List<TblCompany> findCompay(){
        return tblCompanyMapper.findCompany();
    }

    public Integer insertEstate(FcEstate fcEstate){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("estate_code",fcEstate.getEstateCode());
        FcEstate model = fcEstateMapper.selectOne(queryWrapper);
        Integer result = 0;
        if(model == null){
            result = fcEstateMapper.insert(fcEstate);
        }else{
            return result;
        }
        return result;
    }

    public List<FcBuilding> selectBuilding(Integer buildingNumber, String estateCode){
        List<FcBuilding> list = new ArrayList<>();
        FcBuilding fcBuilding;
        for (Integer i = 0; i < buildingNumber; i++) {
            fcBuilding = new FcBuilding();
            fcBuilding.setEstateCode(estateCode);
            fcBuilding.setBuildingCode(estateCode+"-"+i);
            fcBuilding.setBuildingName(i+"幢");
            fcBuildingMapper.insert(fcBuilding);
            list.add(fcBuilding);
        }
        return list;
    }

    public Integer updateBuilding(FcBuilding fcBuilding){
        return fcBuildingMapper.updateById(fcBuilding);
    }

    public Integer updateBatchBuilding(FcBuilding[] fcBuildings){
        Integer count = 0;
        for (FcBuilding fcBuilding : fcBuildings) {
            count += fcBuildingMapper.updateById(fcBuilding);
        }
        return count;
    }


    public List<FcUnit> selectUnit(UnitMessage[] unitMessages){
        List<FcUnit> list = new ArrayList<>();
        for (UnitMessage unitMessage : unitMessages) {
            for (int i = 1; i <= unitMessage.getUnitCount(); i++){
                FcUnit fcUnit = new FcUnit();
                fcUnit.setBuildingCode(unitMessage.getBuildingCode());
                fcUnit.setUnitCode("U-"+i);
                fcUnit.setUnitName("第"+ i + "单元");
                fcUnitMapper.insert(fcUnit);
                list.add(fcUnit);
            }
        }
        return list;
    }
}
