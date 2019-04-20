package com.example.hugh.life.controller.arg;

import com.google.common.base.Strings;
import lombok.Data;

import java.io.Serializable;

@Data
public class GetComponentArg implements Serializable {
    String uid;
    Integer type;

    public boolean isWrongParams(){
        if(Strings.isNullOrEmpty(uid)){
            return true;
        }
        return false;
    }
}
