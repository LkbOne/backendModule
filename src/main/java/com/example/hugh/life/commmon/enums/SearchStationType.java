package com.example.hugh.life.commmon.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SearchStationType {

    BAI_DU(3),
    TX(4),
    DOU_BAN(5),
    JD(6),
    ALIBABA(7);
    Integer type;
}
