package com.atguigu.spzx.mananger.service;

import com.atguigu.spzx.model.dto.product.ProductDto;
import com.atguigu.spzx.model.entity.product.Product;
import com.github.pagehelper.PageInfo;

/**
 * @version 1.0
 */
public interface ProductService {
    //分页查询
    PageInfo<Product> findByPage(Integer page, Integer limit, ProductDto productDto);

    //添加商品
    void save(Product product);

    //查询商品详情
    Product getById(Long id);

    //修改商品
    void updateById(Product product);

    //删除商品
    void deleteById(Long id);

    //修改审核状态
    void updateAuditStatus(Long id, Integer auditStatus);

    //修改上下架状态
    void updateStatus(Long id, Integer status);
}
