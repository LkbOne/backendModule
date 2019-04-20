package com.example.hugh.life.controller.arg;

import com.google.common.base.Strings;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

@Data
public class WarmClockArg implements Serializable {
    String uid;
    Date time;
    Integer operator;  // 0 是添加， 1 是修改, 2 删除
    String previousClockId;
    public boolean isWrongParams(){
//        if(StringUtils.isEmpty(uid)){
//            return true;
//        }
        return false;
    }
}
