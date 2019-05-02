package com.example.hugh.life.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserMiddleMovieInfoEntity {
    String id;
    String userId;
    String movieInfoId;
    Integer count;
    Date createTime;
    Date lastModifyTime;
}
