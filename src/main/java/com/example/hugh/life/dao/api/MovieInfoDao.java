package com.example.hugh.life.dao.api;

import com.example.hugh.life.dao.entity.BookInfoEntity;
import com.example.hugh.life.dao.entity.MovieInfoEntity;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieInfoDao {
    boolean add(MovieInfoEntity entity);
    int countByDoubanId(@Param("doubanId") String doubanId);

    boolean update(MovieInfoEntity entity);

    List<MovieInfoEntity> listByCrawlered(@Param("crawlered") boolean crawlerd);

    List<MovieInfoEntity> recommendByTypeInfo(@Param("typeInfo") String typeInfo,
                                              @Param("userId") String userId,
                                              @Param("topN") Integer topN);

    List<MovieInfoEntity> getMovieInfoExceptUserId(@Param("userId") String userId, @Param("topN") Integer topN);

    List<MovieInfoEntity> listByLikeTitle(@Param("content") String content, @Param("topN") Integer topN);

    List<MovieInfoEntity> getOtherPeopleUser(@Param("userId") String userId);

    List<String> getMovieInfoId(@Param("userId") String userId);


    String getTypesIdByUserId(@Param("userId")  String userId);

    List<MovieInfoEntity> getMovieInfoByTypes(@Param("typeId") String typeId);
}
