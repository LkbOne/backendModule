package com.example.hugh.life.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class MovieInfoEntity {
    String id;
    String duration;
    Float rate;
    String doubanId;
    String region;
    String releaseYear;
    String url;
    String coverUrl;
    String subType;
    Date createTime;
    Date lastModifyTime;
    String title;
    Boolean crawlered;
}
