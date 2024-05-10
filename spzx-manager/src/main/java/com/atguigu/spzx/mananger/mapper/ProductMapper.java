package com.atguigu.spzx.mananger.mapper;

import com.atguigu.spzx.model.dto.product.ProductDto;
import com.atguigu.spzx.model.entity.product.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @version 1.0
 */
@Mapper
public interface ProductMapper {
    // 修改商品
    void updateById(Product product) ;


    public abstract List<Product> findByPage(ProductDto productDto);

    // 添加
    void save(Product product);

    // 查询商品数据
    public abstract Product selectById(Long id);

    // 删除商品数据
    void deleteById(Long id);
}
