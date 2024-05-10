package com.atguigu.spzx.mananger.service;

import com.atguigu.spzx.model.entity.product.Brand;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @version 1.0
 */
public interface BrandService {
    // 根据分页条件查询品牌列表
    PageInfo<Brand> findByPage(Integer page, Integer limit);

    // 根据id查询品牌
    void save(Brand brand);

    //修改品牌
    void updateById(Brand brand);

    //根据id删除品牌
    void deleteById(Long id);

    //查询所有品牌
    List<Brand> findAll();

}
