package com.diao.springcloud.controller;

import com.diao.springcloud.entities.CommonResult;
import com.diao.springcloud.entities.Dept;
import com.diao.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderFeignCon {
    @Resource
    private PaymentFeignService paymentFeignService;
    @GetMapping(value = "/comsumer/payment/get/{id}")
    public CommonResult<Dept>getPaymentById(@PathVariable("id")Long id){
        return paymentFeignService.getDeptById(id);
    }
    @GetMapping(value = "/comsumer/payment/feign/timeout")
    public String paymentFeignTimeout(){
        return paymentFeignService.paymentFeignTimeout();
    }
}
