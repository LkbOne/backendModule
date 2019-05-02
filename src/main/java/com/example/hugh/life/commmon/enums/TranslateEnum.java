package com.example.hugh.life.commmon.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@AllArgsConstructor
public enum  TranslateEnum {

    ENGLISH(1, "en"),
    CHINESE(2,"zh");
    Integer type;
    String language;
}
