package com.example.hugh.life.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserMiddleKeyWordEntity {

    String id;
    String userId;
    String keyWordId;
    Integer count;
    Date createTime;
    Date lastModifyTime;
}
