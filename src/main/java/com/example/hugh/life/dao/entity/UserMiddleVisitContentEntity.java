package com.example.hugh.life.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserMiddleVisitContentEntity {

    String id;
    String userId;
    String visitContentId;
    Integer count;
    Date createTime;
    Date lastModifyTime;
}
