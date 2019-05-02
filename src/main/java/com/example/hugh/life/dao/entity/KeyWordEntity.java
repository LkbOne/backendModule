package com.example.hugh.life.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class KeyWordEntity {
    String id;
    String keyWord;
    String combtokenType;
    String tokenType;
    Date createTime;
}
