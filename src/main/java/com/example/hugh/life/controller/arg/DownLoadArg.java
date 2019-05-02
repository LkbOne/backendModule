package com.example.hugh.life.controller.arg;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class DownLoadArg implements Serializable {
    String path;
    public boolean isWrongParams(){
        if(StringUtils.isEmpty(path) || (!path.startsWith("FE_") && !path.startsWith("TE"))){
            return true;
        }
        return false;
    }
}
