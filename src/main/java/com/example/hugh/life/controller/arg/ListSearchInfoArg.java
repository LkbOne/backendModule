package com.example.hugh.life.controller.arg;

import com.example.hugh.life.commmon.enums.SearchMainTypeEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class ListSearchInfoArg implements Serializable {

    String uid;
    /**
     * {@link com.example.hugh.life.commmon.enums.SearchStationType}
    * */
    Integer type;
    /**
     * {@link SearchMainTypeEnum}
     * */
    Integer mainType;
}
