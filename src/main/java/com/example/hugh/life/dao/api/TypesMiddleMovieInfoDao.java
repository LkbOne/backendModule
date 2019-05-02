package com.example.hugh.life.dao.api;

import com.example.hugh.life.dao.entity.TypesMiddleMovieInfoEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface TypesMiddleMovieInfoDao {
    boolean add(TypesMiddleMovieInfoEntity entity);
}
