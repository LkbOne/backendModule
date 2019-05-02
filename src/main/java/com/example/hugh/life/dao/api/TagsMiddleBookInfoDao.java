package com.example.hugh.life.dao.api;

import com.example.hugh.life.dao.entity.TagsMiddleBookInfoEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface TagsMiddleBookInfoDao {
    boolean add(TagsMiddleBookInfoEntity entity);
}
