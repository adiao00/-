package com.diao.springcloud.service.imp;

import com.diao.springcloud.dao.PaymentDao;
import com.diao.springcloud.entities.Dept;
import com.diao.springcloud.service.DeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DeptServiceImpl implements DeptService {
    @Resource
    private PaymentDao paymentDao;
    @Override
    public int create(Dept dept) {
        return paymentDao.create(dept);
    }

    @Override
    public Dept getDeptById(Long id) {
        return paymentDao.getDeptById(id);
    }
}
