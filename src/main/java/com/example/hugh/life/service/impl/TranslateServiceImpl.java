package com.example.hugh.life.service.impl;

import com.example.hugh.life.api.TranslateService;
import com.example.hugh.life.api.result.TranslateResult;
import com.example.hugh.life.commmon.ModelResult;
import com.example.hugh.life.commmon.SHErrorCode;
import com.example.hugh.life.commmon.enums.TranslateEnum;
import com.example.hugh.life.commmon.util.BeanUtil;
import com.example.hugh.life.commmon.util.UUIDUtil;
import com.example.hugh.life.controller.arg.translate.ListInfoArg;
import com.example.hugh.life.controller.arg.translate.TranslateArg;
import com.example.hugh.life.dao.api.TranslateInfoDao;
import com.example.hugh.life.dao.dto.TranslateInfoDto;
import com.example.hugh.life.dao.entity.TranslateInfoEntity;
import com.example.hugh.life.service.manager.BaiDuTranslateManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TranslateServiceImpl implements TranslateService {

    @Autowired
    BaiDuTranslateManager baiDuTranslateManager;

    @Autowired
    TranslateInfoDao translateInfoDao;
    @Override
    public ModelResult translate(TranslateArg arg) {
        String language = TranslateEnum.CHINESE.getType().equals(arg.getType())?
                TranslateEnum.CHINESE.getLanguage():TranslateEnum.ENGLISH.getLanguage();

        BaiDuTranslateManager.TranslateResult translateResult = baiDuTranslateManager.getTransResult(
                arg.getContent(), "auto", language);
        if(null == translateResult || CollectionUtils.isEmpty(translateResult.getTrans_result())){
            log.warn("TranslateServiceImpl.translate error arg:{} translateResult:{}",
                    arg, translateResult);
            return new ModelResult(SHErrorCode.TRANSLATE_FAIL);
        }
        TranslateInfoEntity entity = new TranslateInfoEntity();
        entity.setUserId(arg.getUserId());
        if(translateResult.getTrans_result().size() > 1){
            log.warn("TranslateServiceImpl.translate  more than two " +
                    "translateResult:{}", translateResult);
        }
        entity.setId(UUIDUtil.getUUID());
        entity.setUserId(arg.getUserId());
        entity.setTranslateContent(translateResult.getTrans_result().get(0).getDst());
        entity.setContent(translateResult.getTrans_result().get(0).getSrc());
        entity.setTo(translateResult.getTo());
        entity.setFrom(translateResult.getFrom());

        TranslateResult result = BeanUtil.copy(entity, TranslateResult.class);

        if(!translateInfoDao.add(entity)){
            return new ModelResult<>(SHErrorCode.ADD_MESSAGE_FAIL, result);
        }
        return new ModelResult<>(SHErrorCode.SUCCESS, result);
    }

    @Override
    public ModelResult listInfo(ListInfoArg arg) {

        List<TranslateInfoDto> result = translateInfoDao.listInfo(arg.getUserId(), arg.getLimit());
        return new ModelResult<>(SHErrorCode.SUCCESS, result);
    }
}
