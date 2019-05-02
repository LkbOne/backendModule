package com.example.hugh.life.dao.api;

import com.example.hugh.life.dao.entity.VisitContentEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitContentDao {

    boolean add(VisitContentEntity entity);

    VisitContentEntity queryByContent(@Param("content") String content);

    List<String> queryTopNLikeContentByContent(@Param("content") String content, @Param("topN") Integer topN);
}
