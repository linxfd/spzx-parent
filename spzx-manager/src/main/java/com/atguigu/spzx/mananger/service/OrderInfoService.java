package com.atguigu.spzx.mananger.service;

import com.atguigu.spzx.model.dto.order.OrderStatisticsDto;
import com.atguigu.spzx.model.vo.order.OrderStatisticsVo;

/**
 * @version 1.0
 */
public interface OrderInfoService {

    //统计订单金额和对应日期
    OrderStatisticsVo getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto);

}
