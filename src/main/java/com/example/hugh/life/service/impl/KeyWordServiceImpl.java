package com.example.hugh.life.service.impl;

import com.example.hugh.life.api.KeyWordService;
import com.example.hugh.life.api.result.RecommendResult;
import com.example.hugh.life.commmon.ModelResult;
import com.example.hugh.life.commmon.SHErrorCode;
import com.example.hugh.life.commmon.util.BeanUtil;
import com.example.hugh.life.commmon.util.UUIDUtil;
import com.example.hugh.life.controller.arg.keyword.AddKeyWordArg;
import com.example.hugh.life.controller.arg.keyword.DeleteKeyWordArg;
import com.example.hugh.life.controller.arg.keyword.RecommendContentArg;
import com.example.hugh.life.controller.arg.keyword.UpdateKeyWordArg;
import com.example.hugh.life.dao.api.*;
import com.example.hugh.life.dao.entity.*;
import com.example.hugh.life.service.manager.BookManager;
import com.example.hugh.life.service.manager.KeyWordManager;
import com.example.hugh.life.service.manager.MovieManager;
import com.example.hugh.life.service.manager.TencentNLPManager;
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
public class KeyWordServiceImpl implements KeyWordService {

    @Autowired
    ChromeVisitUrlDao chromeVisitUrlDao;

    @Autowired
    TencentNLPManager tencentNLPManager;

    @Autowired
    KeyWordDao keyWordDao;

    @Autowired
    VisitContentDao visitContentDao;

    @Autowired
    UserMiddleKeyWordDao userMiddleKeyWordDao;

    @Autowired
    UserMiddleVisitContentDao userMiddleVisitContentDao;

    @Autowired
    KeyWordMiddleVisitContentDao keyWordMiddleVisitContentDao;

    @Autowired
    MovieManager movieManager;

    @Autowired
    MovieInfoDao movieInfoDao;

    @Autowired
    TypesDao typesDao;

    @Autowired
    TypesMiddleMovieInfoDao typesMiddleMovieInfoDao;

    @Autowired
    PeopleInfoDao peopleInfoDao;

    @Autowired
    PeopleInfoMiddleMovieInfoDao peopleInfoMiddleMovieInfoDao;

    @Autowired
    PeopleInfoMiddleIdentityDao peopleInfoMiddleIdentityDao;


    @Autowired
    BookManager bookManager;

    @Autowired
    BookInfoDao bookInfoDao;

    @Autowired
    TagsDao tagsDao;

    @Autowired
    TagsMiddleBookInfoDao tagsMiddleBookInfoDao;

    @Autowired
    PeopleInfoMiddleBookInfoDao peopleInfoMiddleBookInfoDao;

    @Autowired
    KeyWordManager keyWordManager;

    @Override
    public ModelResult add(AddKeyWordArg arg) {
        return null;
    }

    @Override
    public ModelResult update(UpdateKeyWordArg arg) {
        return null;
    }

    @Override
    public ModelResult delete(DeleteKeyWordArg arg) {
        return null;
    }

    @Override
    public ModelResult recommendContent(RecommendContentArg arg) {
        RecommendResult result = new RecommendResult();
        if(arg.getType() == 1){
            List<String> contentList = visitContentDao.queryTopNLikeContentByContent(
                    arg.getContent(), 10);
            result.setContentList(contentList);
        }else{

        }
        return new ModelResult<>(SHErrorCode.SUCCESS, result);
    }

//    @Scheduled(cron = "0 0/50 * * * ?")
    @Override
    public void divideKeyWord() {
        log.warn("divideKeyWord begin");
        List<ChromeVisitUrlEntity> historyEntities = chromeVisitUrlDao.listByDivided(0);
        for (ChromeVisitUrlEntity historyEntity : historyEntities) {
            TencentNLPManager.KeyWord keyWords = tencentNLPManager.getKeyWord(historyEntity.getTitle());
            if(null != keyWords){
                if(!doSetKeyWord(historyEntity, keyWords)){
                    log.warn("KeyWordServiceImpl.divideKeyWord historyEntity:{}", historyEntity);
                }
            }
        }
        log.warn("divideKeyWord end");
    }

    @Transactional
    boolean doSetKeyWord(ChromeVisitUrlEntity historyEntity, TencentNLPManager.KeyWord keyWords){
        List<TencentNLPManager.KeyWord.Combtoken> combtokens = CollectionUtils.isEmpty(keyWords.getCombtokens())
                ?Lists.newArrayList():keyWords.getCombtokens();

        List<TencentNLPManager.KeyWord.Token> tokens = CollectionUtils.isEmpty(keyWords.getTokens())
                ?Lists.newArrayList():keyWords.getTokens();

        String contentId = UUIDUtil.getUUID();
        VisitContentEntity visitContent = visitContentDao.queryByContent(historyEntity.getTitle());
        if(null == visitContent) {
            VisitContentEntity visitContentEntity = new VisitContentEntity();
            visitContentEntity.setContent(historyEntity.getTitle());
            visitContentEntity.setId(contentId);
            visitContentDao.add(visitContentEntity);

            UserMiddleVisitContentEntity userMiddleVisitContentEntity = new UserMiddleVisitContentEntity();
            userMiddleVisitContentEntity.setCount(1);
            userMiddleVisitContentEntity.setId(UUIDUtil.getUUID());
            userMiddleVisitContentEntity.setUserId(historyEntity.getUid());
            userMiddleVisitContentEntity.setVisitContentId(contentId);
            userMiddleVisitContentDao.add(userMiddleVisitContentEntity);
        }else {
            contentId = visitContent.getId();
            userMiddleVisitContentDao.update(contentId, historyEntity.getUid());
        }

        for (TencentNLPManager.KeyWord.Token token : tokens) {
            if(null == token || StringUtils.isEmpty(token.getWord()) ){
                continue;
            }
            String id = keyWordDao.getIdByKeyWord(token.getWord());
            if (StringUtils.isNotBlank(id)) {
                keyWordDao.update(id,null, token.getWtype());
                userMiddleKeyWordDao.update(id, historyEntity.getUid());
            } else {
                doSetKeyWordConnection(historyEntity.getUid(), contentId,
                        token.getWord(), null, token.getWtype());
            }
        }

        for (TencentNLPManager.KeyWord.Combtoken combtoken : combtokens) {
            if(null == combtoken || StringUtils.isEmpty(combtoken.getWord()) ){
                continue;
            }
            String id = keyWordDao.getIdByKeyWord(combtoken.getWord());
            if (StringUtils.isNotBlank(id)) {
                keyWordDao.update(id, combtoken.getCls(), null);
                userMiddleKeyWordDao.update(id, historyEntity.getUid());
            } else {
                doSetKeyWordConnection(historyEntity.getUid(), contentId,
                        combtoken.getWord(), combtoken.getCls(), null);
            }
        }

        chromeVisitUrlDao.updateDivided(historyEntity.getId(), 1);
        return true;
    }

    @Transactional
    boolean doSetKeyWordConnection(String uid, String contentId, String keyWord, String combtokenType, String tokenType){
        String keyWordId = UUIDUtil.getUUID();
        keyWordDao.add(keyWordId, keyWord, combtokenType, tokenType);
        UserMiddleKeyWordEntity userMiddleKeyWordEntity = new UserMiddleKeyWordEntity();
        userMiddleKeyWordEntity.setCount(1);
        userMiddleKeyWordEntity.setId(UUIDUtil.getUUID());
        userMiddleKeyWordEntity.setKeyWordId(keyWordId);
        userMiddleKeyWordEntity.setUserId(uid);
        userMiddleKeyWordDao.add(userMiddleKeyWordEntity);

        KeyWordMiddleVisitContentEntity keyWordMiddleVisitContentEntity = new KeyWordMiddleVisitContentEntity();
        keyWordMiddleVisitContentEntity.setId(UUIDUtil.getUUID());
        keyWordMiddleVisitContentEntity.setKeyWordId(keyWordId);
        keyWordMiddleVisitContentEntity.setVisitContentId(contentId);
        keyWordMiddleVisitContentDao.add(keyWordMiddleVisitContentEntity);
        return true;
    }

//    @Scheduled(cron = "0 0/1 * * * ?")
    public void crawlerDBVideo(){
        int count = 0;
        String[] tags = new String[]{"动作", "喜剧", "爱情", "科幻", "悬疑", "恐怖", "治愈"};

        log.warn("crawlerDBVideo begin:");
        for (String tag : tags) {
            for (int i = count; i < 200000; i++) {
                MovieManager.MovieList movieList = movieManager.movieList(tag, i * 20);
                log.warn("crawlerDBVideo begin:  tag:{}  i:{}", tag, i);
                if (null != movieList && CollectionUtils.isNotEmpty(movieList.getSubjects())) {
                    List<MovieManager.MovieList.movie> movies = movieList.getSubjects();
                    for (MovieManager.MovieList.movie movie : movies) {
                        if (movieInfoDao.countByDoubanId(movie.getId()) == 0) {
                            MovieInfoEntity entity = new MovieInfoEntity();
                            entity.setDoubanId(movie.getId());
                            entity.setId(UUIDUtil.getUUID());
                            try {
                                entity.setRate(Float.valueOf(movie.getRate()));
                            }catch (RuntimeException e){
                                entity.setRate(0f);
                                log.warn("rate  e:{}", e);
                            }
                            entity.setTitle(movie.getTitle());

                            entity.setCoverUrl(movie.getCover());
                            entity.setUrl(movie.getUrl());
                            movieInfoDao.add(entity);
                        }
                    }
                } else {
                    break;
                }
                log.warn("crawlerDBVideo end:  tag:{}  i:{}", tag, i);
            }
        }
        log.warn("crawlerDBVideo end:");
    }

//    @Scheduled(cron = "0 0/1 * * * ?")
    public void crawlerDBVideoDetail(){
        log.warn("crawlerDBVideoDetail begin:");
        List<MovieInfoEntity> movieInfoEntityList = movieInfoDao.listByCrawlered(false);
        if(CollectionUtils.isNotEmpty(movieInfoEntityList)){
            for (MovieInfoEntity movieInfoEntity : movieInfoEntityList) {
                MovieManager.MovieDetail movieDetail = movieManager.movieDetail(movieInfoEntity.getDoubanId());
                MovieManager.MovieDetail.Subject subject = movieDetail.getSubject();
                if(null != subject) {
                    doSetMovieInfo(movieInfoEntity, subject);
                }
            }
        }
        log.warn("crawlerDBVideoDetail end:");
    }

    @Transactional
    public void doSetMovieInfo(MovieInfoEntity movieInfoEntity, MovieManager.MovieDetail.Subject subject){
        movieInfoEntity.setSubType(subject.getSubtype());
        movieInfoEntity.setDuration(subject.getDuration());
        movieInfoEntity.setCrawlered(true);
        movieInfoEntity.setRegion(subject.getRegion());
        movieInfoEntity.setReleaseYear(subject.getRelease_year());
        List<String> actors = subject.getActors();
        List<String> directors = subject.getDirectors();
        List<String> types = subject.getTypes();
        if(CollectionUtils.isNotEmpty(types)) {
            for (String type : types) {
                TypesEntity entity = typesDao.queryByTypeInfo(type);
                if(null == entity){
                    entity = new TypesEntity();
                    String typesId = UUIDUtil.getUUID();
                    entity.setId(typesId);
                    entity.setTypeInfo(type);
                    typesDao.add(entity);
                }
                TypesMiddleMovieInfoEntity typesMiddleMovieInfoEntity = new TypesMiddleMovieInfoEntity();
                typesMiddleMovieInfoEntity.setId(UUIDUtil.getUUID());
                typesMiddleMovieInfoEntity.setMovieInfoId(movieInfoEntity.getId());
                typesMiddleMovieInfoEntity.setTypesId(entity.getId());
                typesMiddleMovieInfoDao.add(typesMiddleMovieInfoEntity);
            }
        }
        doSetPeopleInfo(movieInfoEntity.getId(), "0001b67bb8ce4dd2b4f8983b82017d15", "1", actors);
        doSetPeopleInfo(movieInfoEntity.getId(), "0029844a1b1d4cdf8c7bd6158a48878f", "1", directors);
        if(!movieInfoDao.update(movieInfoEntity)){
            log.warn("update movie:{}", movieInfoEntity);
        }
    }


    private void doSetPeopleInfo(String movieId,String identityId, String type, List<String> peoples){
        if(CollectionUtils.isNotEmpty(peoples)) {
            for (String actor : peoples) {
                PeopleInfoEntity entity = peopleInfoDao.queryByName(actor);
                if(null == entity){
                    entity = new PeopleInfoEntity();
                    String peopleInfoId = UUIDUtil.getUUID();
                    entity.setId(peopleInfoId);
                    entity.setName(actor);
                    entity.setType(type); //电影相关
                    peopleInfoDao.add(entity);
                }
                PeopleInfoMiddleMovieInfoEntity peopleInfoMiddleMovieInfoEntity = new PeopleInfoMiddleMovieInfoEntity();
                peopleInfoMiddleMovieInfoEntity.setId(UUIDUtil.getUUID());
                peopleInfoMiddleMovieInfoEntity.setMovieInfoId(movieId);
                peopleInfoMiddleMovieInfoEntity.setPeopleInfoId(entity.getId());
                peopleInfoMiddleMovieInfoDao.add(peopleInfoMiddleMovieInfoEntity);

                PeopleInfoMiddleIdentityEntity peopleInfoMiddleIdentityEntity = new PeopleInfoMiddleIdentityEntity();
                peopleInfoMiddleIdentityEntity.setId(UUIDUtil.getUUID());
                peopleInfoMiddleIdentityEntity.setIdentityId(identityId);
                peopleInfoMiddleIdentityEntity.setPeopleInfoId(entity.getId());
                peopleInfoMiddleIdentityDao.add(peopleInfoMiddleIdentityEntity);
            }
        }
    }





//    @Scheduled(cron = "0 0/1 * * * ?")
    public void crawlerDBBookDetail(){
        log.warn("crawlerDBBookDetail begin:");
        for(int i = 0; i < 2000000; i++){
            BookManager.BookList bookList = bookManager.bookList("小说", i * 50, 50);
            keyWordManager.keyWord(bookList);
        }
        log.warn("crawlerDBBookDetail end:");
    }

}
