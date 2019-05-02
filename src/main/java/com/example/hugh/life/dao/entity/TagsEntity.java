package com.example.hugh.life.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class TagsEntity {
    String id;
    Integer count;
    String title;
    String name;
    Date createTime;
}
