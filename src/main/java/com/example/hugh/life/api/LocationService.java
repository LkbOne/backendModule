package com.example.hugh.life.api;

import com.example.hugh.life.commmon.ModelResult;
import com.example.hugh.life.controller.arg.AddLocationArg;
import com.example.hugh.life.controller.arg.ListLocationArg;

public interface LocationService {
    ModelResult  addLocation(AddLocationArg arg);
    ModelResult listLocation(ListLocationArg arg);
}
