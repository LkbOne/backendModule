package com.example.hugh.life.controller;

import com.example.hugh.life.api.LocationService;
import com.example.hugh.life.commmon.ModelResult;
import com.example.hugh.life.commmon.SHErrorCode;
import com.example.hugh.life.controller.arg.AddLocationArg;
import com.example.hugh.life.controller.arg.ListLocationArg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/location")
@Slf4j
@CrossOrigin
public class LocationController {

    @Autowired
    LocationService locationService;
    @RequestMapping(value = "add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult addLocation(@RequestBody AddLocationArg arg) {
        arg.setUid("0a6a4fac0f2845708c5bfc1be8a25b7b");
        if(arg.isWrongParams()){
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        return locationService.addLocation(arg);
    }

    @RequestMapping(value = "list", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult listLocation(@RequestBody ListLocationArg arg) {
        arg.setUid("0a6a4fac0f2845708c5bfc1be8a25b7b");
        if(arg.isWrongParams()){
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        return locationService.listLocation(arg);
    }
}
