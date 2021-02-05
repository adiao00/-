package com.diao.springcloud.controller;

import com.diao.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentCon {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverport;
    @GetMapping("/payment/hystrix/ok/{id}")
    public String paumentInfo_ok(@PathVariable("id")Integer id){
        String resul = paymentService.payment_ok(id);
        log.info("result" +resul);
        return resul;
    }
    @GetMapping("/payment/hystrix/no/{id}")
    public String paumentInfo_no(@PathVariable("id")Integer id) {
        String resul = null;
        try {
            resul = paymentService.payment_no(id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("result" +resul);
        return resul;
    }
//    服务熔断
    @GetMapping("/payment/circuit/{id}")
    public String paumentInfo_ok1(@PathVariable("id")Integer id){
        String resul = paymentService.paymentCircuitBreaker(id);
        log.info("result" +resul);
        return resul;
    }
}
