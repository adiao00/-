package com.diao.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component//让容器扫描到
public class MyLB implements LoadBslsnvrt{
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    public final int getAndIncrement(){
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= 2147483647 ? 0 : current+1;

        }while (!this.atomicInteger.compareAndSet(current,next));
        System.out.println("*****第几次访问次数 next"+next);
        return next;
    }
    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
//        rest接口第几次请求数  %  服务器集群总数量 = 实际调用服务器位置下标，每次服务重启后rest接口记数从1开始
        int index = getAndIncrement()%serviceInstances.size();
        return serviceInstances.get(index);
    }
}
