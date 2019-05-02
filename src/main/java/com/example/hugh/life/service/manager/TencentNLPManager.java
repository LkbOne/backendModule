package com.example.hugh.life.service.manager;

import com.example.hugh.life.commmon.ModelResult;
import com.example.hugh.life.dao.entity.ClockEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.cvm.v20170312.CvmClient;
import com.tencentcloudapi.cvm.v20170312.models.DescribeZonesResponse;
import com.tencentcloudapi.sqlserver.v20180328.models.DescribeZonesRequest;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Slf4j
@Service
public class TencentNLPManager {
    Gson gson = new Gson();
    String url = "http://192.168.8.39:8000/getKeyWordByContent";

    public KeyWord getKeyWord(String content){
        String reponseString = decode(url, content);
        ModelResult<KeyWord> modelResult = gson.fromJson(reponseString,
                new TypeToken<ModelResult<KeyWord>>(){}.getType());
        if(modelResult.getErrCode() == 0){
            return modelResult.getData();
        }
        return null;
    }

    @Data
    class RequestBody {
        String content;
    }
    public HttpEntity postHttpEntity(String url, String content) {

        RequestBody bodyEntity = new RequestBody();
        bodyEntity.setContent(content);
        String body = gson.toJson(bodyEntity);

        StringEntity entity = new StringEntity(body, "utf-8");
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.setEntity(entity);
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpResponse response = httpClient.execute(httpPost);
            return response.getEntity();
        } catch (Exception e) {
            log.error("Error: getHttpEntity! url:{}", url, e);
            return null;
        }
    }

    public String decode(String url, String content) {
        StringBuilder reponseString = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            HttpEntity httpEntity = postHttpEntity(url, content);
            bufferedReader = new BufferedReader(new InputStreamReader(httpEntity.getContent(), "utf-8"), 8 * 1024);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                reponseString.append(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (null != bufferedReader) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return reponseString.toString();
        }
    }


    @Data
    public class KeyWord{
        List<Combtoken> combtokens;
        List<Token> tokens;

        @Data
        public class Combtoken{
            String cls;
            int pos;
            String wlen;
            String word;
        }
        @Data
        public class Token{
            int pos;
            String wlen;
            String word;
            String wtype;
            int wtype_pos;
        }
    }

    public static void main(String[] args){
        TencentNLPManager manager = new TencentNLPManager();
        KeyWord keyWord = manager.getKeyWord("腾讯文智自然语言处理NLP");

    }

}