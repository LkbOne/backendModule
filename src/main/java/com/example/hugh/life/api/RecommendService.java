package com.example.hugh.life.api;

import com.example.hugh.life.commmon.ModelResult;
import com.example.hugh.life.controller.arg.recommend.RecommendSearchContentArg;
import com.example.hugh.life.controller.arg.recommend.SimpleRecommendArg;

public interface RecommendService {
    ModelResult simpleRecommend(SimpleRecommendArg arg);

//    ModelResult likeRecommend(LikeRecommendArg arg){
//
//    }

    ModelResult recommendSearchContent(RecommendSearchContentArg arg);
}
