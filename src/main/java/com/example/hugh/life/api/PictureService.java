package com.example.hugh.life.api;

import com.example.hugh.life.api.result.FPathResult;
import com.example.hugh.life.api.result.TPathResult;
import com.example.hugh.life.commmon.ModelResult;
import com.example.hugh.life.controller.arg.DownLoadArg;
import com.example.hugh.life.controller.arg.GetFPathArg;
import com.example.hugh.life.controller.arg.GetTPathArg;

public interface PictureService {
    ModelResult<TPathResult> getTPath(GetTPathArg arg);
    ModelResult<FPathResult> getFPath(GetFPathArg arg);
    byte[] downLoad(DownLoadArg arg);
}
