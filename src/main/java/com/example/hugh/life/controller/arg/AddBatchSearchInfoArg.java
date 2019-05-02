package com.example.hugh.life.controller.arg;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class AddBatchSearchInfoArg implements Serializable {
    String uid;
    List<SearchInfo> urls;
    AddBatchSearchInfoArg(){

    }

    @Data
    @AllArgsConstructor
    public static class SearchInfo implements Serializable{
//        SearchInfo(){
//
//        }
        Integer id;
        String url;
        String title;
        Integer visit_count;
        Integer typed_count;
        Float last_visit_time;
        Integer hidden;
    }
}
