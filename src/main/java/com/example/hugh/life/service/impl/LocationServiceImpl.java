package com.example.hugh.life.service.impl;

import com.example.hugh.life.api.LocationService;
import com.example.hugh.life.api.result.ListLocationResult;
import com.example.hugh.life.commmon.ModelResult;
import com.example.hugh.life.commmon.SHErrorCode;
import com.example.hugh.life.commmon.util.BeanUtil;
import com.example.hugh.life.commmon.util.DateUtil;
import com.example.hugh.life.commmon.util.UUIDUtil;
import com.example.hugh.life.controller.arg.AddLocationArg;
import com.example.hugh.life.controller.arg.ListLocationArg;
import com.example.hugh.life.dao.api.LocationDao;
import com.example.hugh.life.dao.entity.LocationEntity;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Slf4j
@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    LocationDao locationDao;

    @Override
    public ModelResult addLocation(AddLocationArg arg) {
        LocationEntity addLocation = BeanUtil.copy(arg, LocationEntity.class);
        addLocation.setId(UUIDUtil.getUUID());
        if(!locationDao.add(addLocation)){
            log.warn("LocationServiceImpl.addLocation arg:{} addLocation:{}", arg, addLocation);
            return new ModelResult(SHErrorCode.ADD_LOCATION_FAIL);
        }
        return new ModelResult(SHErrorCode.SUCCESS);
    }

    @Override
    public ModelResult<List<List<Double>>> listLocation(ListLocationArg arg) {
        Date endTime = new Date();
        Date beginTime = DateUtil.getDateByInterval(endTime, arg.getInterval());
        List<LocationEntity> locationList = locationDao.listLocationByUid(arg.getUid(), beginTime, endTime);
        List<List<Double>> resultList = Lists.newArrayList();
        for (LocationEntity locationEntity : locationList) {
            List<Double> result = Lists.newArrayList();
            result.add(locationEntity.getLongitude());
            result.add(locationEntity.getLatitude());
            resultList.add(result);
        }
        return new ModelResult<>(SHErrorCode.SUCCESS, resultList);
    }
}
