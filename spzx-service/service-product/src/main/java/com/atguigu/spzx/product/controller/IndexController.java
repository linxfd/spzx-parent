package com.atguigu.spzx.product.controller;

import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.model.entity.product.ProductSku;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.h5.IndexVo;
import com.atguigu.spzx.product.service.CategoryService;
import com.atguigu.spzx.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @version 1.0
 */
@Tag(name = "首页接口管理")
@RestController
@RequestMapping(value="/api/product/index")
//@CrossOrigin   //跨域
public class IndexController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Operation(summary = "获取首页数据")
    @GetMapping
    public Result<IndexVo> findData(){
        // 获取分类
        List<Category> categoryList = categoryService.findOneCategory();
        // 获取商品
        List<ProductSku> productSkuList = productService.findProductSkuBySale();
        // 封装返回数据
        IndexVo indexVo = new IndexVo() ;
        indexVo.setCategoryList(categoryList);
        indexVo.setProductSkuList(productSkuList);
        return Result.build(indexVo , ResultCodeEnum.SUCCESS);
    }
}
