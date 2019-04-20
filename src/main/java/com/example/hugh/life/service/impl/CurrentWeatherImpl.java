package com.example.hugh.life.service.impl;

import com.example.hugh.life.api.CurrentWeatherService;
import com.example.hugh.life.api.dto.ListInfoDto;
import com.example.hugh.life.api.result.ListWeatherInfoResult;
import com.example.hugh.life.commmon.ModelResult;
import com.example.hugh.life.commmon.SHErrorCode;
import com.example.hugh.life.commmon.util.BeanUtil;
import com.example.hugh.life.commmon.util.UUIDUtil;
import com.example.hugh.life.controller.arg.ListWeatherInfoArg;
import com.example.hugh.life.dao.api.CurrentWeatherDao;
import com.example.hugh.life.dao.api.LocationDao;
import com.example.hugh.life.dao.entity.CurrentWeatherEntity;
import com.example.hugh.life.dao.entity.LocationEntity;
import com.example.hugh.life.service.manager.HeWeatherManager;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrentWeatherImpl implements CurrentWeatherService {
    @Autowired
    CurrentWeatherDao currentWeatherDao;

    @Autowired
    LocationDao locationDao;

    @Autowired
    HeWeatherManager heWeatherManager;

    //    @Scheduled(cron = "0/2 * * * * *")
//    @Scheduled(cron = "* * * 0/8 * ?")
    @Override
    public void queryAllUserCurrentWeather() {
        List<String> uidList = locationDao.queryAllUid();
        for (String uid : uidList) {
            List<LocationEntity> locationList = locationDao.queryLocationByUidLimit(uid, 1);
            if (CollectionUtils.isNotEmpty(locationList)) {
                LocationEntity locationEntity = locationList.get(0);
                String data = heWeatherManager.liveWeather(locationEntity.getLongitude(), locationEntity.getLatitude());
                if (StringUtils.isNotBlank(data)) {
                    CurrentWeatherEntity weatherEntity = new CurrentWeatherEntity();
                    weatherEntity.setWeather(data);
                    weatherEntity.setId(UUIDUtil.getUUID());
                    weatherEntity.setLid(locationEntity.getId());
                    currentWeatherDao.add(weatherEntity);
                }
            }
        }
    }

    @Override
    public ModelResult listWeatherInfo(ListWeatherInfoArg arg) {
        List<ListInfoDto> infoList = currentWeatherDao.listInfo(arg.getUid());
        List<ListWeatherInfoResult> resultList = Lists.newArrayList();
        int i = 0;
        for (ListInfoDto info : infoList) {
            if (i > 100) {
                break;
            }
            ListWeatherInfoResult result = BeanUtil.copy(info, ListWeatherInfoResult.class);
            result.setTmp(Double.valueOf(info.getTmp().substring(1, info.getTmp().length() - 1 )));
            resultList.add(result);
            i++;
        }
        return new ModelResult<>(SHErrorCode.SUCCESS, resultList);
    }
    public static void main(String[] args){
    }
}
