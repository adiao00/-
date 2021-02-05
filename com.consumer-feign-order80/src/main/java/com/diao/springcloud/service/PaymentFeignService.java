package com.diao.springcloud.service;

import com.diao.springcloud.entities.CommonResult;
import com.diao.springcloud.entities.Dept;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
    @GetMapping("/dept/create/{id}")
    CommonResult<Dept> getDeptById(@PathVariable("id")Long id);
    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout();
}
