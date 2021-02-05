package com.diao.springcloud.service;

import com.diao.springcloud.entities.Dept;
import org.apache.ibatis.annotations.Param;

public interface DeptService {
    public int create(Dept dept);
    public Dept getDeptById(@Param("id") Long id);
}
