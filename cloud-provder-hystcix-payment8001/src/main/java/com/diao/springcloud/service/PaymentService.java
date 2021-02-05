package com.diao.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    public String payment_ok(Integer id){
        return "线程池 "+Thread.currentThread().getName()+"payment_ok id"+id+"\t";
    }
   @HystrixCommand(fallbackMethod = "payment_no1",commandProperties ={
           @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "1000")//正常1秒 超过一秒上边方法兜底  不管是运行异常还是超时异常都会去兜底方法
   })
    public String payment_no(Integer id) throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        return "线程池 "+Thread.currentThread().getName()+"payment_no id"+id+"\t";
    }

    public String payment_no1(Integer id){
        return "线程池 "+Thread.currentThread().getName()+"payment_no1 id"+id+"\t";
    }
    //    熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value ="true"), // 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60") // 失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if (id < 0){
            throw new RuntimeException("id不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功"+serialNumber;
    }

    public String paymentCircuitBreakerFallback(@PathVariable("id")Integer id){
        return "id不能为负数";
    }
}
