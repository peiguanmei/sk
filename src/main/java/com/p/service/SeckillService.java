package com.p.service;

import com.p.dto.Exposer;
import com.p.dto.SeckillExecution;
import com.p.entity.Seckill;
import com.p.exception.RepeatKillException;
import com.p.exception.SeckillCloseException;
import com.p.exception.SeckillException;

import java.util.List;


public interface SeckillService {


    /**
     * 查询所有的秒杀记录
     *
     * @return the seckill list
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单条秒杀记录
     *
     * @param seckillId the seckill id
     * @return the by id
     */
    Seckill getById(long seckillId);

    /**
     * 在秒杀看iaqi时输出秒杀接口的地址,否则输出系统时间和秒杀时间
     * @param seckillId
     * @return
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作,跑存储所有的异常
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     * @throws SeckillException
     * @throws RepeatKillException
     * @throws SeckillCloseException
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException, RepeatKillException, SeckillCloseException;

}
