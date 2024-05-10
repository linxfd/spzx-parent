package com.atguigu.spzx.mananger.service;

import com.atguigu.spzx.model.dto.product.CategoryBrandDto;
import com.atguigu.spzx.model.entity.product.Brand;
import com.atguigu.spzx.model.entity.product.CategoryBrand;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @version 1.0
 */
public interface CategoryBrandService {
    //根据条件分页查询
    PageInfo<CategoryBrand> findByPage(Integer page, Integer limit, CategoryBrandDto categoryBrandDto);

    //添加分类品牌
    void save(CategoryBrand categoryBrand);

    //修改分类品牌
    void updateById(CategoryBrand categoryBrand);

    //删除分类品牌
    void deleteById(Long id);

    //根据分类id查询品牌列表
    List<Brand> findBrandByCategoryId(Long categoryId);
}
