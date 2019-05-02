package com.example.hugh.life.dao.api;

import com.example.hugh.life.dao.entity.PeopleInfoMiddleIdentityEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleInfoMiddleIdentityDao {
    boolean add(PeopleInfoMiddleIdentityEntity entity);
}
