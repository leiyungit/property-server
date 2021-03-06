package com.msb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.msb.bean.*;
import com.msb.mapper.*;
import com.msb.vo.CellMessage;
import com.msb.vo.UnitMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
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

    @Autowired
    private FcCellMapper fcCellMapper;

    public List<TblCompany> findCompay(){
        return tblCompanyMapper.findCompany();
    }

    public Integer insertSelectEstate(FcEstate fcEstate){
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

    public List<FcEstate> findEstate(){
        QueryWrapper queryWrapper = new QueryWrapper();
        return fcEstateMapper.selectList(queryWrapper);
    }

    public List<FcEstate> selectEstateByCompany(String company){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("company",company);
        return fcEstateMapper.selectList(queryWrapper);
    }

    public List<FcBuilding> selectInsertBuilding(Integer buildingNumber, String estateCode){
        List<FcBuilding> list = new ArrayList<>();
        FcBuilding fcBuilding;
        for (Integer i = 1; i <= buildingNumber; i++) {
            fcBuilding = new FcBuilding();
            fcBuilding.setEstateCode(estateCode);
            fcBuilding.setBuildingCode(estateCode+"-"+i);
            fcBuilding.setBuildingName(i+"幢");
            fcBuildingMapper.insert(fcBuilding);
            list.add(fcBuilding);
        }
        return list;
    }

    public List<FcBuilding> findBuildingByEstateCode(String estateCode){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("estate_code", estateCode);
        List<FcBuilding> list = fcBuildingMapper.selectList(queryWrapper);
        return list;
    }

    /**
     * 下拉数据源
     * @param estateCode
     * @return
     */
    public List<FcBuilding> findSelectBuildingByEstateCode(String estateCode){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("estate_code", estateCode);
        queryWrapper.select("building_code", "building_name");
        List<FcBuilding> list = fcBuildingMapper.selectList(queryWrapper);
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
                fcUnit.setUnitCode(unitMessage.getBuildingCode()+"-"+i);
                fcUnit.setUnitName("第"+ i + "单元");
                fcUnitMapper.insert(fcUnit);
                list.add(fcUnit);
            }
        }
        return list;
    }

    public List<FcUnit> findUnitByBuildingCode(String buildingCode){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("building_code", buildingCode);
        queryWrapper.select("unit_code", "unit_name");
        List<FcUnit> list = fcUnitMapper.selectList(queryWrapper);
        return list;
    }


    public Integer updateUnit(FcUnit fcUnit){
        return fcUnitMapper.updateById(fcUnit);
    }

    public Integer updateBatchUnit(FcUnit[] fcUnits){
        Integer count = 0;
        for (FcUnit fcUnit : fcUnits) {
             count += fcUnitMapper.updateById(fcUnit);        }
        return count;
    }

    //
    public List<FcCell> findCellByUnitCode(String unitCode){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("unit_code", unitCode);
        List<FcCell> list = fcCellMapper.selectList(queryWrapper);
        return list;
    }

    public List<FcCell> insertSelectCell(CellMessage[] cellMessages){
        List<FcCell> list = new ArrayList<>();
        DecimalFormat df=new DecimalFormat("00");
        for (CellMessage cellMessage : cellMessages) {
            // 遍历楼层
            for (int i = cellMessage.getStartFloor(); i <= cellMessage.getStopFloor(); i++){
                // 遍历房号
                for (int j = cellMessage.getStartCellId(); j <= cellMessage.getStopCellId(); j++) {
                    FcCell fcCell = new FcCell();
                    fcCell.setUnitCode(cellMessage.getUnitCode());
                    fcCell.setCellCode(cellMessage.getUnitCode()+"-"+df.format(i)+ df.format(j));
                    fcCell.setCellName(df.format(i)+ df.format(j));
                    fcCell.setFloorNumber(i);
                    fcCellMapper.insert(fcCell);
                    list.add(fcCell);
                }
            }
        }
        return list;
    }

    public Integer updateCell(FcCell fcCell){
        return fcCellMapper.updateById(fcCell);
    }

    public Integer updateBatchCell(FcCell[] fcCells){
        Integer count = 0;
        for (FcCell fcCell : fcCells) {
            count += fcCellMapper.updateById(fcCell);
        }
        return count;
    }

}
