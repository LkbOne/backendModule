package com.example.hugh.life.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserMiddleBookInfoEntity {
    String id;
    String userId;
    String bookInfoId;
    Integer count;
    Date createTime;
    Date lastModifyTime;
}
