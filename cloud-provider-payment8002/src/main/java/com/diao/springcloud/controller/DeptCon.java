package com.diao.springcloud.controller;

import com.diao.springcloud.entities.CommonResult;
import com.diao.springcloud.entities.Dept;
import com.diao.springcloud.service.DeptService;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class DeptCon {
    @Resource
    private DeptService deptService;
    @Value("${server.port}")//获取yml文件里的端口号
    private String serverPort;
    @Resource
    private DiscoveryClient discoveryClient;
    @PostMapping("/dept/create")
    public CommonResult create(@RequestBody Dept dept){
        int result = deptService.create(dept);
        log.info("****插入结果" + result);
        int a = 1+2;
        if (result>0){
            return new CommonResult(200,"成功serverPort"+serverPort,result);
        }else {
            return new CommonResult(444,"插入失败",null);
        }
    }
    @GetMapping("/dept/create/{id}")
    public CommonResult get(@PathVariable("id") Long id){
        Dept dept = deptService.getDeptById(id);
        log.info("****插入结果" + dept);
        if (dept!=null){
            return new CommonResult(200,"成功serverPort"+serverPort,dept);
        }else {
            return new CommonResult(444,"失败",null);
        }
    }
    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> service = discoveryClient.getServices();
        for (String element : service) {
            log.info(element);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance s:instances) {
            log.info(s.getServiceId()+"\t"+s.getHost()+"\t"+s.getPort()+"\t"+s.getUri());
        }
        return this.discoveryClient;
    }
    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }
}
