package com.atguigu.spzx.product.mapper;

import com.atguigu.spzx.model.dto.h5.ProductSkuDto;
import com.atguigu.spzx.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @version 1.0
 */
@Mapper
public interface ProductSkuMapper {

    // 根据销量查询商品
    List<ProductSku> findProductSkuBySale();


    List<ProductSku> findByPage(ProductSkuDto productSkuDto);

    // 根据id查询商品
    ProductSku getById(Long skuId);

    // 根据商品id查询商品sku信息列表
    List<ProductSku> findByProductId(Long productId);
}
