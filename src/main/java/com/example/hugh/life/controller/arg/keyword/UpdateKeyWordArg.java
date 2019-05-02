package com.example.hugh.life.controller.arg.keyword;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class UpdateKeyWordArg implements Serializable {
    String id;
    String keyWord;
    String uid;
    public boolean isWrongParams(){
        if(StringUtils.isEmpty(uid) || StringUtils.isEmpty(keyWord) ||
                StringUtils.isEmpty(id)){
            return true;
        }
        return false;
    }
}
