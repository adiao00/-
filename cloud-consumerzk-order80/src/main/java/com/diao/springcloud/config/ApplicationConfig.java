package com.diao.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {
    @Bean
    @LoadBalanced  //负责负载均衡  手写代码所以注销
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
