package com.example.hugh.life.dao.api;

import com.example.hugh.life.controller.arg.AddBatchSearchInfoArg;
import com.example.hugh.life.dao.entity.SingerQQBaseInfoEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SingerQQBaseInfoDao {
    boolean add(SingerQQBaseInfoEntity entity);
    boolean batchAdd(@Param("entities") Collection<SingerQQBaseInfoEntity> entities);
}
