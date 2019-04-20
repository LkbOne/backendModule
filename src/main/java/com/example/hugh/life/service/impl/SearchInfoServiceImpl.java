package com.example.hugh.life.service.impl;

import com.example.hugh.life.api.SearchInfoService;
import com.example.hugh.life.api.dto.FilterTypeDto;
import com.example.hugh.life.api.result.SearchResult;
import com.example.hugh.life.api.result.SumDaySearchActionResult;
import com.example.hugh.life.api.result.SumSearchActionResult;
import com.example.hugh.life.api.result.UrlCountRankResult;
import com.example.hugh.life.commmon.ModelResult;
import com.example.hugh.life.commmon.SHErrorCode;
import com.example.hugh.life.commmon.enums.SearchMainTypeEnum;
import com.example.hugh.life.commmon.util.BeanUtil;
import com.example.hugh.life.commmon.util.DateUtil;
import com.example.hugh.life.commmon.util.UUIDUtil;
import com.example.hugh.life.commmon.util.WebCrawlerUtil;
import com.example.hugh.life.controller.arg.*;
import com.example.hugh.life.dao.api.ChromeVisitUrlDao;
import com.example.hugh.life.dao.api.SearchInfoDao;
import com.example.hugh.life.dao.api.UserDao;
import com.example.hugh.life.dao.entity.ChromeVisitUrlEntity;
import com.example.hugh.life.dao.entity.SearchInfoEntity;
import com.example.hugh.life.dao.entity.UserEntity;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.*;
@Service("searchService")
public class SearchInfoServiceImpl implements SearchInfoService {

    @Autowired
    UserDao userDao;

    @Autowired
    SearchInfoDao searchInfoDao;

    @Autowired
    ChromeVisitUrlDao chromeVisitUrlDao;

    @Override
    public ModelResult add(SearchArg arg) {
        UserEntity user = userDao.queryUserById(arg.getUid());
        if(null == user){
            return new ModelResult(SHErrorCode.NOT_USER);
        }
        SearchInfoEntity newSearchInfo = BeanUtil.copy(arg, SearchInfoEntity.class);
        newSearchInfo.setId(UUIDUtil.getUUID());
        if(!searchInfoDao.addSearchInfo(newSearchInfo)){

            return new ModelResult(SHErrorCode.ADD_SEARCH_INFO_FAIL);
        }
        return new ModelResult(SHErrorCode.SUCCESS);
    }

    public void list(ListSearchInfoArg arg){

    }

//    @Override
//    public ModelResult crawler(CrawlerArg arg) {
//        String pager = WebCrawlerUtil.getWebpage(arg.getUrl());
//        return new ModelResult(SHErrorCode.SUCCESS);
//    }
    @Override
    public ModelResult crawler(CrawlerArg arg) {
        return haiZeiWangSearch();
//        return new ModelResult(SHErrorCode.SUCCESS);
    }

    @Override
    public ModelResult<SumSearchActionResult> sumSearchAction(SumSearchActionArg arg) {
        Date beginTime = DateUtil.getBeginDate(new Date(), arg.getRecentDay());

        Integer book = searchInfoDao.countSearchInfoByUidAndAllType(arg.getUid(),null, SearchMainTypeEnum.BOOK.getType(),
                null,beginTime, null);
        Integer video = searchInfoDao.countSearchInfoByUidAndAllType(arg.getUid(),null,SearchMainTypeEnum.VIDEO.getType(),
                null,beginTime, null);
        Integer music = searchInfoDao.countSearchInfoByUidAndAllType(arg.getUid(),null,SearchMainTypeEnum.MUSIC.getType(),
                null,beginTime, null);
        Integer other = searchInfoDao.countSearchInfoByUidAndAllType(arg.getUid(), null, null,
                null, beginTime, null) - (book + video + music);

        SumSearchActionResult result = new SumSearchActionResult();
        result.setBook(book);
        result.setVideo(video);
        result.setMusic(music);
        result.setOther(other);

        return new ModelResult<>(SHErrorCode.SUCCESS, result);
    }

    @Override
    public ModelResult<List<SumDaySearchActionResult>> listSumSearchAction(ListSumSearchActionArg arg) {

        List<SumDaySearchActionResult> sumSearchActionList = Lists.newArrayList();
        int i = arg.getRecentDay();
        Date now = new Date();
        while(i > 0) {
            Date beginTime = DateUtil.getBeginDate(now, i);
            Date endTime = DateUtil.getBeginDate(now, i - 1);
            Integer book = searchInfoDao.countSearchInfoByUidAndAllType(arg.getUid(), null, SearchMainTypeEnum.BOOK.getType(),
                    null, beginTime, endTime);
            Integer video = searchInfoDao.countSearchInfoByUidAndAllType(arg.getUid(), null, SearchMainTypeEnum.VIDEO.getType(),
                    null, beginTime, endTime);
            Integer music = searchInfoDao.countSearchInfoByUidAndAllType(arg.getUid(), null, SearchMainTypeEnum.MUSIC.getType(),
                    null, beginTime, endTime);
            Integer other = searchInfoDao.countSearchInfoByUidAndAllType(arg.getUid(), null, null,
                    null, beginTime, endTime) - (book + video + music);
            SumDaySearchActionResult result = new SumDaySearchActionResult();
            result.setBook(book);
            result.setMusic(video);
            result.setMusic(music);
            result.setOther(other);
            result.setDate(endTime);
            sumSearchActionList.add(result);
            i--;
        }
        return new ModelResult<>(SHErrorCode.SUCCESS, sumSearchActionList);
    }

    @Override
    public ModelResult addBatchSearchInfo(AddBatchSearchInfoArg arg) {
        chromeVisitUrlDao.batchAdd("0a6a4fac0f2845708c5bfc1be8a25b7b", arg.getUrls());
        return new ModelResult(SHErrorCode.SUCCESS);
    }



    private String doHost(String url){
        String[] parsed_url_components = url.split("://");
        if(parsed_url_components.length <= 1){
            return null;
        }
        String[] sublevel_split = parsed_url_components[1].split("/");
        return sublevel_split[0].replace("www.","");
    }
    // search --> {"baidu.com", "google.com"}
    // video --> {"youtube.com", "mgtv.com", "iqiyi.com", "movie.douban.com"}
    // learning --> {"github.com", "blog.csdn.net", "cnblogs.com" , "liaoxuefeng.com", "developers.weixin.qq.com",
    // "leetcode.com", "study-area.org", "stackoverflow.com", "jianshu.com", "bbs.csdn.net", "zhihu.com", "w3cschool.cn"
    // ,"jikexueyuan.com"}
    // 云盘  --> {"pan.baidu.com"}
    // shop --> {"item.jd.com", "search.jd.com", "list.jd.com", "detail.tmall.com", "s.taobao.com"}
    // music --> {"y.qq.com", "music.douban.com"}
    // book --> {"book.douban.com"}
    // TY -->{"sports.qq.com"}
    private List<FilterTypeDto> initFilterType(){

        FilterTypeDto search =  new FilterTypeDto();
        search.setType("search");
        List<FilterTypeDto.Url> searchList = Lists.newArrayList();
        searchList.add(new FilterTypeDto.Url("baidu.com", 0));
        searchList.add(new FilterTypeDto.Url("google.com", 0));
        searchList.add(new FilterTypeDto.Url("huohuohuohuo", 0));
        search.setUrlList(searchList);

        FilterTypeDto video = new FilterTypeDto();
        video.setType("video");
        List<FilterTypeDto.Url> videoList = Lists.newArrayList();
        videoList.add(new FilterTypeDto.Url("youtube.com" , 0));
        videoList.add(new FilterTypeDto.Url("mgtv.com" , 0));
        videoList.add(new FilterTypeDto.Url("iqiyi.com" , 0));
        videoList.add(new FilterTypeDto.Url("movie.douban.com" , 0));
        video.setUrlList(videoList);

        FilterTypeDto learn = new FilterTypeDto();
        List<FilterTypeDto.Url> learnList = Lists.newArrayList();
        learn.setType("learn");
        learnList.add(new FilterTypeDto.Url("github.com", 0));
        learnList.add(new FilterTypeDto.Url("blog.csdn.net", 0));
        learnList.add(new FilterTypeDto.Url("cnblogs.com", 0));
        learnList.add(new FilterTypeDto.Url("stackoverflow.com" , 0));
        learn.setUrlList(learnList);

        List<FilterTypeDto> typeSetList = Lists.newArrayList();
        typeSetList.add(search);
        typeSetList.add(video);
        typeSetList.add(search);
        return typeSetList;
    }

    private List<FilterTypeDto> filterUrlByType(List<UrlCountRankResult> resultList){
        List<FilterTypeDto> filterList = initFilterType();
        for (UrlCountRankResult result : resultList) {
            boolean flag = false;
            String url = result.getUrl();
            Integer count = result.getCount();
            for (FilterTypeDto filter : filterList) {
                if(flag){
                    break;
                }
                List<FilterTypeDto.Url> urlList = filter.getUrlList();
                for (FilterTypeDto.Url urlRegex : urlList) {
                    Pattern pTitle = Pattern.compile(".*" + urlRegex.getUrl(), Pattern.CASE_INSENSITIVE);
                    Matcher urlMatcher = pTitle.matcher(url);
                    if(urlMatcher.find()){
                        filter.setCount(filter.getCount() + 1);
                        urlRegex.setCount(urlRegex.getCount() + count);
                        flag = true;
                        break;
                    }
                }
            }
        }
        return filterList;
    }

    private Map<String, List<String>> recommadation(List<UrlCountRankResult> resultList){
        List<FilterTypeDto> filterList = filterUrlByType(resultList);
        Map<String, List<String>> typeMapUrlListMap = Maps.newConcurrentMap();
        for (FilterTypeDto filterTypeDto : filterList) {
            List<FilterTypeDto.Url> urlList = filterTypeDto.getUrlList();
            for (FilterTypeDto.Url url : urlList) {
                if(url.getCount() == 0){
                    if(typeMapUrlListMap.containsKey(filterTypeDto.getType())){
                        typeMapUrlListMap.get(filterTypeDto.getType()).add(url.getUrl());
                    }else{
                        List<String> recommadationUrl = Lists.newArrayList();
                        recommadationUrl.add(url.getUrl());
                        typeMapUrlListMap.put(filterTypeDto.getType(), recommadationUrl);
                    }
                }
            }
        }
        return typeMapUrlListMap;
    }


    private String getProgram(String title){
        String[] parsed_url_components = title.split("：");
        if(parsed_url_components.length <= 1){
            return null;
        }

        return parsed_url_components[0];
    }

    public ModelResult recommendProgram(SearchInfoStatisticArg arg) {
        List<ChromeVisitUrlEntity> chromeVisitUrlList = chromeVisitUrlDao.listByLikeUrl(arg.getUid(), "mgtv.com");
        Map<String, Integer> programCountMap = Maps.newConcurrentMap();
        for (ChromeVisitUrlEntity chromeVisitUrlEntity : chromeVisitUrlList) {
            String program = getProgram(chromeVisitUrlEntity.getTitle());
            if(null != program){
                if(programCountMap.containsKey(program)){
                    programCountMap.put(program, programCountMap.get(program) + 1);
                }else {
                    programCountMap.put(program, 1);
                }
            }
        }

        return new ModelResult(SHErrorCode.SUCCESS);
    }
//    "select *from chrome_visit_url where url like '%mgtv.com%'"

    public ModelResult<List<UrlCountRankResult>> searchInfoStatistic(SearchInfoStatisticArg arg){
        List<ChromeVisitUrlEntity> chromeVisitUrlList = chromeVisitUrlDao.listByUid(arg.getUid());
        HashMap<String, Integer> urlMapCount = new HashMap();

        if(CollectionUtils.isNotEmpty(chromeVisitUrlList)){
            for (ChromeVisitUrlEntity chromeVisitUrl : chromeVisitUrlList) {
                String hostUrl = doHost(chromeVisitUrl.getUrl());
                if(null == hostUrl){
                    continue;
                }
                if(urlMapCount.containsKey(hostUrl)){
                    urlMapCount.put(hostUrl, urlMapCount.get(hostUrl) + chromeVisitUrl.getVisitCount());
                }else{
                    urlMapCount.put(hostUrl, chromeVisitUrl.getVisitCount());
                }
            }
        }
        Map<String, Integer> sorted  = urlMapCount
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));
        List<UrlCountRankResult> resultList = Lists.newArrayList();
//        int top = 0;
        for (Map.Entry<String, Integer> entry : sorted.entrySet()) {
//            if(top == 10){
//                break;
//            }
            UrlCountRankResult result = new UrlCountRankResult();
            result.setUrl(entry.getKey());
            result.setCount(entry.getValue());
            resultList.add(result);
//            top++;
        }
        Map<String, List<String>> recommadationResult = recommadation(resultList);
        return new ModelResult(SHErrorCode.SUCCESS, resultList);
    }





    public List<SearchResult> doBaiduSearch(String searchContent){

        String url = "https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd=" + searchContent;
        String pager = WebCrawlerUtil.getWebpage(url);
        String successTry = "div[class~=.*c-container]";

        Document doc = Jsoup.parse(pager);
        Elements div = doc.select(successTry);
        List<SearchResult> resultList = Lists.newArrayList();
        for (Element element : div) {
            Elements aHref = element.select("a");

            SearchResult result = new SearchResult();
            result.setUrl(aHref.get(0).attr("href"));
            result.setTitle(aHref.get(0).text());
            resultList.add(result);
        }
        return resultList;
    }


    public ModelResult<List<SearchResult>>  haiZeiWangSearch()  {
//        String url = "https://www.fzdm.com/manhua//2";
        String url = "http://op.hanhande.net/";
        String pager = WebCrawlerUtil.getWebpage(url);
        String successTry = "cite";

        Document doc = Jsoup.parse(pager);
        Elements div = doc.select(successTry);
        List<SearchResult> resultList = Lists.newArrayList();
        String needUrl = null;
        for (Element element : div) {
            Elements aHref = element.select("a");
            SearchResult result = new SearchResult();
            result.setUrl(aHref.get(0).attr("href"));
            result.setTitle(aHref.get(0).text());
            Pattern pTitle = Pattern.compile(".*938", Pattern.CASE_INSENSITIVE);
            Matcher aa = null;
            aa = pTitle.matcher(aHref.get(0).text());
            System.out.println("title:" + result.getTitle());
            if(aa.find()){
                needUrl = aHref.get(0).attr("href");
                break;
            }
            resultList.add(result);
        }


        String childPager = WebCrawlerUtil.getWebpage(needUrl);
        String childrenTag = "p[style~=text-align: center]";

        Document childDoc = Jsoup.parse(childPager);
        Elements tag = childDoc.select(childrenTag);
        List<SearchResult> childResult = Lists.newArrayList();
        for (Element element : tag) {
            Elements aHref = element.select("img");
            SearchResult result = new SearchResult();
            result.setUrl(aHref.get(0).attr("src"));
            result.setTitle(aHref.get(0).text());
            childResult.add(result);
        }
        return new ModelResult<>(SHErrorCode.SUCCESS, childResult);
    }



}
