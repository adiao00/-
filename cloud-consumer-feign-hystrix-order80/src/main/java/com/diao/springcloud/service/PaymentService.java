package com.diao.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentFallbackService.class)
public interface PaymentService {
    @GetMapping("/payment/hystrix/ok/{id}")
    public String paumentInfo_ok(@PathVariable("id")Integer id);
    @GetMapping("/payment/hystrix/no/{id}")
    public String paumentInfo_no(@PathVariable("id")Integer id);
}
