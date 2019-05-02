package com.example.hugh.life.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class TranslateInfoEntity {
    String id;
    String userId;
    String content;
    String translateContent;
    String from;
    String to;
    Date createTime;
}
