package com.example.hugh.life.controller;

import com.example.hugh.life.api.DayActionService;
import com.example.hugh.life.commmon.ModelResult;
import com.example.hugh.life.commmon.SHErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dayAction")
@Slf4j
@CrossOrigin
public class DayActionController {
    @Autowired
    DayActionService dayActionService;

    @RequestMapping(value = "addWarmClock", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ModelResult addWarmClock() {
        return new ModelResult(SHErrorCode.SUCCESS);
    }
}
