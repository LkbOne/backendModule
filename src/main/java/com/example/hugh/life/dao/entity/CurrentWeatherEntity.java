package com.example.hugh.life.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class CurrentWeatherEntity {
    String id;
    String lid;
    String weather;
//    Date updateTime;
//    Integer cloud;
//    String condCode;
//    String condTxt;
//    Integer fl;
//    Integer hum;
//    Float pcpn;
//    Integer pres;
//    Integer tmp;
//    Integer vis;
//    Integer windDeg;
//    String windDir;
//    Integer windSc;
//    Integer windSpd;
    Date createTime;
}
