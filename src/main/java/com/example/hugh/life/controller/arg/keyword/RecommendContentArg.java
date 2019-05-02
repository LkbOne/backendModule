package com.example.hugh.life.controller.arg.keyword;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class RecommendContentArg implements Serializable {
    String content;
    String uid;
    Integer type;  // 1 是关键字  2 是文本（需要分解关键字）
    public boolean isWrongParams(){
        if(StringUtils.isEmpty(content) || StringUtils.isEmpty(uid)){
            return true;
        }
        return false;
    }
}
