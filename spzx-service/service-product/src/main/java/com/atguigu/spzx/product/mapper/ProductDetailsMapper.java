package com.atguigu.spzx.product.mapper;

import com.atguigu.spzx.model.entity.product.ProductDetails;
import org.apache.ibatis.annotations.Mapper;

/**
 * @version 1.0
 */
@Mapper
public interface ProductDetailsMapper {

    // 根据商品id查询商品详情
    ProductDetails getByProductId(Long productId);
}
