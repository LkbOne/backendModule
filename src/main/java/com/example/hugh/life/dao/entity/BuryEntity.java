package com.example.hugh.life.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class BuryEntity {
    String id;
    String uid;
    Integer type;
    Integer subType;
    Date createTime;
    Date lastModifyTime;
}
