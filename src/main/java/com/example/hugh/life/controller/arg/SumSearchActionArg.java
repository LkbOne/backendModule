package com.example.hugh.life.controller.arg;

import com.google.common.base.Strings;
import lombok.Data;

import java.io.Serializable;

@Data
public class SumSearchActionArg implements Serializable {

    String uid;

    int recentDay;

    public boolean isWrongParams(){
        if(Strings.isNullOrEmpty(uid) || recentDay < 7){
            return true;
        }
        return false;
    }
}
