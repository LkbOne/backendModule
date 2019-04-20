package com.example.hugh.life.controller;

import com.example.hugh.life.api.result.SumDaySearchActionResult;
import com.example.hugh.life.api.result.SumSearchActionResult;
import com.example.hugh.life.api.result.UrlCountRankResult;
import com.example.hugh.life.commmon.ModelResult;
import com.example.hugh.life.api.SearchInfoService;
import com.example.hugh.life.commmon.SHErrorCode;
import com.example.hugh.life.controller.arg.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
@Slf4j
@CrossOrigin
public class SearchInfoController {

    @Autowired
    SearchInfoService searchService;

    @RequestMapping(value = "addSearchInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult search(@RequestBody SearchArg arg) {
        arg.setUid("0a6a4fac0f2845708c5bfc1be8a25b7b");
        return searchService.add(arg);
    }

    @RequestMapping(value = "crawlerHobby", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult crawler(@RequestBody CrawlerArg arg) {
        return searchService.crawler(arg);
    }
    @RequestMapping(value = "sumSearchAction", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult<SumSearchActionResult> sumSearchAction(@RequestBody SumSearchActionArg arg) {

        if(arg.isWrongParams()){
            log.warn("SearchInfoController.sumSearchAction arg:{}", arg);
            return new ModelResult(SHErrorCode.PARAMS_ERROR, "0a6a4fac0f2845708c5bfc1be8a25b7b");
        }
        arg.setUid("0a6a4fac0f2845708c5bfc1be8a25b7b");
        return searchService.sumSearchAction(arg);
    }

    @RequestMapping(value = "listSumSearchAction", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult<List<SumDaySearchActionResult>> listSumSearchAction(@RequestBody ListSumSearchActionArg arg) {
        if(arg.isWrongParams()){
            log.warn("SearchInfoController.sumSearchAction arg:{}", arg);
            return new ModelResult<>(SHErrorCode.PARAMS_ERROR);
        }
        return searchService.listSumSearchAction(arg);
    }

    @RequestMapping(value = "addBatchSearchInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult addBatchSearchInfo(@RequestBody AddBatchSearchInfoArg arg) {
       return searchService.addBatchSearchInfo(arg);
    }

    @RequestMapping(value = "statisticSearchInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult<List<UrlCountRankResult>> statisticSearchIno(@RequestBody SearchInfoStatisticArg arg) {
        arg.setUid("0a6a4fac0f2845708c5bfc1be8a25b7b");
        return searchService.searchInfoStatistic(arg);
    }
    @RequestMapping(value = "recommendProgram", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult recommendProgram(@RequestBody SearchInfoStatisticArg arg) {
        arg.setUid("0a6a4fac0f2845708c5bfc1be8a25b7b");
        return searchService.recommendProgram(arg);
    }
}
