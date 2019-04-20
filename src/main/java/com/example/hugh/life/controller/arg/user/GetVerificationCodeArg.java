package com.example.hugh.life.controller.arg.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetVerificationCodeArg implements Serializable {
    String email;
    Integer type;  // 1 邮箱  2 短信

    // 细节做验证邮箱的代码
    public boolean isWrongParams(){
        return false;
    }
}
