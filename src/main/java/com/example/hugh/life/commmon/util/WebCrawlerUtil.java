package com.example.hugh.life.commmon.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 网络爬虫
 */
@Slf4j
public class WebCrawlerUtil {
    public static final String FORMAT = "UTF-8";
    public static final String EXT = "html";

    public static String getWebpage(String url) {
        StringBuilder content = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            HttpEntity httpEntity = getHttpEntity(url);
            bufferedReader = new BufferedReader(new InputStreamReader(httpEntity.getContent(), "utf-8"), 8 * 1024);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (null != bufferedReader) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return content.toString();
        }
    }

    public static HttpEntity getHttpEntity(String url) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
//            httpGet.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            httpGet.addHeader("Content-Type", "text/html;charset=utf-8");
//            httpGet.addHeader("accept-language", "zh-CN,zh;q=0.9,zh-TW;q=0.8");
//            httpGet.addHeader("accept-encoding", "gzip, deflate, br");
             httpGet.addHeader("user-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36");
            HttpResponse response = httpClient.execute(httpGet);
            return response.getEntity();
        } catch (Exception e) {
            log.error("Error: getHttpEntity! url:{}", url, e);
            return null;
        }
    }

    /**
     * 根据正则取对应内容
     *
     * @param isNeedSecondSearch 是否需要二次搜索
     * @param secondSearchReg 二次搜索正则
     */
    public static List<String> getContentByReg(String content, String reg, Boolean isNeedSecondSearch, String secondSearchReg) {
        List<String> result = new ArrayList<String>();
        try {
            Matcher m = Pattern.compile(reg).matcher(content);
            while (m.find()) {
                //通过正则表达式占位符来获取需要的内容
                if (isNeedSecondSearch) {
                    // 若需二次搜索
                    if (m.group(1).trim().matches(secondSearchReg)) {
                        m = Pattern.compile(secondSearchReg).matcher(m.group(1).trim());
                        while (m.find()) {
                            result.add(m.group(2));
                        }
                    } else {
                        result.add(m.group(1));
                    }
                } else {
                    result.add(m.group(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据正则pattern取对应内容
     *
     * @param content
     * @param pattern
     * @param isNeedSecondSearch 是否需要二次搜索
     * @param secondCondition    二次搜索条件
     * @param secondPattern
     * @return
     */
    public static List<String> getContentByPattern(String content, Pattern pattern, Boolean isNeedSecondSearch, String secondCondition, Pattern secondPattern) {
        List<String> result = new ArrayList<String>();
        try {
            Matcher m = pattern.matcher(content);
            while (m.find()) {
                //通过正则表达式占位符来获取需要的内容
                if (isNeedSecondSearch) {
                    // 若需二次搜索
                    if (m.group(1).trim().matches(secondCondition)) {
                        m = secondPattern.matcher(m.group(1).trim());
                        while (m.find()) {
                            result.add(m.group(2));
                        }
                    } else {
                        result.add(m.group(1));
                    }
                } else {
                    result.add(m.group(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据正则pattern取满足条件的首个内容
     *
     * @param content
     * @param pattern
     * @return
     */
    public static String getFirstContentByPattern(String content, Pattern pattern) {
        List<String> result = getContentByPattern(content, pattern, false, null, null);
        if (CollectionUtils.isEmpty(result)) {
            return null;
        }
        return result.get(0);
    }

    public static boolean isUrl(String url) {
        Pattern urlPattern = Pattern.compile(CrawlerContent.URL_REGEX);
        Matcher matcher = urlPattern.matcher(url);
        return matcher.find();
    }

    public static boolean isWXUrl(String url) {
        return url.contains("weixin.qq.com") ? true : false;
    }

    public static void main(String[] args) {
        String successTry = "div[class~=.*c-container]";
        String searchContent = "海贼王";

//        String url = "https://www.google.com/search?source=hp&ei=1VWTXKnlM4a50PEPgK-BiAE&q=AA&btnK=Google+%E6%90%9C%E7%B4%A2&oq=AA&gs_l=psy-ab.3..35i39l2j0l8.275.423..802...0.0..0.510.743.2-1j5-1......0....1..gws-wiz.....0.N3riuQzHDuQ";
        String url = "https://music.douban.com/subject_search?search_text=" + searchContent +"&cat=103";
        String pager = getWebpage(url);

//        FileUtil.writeFile(getWebpage(url));
//        Matcher matcher = pattern.matcher();
//        if(matcher.find()){
//            MatchResult aa  = matcher.toMatchResult();
//            System.out.println("count:" + matcher.group(0));
//        }
        System.out.println("finish");
    }
}