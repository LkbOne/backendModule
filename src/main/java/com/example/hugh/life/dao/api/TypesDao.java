package com.example.hugh.life.dao.api;

import com.example.hugh.life.dao.entity.TypesEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TypesDao {
    boolean add(TypesEntity entity);

    TypesEntity queryByTypeInfo(@Param("typesInfo") String typesInfo);
}
