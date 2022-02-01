package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class OrderFallbackService implements OrderService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "-----OrderFallbackService fall back-paymentInfo_OK , (┬＿┬)";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "-----OrderFallbackService fall back-paymentInfo_TimeOut , (┬＿┬)";
    }
}
