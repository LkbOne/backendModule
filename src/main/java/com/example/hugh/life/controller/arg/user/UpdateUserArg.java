package com.example.hugh.life.controller.arg.user;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class UpdateUserArg implements Serializable {
    String id;
    String account;
    String password;
    String name;

    public boolean isWrongParams(){
        if(StringUtils.isEmpty(id)){
            return true;
        }
        return false;
    }
}
