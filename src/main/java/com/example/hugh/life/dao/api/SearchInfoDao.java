package com.example.hugh.life.dao.api;

import com.example.hugh.life.controller.arg.AddBatchSearchInfoArg;
import com.example.hugh.life.dao.entity.SearchInfoEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Repository
public interface SearchInfoDao {
    boolean addSearchInfo(SearchInfoEntity searchInfoEntity);
    SearchInfoEntity querySearchInfoById(@Param("id") String id);
    List<SearchInfoEntity> listSearchInfoByUid(@Param("uid") String uid, @Param("beginDate") Date beginTime, @Param("endTime")
    Date endTime);

    Integer countSearchInfoByUidAndAllType(@Param("uid") String uid, @Param("type") Integer type, @Param("mainType") Integer mainType,
            @Param("traceType") Integer traceType, @Param("beginDate") Date beginTime, @Param("endDate")
            Date endTime);
}
