package com.atguigu.spzx.mananger.mapper;

import com.atguigu.spzx.model.entity.product.ProductSpec;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @version 1.0
 */
@Mapper
public interface ProductSpecMapper {

    //查询所有
    public abstract List<ProductSpec> findByPage();

    //添加规格
    void save(ProductSpec productSpec);

    //修改规格
    void updateById(ProductSpec productSpec);

    //删除规格
    public abstract void deleteById(Long id);

    //查询所有规格
    List<ProductSpec> findAll();

}