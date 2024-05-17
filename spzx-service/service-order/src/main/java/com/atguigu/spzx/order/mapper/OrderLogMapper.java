package com.atguigu.spzx.order.mapper;

import com.atguigu.spzx.model.entity.order.OrderLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @version 1.0
 */
@Mapper
public interface OrderLogMapper {

    // 保存订单日志
    void save(OrderLog orderLog);
}