package com.atguigu.spzx.product.service;


import com.atguigu.spzx.model.dto.h5.ProductSkuDto;
import com.atguigu.spzx.model.entity.product.ProductSku;
import com.atguigu.spzx.model.vo.h5.ProductItemVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @version 1.0
 */
public interface ProductService {
    // 根据销量查询商品
    List<ProductSku> findProductSkuBySale();

    // 分页查询商品
    PageInfo<ProductSku> findByPage(Integer page, Integer limit, ProductSkuDto productSkuDto);

    // 商品详情
    ProductItemVo item(Long skuId);

    // 根据skuId查询商品
    ProductSku getBySkuId(Long skuId);
}
