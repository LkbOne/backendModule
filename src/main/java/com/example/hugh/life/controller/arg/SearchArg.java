package com.example.hugh.life.controller.arg;

import com.example.hugh.life.commmon.enums.SearchMainTypeEnum;
import com.example.hugh.life.commmon.enums.SearchStationType;
import lombok.Data;

import java.io.Serializable;

@Data
public class SearchArg implements Serializable {

    /**
    * 搜索的文本
    * */
    String content;
    /**
    * 搜索的url类型
     * {@link SearchStationType}
    * */
    Integer type;

    /**
     * {@link SearchMainTypeEnum}
     * */

    Integer mainType;
    /**
     * {@link com.example.hugh.life.commmon.enums.SearchTraceType}
    * */
    Integer traceType;
    /**
     * 用户id
     * */
    String uid;

    String url;


}
