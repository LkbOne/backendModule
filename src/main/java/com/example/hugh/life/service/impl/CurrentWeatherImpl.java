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
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CurrentWeatherImpl implements CurrentWeatherService {
    @Autowired
    CurrentWeatherDao currentWeatherDao;

    @Autowired
    LocationDao locationDao;

    @Autowired
    HeWeatherManager heWeatherManager;

    //    @Scheduled(cron = "0/2 * * * * *")
//    @Scheduled(cron = "0 0/10 * * * ?")
    @Override
    public void queryAllUserCurrentWeather() {
        log.warn("CurrentWeatherImpl.queryAllUserCurrentWeather begin");
//        List<String> uidList = locationDao.queryAllUid();
//        for (String uid : uidList) {
            List<LocationEntity> locationList = locationDao.queryBywWeathered(0);
            for (LocationEntity locationEntity : locationList) {
                String data = heWeatherManager.liveWeather(locationEntity.getLongitude(), locationEntity.getLatitude());
                log.warn("data:{}", data);
                if (StringUtils.isNotBlank(data)) {
                    CurrentWeatherEntity weatherEntity = new CurrentWeatherEntity();
                    weatherEntity.setWeather(data);
                    weatherEntity.setId(UUIDUtil.getUUID());
                    weatherEntity.setLid(locationEntity.getId());
                    currentWeatherDao.add(weatherEntity);
                    locationEntity.setWeathered(1);
                    locationDao.update(locationEntity);
                }

            }
            log.warn("CurrentWeatherImpl.queryAllUserCurrentWeather end");
//        }
    }

    @Override
    public ModelResult listWeatherInfo(ListWeatherInfoArg arg) {
        List<LocationEntity> infoList = locationDao.queryByLimit(arg.getUid());
        List<ListWeatherInfoResult> resultList = Lists.newArrayList();
        for (LocationEntity locationEntity : infoList) {
            ListWeatherInfoResult result = new ListWeatherInfoResult();
            result.setLatitude(locationEntity.getLatitude());
            result.setLongitude(locationEntity.getLongitude());
            String tmp = currentWeatherDao.listInfoByLimit(locationEntity.getId());
            result.setTmp(Double.valueOf(tmp.substring(1, tmp.length() - 1 )));
            resultList.add(result);
        }
        return new ModelResult<>(SHErrorCode.SUCCESS, resultList);
    }
    public static void main(String[] args){
    }
}
