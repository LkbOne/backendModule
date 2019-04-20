package com.example.hugh.life.dao.api;

import com.example.hugh.life.dao.entity.LocationEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LocationDao {

    List<LocationEntity> listLocationByUid(@Param("uid") String uid,
                                           @Param("beginTime")Date beginTime,
                                           @Param("endTime")Date endTime);

    boolean add(LocationEntity locationEntity);

    List<LocationEntity> queryLocationByUidLimit(@Param("uid") String uid, @Param("limit") Integer limit);
    List<String> queryAllUid();
}
