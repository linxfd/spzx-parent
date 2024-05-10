package com.atguigu.spzx.mananger.mapper;

import com.atguigu.spzx.model.dto.product.CategoryBrandDto;
import com.atguigu.spzx.model.entity.product.Brand;
import com.atguigu.spzx.model.entity.product.CategoryBrand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @version 1.0
 */
@Mapper
public interface CategoryBrandMapper {

    //根据条件分页查询
    List<CategoryBrand> findByPage(CategoryBrandDto categoryBrandDto);

    //添加分类品牌
    void save(CategoryBrand categoryBrand);

    //修改分类品牌
    void updateById(CategoryBrand categoryBrand);

    //删除分类品牌
    void deleteById(Long id);

    //根据分类id查询品牌
    List<Brand> findBrandByCategoryId(Long categoryId);
}
