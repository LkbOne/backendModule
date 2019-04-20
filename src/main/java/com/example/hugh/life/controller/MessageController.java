package com.example.hugh.life.controller;

import com.example.hugh.life.api.MessageService;
import com.example.hugh.life.commmon.ModelResult;
import com.example.hugh.life.commmon.SHErrorCode;
import com.example.hugh.life.controller.arg.SendMessageArg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
@Slf4j
@CrossOrigin
public class MessageController {
    @Autowired
    MessageService messageService;

    @RequestMapping(value = "add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult sendMessage(@RequestBody SendMessageArg arg) {
        System.out.println("arg:"+ arg.getMessage());
        if(arg.isWrongParams()){
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        return messageService.add(arg);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ModelResult listMessages() {
        return messageService.list();
    }
}
