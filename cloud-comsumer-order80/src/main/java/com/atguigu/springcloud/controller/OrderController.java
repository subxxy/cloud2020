package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.lb.MySelfLB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("consumer")
public class OrderController {

    public static final  String PYAMENT_URL="http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private MySelfLB mySelfLB;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PYAMENT_URL+"/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymet(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PYAMENT_URL+"/payment/get/"+id, CommonResult.class);
    }

    @GetMapping("/payment/createEnrity")
    public CommonResult<Payment> createEntity(Payment payment) {
        ResponseEntity<CommonResult> crr = restTemplate.postForEntity(PYAMENT_URL + "/payment/create", payment, CommonResult.class);
        if (crr.getStatusCode().is2xxSuccessful()) {
            return crr.getBody();
        } else {
            return new CommonResult(444,"创建失败", null);
        }
    }

    @GetMapping("/payment/getEntity/{id}")
    public CommonResult<Payment> getEntity(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> crr = restTemplate.getForEntity(PYAMENT_URL + "/payment/get/"+id, CommonResult.class);
        if (crr.getStatusCode().is2xxSuccessful()) {
            return crr.getBody();
        } else {
            return new CommonResult(444,"创建失败", null);
        }
    }

    @GetMapping("/payment/LB")
    public CommonResult<Payment> getLB() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() <= 0) {
            return new CommonResult(444,"服务失败", null);
        }
        ServiceInstance instance = mySelfLB.instance(instances);
        return restTemplate.getForObject(instance.getUri() + "/payment/get/1001", CommonResult.class);
    }
}
