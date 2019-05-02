package com.example.hugh.life.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class PeopleInfoMiddleBookInfoEntity {
    String id;
    String peopleInfoId;
    String bookInfoId;
    Date createTime;
    Date lastModifyTime;
}
