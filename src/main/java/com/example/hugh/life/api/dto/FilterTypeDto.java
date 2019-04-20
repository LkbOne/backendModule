package com.example.hugh.life.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FilterTypeDto implements Serializable {
    String type;
    Integer count = 0;
    List<Url> urlList;

    @Data
    @AllArgsConstructor
    public static class Url implements Serializable{
        String url;
        Integer count = 0;
    }
}
