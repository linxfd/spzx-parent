package com.atguigu.spzx.product.service;

import com.atguigu.spzx.model.entity.product.Brand;

import java.util.List;

/**
 * @version 1.0
 */
public interface BrandService {
    //查询全部品牌
    List<Brand> findAll();

}
