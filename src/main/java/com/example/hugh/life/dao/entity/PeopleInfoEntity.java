package com.example.hugh.life.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class PeopleInfoEntity {
    String id;
    String name;
    String type;
    Date createTime;

}
