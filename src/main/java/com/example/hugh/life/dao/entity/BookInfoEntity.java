package com.example.hugh.life.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class BookInfoEntity {
    String id;
    Float numRaters;
    Float rateAverage;
    String pubdate;
    String originTitle;
    String image;
    String binding;
    String catalog;
    Integer pages;
    String imagesSmall;
    String imagesLarge;
    String imageMedium;
    String alt;
    String doubanId;
    String publisher;
    String isbn10;
    String isbn13;
    String title;
    String url;
    String altTitle;
    String authorIntro;
    String summary;
    String price;
    Date createTime;
    Date lastModifyTime;
}
