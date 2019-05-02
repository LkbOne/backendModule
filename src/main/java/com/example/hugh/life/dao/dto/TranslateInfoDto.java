package com.example.hugh.life.dao.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TranslateInfoDto implements Serializable {
    String content;
    String translateContent;
    Date createTime;
}
