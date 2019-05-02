package com.example.hugh.life.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class KeyWordMiddleVisitContentEntity {
    String id;
    String keyWordId;
    String visitContentId;
    Date createTime;
    Date lastModifyTime;
}
