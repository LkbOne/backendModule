package com.example.hugh.life.controller.arg.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class QueryUserByIdArg implements Serializable {
    String id;
}
