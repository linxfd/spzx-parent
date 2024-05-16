package com.atguigu.spzx.product.service.impl;

import com.atguigu.spzx.model.entity.product.Category;

import com.atguigu.spzx.product.mapper.CategoryMapper;
import com.atguigu.spzx.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @version 1.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    public List<Category> findOneCategory() {
        return categoryMapper.findOneCategory();
    }

    // 接口实现类
    @Cacheable(value = "category",key = "'all'")
    @Override
    public List<Category> findCategoryTree() {
        // 查询所有分类
        List<Category> categoryList = categoryMapper.findAll();
        //全部一级分类
        List<Category> oneCategoryList = categoryList.stream()
                .filter(item -> item.getParentId().longValue() == 0)
                .collect(Collectors.toList());

        if(!CollectionUtils.isEmpty(oneCategoryList)) {
            for (Category oneCategory : oneCategoryList) {
                // 查询一级分类下的二级分类
                List<Category> twoCategoryList = categoryList.stream()
                        .filter(item -> item.getParentId().longValue() == oneCategory.getId().longValue())
                        .collect(Collectors.toList());
                // 设置一级分类下的二级分类
                oneCategory.setChildren(twoCategoryList);

                if (!CollectionUtils.isEmpty(twoCategoryList)) {
                    // 查询二级分类下的三级分类
                    for (Category twoCategory : twoCategoryList) {
                        List<Category> threeCategoryList = categoryList.stream()
                                .filter(item -> item.getParentId().longValue() == twoCategory.getId().longValue())
                                .collect(Collectors.toList());
                        // 设置二级分类下的三级分类
                        twoCategory.setChildren(threeCategoryList);
                    }
                }
            }
        }
        return oneCategoryList;
    }
}
