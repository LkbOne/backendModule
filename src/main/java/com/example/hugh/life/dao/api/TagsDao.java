package com.example.hugh.life.dao.api;

import com.example.hugh.life.dao.entity.TagsEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TagsDao {
    boolean add(TagsEntity entity);

    TagsEntity queryByNameAndTitle(@Param("name") String name, @Param("title") String title);
}
