package com.example.hugh.life.service.impl;

import com.example.hugh.life.api.BuryService;
import com.example.hugh.life.commmon.ModelResult;
import com.example.hugh.life.commmon.SHErrorCode;
import com.example.hugh.life.commmon.util.BeanUtil;
import com.example.hugh.life.commmon.util.UUIDUtil;
import com.example.hugh.life.controller.arg.CommonArg;
import com.example.hugh.life.controller.arg.GetComponentArg;
import com.example.hugh.life.dao.api.BuryDao;
import com.example.hugh.life.dao.entity.BuryEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BuryServiceImpl implements BuryService {
    @Autowired
    BuryDao buryDao;

    @Override
    public ModelResult common(CommonArg arg) {
        BuryEntity addingEntity = BeanUtil.copy(arg, BuryEntity.class);
        addingEntity.setId(UUIDUtil.getUUID());
        try{
            if(!buryDao.insert(addingEntity)){
                log.warn("BuryServiceImpl.common insert fail arg:{} addingEntity:{}", arg, addingEntity);
                return new ModelResult(SHErrorCode.ADD_BURY_FAIL);
            }
        }catch (RuntimeException e){
            log.warn("BuryServiceImpl.common insert fail arg:{} addingEntity:{} e:{}", arg, addingEntity, e);
            return new ModelResult(SHErrorCode.ADD_BURY_FAIL);
        }
        return new ModelResult(SHErrorCode.SUCCESS);
    }

    @Override
    public ModelResult getComponent(GetComponentArg arg) {

        return null;
    }
}
