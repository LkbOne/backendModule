package com.example.hugh.life.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class PeopleInfoMiddleMovieInfoEntity {
    String id;
    String movieInfoId;
    String peopleInfoId;
    Date createTime;
    Date lastModifyTime;
}
