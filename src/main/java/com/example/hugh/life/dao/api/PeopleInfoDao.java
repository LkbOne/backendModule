package com.example.hugh.life.dao.api;

import com.example.hugh.life.dao.entity.PeopleInfoEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleInfoDao {
    boolean add(PeopleInfoEntity entity);
    PeopleInfoEntity queryByName(@Param("name") String name);

    List<String> getPeopleNameByIdentityAndMovie(@Param("movieInfoId") String movieInfoId,
                                                 @Param("identity") String identity,
                                                 @Param("topN") Integer topN);
    List<String> getPeopleNameByIdentityAndBook(@Param("bookInfoId") String bookInfoId,
                                                @Param("identity") String identity,
                                                @Param("topN") Integer topN);
}
