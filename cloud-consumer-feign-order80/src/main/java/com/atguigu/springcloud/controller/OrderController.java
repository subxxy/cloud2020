package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("consumer")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymet(@PathVariable("id") Long id) {
        return orderService.selectOne(id);
    }

    @GetMapping("/payment/feign/timeout")
    public String feignTimeout(){
        return orderService.feignTimeout();
    }
}
