package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentByid(@Param("id") Long id);
}
