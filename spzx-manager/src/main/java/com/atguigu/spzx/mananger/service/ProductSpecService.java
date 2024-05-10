package com.atguigu.spzx.mananger.service;

import com.atguigu.spzx.model.entity.product.ProductSpec;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @version 1.0
 */
public interface ProductSpecService {

    //分页查询
    PageInfo<ProductSpec> findByPage(Integer page, Integer limit);

    //添加规格
    void save(ProductSpec productSpec);

    //修改规格
    void updateById(ProductSpec productSpec);

    //删除规格
    void deleteById(Long id);

    //查询所有规格
    List<ProductSpec> findAll();

}
