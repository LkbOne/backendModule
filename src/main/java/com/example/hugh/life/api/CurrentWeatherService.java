package com.example.hugh.life.api;

import com.example.hugh.life.commmon.ModelResult;
import com.example.hugh.life.controller.arg.ListWeatherInfoArg;

public interface CurrentWeatherService {
    void queryAllUserCurrentWeather();
    ModelResult listWeatherInfo(ListWeatherInfoArg arg);
}
