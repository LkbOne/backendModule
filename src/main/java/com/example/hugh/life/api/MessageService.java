package com.example.hugh.life.api;

import com.example.hugh.life.api.result.MessageResult;
import com.example.hugh.life.commmon.ModelResult;
import com.example.hugh.life.commmon.PageResult;
import com.example.hugh.life.controller.arg.SendMessageArg;

public interface MessageService {
    ModelResult add(SendMessageArg arg);

    ModelResult<PageResult<MessageResult>> list();
}
