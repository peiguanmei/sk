package com.p.service.impl;

import com.p.dao.SeckillDao;
import com.p.dao.SuccessKilledDao;
import com.p.dao.cache.RedisDao;
import com.p.dto.Exposer;
import com.p.dto.SeckillExecution;
import com.p.entity.Seckill;
import com.p.entity.SuccessKilled;
import com.p.enums.SeckillStaEnum;
import com.p.exception.RepeatKillException;
import com.p.exception.SeckillCloseException;
import com.p.exception.SeckillException;
import com.p.service.SeckillService;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@Service
public class SeckillServiceImpl implements SeckillService {

    private final String salt = "p";
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SuccessKilledDao successKilledDao;

    @Autowired
    private RedisDao redisDao;

    @Override
    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0, 4);
    }

    @Override
    public Seckill getById(long seckillId) {
        return seckillDao.selectByPrimaryKey(seckillId);
    }

    @Override
    public Exposer exportSeckillUrl(long seckillId) {
        //Seckill seckill = seckillDao.selectByPrimaryKey(seckillId);
        //缓存优化使用redis替代
        //1.访问redis
        Seckill seckill = redisDao.getSeckill(seckillId);

        //没有商品id
        if (seckill == null) {
            //return new Exposer(false, seckillId);
            //2.redis中没有,就去数据库中取
            seckill = seckillDao.selectByPrimaryKey(seckillId);
            //数据库中也没有,说明活动已经结束
            if (seckill == null) {
                return new Exposer(false, seckillId);
            } else {
                //3.将商品缓存到redis中
                redisDao.putSeckill(seckill);
            }
        }

        //如果秒杀未开启
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date nowTime = new Date();
        if (startTime.getTime() > nowTime.getTime() || endTime.getTime() < nowTime.getTime()) {
            return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }

        //秒杀开启,返回商品秒杀的id,用给接口加密的md5
        String md5 = getMD5(seckillId);
        return new Exposer(true, md5, seckillId);
    }

    private String getMD5(long seckillId) {
        String base = seckillId + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    /**
     * 秒杀是否成功,成功减库存,增加明细；失败,抛出异常,事务回滚
     */
    @Override
    @Transactional(rollbackFor = SeckillException.class)
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException {

        if (md5 == null || !md5.equals(getMD5(seckillId))) {
            throw new SeckillException("seckill data rewrite, wrong md5 ");
        }
        //执行秒杀逻辑,减库存+增加购买明细
        Date nowTime = new Date();

        try {
/*            //减库存
            int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
            if (updateCount <= 0) {
                //没有更新库存记录,说明秒杀结束
                throw new SeckillCloseException("seckill is closed");
            } else {
                //更新了库存记录,秒杀成功,并且增加明细
                int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
                //查看明细是否被重复插入,用户不能重复秒杀同一样商品
                if (insertCount <= 0) {
                    throw new RepeatKillException("seckill repeated");
                } else {
                    //秒杀成功
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExecution(seckillId, SeckillStaEnum.SUCCESS, successKilled);
                }
            }*/

            //优化后改为先插入购买明细,在减库存,减少update持有行级锁的时间
            int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
            //查看明细是否被重复插入,用户不能重复秒杀同一样商品
            if (insertCount <= 0) {
                throw new RepeatKillException("seckill repeated");
            } else {
                int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
                if (updateCount <= 0) {
                    //没有更新库存记录,说明秒杀结束
                    throw new SeckillCloseException("seckill is closed");
                } else {
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExecution(seckillId, SeckillStaEnum.SUCCESS, successKilled);
                }
            }
        } catch (SeckillCloseException e1) {
            throw e1;
        } catch (RepeatKillException e2) {
            throw e2;
        } catch (Exception e) {
            logger.error(e.getMessage());
            //编译期异常转化为运行期异常
            throw new SeckillException("seckill inner error :" + e.getMessage());
        }
    }
}
