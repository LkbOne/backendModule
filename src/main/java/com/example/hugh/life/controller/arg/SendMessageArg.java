package com.example.hugh.life.controller.arg;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class SendMessageArg implements Serializable {
    String message;

    public boolean isWrongParams(){
        if(StringUtils.isEmpty(message)){
            return true;
        }
        return false;
    }
}
