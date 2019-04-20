package com.example.hugh.life.controller.arg.user;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class AddUserArg implements Serializable {
    String account;
    String password;
    String name;
    String email;
    String description;
    String phone;

    public boolean isWrongParams(){
        if(StringUtils.isEmpty(account) || StringUtils.isEmpty(password) || StringUtils.isEmpty(name)){
            return true;
        }
        return false;
    }
}
