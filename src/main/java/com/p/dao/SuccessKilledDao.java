package com.p.dao;

import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import com.p.entity.SuccessKilled;

@Repository
public interface SuccessKilledDao extends Mapper<SuccessKilled> {
    /**
     * 插入购买明细,可过滤重复
     * @param seckillId
     * @param userPhone
     * @return插入的行数
     */
    int insertSuccessKilled(long seckillId,long userPhone);


    /**
     * 根据秒杀商品的id查询明细SuccessKilled对象(该对象携带了Seckill秒杀产品对象)
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(long seckillId,long userPhone);
}
