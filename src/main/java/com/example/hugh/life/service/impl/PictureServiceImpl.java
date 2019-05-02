package com.example.hugh.life.service.impl;

import com.example.hugh.life.api.PictureService;
import com.example.hugh.life.api.result.FPathResult;
import com.example.hugh.life.api.result.TPathResult;
import com.example.hugh.life.commmon.ModelResult;
import com.example.hugh.life.commmon.SHErrorCode;
import com.example.hugh.life.commmon.enums.FileTypeEnum;
import com.example.hugh.life.commmon.util.UUIDUtil;
import com.example.hugh.life.controller.arg.DownLoadArg;
import com.example.hugh.life.controller.arg.GetFPathArg;
import com.example.hugh.life.controller.arg.GetTPathArg;
import com.example.hugh.life.service.manager.QiNiuManager;
import com.google.common.io.ByteStreams;
import com.google.common.io.CharStreams;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

@Slf4j
@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    QiNiuManager qiNiuManager;

    @Override
    public ModelResult<TPathResult> getTPath(GetTPathArg arg) {
        String tPath = null;
        if (arg.getType().equals(FileTypeEnum.picture.getType())) {
            tPath = qiNiuManager.uploadByStream(arg.getFile(), null,"T");
            if (StringUtils.isEmpty(tPath)) {
                return new ModelResult<>(SHErrorCode.TRANSFER_TPATH_FAIL);
            }
        }
        if (arg.getType().equals(FileTypeEnum.file.getType())) {

        }
        TPathResult result = new TPathResult();
        result.setTPath(tPath);
        return new ModelResult<>(SHErrorCode.SUCCESS, result);
    }

    @Override
    public ModelResult<FPathResult> getFPath(GetFPathArg arg) {
        String tPath = null;
        String fPath = qiNiuManager.changeQiNiuFileName(arg.getTPath());
        if(StringUtils.isEmpty(fPath)){
            return new ModelResult<>(SHErrorCode.TRANSFER_FPATH_FAIL);
        }
        FPathResult result = new FPathResult();
        result.setFPath(tPath);
        return new ModelResult<>(SHErrorCode.SUCCESS, result);
    }

    @Override
    public byte[] downLoad(DownLoadArg arg){
        byte[] file = null;
        HttpEntity httpEntity = getHttpEntity(qiNiuManager.connectPathAndUrl(arg.getPath()));
        try {
            file = ByteStreams.toByteArray(httpEntity.getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static HttpEntity getHttpEntity(String url) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
//            httpGet.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
//            httpGet.addHeader("Content-Type", "text/html;charset=utf-8");
//            httpGet.addHeader("accept-language", "zh-CN,zh;q=0.9,zh-TW;q=0.8");
//            httpGet.addHeader("accept-encoding", "gzip, deflate, br");
            HttpResponse response = httpClient.execute(httpGet);
            return response.getEntity();
        } catch (Exception e) {
            log.error("Error: getHttpEntity! url:{}", url, e);
            return null;
        }finally {

        }
    }

}
