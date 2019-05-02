package com.example.hugh.life.controller.arg.keyword;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class AddKeyWordArg implements Serializable {
    String uid;
    String keyWord;


    public boolean isWrongParams(){
        if(StringUtils.isEmpty(uid) || StringUtils.isEmpty(keyWord)){
            return true;
        }
        return false;
    }

}
