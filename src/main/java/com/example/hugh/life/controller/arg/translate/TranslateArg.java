package com.example.hugh.life.controller.arg.translate;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class TranslateArg implements Serializable {
    String content;
    Integer type;
    String userId;
    public boolean isWrongParams(){
        if(StringUtils.isEmpty(content)){
            return true;
        }
        return false;
    }
}
