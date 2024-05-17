package com.atguigu.spzx.pay.service;

import com.atguigu.spzx.model.entity.pay.PaymentInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @version 1.0
 */
public interface PaymentInfoService {
    PaymentInfo savePaymentInfo(String orderNo);


    // 修改支付状态
    void updatePaymentStatus(Map<String, String> map, Integer payType);
}