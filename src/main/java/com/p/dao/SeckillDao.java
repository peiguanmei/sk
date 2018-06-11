package com.p.dao;

import com.p.entity.Seckill;
import java.util.List;

public interface SeckillDao {
    int deleteByPrimaryKey(Long seckillId);

    int insert(Seckill record);

    Seckill selectByPrimaryKey(Long seckillId);

    List<Seckill> selectAll();

    int updateByPrimaryKey(Seckill record);
}