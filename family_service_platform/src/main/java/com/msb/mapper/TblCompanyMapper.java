package com.msb.mapper;

import com.msb.bean.TblCompany;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 企业档案 Mapper 接口
 * </p>
 *
 * @author leiy
 * @since 2020-10-21
 */
@Component
public interface TblCompanyMapper extends BaseMapper<TblCompany> {

    List<TblCompany> findCompany();
}
