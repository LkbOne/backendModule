package com.example.hugh.life.controller;

import com.example.hugh.life.api.BuryService;
import com.example.hugh.life.commmon.ModelResult;
import com.example.hugh.life.commmon.SHErrorCode;
import com.example.hugh.life.controller.arg.CommonArg;
import com.example.hugh.life.controller.arg.GetComponentArg;
import com.example.hugh.life.controller.arg.SendMessageArg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bury")
@Slf4j
@CrossOrigin
public class BuryController {

    @Autowired
    BuryService buryService;

    /*
    * 普通的操作埋点
    * */
    @RequestMapping(value = "common", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult common(@RequestBody CommonArg arg) {
        if(arg.isWrongParams()){
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        return buryService.common(arg);
    }


    @RequestMapping(value = "getComponent", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult getComponent(@RequestBody GetComponentArg arg) {
        if(arg.isWrongParams()){
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        return buryService.getComponent(arg);
    }

}
