package com.p.dao.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisDao {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final JedisPool jedisPool;

    public RedisDao(JedisPoolConfig config, String ip, int port, int timeout, String password) {
        config.setMaxIdle(8);
        config.setMaxTotal(18);
        jedisPool = new JedisPool(config, ip, port, timeout, password);
        //Jedis jedis = jedisPool.getResource();
        //jedis.auth(password);
        //String value = jedis.get("key");
        //jedis.close();
        //jedisPool.close();
    }

    public Jedis getJedis() {
        return jedisPool.getResource();
    }

    public static void returnResource(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}
