package com.example.hugh.life.dao.api;

import com.example.hugh.life.dao.entity.PeopleInfoMiddleMovieInfoEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleInfoMiddleMovieInfoDao {
    boolean add(PeopleInfoMiddleMovieInfoEntity entity);
}
