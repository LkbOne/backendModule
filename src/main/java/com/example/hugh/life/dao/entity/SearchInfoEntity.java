package com.example.hugh.life.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SearchInfoEntity {
    String id;
    String uid;
    String url;
    String content;
    Integer type;
    Date createTime;
    Date lastModifyTime;
    Integer traceType;
    Integer mainType;
}
