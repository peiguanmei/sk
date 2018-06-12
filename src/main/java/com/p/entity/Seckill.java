package com.p.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "seckill")
public class Seckill implements Serializable {
    /**
     * 商品库存ID
     */
    @Id
    @Column(name = "seckill_id")
    @GeneratedValue(generator = "JDBC")
    private Long seckillId;

    /**
     * 商品名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 库存数量
     */
    @Column(name = "number")
    private Integer number;

    /**
     * 秒杀开始时间
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 秒杀结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取商品库存ID
     *
     * @return seckill_id - 商品库存ID
     */
    public Long getSeckillId() {
        return seckillId;
    }

    /**
     * 设置商品库存ID
     *
     * @param seckillId 商品库存ID
     */
    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }

    /**
     * 获取商品名称
     *
     * @return name - 商品名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置商品名称
     *
     * @param name 商品名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取库存数量
     *
     * @return number - 库存数量
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 设置库存数量
     *
     * @param number 库存数量
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * 获取秒杀开始时间
     *
     * @return start_time - 秒杀开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置秒杀开始时间
     *
     * @param startTime 秒杀开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取秒杀结束时间
     *
     * @return end_time - 秒杀结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置秒杀结束时间
     *
     * @param endTime 秒杀结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
        sb.append(", name=").append(name);
        sb.append(", number=").append(number);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}