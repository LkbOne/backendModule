package com.example.hugh.life.commmon.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SearchMainTypeEnum {
    SEARCH(1),
    BOOK(2),
    VIDEO(3),
    MUSIC(4);
    Integer type;
}
