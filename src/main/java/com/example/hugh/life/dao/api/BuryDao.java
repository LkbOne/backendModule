package com.example.hugh.life.dao.api;

import com.example.hugh.life.dao.entity.BuryEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface BuryDao {
    boolean insert(BuryEntity buryEntity);
}
