package com.example.hugh.life.controller;

import com.example.hugh.life.api.ClockService;
import com.example.hugh.life.commmon.ModelResult;
import com.example.hugh.life.commmon.SHErrorCode;
import com.example.hugh.life.controller.arg.AddClockArg;
import com.example.hugh.life.controller.arg.ListClockArg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clock")
@Slf4j
@CrossOrigin
public class ClockController {

    @Autowired
    ClockService clockService;

    @RequestMapping(value = "add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult addClock(@RequestBody AddClockArg arg) {
        if(arg.isWrongParams()){
            log.warn("ClockController.addClock arg:{}", arg);
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }

        return clockService.addClock(arg);
    }

    @RequestMapping(value = "list", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult listClock(@RequestBody ListClockArg arg) {
        if(arg.isWrongParams()){
            log.warn("ClockController.addClock arg:{}", arg);
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        return clockService.listClock(arg);
    }
}
