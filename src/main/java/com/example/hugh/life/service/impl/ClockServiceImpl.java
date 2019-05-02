package com.example.hugh.life.service.impl;

import com.example.hugh.life.api.ClockService;
import com.example.hugh.life.api.result.ListClockResult;
import com.example.hugh.life.commmon.ModelResult;
import com.example.hugh.life.commmon.SHErrorCode;
import com.example.hugh.life.commmon.util.BeanUtil;
import com.example.hugh.life.commmon.util.UUIDUtil;
import com.example.hugh.life.controller.arg.AddClockArg;
import com.example.hugh.life.controller.arg.ListClockArg;
import com.example.hugh.life.dao.api.ClockDao;
import com.example.hugh.life.dao.entity.ClockEntity;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClockServiceImpl implements ClockService {
    @Autowired
    ClockDao clockDao;

    @Override
    public ModelResult addClock(AddClockArg arg) {
        ClockEntity addClock = BeanUtil.copy(arg, ClockEntity.class);
        addClock.setId(UUIDUtil.getUUID());
        addClock.setDescription(arg.getDesc());
        if(!clockDao.add(addClock)){

            return new ModelResult(SHErrorCode.ADD_CLOCK_FAIL);
        }

        return new ModelResult(SHErrorCode.SUCCESS);
    }

    @Override
    public ModelResult<List<ListClockResult>> listClock(ListClockArg arg) {
        List<ClockEntity> clockList = clockDao.listClock(arg.getUid());
        List<ListClockResult> resultList = Lists.newArrayList();
        if(CollectionUtils.isNotEmpty(clockList)){
            for (ClockEntity clock : clockList) {
                ListClockResult result = BeanUtil.copy(clock, ListClockResult.class);
                result.setTime(clock.getTime().getTime());
                resultList.add(result);
            }
        }
        return new ModelResult<>(SHErrorCode.SUCCESS, resultList);
    }
}
