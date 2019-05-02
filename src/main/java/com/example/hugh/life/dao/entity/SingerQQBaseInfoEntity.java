package com.example.hugh.life.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SingerQQBaseInfoEntity {
    String id;
    String country;
    String singerId;
    String singerMid;
    String singerName;
    String singerPic;
    Date createTime;
    Date lastModifyTime;
}
