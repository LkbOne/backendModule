package com.example.hugh.life.dao.api;

import com.example.hugh.life.dao.entity.UserMiddleKeyWordEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMiddleKeyWordDao {

    boolean add(UserMiddleKeyWordEntity entity);
    boolean update(@Param("keyWordId") String keyWordId, @Param("userId") String userId);
}
