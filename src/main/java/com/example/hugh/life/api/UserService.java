package com.example.hugh.life.api;

import com.example.hugh.life.commmon.ModelResult;
import com.example.hugh.life.commmon.SHErrorCode;
import com.example.hugh.life.commmon.util.result.LoginResult;
import com.example.hugh.life.commmon.util.result.UserResult;
import com.example.hugh.life.controller.arg.user.*;

public interface UserService {
    ModelResult addUser(AddUserArg arg);
    ModelResult updateUser(UpdateUserArg arg);
    ModelResult<LoginResult> login(LoginArg arg);
    ModelResult getVerificationCode(GetVerificationCodeArg arg);
    void queryUserByAccount();
    ModelResult<UserResult> queryUserById(QueryUserByIdArg arg);
    void deleteUserById();
}
