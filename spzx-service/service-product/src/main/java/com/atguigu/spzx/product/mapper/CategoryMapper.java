package com.atguigu.spzx.product.mapper;

import com.atguigu.spzx.model.entity.product.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @version 1.0
 */
@Mapper
public interface CategoryMapper {

    // 查询一级分类
    List<Category> findOneCategory();

    // 查询所有分类
    List<Category> findAll();

}
