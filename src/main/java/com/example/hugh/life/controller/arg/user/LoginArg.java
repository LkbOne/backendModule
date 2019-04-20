package com.example.hugh.life.controller.arg.user;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class LoginArg implements Serializable {
    String content;
    String password;
    Integer type; // 0 为普通登陆， 1 为邮箱登陆  2 短信登录
//    String email;
//    String verificationCode;
    public boolean isWrongParams(){
        if(StringUtils.isEmpty(content) || StringUtils.isEmpty(password)){
            return true;
        }
        return false;
    }
}
