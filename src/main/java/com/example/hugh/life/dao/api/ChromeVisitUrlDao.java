package com.example.hugh.life.dao.api;

import com.example.hugh.life.controller.arg.AddBatchSearchInfoArg;
import com.example.hugh.life.dao.entity.ChromeVisitUrlEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


@Repository
public interface ChromeVisitUrlDao {
    boolean add(ChromeVisitUrlEntity entity);
    void batchAdd(@Param("uid") String uid, @Param("entities") Collection<AddBatchSearchInfoArg.SearchInfo> entities);
    List<ChromeVisitUrlEntity> listByUid(@Param("uid") String uid);

    List<ChromeVisitUrlEntity> listByLikeUrl(@Param("uid") String uid, @Param("part") String part);

}