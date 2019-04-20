package com.example.hugh.life.service.manager;


import com.example.hugh.life.commmon.util.HttpClientUtil;
import com.ning.http.client.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class HeWeatherManager {
    private final String key = "aad5a11ff2994a1aba9d2287bf2c0b39";
    public String liveWeather(double longitude, double latitude){
        String url = "https://free-api.heweather.net/s6/weather/now?location="+String.valueOf(longitude)+","+String.valueOf(latitude)+
                "&lang=en&key=" + key;
        System.out.println("url:" + url);
        Response response = HttpClientUtil.doGet(url);
        String data = null;
        try {
            data = response.getResponseBody();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    public static void main(String[] args){
        HeWeatherManager heWeatherManager = new HeWeatherManager();
        heWeatherManager.liveWeather(113.9001130,22.5818600);
    }

}
