package com.p.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import com.p.entity.Seckill;

import java.util.Date;
import java.util.List;

@Repository
public interface SeckillDao extends Mapper<Seckill> {

    /**
     * 减库存
     *
     * @param seckillId the seckill id
     * @param killTime  the kill time
     * @return 如果影响行数>1，表示更新库存的记录行数
     */
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

    /**
     * 根据偏移量查询秒杀商品列表
     *
     * @param offset the offset
     * @param limit  the limit
     * @return the list
     */
    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);
}
