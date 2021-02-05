package com.diao.springcloud;

import com.diao.pack.MyselfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@EnableEurekaClient
@SpringBootApplication
//@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = MyselfRule.class)//负载均衡随机  默认为轮询
public class order80 {
    public static void main(String[] args) {
        SpringApplication.run(order80.class,args);
    }
}
