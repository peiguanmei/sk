package com.p.dao;

import com.p.entity.SuccessKilled;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SuccessKillDao {
    int deleteByPrimaryKey(@Param("seckillId") Long seckillId, @Param("userPhone") Long userPhone);

    int insert(SuccessKilled record);

    SuccessKilled selectByPrimaryKey(@Param("seckillId") Long seckillId, @Param("userPhone") Long userPhone);

    List<SuccessKilled> selectAll();

    int updateByPrimaryKey(SuccessKilled record);
}