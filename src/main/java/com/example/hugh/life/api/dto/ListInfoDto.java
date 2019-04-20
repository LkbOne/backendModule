package com.example.hugh.life.api.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ListInfoDto implements Serializable {
    Double longitude;
    Double latitude;
    String tmp;
}
