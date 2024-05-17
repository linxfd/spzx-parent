package com.atguigu.spzx.order.mapper;

import com.atguigu.spzx.model.entity.order.OrderInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @version 1.0
 */
@Mapper
public interface OrderInfoMapper {

    // 保存订单
    void save(OrderInfo orderInfo);

    // 根据订单id查询订单
    OrderInfo getById(Long orderId);

    // 根据用户id查询订单
    List<OrderInfo> findUserPage(Long userId, Integer orderStatus);

    // 根据订单编号查询订单
    OrderInfo getByOrderNo(String orderNo);

    // 修改订单
    void updateById(OrderInfo orderInfo);
}