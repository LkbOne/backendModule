package com.example.hugh.life.controller;

import com.example.hugh.life.api.CurrentWeatherService;
import com.example.hugh.life.commmon.ModelResult;
import com.example.hugh.life.commmon.SHErrorCode;
import com.example.hugh.life.controller.arg.ListWeatherInfoArg;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
@Slf4j
@CrossOrigin
public class WeatherController {
    @Autowired
    CurrentWeatherService currentWeatherService;

    @RequestMapping(value = "listInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult listWeatherInfo(@RequestBody ListWeatherInfoArg arg) {
        if(arg.isWrongParams()){
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        return currentWeatherService.listWeatherInfo(arg);
    }
}
