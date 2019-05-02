package com.example.hugh.life.controller;


import com.example.hugh.life.api.TranslateService;
import com.example.hugh.life.commmon.ModelResult;
import com.example.hugh.life.commmon.SHErrorCode;
import com.example.hugh.life.controller.arg.translate.ListInfoArg;
import com.example.hugh.life.controller.arg.translate.TranslateArg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/translate")
@Slf4j
@CrossOrigin
public class TranslateController {

    @Autowired
    TranslateService translateService;

    @RequestMapping(value = "translate", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult translate(@RequestBody TranslateArg arg) {
        if(arg.isWrongParams()){
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        return translateService.translate(arg);
    }

    @RequestMapping(value = "listInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult listInfo(@RequestBody ListInfoArg arg) {
        if(arg.isWrongParams()){
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        return translateService.listInfo(arg);
    }

}
