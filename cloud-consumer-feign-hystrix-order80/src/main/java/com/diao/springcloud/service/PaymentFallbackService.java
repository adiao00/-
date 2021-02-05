package com.diao.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public String paumentInfo_ok(Integer id) {
        return "ok";
    }

    @Override
    public String paumentInfo_no(Integer id) {
        return "no";
    }
}
