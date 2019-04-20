package com.example.hugh.life.controller;

import com.example.hugh.life.api.UserService;
import com.example.hugh.life.commmon.ModelResult;
import com.example.hugh.life.commmon.SHErrorCode;
import com.example.hugh.life.controller.arg.user.AddUserArg;
import com.example.hugh.life.controller.arg.user.QueryUserByIdArg;
import com.example.hugh.life.controller.arg.user.GetVerificationCodeArg;
import com.example.hugh.life.controller.arg.user.LoginArg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;
    @RequestMapping(value = "add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult add(@RequestBody AddUserArg arg) {
        if(arg.isWrongParams()){
            log.warn("UserController.add params error arg:{}", arg);
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        return userService.addUser(arg);
    }
    @RequestMapping(value = "login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult login(@RequestBody LoginArg arg) {
        return userService.login(arg);
    }

    @RequestMapping(value = "queryUserById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult queryUserById(@RequestBody QueryUserByIdArg arg) {
        return userService.queryUserById(arg);
    }

    @RequestMapping(value = "getVerificationCode", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult getVerificationCode(@RequestBody GetVerificationCodeArg arg) {
        if(arg.isWrongParams()){
            log.warn("UserController.getVerificationCode arg:{}", arg);
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
//        emailManger.sendHtmlMail("893405674@qq.com", "html主题", "");

        return userService.getVerificationCode(arg);
    }
}
