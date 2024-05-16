package com.atguigu.spzx.product.mapper;

import com.atguigu.spzx.model.entity.product.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @version 1.0
 */
@Mapper
public interface BrandMapper {

    List<Brand> findAll();

}
