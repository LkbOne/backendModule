package com.example.hugh.life.dao.api;

import com.example.hugh.life.dao.entity.UserMiddleVisitContentEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMiddleVisitContentDao {

    boolean add(UserMiddleVisitContentEntity entity);
    boolean update(@Param("visitContentId") String visitContentId, @Param("userId") String userId);
}
