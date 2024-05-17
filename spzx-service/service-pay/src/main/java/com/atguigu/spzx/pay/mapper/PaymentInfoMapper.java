package com.atguigu.spzx.pay.mapper;

import com.atguigu.spzx.model.entity.pay.PaymentInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @version 1.0
 */
@Mapper
public interface PaymentInfoMapper {
    //保存支付信息
    void save(PaymentInfo paymentInfo);

    //根据订单编号查询支付信息
    PaymentInfo getByOrderNo(String orderNo);

    //修改支付信息
    void updateById(PaymentInfo paymentInfo);
}