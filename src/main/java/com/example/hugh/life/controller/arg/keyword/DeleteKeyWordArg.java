package com.example.hugh.life.controller.arg.keyword;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class DeleteKeyWordArg implements Serializable {
    String id;
    String uid;

    public boolean isWrongParams(){
        if(StringUtils.isEmpty(uid) || StringUtils.isEmpty(id)){
            return true;
        }
        return false;
    }
}
