package com.atguigu.spzx.cart.service;

import com.atguigu.spzx.model.entity.h5.CartInfo;

import java.util.List;

/**
 * @version 1.0
 */
public interface CartService {
    // 添加购物车
    void addToCart(Long skuId, Integer skuNum);

    // 获取购物车列表
    List<CartInfo> getCartList();

    // 删除购物车商品
    void deleteCart(Long skuId);

    // 修改购物车商品选中状态
    void checkCart(Long skuId, Integer isChecked);

    // 更新购物车商品全部选中状态
    void allCheckCart(Integer isChecked);

    // 清空购物车
    void clearCart();

}
