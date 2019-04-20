package com.example.hugh.life.service.impl;

import com.example.hugh.life.api.UserService;
import com.example.hugh.life.api.result.GetVerificationCodeResult;
import com.example.hugh.life.commmon.ModelResult;
import com.example.hugh.life.commmon.SHErrorCode;
import com.example.hugh.life.commmon.util.BeanUtil;
import com.example.hugh.life.commmon.util.UUIDUtil;
import com.example.hugh.life.commmon.util.result.LoginResult;
import com.example.hugh.life.commmon.util.result.UserResult;
import com.example.hugh.life.controller.arg.user.*;
import com.example.hugh.life.dao.api.UserDao;
import com.example.hugh.life.dao.entity.UserEntity;
import com.example.hugh.life.service.manager.EmailManger;
import com.example.hugh.life.service.manager.RedisManager;
import com.example.hugh.life.service.manager.SMSManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    @Autowired
    RedisManager redisManager;
    @Autowired
    EmailManger emailManger;
    @Autowired
    SMSManager smsManager;

    @Override
    public ModelResult addUser(AddUserArg arg) {
        UserEntity existUser = userDao.queryUserByAccount(arg.getAccount());
        if(existUser != null){
            log.warn("UserServiceImpl.addUser arg:{} existUser:{}", arg, existUser);
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }

        UserEntity user = BeanUtil.copy(arg, UserEntity.class);
        user.setId(UUIDUtil.getUUID());
        if(!userDao.addUser(user)){
            log.warn("UserServiceImpl.addUser arg:{} user:{}", arg, user);
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        return new ModelResult(SHErrorCode.SUCCESS);
    }

    @Override
    public ModelResult updateUser(UpdateUserArg arg) {
        UserEntity existUser = userDao.queryUserById(arg.getId());
        if(null == existUser){
            log.warn("UserServiceImpl.updateUser this user not exist arg:{}", arg);
            return new ModelResult(SHErrorCode.NO_DATA);
        }
        existUser.setAccount(arg.getAccount());
        existUser.setName(arg.getName());
        existUser.setPassword(arg.getPassword());

        if(!userDao.updateUser(existUser)){
            log.warn("UserServiceImpl.updateUser update fail arg:{} existUser:{}", arg, existUser);
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        return new ModelResult(SHErrorCode.SUCCESS);
    }

    @Override
    public ModelResult<LoginResult> login(LoginArg arg) {
        UserEntity user;
        if(arg.getType() == 0){
            user = userDao.queryUserByAccountAndPassword(arg.getContent(), arg.getPassword());
            if(null == user){
                return new ModelResult<>(SHErrorCode.LOGIN_FAIL);
            }
        }else{
            String verificationCode= redisManager.getVerificationCodeFromRedis(arg.getContent());
            if(verificationCode.equals(arg.getPassword())) {
                if(arg.getType() == 1){
                    user = userDao.queryUserByEmail(arg.getContent());
                }else{
                    user = userDao.queryUserByPhone(arg.getContent());
                }
                if(null == user){
                    return new ModelResult<>(SHErrorCode.VERIFICATION_CODE_ERROR);
                }
            }else {
                return new ModelResult<>(SHErrorCode.VERIFICATION_CODE_ERROR);
            }
        }
        LoginResult result = new LoginResult();
        result.setId(user.getId());
        return new ModelResult<>(SHErrorCode.SUCCESS, result);
    }

    @Override
    public ModelResult getVerificationCode(GetVerificationCodeArg arg) {
        String verificationCode = String.valueOf(new Random().nextInt(899999) + 100000);
        if(arg.getType() == 2) {
            if(!smsManager.sendSMS(arg.getEmail(), verificationCode,3)){
                return new ModelResult<>(SHErrorCode.SMS_GENERATE_FAIL);
            }
        }else {
            if (!emailManger.sendHtmlMail(arg.getEmail(), "html主题", verificationCode)) {
                return new ModelResult<>(SHErrorCode.EMAIL_GENERATE_FAIL);
            }
        }
        try {
            redisManager.setVerificationCode2Redis(arg.getEmail(), verificationCode);
        }catch (RuntimeException e){
            log.warn("");
            return new ModelResult<>(SHErrorCode.EMAIL_GENERATE_FAIL);
        }
        return new ModelResult<>(SHErrorCode.SUCCESS);
    }

    @Override
    public void queryUserByAccount() {

    }

    @Override
    public ModelResult<UserResult> queryUserById(QueryUserByIdArg arg) {
        UserEntity user = userDao.queryUserById(arg.getId());
        if(null == user){
            return new ModelResult<>(SHErrorCode.NO_DATA);
        }
        UserResult result = BeanUtil.copy(user, UserResult.class);
        return new ModelResult<>(SHErrorCode.SUCCESS, result);
    }


    @Override
    public void deleteUserById() {

    }
}
