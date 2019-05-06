package com.example.hugh.life.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class LocationEntity {
    String id;
    String uid;
    Double longitude;
    Double latitude;
    Date createTime;
    Integer weathered;
}
