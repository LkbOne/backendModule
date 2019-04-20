package com.example.hugh.life.api.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class ListClockResult implements Serializable {
    String id;
    String name;
    String desc;
    Integer status;
    Integer cycle;
    long time;
}
