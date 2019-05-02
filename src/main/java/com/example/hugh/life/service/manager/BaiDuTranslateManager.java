package com.example.hugh.life.service.manager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BaiDuTranslateManager {
    private Gson gson = new Gson();
    private static final String TRANS_API_HOST = "http://api.fanyi.baidu.com/api/trans/vip/translate";

    private String appid = "20190426000291927";
    private String securityKey = "u61wX_Ppq6nA177__V9f";

//    public BaiDuTranslateManager(String appid, String securityKey) {
//        this.appid = appid;
//        this.securityKey = securityKey;
//    }

    public TranslateResult getTransResult(String query, String from, String to) {
        Map<String, String> params = buildParams(query, from, to);
        String baiduResult = BaiDuHttpGet.get(TRANS_API_HOST, params);
        return initResult(baiduResult);
    }

    private Map<String, String> buildParams(String query, String from, String to) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);

        params.put("appid", appid);

        // 随机数
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);

        // 签名
        String src = appid + query + salt + securityKey; // 加密前的原文
        params.put("sign", BaiDuMD5.md5(src));

        return params;
    }

    private TranslateResult initResult(String baiduResult){
        TranslateResult result = null;
        try {
            result = gson.fromJson(baiduResult,
                    new TypeToken<TranslateResult>() {}.getType());
        }catch (RuntimeException e){
            return null;
        }finally {
            return result;
        }
    }

    @Data
    public class TranslateResult{
        String from;
        String to;
        List<TransResult> trans_result;

        @Data
        public class TransResult{
            String src;
            String dst;
        }
    }

}
