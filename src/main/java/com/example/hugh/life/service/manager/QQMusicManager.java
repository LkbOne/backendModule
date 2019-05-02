package com.example.hugh.life.service.manager;

import com.example.hugh.life.commmon.util.HttpClientUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ning.http.client.Response;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class QQMusicManager {
    Gson gson = new Gson();
    public List<QQSingerListResult.SingerList.Datas.Singerlist> signerList(int sin, int curPage){
       String singerUrl = " https://u.y.qq.com/cgi-bin/musicu.fcg?-=getUCGI1341578183725467&g_tk=5381&loginUin=0&hostUin=0&format=json&inCharset=utf8&outCharset=utf-8&notice=0&platform=yqq.json&needNewCode=0&data={\"comm\":{\"ct\":24,\"cv\":0},\"singerList\":{\"module\":\"Music.SingerListServer\",\"method\":\"get_singer_list\",\"param\":{\"area\":-100,\"sex\":-100,\"genre\":-100,\"index\":-100,\"sin\":"+String.valueOf(sin)+",\"cur_page\":"+ String.valueOf(curPage)+"}}}";
        System.out.println("music url:" + singerUrl);
        Response response = HttpClientUtil.doGet(singerUrl);
        String data = null;
        QQSingerListResult result = null;
        try {
            data = response.getResponseBody();
            result = initResult(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.getSingerList();
    }

    private QQMusicManager.QQSingerListResult initResult(String musicResult){
        QQMusicManager.QQSingerListResult result = null;
        try {
            result = gson.fromJson(musicResult,
                    new TypeToken<QQMusicManager.QQSingerListResult>() {}.getType());
        }catch (RuntimeException e){
            return null;
        }finally {
            return result;
        }
    }

    @Data
    public class QQSingerListResult{
        String code;
        String ts;
        SingerList singerList;
        public List<SingerList.Datas.Singerlist> getSingerList(){
            if(getCode().equals("0")){
                return singerList.getDatas();
            }
            return null;
        }
        @Data
        public class SingerList{
            String code;
            Datas data;
            public List<Datas.Singerlist> getDatas(){
                if(getCode().equals("0")){
                    return data.getSingerlist();
                }
                return null;
            }
            @Data
            public class Datas{
                Integer area;
                Integer genre;
                Integer index;
                Integer sex;
                List<Singerlist> singerlist;
                @Data
                public class Singerlist{
                    String country;
                    String singer_id;
                    String singer_mid;
                    String singer_name;
                    String singer_pic;
                }
            }
        }

    }
    public static void main(String[] args){
        QQMusicManager qqMusicManager = new QQMusicManager();
        qqMusicManager.signerList(0,1);
    }
}
