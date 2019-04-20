package com.example.hugh.life.service.impl;

import com.example.hugh.life.api.DayActionService;
import com.example.hugh.life.commmon.ModelResult;
import com.example.hugh.life.commmon.SHErrorCode;
import com.example.hugh.life.controller.arg.WarmClockArg;
import org.springframework.stereotype.Service;

@Service
public class DayActionServiceImpl implements DayActionService {
    @Override
    public ModelResult WarmClockAction(WarmClockArg arg) {
        return new ModelResult(SHErrorCode.SUCCESS);
    }
}
