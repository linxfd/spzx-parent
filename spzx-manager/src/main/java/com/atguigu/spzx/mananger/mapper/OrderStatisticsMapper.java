package com.atguigu.spzx.mananger.mapper;

import com.atguigu.spzx.model.dto.order.OrderStatisticsDto;
import com.atguigu.spzx.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @version 1.0
 */
@Mapper
public interface OrderStatisticsMapper {

    //新增
    public abstract void insert(OrderStatistics orderStatistics);

    //根据日期查询统计表
    List<OrderStatistics> selectList(OrderStatisticsDto orderStatisticsDto);
}