package com.example.hugh.life.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserEntity {
    String id;
    String account;
    String password;
    String name;
    Date createTime;
    Date lastModifyTime;
    String email;
    String description;
    String phone;
}
