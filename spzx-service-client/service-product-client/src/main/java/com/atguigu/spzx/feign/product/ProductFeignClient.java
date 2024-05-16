package com.atguigu.spzx.feign.product;

import com.atguigu.spzx.model.entity.product.ProductSku;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @version 1.0
 */
@FeignClient(value = "service-product")  // 指定微服务的名称
public interface ProductFeignClient {

    @GetMapping("/api/product/getBySkuId/{skuId}")
    public abstract ProductSku getBySkuId(@PathVariable("skuId") Long skuId) ;

}