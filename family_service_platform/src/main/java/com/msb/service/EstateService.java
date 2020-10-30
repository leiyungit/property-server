package com.msb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.msb.bean.FcEstate;
import com.msb.bean.TblCompany;
import com.msb.mapper.FcEstateMapper;
import com.msb.mapper.TblCompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstateService {

    @Autowired
    private TblCompanyMapper tblCompanyMapper;

    @Autowired
    private FcEstateMapper fcEstateMapper;

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
}
