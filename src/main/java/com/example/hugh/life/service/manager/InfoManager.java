package com.example.hugh.life.service.manager;

import com.example.hugh.life.dao.api.*;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoManager {

    @Autowired
    PeopleInfoDao peopleInfoDao;


    private List<String> getPeopleName(Integer type, String identity, String id){
        List<String> result = Lists.newArrayList();
        if(type == 1) {
            result = peopleInfoDao.getPeopleNameByIdentityAndBook(id, identity, 3);
        }else if(type == 2){
            result = peopleInfoDao.getPeopleNameByIdentityAndMovie(id, identity, 3);
        }else {

        }
        return result;
    }

    public String bookAuthor(String id){
        List<String> nameList = getPeopleName(1, "author", id);
        StringBuilder author = new StringBuilder("作者：");
        if(CollectionUtils.isNotEmpty(nameList)){
            for (int i = 0; i < nameList.size(); i++) {
                author.append(nameList.get(i));
                if(i < nameList.size() - 1){
                    author.append("/");
                }
            }
            return author.toString();
        }
        return null;
    }

    public String movieAuthor(String id){
        List<String> actorList = getPeopleName(2, "actor", id);
        List<String> directorList = getPeopleName(2, "director", id);
        StringBuilder author = new StringBuilder();
        if(CollectionUtils.isNotEmpty(directorList)){
            author.append("导演：").append(directorList.get(0)).append(" ");
        }
        if(CollectionUtils.isNotEmpty(actorList)){
            author.append("主演：");
            for (int i = 0; i < actorList.size(); i++) {
                author.append(actorList.get(i));
                if(i < actorList.size() - 1){
                    author.append("/");
                }
            }
            return author.toString();
        }
        return null;
    }

}
