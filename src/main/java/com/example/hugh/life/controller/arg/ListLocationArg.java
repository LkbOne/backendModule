package com.example.hugh.life.controller.arg;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class ListLocationArg implements Serializable {

    String uid;

    // 1 : day 2 week 3 month 4 year 5 all
    Integer interval;

    public boolean isWrongParams(){
        if(StringUtils.isEmpty(uid)){
            return true;
        }
        return false;
    }
}
