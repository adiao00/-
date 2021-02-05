package com.diao.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicagionContextConfig {
    @Bean
//    @LoadBalanced//负载均衡的能力
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
//往spring bean里注入