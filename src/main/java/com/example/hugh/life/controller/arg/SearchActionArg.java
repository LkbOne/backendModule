package com.example.hugh.life.controller.arg;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class SearchActionArg implements Serializable {
    String userId;
    String content;
    Integer type;

    public boolean isWrongParams(){
        if(StringUtils.isEmpty(userId) || StringUtils.isEmpty(content)){
            return true;
        }
        return false;
    }
}
