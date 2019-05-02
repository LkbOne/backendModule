package com.example.hugh.life.service.impl;

import com.example.hugh.life.api.MusicService;
import com.example.hugh.life.commmon.ModelResult;
import com.example.hugh.life.commmon.SHErrorCode;
import com.example.hugh.life.commmon.util.BeanUtil;
import com.example.hugh.life.commmon.util.UUIDUtil;
import com.example.hugh.life.dao.api.SingerQQBaseInfoDao;
import com.example.hugh.life.dao.entity.SingerQQBaseInfoEntity;
import com.example.hugh.life.service.manager.QQMusicManager;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class MusicServiceImpl implements MusicService {

    @Autowired
    SingerQQBaseInfoDao singerQQBaseInfoDao;

    @Autowired
    QQMusicManager qqMusicManager;

    @Override
    public ModelResult crawlerSinger() {
        Date begin = new Date();
        log.warn("begin:{}", begin);
        for(int i = 1; i < 300; i++){
            List<QQMusicManager.QQSingerListResult.SingerList.Datas.Singerlist> singerlists =
                    qqMusicManager.signerList((i - 1) * 80, i);
            if(CollectionUtils.isNotEmpty(singerlists)){
                List<SingerQQBaseInfoEntity> entities = Lists.newArrayList();
                for (QQMusicManager.QQSingerListResult.SingerList.Datas.Singerlist singerlist : singerlists) {
                    SingerQQBaseInfoEntity entity = new SingerQQBaseInfoEntity();
                    entity.setCountry(singerlist.getCountry());
                    entity.setId(UUIDUtil.getUUID());
                    entity.setSingerId(singerlist.getSinger_id());
                    entity.setSingerMid(singerlist.getSinger_mid());
                    entity.setSingerName(singerlist.getSinger_name());
                    entity.setSingerPic(singerlist.getSinger_pic());
                    entities.add(entity);
                }
                singerQQBaseInfoDao.batchAdd(entities);
                log.warn("times:{}", i);
            }
        }
        Date end = new Date();
        log.warn("end:{}", end);
        log.warn("interval:{}", end.getTime() - begin.getTime());
        return new ModelResult(SHErrorCode.SUCCESS);
    }
}
