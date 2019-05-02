package com.example.hugh.life.dao.api;

import com.example.hugh.life.dao.entity.KeyWordEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyWordDao {
    boolean add(@Param("id") String id, @Param("keyWord") String keyWord,
                @Param("combtokenType") String combtokenType, @Param("tokenType") String tokenType);
    boolean update(@Param("id") String id, @Param("combtokenType") String combtokenType, @Param("tokenType") String tokenType);
    int countByKeyWord(@Param("keyWord") String keyWord);

    KeyWordEntity queryByKeyWord(@Param("keyWord") String keyWord);
    String getIdByKeyWord(@Param("keyWord") String keyWord);
}
