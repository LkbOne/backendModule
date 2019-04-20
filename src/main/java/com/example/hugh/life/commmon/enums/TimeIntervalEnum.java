package com.example.hugh.life.commmon.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  TimeIntervalEnum {

    day(1),
    week(2),
    month(3),
    year(4),
    all(5);
    int interval;

}
