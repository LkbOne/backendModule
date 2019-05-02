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
public class BookManager {
    Gson gson = new Gson();

    public BookList bookList(String tag, int page_start, int count) {
        String singerUrl = "https://api.douban.com/v2/book/search?" +
                "q=" + tag + "&start=" + String.valueOf(page_start) + "&count="+String.valueOf(count) + "&apikey=0b2bdeda43b5688921839c8ecb20399b";

        System.out.println("book url:" + singerUrl);
        Response response = HttpClientUtil.doGet(singerUrl);
        BookList result = null;
        try {
            String data = response.getResponseBody();
            result = initListResult(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private BookList initListResult(String musicResult) {
        BookList result = null;
        try {
            result = gson.fromJson(musicResult,
                    new TypeToken<BookList>() {
                    }.getType());
        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        } finally {
            return result;
        }
    }

    @Data
    public class BookList {
        Integer count;
        Integer start;
        Integer total;
        List<Book> books;

        @Data
        public class Book {
            Rating rating;
            String subtitle;
            List<String> author;
            String pubdate;
            List<Tag> tags;
            String origin_title;
            String image;
            String binding;
            List<String> translator;
            String catalog;
            String pages;
            Images images;
            String alt;
            String id;
            String publisher;
            String isbn10;
            String isbn13;
            String title;
            String url;
            String alt_title;
            String author_intro;
            String summary;
            Series series;
            String price;
            @Data
            public class Rating {
                String max;
                String numRaters;
                String average;
                String min;
            }

            @Data
            public class Tag {
                String count;
                String name;
                String title;
            }
            @Data
            public class Images{
                String small;
                String large;
                String medium;
            }
            @Data
            public class Series{
                String id;
                String title;
            }

        }
    }

    //    喜剧  爱情  科幻  悬疑  恐怖  治愈
    public static void main(String args[]) {
        BookManager movieManager = new BookManager();
//        MovieList list = movieManager.movieList("治愈", 20);
        BookList detail = movieManager.bookList("小说", 0, 50);
        System.out.println("");
    }

}
