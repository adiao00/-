package com.diao.springcloud.controller;

import com.diao.springcloud.entities.CommonResult;
import com.diao.springcloud.entities.Dept;
import com.diao.springcloud.lb.LoadBslsnvrt;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderCon {
    @Resource
    private RestTemplate template;
    @Resource
    private LoadBslsnvrt loadBslsnvrt;
    @Resource
    private DiscoveryClient discoveryClient;
//    public static final  String PAYMENT_URL  = "http://localhost:8001";
    public static final  String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
    @GetMapping("/consumer/dept/create")
    public CommonResult<Dept> create(Dept dept){
        return template.postForObject(PAYMENT_URL+"/dept/create",dept,CommonResult.class);
    }
    @GetMapping("/consumer/dept/get/{id}")
    public CommonResult<Dept> get(@PathVariable("id")Long id){
        return template.getForObject(PAYMENT_URL+"/dept/create/"+id,CommonResult.class);
    }
    @GetMapping("/consumer/payment/lb")
    public String getPaymentLB(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size()<=0){
            return null;
        }
        ServiceInstance serviceInstance = loadBslsnvrt.instances(instances);
        URI uri = serviceInstance.getUri();
        System.out.println(uri);
        return template.getForObject(uri+"/payment/lb",String.class);
    }
}
