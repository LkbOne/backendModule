package com.example.hugh.life.controller.arg.translate;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class ListInfoArg implements Serializable {
    String userId;
    int limit;
    public boolean isWrongParams(){
        if(StringUtils.isEmpty(userId) || limit < 1){
            return true;
        }
        return false;
    }
}
