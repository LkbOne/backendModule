package com.example.hugh.life.api.result;

import lombok.Data;

@Data
public class RecommendUsuallyResult {
    String id;
    Integer type;
    String coverUrl;
    String detailUrl;
    String peopleContent;
    String otherContent;
    String title;
    Float rate;
}
