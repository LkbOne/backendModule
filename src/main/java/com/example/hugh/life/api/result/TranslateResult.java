package com.example.hugh.life.api.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class TranslateResult implements Serializable {

    String translateContent;
    String to;
}
