package com.example.hugh.life.dao.api;

import com.example.hugh.life.dao.entity.UserMiddlePeopleInfoEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMiddlePeopleInfoDao {
    boolean add(UserMiddlePeopleInfoEntity entity);
}
