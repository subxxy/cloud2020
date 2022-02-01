package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        if (result > 0) {
            return new CommonResult(200, "插入数据库成功, serverPort: " + serverPort, result);
        } else {
            return new CommonResult(444, "插入数据库失败", null);
        }
    }

    @GetMapping("/get/{id}")
    public CommonResult<Payment> selectOne(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentByid(id);

        if (payment != null) {
            return new CommonResult(200, "恭喜你！检索到了数据。serverPort: " + serverPort, payment);
        } else {
            return new CommonResult(444, "检索的数据id:" + id +",数据库里面查询不到", null);
        }
    }

    @GetMapping("/lb")
    public String paymentConsul(){
        return serverPort;
    }
}
