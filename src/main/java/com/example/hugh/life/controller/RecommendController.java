package com.example.hugh.life.controller;

import com.example.hugh.life.api.RecommendService;
import com.example.hugh.life.commmon.ModelResult;
import com.example.hugh.life.commmon.SHErrorCode;
import com.example.hugh.life.controller.arg.recommend.RecommendSearchContentArg;
import com.example.hugh.life.controller.arg.recommend.SimpleRecommendArg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("/recommend")
@Slf4j
@CrossOrigin
public class RecommendController {

    @Autowired
    RecommendService recommendService;


    @RequestMapping(value = "simpleRecommend", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult simpleRecommend(@RequestBody SimpleRecommendArg arg) {
        if(arg.isWrongParams()){
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        return recommendService.simpleRecommend(arg);
    }
    @RequestMapping(value = "recommendSearchContent", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult recommendSearchContent(@RequestBody RecommendSearchContentArg arg) {
        if(arg.isWrongParams()){
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        return recommendService.recommendSearchContent(arg);
    }
}
