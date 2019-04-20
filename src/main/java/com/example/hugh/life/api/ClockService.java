package com.example.hugh.life.api;

import com.example.hugh.life.commmon.ModelResult;
import com.example.hugh.life.controller.arg.AddClockArg;
import com.example.hugh.life.controller.arg.ListClockArg;
import sun.awt.ModalExclude;

public interface ClockService {

    ModelResult addClock(AddClockArg arg);
    ModelResult listClock(ListClockArg arg);
}
