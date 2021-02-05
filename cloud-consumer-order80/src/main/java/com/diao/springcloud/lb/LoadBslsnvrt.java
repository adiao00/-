package com.diao.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;
//搜集现在集群上有多少台服务实例
public interface LoadBslsnvrt {
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
