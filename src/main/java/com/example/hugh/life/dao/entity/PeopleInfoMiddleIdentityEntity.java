package com.example.hugh.life.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class PeopleInfoMiddleIdentityEntity {
    String id;
    String peopleInfoId;
    String identityId;
    Date createTime;
}
