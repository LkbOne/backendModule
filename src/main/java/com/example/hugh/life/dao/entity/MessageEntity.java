package com.example.hugh.life.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class MessageEntity {
    String id;
    String message;
    String uid;
    Date createTime;
    Date lastModifyTime;
}
