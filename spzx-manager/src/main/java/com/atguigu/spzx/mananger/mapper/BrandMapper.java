package com.atguigu.spzx.mananger.mapper;

import com.atguigu.spzx.model.entity.product.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @version 1.0
 */
@Mapper
public interface BrandMapper {

    // 查询所有品牌
    public abstract List<Brand> findByPage();

    // 新增品牌
    void save(Brand brand);

    // 修改品牌
    void updateById(Brand brand);

    // 根据id删除品牌
    void deleteById(Long id);

    // 查询所有品牌
    List<Brand> findAll();

}