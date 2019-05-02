package com.example.hugh.life.dao.api;

import com.example.hugh.life.dao.entity.KeyWordMiddleVisitContentEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyWordMiddleVisitContentDao {
    boolean add(KeyWordMiddleVisitContentEntity entity);
}
