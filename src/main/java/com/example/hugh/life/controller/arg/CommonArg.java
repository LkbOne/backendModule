package com.example.hugh.life.controller.arg;

import com.google.common.base.Strings;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class CommonArg implements Serializable {
    String uid;
    Integer type; // 1 视频  2 图书 3 音乐
    String infoId;
    public boolean isWrongParams(){
        if(Strings.isNullOrEmpty(uid) || StringUtils.isEmpty(infoId)){
            return true;
        }
        return false;
    }
}
