package com.example.hugh.life.dao.api;

import com.example.hugh.life.dao.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    boolean addUser(UserEntity userEntity);
    boolean updateUser(UserEntity userEntity);
    UserEntity queryUserById(@Param("id") String id);
    UserEntity queryUserByAccount(@Param("account") String account);
    UserEntity queryUserByAccountAndPassword(@Param("account") String account, @Param("password") String password);
    UserEntity queryUserByEmail(@Param("email") String email);
    UserEntity queryUserByPhone(@Param("phone") String phone);
}
