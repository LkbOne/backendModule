package com.example.hugh.life.api.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class SumSearchActionResult implements Serializable {

    Integer book;
    Integer video;
    Integer music;
    Integer other;
}
