package com.example.hugh.life.controller.arg;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetFPathArg implements Serializable {

    String tPath;

    public boolean isWrongParams(){
        if(null == tPath || !tPath.startsWith("TE")){
            return true;
        }
        return false;
    }
}
