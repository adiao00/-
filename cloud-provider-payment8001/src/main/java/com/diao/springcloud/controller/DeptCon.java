package com.diao.springcloud.controller;

import com.diao.springcloud.entities.CommonResult;
import com.diao.springcloud.entities.Dept;
import com.diao.springcloud.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class DeptCon {
    @Resource
    private DeptService deptService;
    @Value("${server.port}")//获取yml文件里的端口号
    private String serverPort;
    @PostMapping("/dept/create")
    public CommonResult create(@RequestBody Dept dept){
        int result = deptService.create(dept);
        log.info("****插入结果" + result);
        int a = 1+2;
        if (result>0){
            return new CommonResult(200,"成功，serverPort"+serverPort,result);
        }else {
            return new CommonResult(444,"插入失败",null);
        }
    }
    @GetMapping("/dept/create/{id}")
    public CommonResult get(@PathVariable("id") Long id){
        Dept dept = deptService.getDeptById(id);
        log.info("****插入结果" + dept);
        if (dept!=null){
            return new CommonResult(200,"成功 serverPort"+serverPort,dept);
        }else {
            return new CommonResult(444,"失败",null);
        }
    }
    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }
    //模拟服务超时
    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return serverPort;
    }
}
