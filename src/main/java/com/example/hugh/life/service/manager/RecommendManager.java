package com.example.hugh.life.service.manager;

import com.example.hugh.life.api.result.RecommendUsuallyResult;
import com.example.hugh.life.dao.entity.BookInfoEntity;
import com.example.hugh.life.dao.entity.MovieInfoEntity;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendManager {
    @Autowired
    InfoManager infoManager;

    public List<RecommendUsuallyResult> doSetResult(List<MovieInfoEntity> movieList, List<BookInfoEntity> bookInfoList){
        List<RecommendUsuallyResult> results = Lists.newArrayList();
        for (MovieInfoEntity movieInfoEntity : movieList) {
            RecommendUsuallyResult result = new RecommendUsuallyResult();
            result.setCoverUrl(movieInfoEntity.getCoverUrl());
            result.setDetailUrl(movieInfoEntity.getUrl());
            result.setId(movieInfoEntity.getId());
            result.setRate(movieInfoEntity.getRate());
            result.setType(2);
            result.setTitle(movieInfoEntity.getTitle());
            result.setPeopleContent(infoManager.movieAuthor(movieInfoEntity.getId()));
            results.add(result);
        }
        for (BookInfoEntity bookInfoEntity : bookInfoList) {
            RecommendUsuallyResult result = new RecommendUsuallyResult();
            result.setCoverUrl(bookInfoEntity.getImageMedium());
            result.setDetailUrl(bookInfoEntity.getAlt());
            result.setId(bookInfoEntity.getId());
            result.setRate(bookInfoEntity.getRateAverage());
            result.setType(1);
            result.setTitle(bookInfoEntity.getTitle());
            result.setPeopleContent(infoManager.bookAuthor(bookInfoEntity.getId()));
            results.add(result);
        }
        return results;
    }
}
