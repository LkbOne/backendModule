package com.example.hugh.life.dao.api;

import com.example.hugh.life.dao.dto.TranslateInfoDto;
import com.example.hugh.life.dao.entity.TranslateInfoEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TranslateInfoDao {

    boolean add(TranslateInfoEntity entity);
    boolean update(TranslateInfoEntity entity);
    List<TranslateInfoDto> listInfo(@Param("userId") String userId, @Param("limit") Integer limit);
}
