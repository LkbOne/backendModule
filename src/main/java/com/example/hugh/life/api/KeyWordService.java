package com.example.hugh.life.api;

import com.example.hugh.life.commmon.ModelResult;
import com.example.hugh.life.controller.arg.keyword.AddKeyWordArg;
import com.example.hugh.life.controller.arg.keyword.DeleteKeyWordArg;
import com.example.hugh.life.controller.arg.keyword.RecommendContentArg;
import com.example.hugh.life.controller.arg.keyword.UpdateKeyWordArg;

public interface KeyWordService  {
    ModelResult add(AddKeyWordArg arg);
    ModelResult update(UpdateKeyWordArg arg);
    ModelResult delete(DeleteKeyWordArg arg);
    ModelResult recommendContent(RecommendContentArg arg);

    void divideKeyWord();
    void crawlerDBBookDetail();
    void crawlerDBVideoDetail();
    void crawlerDBVideo();
}
