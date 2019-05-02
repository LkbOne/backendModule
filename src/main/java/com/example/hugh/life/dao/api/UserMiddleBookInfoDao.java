package com.example.hugh.life.dao.api;

import com.example.hugh.life.dao.entity.UserMiddleBookInfoEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMiddleBookInfoDao {
    boolean add(UserMiddleBookInfoEntity entity);

    UserMiddleBookInfoEntity queryByUserIdAndBookInfoId(@Param("userId") String userId,
                                                        @Param("bookInfoId") String bookInfoId);
    boolean update(UserMiddleBookInfoEntity entity);
}
