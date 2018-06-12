package com.p.dao;

import com.p.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    @Resource
    private SeckillDao seckillDao;

    @Test
    public void reduceNumber() {
        long seckillId = 1000;
        Seckill seckill = seckillDao.selectByPrimaryKey(seckillId);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }

    @Test
    public void queryAll() {
    }
}