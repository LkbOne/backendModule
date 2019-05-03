package com.example.hugh.life.service.manager;

import com.example.hugh.life.commmon.util.UUIDUtil;
import com.example.hugh.life.dao.api.*;
import com.example.hugh.life.dao.entity.*;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Slf4j
@Service
public class KeyWordManager {
    @Autowired
    BookInfoDao bookInfoDao;

    @Autowired
    TagsDao tagsDao;

    @Autowired
    TagsMiddleBookInfoDao tagsMiddleBookInfoDao;

    @Autowired
    PeopleInfoDao peopleInfoDao;

    @Autowired
    PeopleInfoMiddleBookInfoDao peopleInfoMiddleBookInfoDao;

    @Autowired
    PeopleInfoMiddleIdentityDao peopleInfoMiddleIdentityDao;

    public List<BookInfoEntity> keyWord(BookManager.BookList bookList){
        List<BookInfoEntity> entityList = Lists.newArrayList();
        if(null!= bookList && CollectionUtils.isNotEmpty(bookList.getBooks())){
            List<BookManager.BookList.Book> books = bookList.getBooks();
            for (BookManager.BookList.Book book : books) {
                if(null != book) {
                    BookInfoEntity entity = bookInfoDao.queryByDouBanId(book.getId());
                    if(null == entity) {
                        entity = new BookInfoEntity();
                        entity.setAlt(book.getAlt());
                        entity.setAltTitle(book.getAlt_title());
                        entity.setAuthorIntro(book.getAuthor_intro());
                        entity.setBinding(book.getBinding());
                        entity.setCatalog(book.getCatalog());
                        entity.setDoubanId(book.getId());
                        entity.setId(UUIDUtil.getUUID());
                        entity.setUrl(book.getUrl());
                        entity.setTitle(book.getTitle());
                        entity.setSummary(book.getSummary());
                        entity.setPublisher(book.getPublisher());
                        try {
                            entity.setPages(StringUtils.isEmpty(book.getPages()) ? 0 :
                                    Integer.valueOf(book.getPages()));
                        }catch (RuntimeException e){
                            log.warn("pages:{}", book.getPages());
                            entity.setPages(0);
                        }
                        entity.setPrice(book.getPrice());
                        entity.setPubdate(book.getPubdate());
                        entity.setOriginTitle(book.getOrigin_title());
                        entity.setImage(book.getImage());
                        entity.setIsbn10(book.getIsbn10());
                        entity.setIsbn13(book.getIsbn13());

                        BookManager.BookList.Book.Images images = book.getImages();
                        if(null != images) {
                            entity.setImagesSmall(images.getSmall());
                            entity.setImagesLarge(images.getLarge());
                            entity.setImageMedium(images.getMedium());
                        }
                        BookManager.BookList.Book.Rating rating = book.getRating();
                        if(null != rating) {
                            entity.setRateAverage(StringUtils.isEmpty(rating.getAverage())?0:
                                    Float.valueOf(rating.getAverage()));
                            entity.setNumRaters(StringUtils.isEmpty(rating.getNumRaters())?0:
                                    Float.valueOf(rating.getAverage()));
                        }
                        doSetBookInfo(entity, book);
                    }
                    entityList.add(entity);
                }
            }
        }
        return entityList;
    }
    @Transactional
    public void doSetBookInfo(BookInfoEntity entity, BookManager.BookList.Book book){
        List<String> author = book.getAuthor(); // 作者
        List<String> translator = book.getTranslator(); //翻译
        List<BookManager.BookList.Book.Tag> tags = book.getTags();
        if(CollectionUtils.isNotEmpty(tags)) {
            for (BookManager.BookList.Book.Tag tag : tags) {
                TagsEntity tagsEntity = tagsDao.queryByNameAndTitle(tag.getTitle(), tag.getName());
                if(null == tagsEntity){
                    tagsEntity = new TagsEntity();
                    String typesId = UUIDUtil.getUUID();
                    tagsEntity.setId(typesId);
                    tagsEntity.setCount(StringUtils.isEmpty(tag.getCount())?0:Integer.valueOf(tag.getCount()));
                    tagsEntity.setName(tag.getName());
                    tagsEntity.setTitle(tag.getTitle());
                    tagsDao.add(tagsEntity);
                }
                TagsMiddleBookInfoEntity tagsMiddleMovieInfoEntity = new TagsMiddleBookInfoEntity();
                tagsMiddleMovieInfoEntity.setId(UUIDUtil.getUUID());
                tagsMiddleMovieInfoEntity.setBookInfoId(entity.getId());
                tagsMiddleMovieInfoEntity.setTagsId(tagsEntity.getId());
                tagsMiddleBookInfoDao.add(tagsMiddleMovieInfoEntity);
            }
        }
        doSetBookPeopleInfo(entity.getId(), "001359c273aa4fe298543a98ad030a14", "2", author);
        doSetBookPeopleInfo(entity.getId(), "0016d3d6e8d24a73b9bb1ab6a0391c46", "2", translator);
        if(!bookInfoDao.add(entity)){
            log.warn("add book:{}", entity);
        }
    }

    private void doSetBookPeopleInfo(String bookId,String identityId, String type, List<String> peoples){
        if(CollectionUtils.isNotEmpty(peoples)) {
            for (String people : peoples) {
                PeopleInfoEntity entity = peopleInfoDao.queryByName(people);
                if(null == entity){
                    entity = new PeopleInfoEntity();
                    String peopleInfoId = UUIDUtil.getUUID();
                    entity.setId(peopleInfoId);
                    entity.setName(people);
                    entity.setType(type); //电影相关
                    peopleInfoDao.add(entity);
                }
                PeopleInfoMiddleBookInfoEntity peopleInfoMiddleMovieInfoEntity = new PeopleInfoMiddleBookInfoEntity();
                peopleInfoMiddleMovieInfoEntity.setId(UUIDUtil.getUUID());
                peopleInfoMiddleMovieInfoEntity.setBookInfoId(bookId);
                peopleInfoMiddleMovieInfoEntity.setPeopleInfoId(entity.getId());
                peopleInfoMiddleBookInfoDao.add(peopleInfoMiddleMovieInfoEntity);

                PeopleInfoMiddleIdentityEntity peopleInfoMiddleIdentityEntity = new PeopleInfoMiddleIdentityEntity();
                peopleInfoMiddleIdentityEntity.setId(UUIDUtil.getUUID());
                peopleInfoMiddleIdentityEntity.setIdentityId(identityId);
                peopleInfoMiddleIdentityEntity.setPeopleInfoId(entity.getId());
                peopleInfoMiddleIdentityDao.add(peopleInfoMiddleIdentityEntity);
            }
        }
    }

    public BookInfoEntity setKeyWord(BookManager.BookList.Book book){
        BookInfoEntity entity = new BookInfoEntity();
        entity.setAlt(book.getAlt());
        entity.setAltTitle(book.getAlt_title());
        entity.setAuthorIntro(book.getAuthor_intro());
        entity.setBinding(book.getBinding());
        entity.setCatalog(book.getCatalog());
        entity.setDoubanId(book.getId());
        entity.setId(UUIDUtil.getUUID());
        entity.setUrl(book.getUrl());
        entity.setTitle(book.getTitle());
        entity.setSummary(book.getSummary());
        entity.setPublisher(book.getPublisher());
        try {
            entity.setPages(StringUtils.isEmpty(book.getPages()) ? 0 :
                    Integer.valueOf(book.getPages()));
        }catch (RuntimeException e){
            log.warn("pages:{}", book.getPages());
            entity.setPages(0);
        }
        entity.setPrice(book.getPrice());
        entity.setPubdate(book.getPubdate());
        entity.setOriginTitle(book.getOrigin_title());
        entity.setImage(book.getImage());
        entity.setIsbn10(book.getIsbn10());
        entity.setIsbn13(book.getIsbn13());

        BookManager.BookList.Book.Images images = book.getImages();
        if(null != images) {
            entity.setImagesSmall(images.getSmall());
            entity.setImagesLarge(images.getLarge());
            entity.setImageMedium(images.getMedium());
        }
        BookManager.BookList.Book.Rating rating = book.getRating();
        if(null != rating) {
            entity.setRateAverage(StringUtils.isEmpty(rating.getAverage())?0:
                    Float.valueOf(rating.getAverage()));
            entity.setNumRaters(StringUtils.isEmpty(rating.getNumRaters())?0:
                    Float.valueOf(rating.getAverage()));
        }
        return entity;
    }
}
