package com.atguigu.spzx.product.mapper;

import com.atguigu.spzx.model.entity.product.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * @version 1.0
 */
@Mapper
public interface ProductMapper {

    // 根据id查询商品信息
    Product getById(Long id);
}
