package com.example.hugh.life.api;

import com.example.hugh.life.commmon.ModelResult;
import com.example.hugh.life.controller.arg.CommonArg;
import com.example.hugh.life.controller.arg.GetComponentArg;

public interface BuryService {
    ModelResult common(CommonArg arg);

    ModelResult getComponent(GetComponentArg arg);
}