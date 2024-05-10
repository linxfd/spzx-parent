package com.atguigu.spzx.mananger.mapper;

import com.atguigu.spzx.model.entity.product.ProductDetails;
import org.apache.ibatis.annotations.Mapper;

/**
 * @version 1.0
 */
@Mapper
public interface ProductDetailsMapper {

    public abstract void save(ProductDetails productDetails);

    // 根据商品的id查询商品详情数据
    ProductDetails selectByProductId(Long id);

    // 修改商品详情数据
    void updateById(ProductDetails productDetails);

    // 根据商品的id删除商品详情数据
    void deleteByProductId(Long id);
}
