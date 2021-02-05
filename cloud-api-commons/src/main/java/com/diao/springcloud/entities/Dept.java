package com.diao.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

//serializable 实现序列化所有实体类务必实现

@NoArgsConstructor
@Data
@AllArgsConstructor
@Accessors(chain = true)//链式写法
public class Dept implements Serializable {//dept orm 映射
    private Long Deptno;
    private String dname;
    //这个数据存在哪个数据库字段~微服务，一个服务对应一个数据库，同一个信息可能存在不同的数据库
    private String db_source;

    //链式写法
//    Dept dept = new Dept();
//    dept.setdname().setdb_sourse()
}
