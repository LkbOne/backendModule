package com.example.hugh.life.commmon.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DateIntervalEnum {
    YEAR(1),
    MONTH(2),
    WEEK(3),
    DAY(4);
    Integer type;
}
