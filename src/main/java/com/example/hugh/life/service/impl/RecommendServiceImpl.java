package com.example.hugh.life.service.impl;

import com.example.hugh.life.api.RecommendService;
import com.example.hugh.life.api.result.RecommendUsuallyResult;
import com.example.hugh.life.commmon.ModelResult;
import com.example.hugh.life.commmon.SHErrorCode;
import com.example.hugh.life.controller.arg.recommend.RecommendSearchContentArg;
import com.example.hugh.life.controller.arg.recommend.SimpleRecommendArg;
import com.example.hugh.life.dao.api.*;
import com.example.hugh.life.dao.entity.BookInfoEntity;
import com.example.hugh.life.dao.entity.LocationEntity;
import com.example.hugh.life.dao.entity.MovieInfoEntity;
import com.example.hugh.life.service.manager.HeWeatherManager;
import com.example.hugh.life.service.manager.InfoManager;
import com.example.hugh.life.service.manager.RecommendManager;
import com.example.hugh.life.service.manager.TencentNLPManager;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    CurrentWeatherDao currentWeatherDao;

    @Autowired
    LocationDao locationDao;

    @Autowired
    BookInfoDao bookInfoDao;

    @Autowired
    HeWeatherManager heWeatherManager;

    @Autowired
    MovieInfoDao movieInfoDao;

    @Autowired
    InfoManager infoManager;

    @Autowired
    RecommendManager recommendManager;
    @Override
    public ModelResult simpleRecommend(SimpleRecommendArg arg) {
//        List<RecommendUsuallyResult> results = doSetLocation(arg);
        List<RecommendUsuallyResult> results = doSetLocation(arg);
        if(CollectionUtils.isEmpty(results)){
            //随便推荐
            List<MovieInfoEntity> movieList = movieInfoDao.getMovieInfoExceptUserId(arg.getUserId(), 3);
            List<BookInfoEntity> bookInfoList = bookInfoDao.getBookInfoExceptUserId(arg.getUserId(),3);
            results = recommendManager.doSetResult(movieList, bookInfoList);
        }
        return new ModelResult<>(SHErrorCode.SUCCESS, results);
    }

    @Autowired
    TencentNLPManager tencentNLPManager;

    @Autowired
    UserMiddleBookInfoDao userMiddleBookInfoDao;

    @Autowired
    UserMiddleMovieInfoDao userMiddleMovieInfoDao;


    @Override
    public ModelResult recommendSearchContent(RecommendSearchContentArg arg) {
        List<BookInfoEntity> bookInfoEntities = bookInfoDao.getOtherPeopleUser(arg.getUserId());
        List<MovieInfoEntity> movieInfoEntityList =  movieInfoDao.getOtherPeopleUser(arg.getUserId());
        List<RecommendUsuallyResult> results = recommendManager.doSetResult(movieInfoEntityList, bookInfoEntities);

        return new ModelResult<>(SHErrorCode.SUCCESS, results);
    }


    private List<RecommendUsuallyResult> doSetLocation(SimpleRecommendArg arg){
        List<RecommendUsuallyResult> results = Lists.newArrayList();
        //搞定了从天气，做推荐。
        List<LocationEntity> locationList = locationDao.queryLocationByUidLimit(arg.getUserId(), 1);
        List<MovieInfoEntity> movieList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(locationList)) {
            LocationEntity locationEntity = locationList.get(0);
            String data = heWeatherManager.liveWeather(locationEntity.getLongitude(), locationEntity.getLatitude());
            HeWeatherManager.CurrentWeather currentWeather = heWeatherManager.doInitCurrentResult(data);
            if(null != currentWeather){
                HeWeatherManager.CurrentWeather.HeWeather.Now now = currentWeather.getNow();
                String types = HeWeatherManager.WEATHERCODE_MAP_TYPES.get(now.getCond_code());
                movieList = movieInfoDao.recommendByTypeInfo(types, arg.getUserId(), 3);
            }
        }

        // 坐标

        // 随机推荐 （拼的方式。视频， 图书， 音乐）
        List<BookInfoEntity> bookInfoList = Lists.newArrayList();
        List<LocationEntity> locationEntityList = locationDao.queryLocationByUidLimit(arg.getUserId(), 3);
        if(CollectionUtils.isNotEmpty(locationEntityList)){
            double ldtitudeDiff = 0;
            double longitudeDiff = 0;
            for (LocationEntity locationEntity : locationEntityList) {
                ldtitudeDiff += locationEntity.getLatitude() * 100000;
                longitudeDiff += locationEntity.getLongitude() * 100000;
            }
            ldtitudeDiff =((ldtitudeDiff)/locationEntityList.size() - locationEntityList.get(locationEntityList.size() - 1).getLatitude()* 100000);
            longitudeDiff=((longitudeDiff)/locationEntityList.size() - locationEntityList.get(locationEntityList.size() - 1).getLongitude()* 100000);
            // 在家呆着了
            if(ldtitudeDiff < 1 && longitudeDiff < 1){
                bookInfoList = bookInfoDao.recommendByPagesInfo(arg.getUserId(), 100, 200,3);
            }
        }
        return recommendManager.doSetResult(movieList, bookInfoList);
    }

}
