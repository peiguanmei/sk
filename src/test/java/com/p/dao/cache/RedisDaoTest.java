package com.p.dao.cache;

import com.p.dao.SeckillDao;
import com.p.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext-dao.xml"})
public class RedisDaoTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private long id = 1002;

    @Autowired
    private RedisDao redisDao;

    @Autowired
    private SeckillDao seckillDao;

    @Test
    public void testRedisConnect() {
        Jedis jedis = redisDao.getJedis();
        System.out.println(jedis.get("key:51"));
    }

    @Test
    public void testSeckill() {
        Seckill seckill = redisDao.getSeckill(id);
        if (seckill == null) {
            seckill = seckillDao.selectByPrimaryKey(id);
            if (seckill != null) {
                String result = redisDao.putSeckill(seckill);
                logger.info("result={}", result);
                seckill = redisDao.getSeckill(id);
                logger.info("seckill={}", seckill);
            }
        }
    }
}