package com.example.hugh.life.dao.api;

import com.example.hugh.life.dao.entity.ClockEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClockDao {
    boolean add(ClockEntity clockEntity);
    List<ClockEntity> listClock(@Param("uid") String uid);
}
