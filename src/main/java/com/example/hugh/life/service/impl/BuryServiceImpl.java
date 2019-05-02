package com.example.hugh.life.service.impl;

import com.example.hugh.life.api.BuryService;
import com.example.hugh.life.commmon.ModelResult;
import com.example.hugh.life.commmon.SHErrorCode;
import com.example.hugh.life.commmon.util.BeanUtil;
import com.example.hugh.life.commmon.util.UUIDUtil;
import com.example.hugh.life.controller.arg.CommonArg;
import com.example.hugh.life.controller.arg.GetComponentArg;
import com.example.hugh.life.dao.api.BuryDao;
import com.example.hugh.life.dao.api.UserMiddleBookInfoDao;
import com.example.hugh.life.dao.api.UserMiddleMovieInfoDao;
import com.example.hugh.life.dao.entity.BuryEntity;
import com.example.hugh.life.dao.entity.UserMiddleBookInfoEntity;
import com.example.hugh.life.dao.entity.UserMiddleMovieInfoEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BuryServiceImpl implements BuryService {
    @Autowired
    BuryDao buryDao;
    @Autowired
    UserMiddleBookInfoDao userMiddleBookInfoDao;
    @Autowired
    UserMiddleMovieInfoDao userMiddleMovieInfoDao;
    @Override
    public ModelResult common(CommonArg arg) {
//        BuryEntity addingEntity = BeanUtil.copy(arg, BuryEntity.class);
//        addingEntity.setId(UUIDUtil.getUUID());
//        try{
//            if(!buryDao.insert(addingEntity)){
//                log.warn("BuryServiceImpl.common insert fail arg:{} addingEntity:{}", arg, addingEntity);
//                return new ModelResult(SHErrorCode.ADD_BURY_FAIL);
//            }
//        }catch (RuntimeException e){
//            log.warn("BuryServiceImpl.common insert fail arg:{} addingEntity:{} e:{}", arg, addingEntity, e);
//            return new ModelResult(SHErrorCode.ADD_BURY_FAIL);
//        }

        if(arg.getType() == 2){
            UserMiddleMovieInfoEntity entity = userMiddleMovieInfoDao.queryByUserIdAndMovieInfoId(arg.getUid(), arg.getInfoId());
            if(null == entity){
                entity = new UserMiddleMovieInfoEntity();
                entity.setId(UUIDUtil.getUUID());
                entity.setCount(1);
                entity.setMovieInfoId(arg.getInfoId());
                entity.setUserId(arg.getUid());
                userMiddleMovieInfoDao.add(entity);
            }else {
                entity.setCount(entity.getCount() + 1);
                userMiddleMovieInfoDao.update(entity);
            }
        }else if(arg.getType() == 1){
            UserMiddleBookInfoEntity entity = userMiddleBookInfoDao.queryByUserIdAndBookInfoId(arg.getUid(), arg.getInfoId());
            if(null == entity){
                entity = new UserMiddleBookInfoEntity();
                entity.setId(UUIDUtil.getUUID());
                entity.setCount(1);
                entity.setBookInfoId(arg.getInfoId());
                entity.setUserId(arg.getUid());
                userMiddleBookInfoDao.add(entity);
            }else {
                entity.setCount(entity.getCount() + 1);
                userMiddleBookInfoDao.update(entity);
            }
        }else{

        }
        return new ModelResult(SHErrorCode.SUCCESS);
    }

    @Override
    public ModelResult getComponent(GetComponentArg arg) {

        return null;
    }
}
