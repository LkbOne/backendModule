package com.example.hugh.life.controller.arg;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class ListClockArg implements Serializable {
    String uid;

    public boolean isWrongParams(){
        if(StringUtils.isEmpty(uid)){
            return true;
        }
        return false;
    }
}
