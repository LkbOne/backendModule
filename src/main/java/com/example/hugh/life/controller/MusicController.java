package com.example.hugh.life.controller;

import com.example.hugh.life.api.MessageService;
import com.example.hugh.life.api.MusicService;
import com.example.hugh.life.commmon.ModelResult;
import com.example.hugh.life.commmon.SHErrorCode;
import com.example.hugh.life.controller.arg.SendMessageArg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/music")
@Slf4j
@CrossOrigin
public class MusicController {

    @Autowired
    MusicService musicService;

    @RequestMapping(value = "crawlerSinger", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult crawlerSinger(@RequestBody SendMessageArg arg) {
        System.out.println("arg:"+ arg.getMessage());
        return musicService.crawlerSinger();
    }
}
