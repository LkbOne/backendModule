package com.example.hugh.life.dao.api;

import com.example.hugh.life.dao.entity.UserMiddleBookInfoEntity;
import com.example.hugh.life.dao.entity.UserMiddleMovieInfoEntity;
import com.example.hugh.life.dao.entity.UserMiddlePeopleInfoEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMiddleMovieInfoDao {
    boolean add(UserMiddleMovieInfoEntity entity);

    UserMiddleMovieInfoEntity queryByUserIdAndMovieInfoId(@Param("userId") String userId,
                                                          @Param("movieInfoId") String movieInfo);
    boolean update(UserMiddleMovieInfoEntity entity);
}
