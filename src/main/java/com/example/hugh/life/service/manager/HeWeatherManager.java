package com.example.hugh.life.service.manager;


import com.example.hugh.life.commmon.util.HttpClientUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ning.http.client.Response;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HeWeatherManager {
    public static final Map<Integer,String> WEATHERCODE_MAP_TYPES = new HashMap<Integer,String>(){
        private static final long serialVersionUID = -4851328606531379141L;
        {

            // 风少，云刚刚好
            put(100,"奇幻");
            put(101,"家庭");
            put(102,"古装");
            put(103,"动画");
            put(200,"喜剧");
            put(201,"爱情");
            put(202,"运动");
            put(203,"音乐");
            put(204,"剧情");


            put(104,"运动");

            //风大
            put(205,"奇幻");
            put(206,"奇幻");
            put(207,"科幻");
            put(208,"科幻");
            put(209,"科幻");
            put(211,"灾难");
            put(212,"灾难");
            put(213,"灾难");


            // 下雨
            put(300,"运动");
            put(301,"运动");
            put(302,"运动");
            put(303,"运动");
            put(304,"运动");
            put(305,"武侠");
            put(306,"武侠");
            put(307,"武侠");
            put(308,"武侠");
            put(309,"武侠");
            put(310,"武侠");
            put(311,"武侠");
            put(312,"历史");
            put(314,"历史");
            put(315,"历史");
            put(316,"历史");
            put(317,"历史");
            put(318,"历史");
            put(399,"历史");


            //雪
            put(400,"古装");
            put(401,"古装");
            put(402,"古装");
            put(403,"古装");
            put(404,"历史");
            put(406,"历史");
            put(407,"战争");
            put(408,"战争");
            put(409,"战争");
            put(410,"战争");
            put(499,"歌舞");

            // 雾霾
            put(500,"悬疑");
            put(501,"惊悚");
            put(502,"悬疑");
            put(503,"惊悚");
            put(504,"爱情");
            put(507,"爱情");
            put(508,"惊悚");
            put(509,"惊悚");
            put(510,"犯罪");
            put(511,"爱情");
            put(512,"爱情");
            put(513,"爱情");
            put(514,"爱情");
            put(515,"惊悚");


            //热
            put(900,"同性");

            //冷
            put(901,"运动");

            //未知
            put(999, "歌舞");



        }
    };


    Gson gson = new Gson();
    private final String key = "aad5a11ff2994a1aba9d2287bf2c0b39";
    public String liveWeather(double longitude, double latitude){
        String url = "https://free-api.heweather.net/s6/weather/now?location="+String.valueOf(longitude)+","+String.valueOf(latitude)+
                "&lang=en&key=" + key;
        Response response = HttpClientUtil.doGet(url);
        String data = null;
        try {
            data = response.getResponseBody();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public CurrentWeather doInitCurrentResult(String currentWeatherData){
        CurrentWeather result = null;
        try {
            result = gson.fromJson(currentWeatherData,
                    new TypeToken<CurrentWeather>() {}.getType());
        }catch (RuntimeException e){
            return null;
        }finally {
            return result;
        }
    }


    @Data
    public class CurrentWeather{
        List<HeWeather> HeWeather6;

        public HeWeather.Basic getBasic(){
            if(CollectionUtils.isNotEmpty(HeWeather6)
                    && HeWeather6.get(0).getStatus().equals("ok")){
                return HeWeather6.get(0).getBasic();
            }
            return null;
        }

        public HeWeather.Now getNow(){
            if(CollectionUtils.isNotEmpty(HeWeather6)
                    && HeWeather6.get(0).getStatus().equals("ok")){
                return HeWeather6.get(0).getNow();
            }
            return null;
        }

        @Data
        public class HeWeather{
            Now now;
            Basic basic;
            Update update;
            String status;
            @Data
            public class Now{
                Integer fl;
                Integer hum;
                Integer tmp;
                Integer vis;
                Float pcpn;
                Integer pres;
                Integer cloud;
                Integer wind_sc;
                String cond_txt;
                Integer wind_deg;
                String wind_dir;
                Integer wind_spd;
                Integer cond_code;
            }

            @Data
            public class Basic{
                String tz;
                String cid;
                Double lat;
                Double lon;
                String cnty;
                String location;
                String admin_area;
                String parent_city;
            }
            @Data
            public class Update{
                String loc;
                String utc;
            }
        }
    }


    public static void main(String[] args){
        HeWeatherManager heWeatherManager = new HeWeatherManager();
        heWeatherManager.liveWeather(113.9001130,22.5818600);
    }

}
