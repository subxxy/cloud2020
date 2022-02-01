package com.atguigu.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("consumer")
public class OrderController {
    private static final String CONSUL_URL="http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/payment/consul")
    public String payment() {
        return restTemplate.getForObject(CONSUL_URL+"/payment/consul", String.class);
    }
}
