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
public class MovieManager {
    Gson gson = new Gson();
    public MovieList movieList(String tag, int page_start ){
        String singerUrl = "https://movie.douban.com/j/search_subjects?type=movie&tag="
                +tag+"&sort=time&page_limit=20&page_start=" + String.valueOf(page_start);
        System.out.println("movie url:" + singerUrl);
        Response response = HttpClientUtil.doGet(singerUrl);
        MovieList result= null;
        try {
            String data = response.getResponseBody();
            result = initListResult(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private MovieList initListResult(String musicResult){
        MovieList result = null;
        try {
            result = gson.fromJson(musicResult,
                    new TypeToken<MovieList>() {}.getType());
        }catch (RuntimeException e){
            return null;
        }finally {
            return result;
        }
    }

    @Data
    public class MovieList{
        List<movie> subjects;

        @Data
        public class movie{
            String rate;
            Integer cover_x;
            String title;
            String url;
            Boolean playable;
            String cover;
            String id;
            Integer cover_y;
            Boolean is_new;
        }
    }

    public MovieDetail movieDetail(String id){
        String singerUrl = "https://movie.douban.com/j/subject_abstract?subject_id=" + id;
        System.out.println("movieDetail url:" + singerUrl);
        Response response = HttpClientUtil.doGet(singerUrl);
        MovieDetail result= null;
        try {
            String data = response.getResponseBody();
            result = doInitDetailResult(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private MovieDetail doInitDetailResult(String movieDetailResult){
        MovieDetail result = null;
        try {
            result = gson.fromJson(movieDetailResult,
                    new TypeToken<MovieDetail>() {}.getType());
        }catch (RuntimeException e){
            return null;
        }finally {
            return result;
        }
    }

    @Data
    public class MovieDetail{
        Subject subject;
        @Data
        public class Subject{
            String episodes_count;
            String star;
            String blacklisted;
            String title;
            String url;
            String collection_status;
            String rate;
            ShortComment short_comment;
            Boolean is_tv;
            String subtype;
            List<String> directors;
            List<String> actors;
            String duration;
            String region;
            Boolean playable;
            String id;
            List<String> types;
            String release_year;

            @Data
            public class ShortComment{
                String content;
                String author;
            }
        }

    }


//    喜剧  爱情  科幻  悬疑  恐怖  治愈
    public static void main(String args[]){
        MovieManager movieManager = new MovieManager();
//        MovieList list = movieManager.movieList("治愈", 20);
        MovieDetail detail = movieManager.movieDetail("11600078");
        System.out.println("");
    }

}
