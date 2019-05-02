package com.example.hugh.life.controller;

import com.example.hugh.life.api.KeyWordService;
import com.example.hugh.life.commmon.ModelResult;
import com.example.hugh.life.commmon.SHErrorCode;
import com.example.hugh.life.controller.arg.AddLocationArg;
import com.example.hugh.life.controller.arg.keyword.AddKeyWordArg;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/keyWord")
@Slf4j
@CrossOrigin
public class KeyWordController {

    @Autowired
    KeyWordService keyWordService;

    @RequestMapping(value = "add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult addLocation(@RequestBody AddKeyWordArg arg) {

        if(arg.isWrongParams()){
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        return keyWordService.add(arg);
    }

    @RequestMapping(value = "crawlerDBBookDetail", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult crawlerDBBookDetail(@RequestBody AddKeyWordArg arg) {

        if(arg.isWrongParams()){
        }
        keyWordService.crawlerDBBookDetail();
        return new ModelResult(SHErrorCode.SUCCESS);
    }
    @RequestMapping(value = "crawlerDBVideo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult crawlerDBVideo(@RequestBody AddKeyWordArg arg) {

        if(arg.isWrongParams()){
        }
        keyWordService.crawlerDBVideo();
        return new ModelResult(SHErrorCode.SUCCESS);
    }
    @RequestMapping(value = "crawlerDBVideoDetail", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult crawlerDBVideoDetail(@RequestBody AddKeyWordArg arg) {

        if(arg.isWrongParams()){
        }
        keyWordService.crawlerDBVideoDetail();
        return new ModelResult(SHErrorCode.SUCCESS);
    }
    @RequestMapping(value = "divideKeyWord", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult divideKeyWord(@RequestBody AddKeyWordArg arg) {

        if(arg.isWrongParams()){
        }
        keyWordService.divideKeyWord();
        return new ModelResult(SHErrorCode.SUCCESS);
    }
}
