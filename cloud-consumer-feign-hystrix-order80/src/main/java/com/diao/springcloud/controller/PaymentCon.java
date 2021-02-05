package com.diao.springcloud.controller;

import com.diao.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_no2")
public class PaymentCon {
    @Resource
    private PaymentService paymentService;
    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paumentInfo_ok(@PathVariable("id")Integer id){
        String result = paymentService.paumentInfo_ok(id);
        return result;
    }
    @GetMapping("/consumer/payment/hystrix/no/{id}")
//    @HystrixCommand(fallbackMethod = "payment_no1",commandProperties ={
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "1000")//正常1秒 超过一秒上边方法兜底  不管是运行异常还是超时异常都会去兜底方法
//    })
    @HystrixCommand
    public String paumentInfo_no(@PathVariable("id")Integer id){
        int a = 10/0;
        String result = paymentService.paumentInfo_no(id);
        return result;
    }
    public String payment_no1(Integer id){
        return "消费者80异常";
    }

    public String payment_no2(){
        return "消费者80全局异常";
    }
}
