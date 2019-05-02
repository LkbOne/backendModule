package com.example.hugh.life.dao.entity;

import lombok.Data;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

@Data
public class ChromeVisitUrlEntity {
    Integer id;
    String uid;
    String title;
    String url;
    Integer visitCount;
    Integer typedCount;
    Integer hidden;
    Float lastVisitTime;
    Integer divided;
}
