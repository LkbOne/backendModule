package com.example.hugh.life.commmon.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SearchTraceType {

    ORIGIN(0),
    CHILD(1);

    /**
    * {@link com.example.hugh.life.commmon.enums.SearchStationType}
    * */
    Integer type;
}
