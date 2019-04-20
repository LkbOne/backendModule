package com.example.hugh.life.commmon.util.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserResult implements Serializable {

    String name;
    String phone;
    String id;
    String email;
    String description;
    String account;
}
