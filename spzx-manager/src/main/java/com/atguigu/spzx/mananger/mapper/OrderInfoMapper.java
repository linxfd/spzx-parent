package com.atguigu.spzx.mananger.mapper;

import com.atguigu.spzx.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

/**
 * @version 1.0
 */
@Mapper
public interface OrderInfoMapper {


    OrderStatistics selectOrderStatistics(String createTime);

}
