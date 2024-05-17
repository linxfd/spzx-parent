package com.atguigu.spzx.order.mapper;

import com.atguigu.spzx.model.entity.order.OrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @version 1.0
 */
@Mapper
public interface OrderItemMapper {

    //保存订单项
    void save(OrderItem orderItem);

    //根据订单id查询订单项列表
    List<OrderItem> findByOrderId(Long id);
}