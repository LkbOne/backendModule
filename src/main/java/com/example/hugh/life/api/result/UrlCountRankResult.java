package com.example.hugh.life.api.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class UrlCountRankResult implements Serializable {
    String url;
    Integer count;
}
