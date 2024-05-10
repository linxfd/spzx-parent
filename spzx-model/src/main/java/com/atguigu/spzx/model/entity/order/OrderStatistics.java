package com.atguigu.spzx.model.entity.order;

import com.atguigu.spzx.model.entity.base.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderStatistics extends BaseEntity {

    //统计的日期
    private Date orderDate;
    //总金额
    private BigDecimal totalAmount;
    //订单数量
    private Integer totalNum;
    
}