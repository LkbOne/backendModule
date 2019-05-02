package com.example.hugh.life.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ClockEntity {
    String id;
    String uid;
    String description;
    String name;
    Date time;
    Integer cycle;
    Integer status;
    Float duration;
    Date createTime;
    Date lastModifyTime;
}
