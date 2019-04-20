package com.example.hugh.life.controller.arg;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

@Data
public class AddClockArg implements Serializable {

    String uid;
    Date time;
    String name;
    String desc;
    Integer status;  // 0 为开  1 为关  2为删除
    Integer cycle;  // 0 每天 1 每周 2 每月
    public boolean isWrongParams(){
        if(StringUtils.isEmpty(uid)){
            return true;
        }
        return false;
    }
}
