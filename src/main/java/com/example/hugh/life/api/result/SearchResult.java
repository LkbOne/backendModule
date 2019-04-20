package com.example.hugh.life.api.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class SearchResult implements Serializable {

    String title;
    String url;
}

