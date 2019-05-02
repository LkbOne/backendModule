package com.example.hugh.life.controller.arg.recommend;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class SimpleRecommendArg implements Serializable {
    String userId;

    public boolean isWrongParams(){
        if(StringUtils.isEmpty(userId)){
            return true;
        }
        return false;
    }
}
