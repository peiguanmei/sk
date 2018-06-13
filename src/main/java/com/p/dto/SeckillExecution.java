package com.p.dto;

import com.p.entity.SuccessKilled;
import com.p.enums.SeckillStaEnum;

/**
 * 封装执行秒杀后的结果:是否秒杀成功
 */
public class SeckillExecution {
    private long seckillId;

    private int state;

    private String stateInfo;

    private SuccessKilled successKilled;

    /**
     * 秒杀成功返回所有信息
     * @param seckillId
     * @param state
     * @param stateInfo
     * @param successKilled
     */
    public SeckillExecution(long seckillId, SeckillStaEnum staEnum, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.state = staEnum.getState();
        this.stateInfo = staEnum.getInfo();
        this.successKilled = successKilled;
    }

    /**
     * 秒杀失败
     * @param seckillId
     * @param state
     * @param stateInfo
     */
    public SeckillExecution(long seckillId, SeckillStaEnum staEnum) {
        this.seckillId = seckillId;
        this.state = staEnum.getState();
        this.stateInfo = staEnum.getInfo();
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }

    @Override
    public String toString() {
        return "SeckillExecution{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successKilled=" + successKilled +
                '}';
    }
}
