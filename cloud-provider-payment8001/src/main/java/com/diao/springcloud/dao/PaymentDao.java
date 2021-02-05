package com.diao.springcloud.dao;

import com.diao.springcloud.entities.Dept;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface PaymentDao {
    public int create(Dept dept);
    public Dept getDeptById(@Param("id")Long id);

}
