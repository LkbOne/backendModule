package com.example.hugh.life.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class TagsMiddleBookInfoEntity {
    String id;
    String tagsId;
    String bookInfoId;
    Date createTime;
}
