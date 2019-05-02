package com.example.hugh.life.dao.api;

import com.example.hugh.life.dao.entity.PeopleInfoMiddleBookInfoEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleInfoMiddleBookInfoDao {
    boolean add(PeopleInfoMiddleBookInfoEntity entity);
}
