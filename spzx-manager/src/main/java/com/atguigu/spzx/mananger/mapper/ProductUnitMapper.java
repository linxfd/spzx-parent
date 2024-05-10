package com.atguigu.spzx.mananger.mapper;

import com.atguigu.spzx.model.entity.base.ProductUnit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @version 1.0
 */
@Mapper
public interface ProductUnitMapper {

    public abstract List<ProductUnit> findAll();
}
