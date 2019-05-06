package com.example.hugh.life.dao.api;

import com.example.hugh.life.api.dto.ListInfoDto;
import com.example.hugh.life.dao.entity.CurrentWeatherEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrentWeatherDao {
    boolean add(CurrentWeatherEntity entity);
    List<CurrentWeatherEntity> listAndLimitEntity(@Param("lid") String lid, @Param("limit") Integer limit);

    List<ListInfoDto> listInfo(@Param("uid") String uid);

    String listInfoByLimit(@Param("lid") String lid);
}
