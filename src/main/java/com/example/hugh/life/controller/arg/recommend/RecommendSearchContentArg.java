package com.example.hugh.life.controller.arg.recommend;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class RecommendSearchContentArg implements Serializable {
    String userId;
    String content;
    Integer type;

    public boolean isWrongParams(){
        if(StringUtils.isEmpty(userId)){
            return true;
        }
        return false;
    }
}
