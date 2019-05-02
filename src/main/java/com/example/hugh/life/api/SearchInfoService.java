package com.example.hugh.life.api;


import com.example.hugh.life.api.result.SumDaySearchActionResult;
import com.example.hugh.life.api.result.SumSearchActionResult;
import com.example.hugh.life.api.result.UrlCountRankResult;
import com.example.hugh.life.commmon.ModelResult;
import com.example.hugh.life.controller.arg.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("searchService")
public interface SearchInfoService {

    ModelResult add(SearchArg arg) ;
    ModelResult crawler(CrawlerArg arg);
    ModelResult<SumSearchActionResult> sumSearchAction(SumSearchActionArg arg);
    ModelResult<List<SumDaySearchActionResult>> listSumSearchAction(ListSumSearchActionArg arg);

    ModelResult addBatchSearchInfo(AddBatchSearchInfoArg arg);

    ModelResult<List<UrlCountRankResult>> searchInfoStatistic(SearchInfoStatisticArg arg);
    ModelResult recommendProgram(SearchInfoStatisticArg arg);
    ModelResult searchAction(SearchActionArg arg);
}
