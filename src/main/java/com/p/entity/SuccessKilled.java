package com.p.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "success_killed")
public class SuccessKilled {
    /**
     * 秒杀商品ID
     */
    @Id
    @Column(name = "seckill_id")
    @GeneratedValue(generator = "JDBC")
    private Long seckillId;

    /**
     * 用户手机号
     */
    @Id
    @Column(name = "user_phone")
    private Long userPhone;

    /**
     * 状态标识:-1:无效 0:成功 1:已付款 2:已发货
     */
    @Column(name = "state")
    private Byte state;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    //多对一，因为一件商品在库存中有很多数量，对应的购买明细也有很多。
    private Seckill seckill;

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    /**
     * 获取秒杀商品ID
     *
     * @return seckill_id - 秒杀商品ID
     */
    public Long getSeckillId() {
        return seckillId;
    }

    /**
     * 设置秒杀商品ID
     *
     * @param seckillId 秒杀商品ID
     */
    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }

    /**
     * 获取用户手机号
     *
     * @return user_phone - 用户手机号
     */
    public Long getUserPhone() {
        return userPhone;
    }

    /**
     * 设置用户手机号
     *
     * @param userPhone 用户手机号
     */
    public void setUserPhone(Long userPhone) {
        this.userPhone = userPhone;
    }

    /**
     * 获取状态标识:-1:无效 0:成功 1:已付款 2:已发货
     *
     * @return state - 状态标识:-1:无效 0:成功 1:已付款 2:已发货
     */
    public Byte getState() {
        return state;
    }

    /**
     * 设置状态标识:-1:无效 0:成功 1:已付款 2:已发货
     *
     * @param state 状态标识:-1:无效 0:成功 1:已付款 2:已发货
     */
    public void setState(Byte state) {
        this.state = state;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", seckillId=").append(seckillId);
        sb.append(", userPhone=").append(userPhone);
        sb.append(", state=").append(state);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}