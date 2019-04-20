package com.example.hugh.life.service.manager;

import com.example.hugh.life.commmon.model.RedisLoginEntity;
import com.example.hugh.life.commmon.util.RedisUtil;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service
public class RedisManager {
    private RedisUtil redisUtil = new RedisUtil();
    final static String PRE_TOKEN = "pre_token";
    final static Integer TOKEN_TIME = 2 * 60 * 60;
    Gson gs = new Gson();

    public void setToken2Redis(String key, String value){
        key = PRE_TOKEN + key;
        System.out.println("setToken2Redis key:" + key);
        System.out.println("setToken2Redis value:" + value);
        redisUtil.setex(key, value, TOKEN_TIME);
    }

    public RedisLoginEntity getLoginFromRedis(String key){
        key = PRE_TOKEN + key;
        String value = redisUtil.get(key, 0);
        if (value == null){
            return null;
        }
        System.out.println("getLoginFromRedis key:" +key);
        System.out.println("getLoginFromRedis value:" + value);
        RedisLoginEntity redisLoginEntity = gs.fromJson(value, RedisLoginEntity.class);
        return redisLoginEntity;
    }

    final static String PRE_VERIFICATION_CODE = "pre_verificationCode";
    final static Integer VERIFICATION_CODE_TIME = 5 * 60;

    public void setVerificationCode2Redis(String key, String value){
        key = PRE_VERIFICATION_CODE + key;
        redisUtil.setex(key, value, VERIFICATION_CODE_TIME);
    }

    public String getVerificationCodeFromRedis(String key){
        key = PRE_VERIFICATION_CODE + key;
        String value = redisUtil.get(key, 0);
        return value;
    }
}
