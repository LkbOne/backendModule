package com.example.hugh.life.api;

import com.example.hugh.life.commmon.ModelResult;
import com.example.hugh.life.controller.arg.translate.ListInfoArg;
import com.example.hugh.life.controller.arg.translate.TranslateArg;

public interface TranslateService {
    ModelResult translate(TranslateArg arg);
    ModelResult listInfo(ListInfoArg arg);
}
