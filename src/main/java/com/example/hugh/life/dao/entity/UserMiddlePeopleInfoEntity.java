package com.example.hugh.life.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserMiddlePeopleInfoEntity {
    String id;
    String userId;
    String peopleInfoId;
    Integer count;
    Date createTime;
    Date lastModifyTime;
}
