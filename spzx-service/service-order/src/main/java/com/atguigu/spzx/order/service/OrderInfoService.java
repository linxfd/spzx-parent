package com.atguigu.spzx.order.service;

import com.atguigu.spzx.model.dto.h5.OrderInfoDto;
import com.atguigu.spzx.model.entity.order.OrderInfo;
import com.atguigu.spzx.model.vo.h5.TradeVo;
import com.github.pagehelper.PageInfo;

/**
 * @version 1.0
 */
public interface OrderInfoService {
    //获取订单交易信息
    TradeVo getTrade();

    //提交订单
    Long submitOrder(OrderInfoDto orderInfoDto);

    //根据订单id查询订单信息
    OrderInfo getOrderInfo(Long orderId);

    //立即购买
    TradeVo buy(Long skuId);

    //获取订单分页列表
    PageInfo<OrderInfo> findUserPage(Integer page, Integer limit, Integer orderStatus);

    //根据订单编号查询订单信息
    OrderInfo getByOrderNo(String orderNo);

    //更新订单状态
    void updateOrderStatus(String orderNo, Integer orderStatus);

}
