package com.example.hugh.life.dao.api;

import com.example.hugh.life.dao.entity.IdentityEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentityDao {
    boolean add(IdentityEntity entity);
}
