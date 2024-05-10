package com.atguigu.spzx.mananger.mapper;

import com.atguigu.spzx.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @version 1.0
 */
@Mapper
public interface ProductSkuMapper {


    public abstract void save(ProductSku productSku);


    //// 根据商品的id查询sku数据
    List<ProductSku> selectByProductId(Long id);

    //// 根据商品的id查询sku数据
    void updateById(ProductSku productSku);

    /// 根据商品的id删除sku数据
    void deleteByProductId(Long id);
}
