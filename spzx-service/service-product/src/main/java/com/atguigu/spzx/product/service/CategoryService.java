package com.atguigu.spzx.product.service;

import com.atguigu.spzx.model.entity.product.Category;

import java.util.List;

/**
 * @version 1.0
 */
public interface CategoryService {

    //查询一级分类
    List<Category> findOneCategory();

    //查询分类树
    List<Category> findCategoryTree();
}
