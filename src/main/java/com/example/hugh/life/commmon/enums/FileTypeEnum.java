package com.example.hugh.life.commmon.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FileTypeEnum {
    picture(1),
    file(2);

    Integer type;
}
