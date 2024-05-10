package com.atguigu.spzx.mananger.mapper;

import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.model.vo.product.CategoryExcelVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @version 1.0
 */
@Mapper
public interface CategoryMapper {


    //根据父id查询子节点
    List<Category> selectByParentId(Long parentId);

    //根据分类的id查询子分类的数量
    int countByParentId(Long id);

    //查询所有分类
    List<Category> selectAll();

    //批量导入分类
    int batchInsert(List cachedDataList);


//    //导入分类
//    int insert(CategoryExcelVo categoryExcelVo);
}
