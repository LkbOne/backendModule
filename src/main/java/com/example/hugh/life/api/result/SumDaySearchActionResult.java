package com.example.hugh.life.api.result;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SumDaySearchActionResult implements Serializable {
    Integer book;
    Integer video;
    Integer music;
    Integer other;
    Date date;
}
