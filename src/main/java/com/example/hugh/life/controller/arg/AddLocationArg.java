package com.example.hugh.life.controller.arg;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class AddLocationArg implements Serializable {
    String uid;
    Double longitude;
    Double latitude;
    public boolean isWrongParams(){
        if(StringUtils.isEmpty(uid)){
            return true;
        }
        return false;
    }
}
