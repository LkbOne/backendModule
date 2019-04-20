package com.example.hugh.life.commmon.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrawlerContent {
    // 定义script的正则表达式
    public static final String REGEX_SCRIPT = "<script[^>]*?>[\\s\\S]*?<\\/script>";
    // 定义style的正则表达式
    public static final String REGEX_STYLE = "<style[^>]*?>[\\s\\S]*?<\\/style>";
    // 定义HTML标签的正则表达式
    public static final String REGEX_HTML = "<[^>]+>";
    // 定义空格回车换行符
    public static final String REGEX_SPACE = "\\s*|\t|\r|\n";
    public static final String REGEX_NBSP = "&nbsp;";
    public static final String URL_REGEX = "(((http|ftp|https)://)|(www\\.))[a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6}(:[0-9]{1,4})?(/[a-zA-Z0-9\\&%_\\./-~-]*)?";
    public static final String VIDEO_COVER = "(<txpdiv)(.*)(class=\"txp_poster\")(.*)(&quot;\\);)";
    // 标题
    public static String TITLE_REG = "var msg_title = \"(.*?)\"";
    //所有图片
    public static String IMG_REG = "<img(.*?)>";
    //所有照片
    public static String PIC_REG = "data-src=\"(.*?)\" ";
    // 日期
    public static String DATE_REG = "var publish_time = \"(.*?)\"";
    // 作者
    public static String CREATOR_REG = "<span class=\"rich_media_meta rich_media_meta_text\">(.*?)</span>";
    // 作者(原创非本人，不可直接使用)
    public static String CREATOR_REG_PLUS = "<span (.*)>(.*)";
    // 微信号
    public static String WX_NUM_REG = "<span class=\"profile_meta_value\">(.*?)</span>";
    // 内容
    public static String JS_CONTENT_REG = "id=\"js_content\">(.*?)</div>";
    public static String TEST_CONTENT = "";

    // 通过js获取文章的封面
    public static String JS_MSG_CDN_URL = "var msg_cdn_url = \"(.*?)\"";
    public static Pattern JS_MSG_CDN_URL_PATTERN = Pattern.compile(JS_MSG_CDN_URL);

    public static void main(String[] args) {
        String str = " <txpdiv class=\"txp_ad_skip_text\">进入广告</txpdiv>      </txpdiv>  </txpdiv><txpdiv data-role=\"hd-adapter-interactivelayer\" class=\"txp_none\" style=\"position: absolute; top: 0px; right: 0px; width: 100%; height: 100%; z-index: 4; pointer-events: none;\"></txpdiv><txpdiv data-role=\"hd-adapter-adlayer\" class=\"txp_none\" style=\"position: absolute; top: 0px; right: 0px; width: 100%; height: 100%; z-index: 4; background-color: rgb(0, 0, 0);\"></txpdiv><txpdiv data-role=\"txp-ui-poster\" class=\"txp_poster\" style=\"background-image: url(&quot;https://vpic.video.qq.com/-78179631/l07161m6en0.png&quot;); background-size: cover;\"></txpdiv><txpdiv class=\"txp_overlay_loading\">    <txpdiv class=\"txp_text txp_none\" data-role=\"txp-ui-continue-play\"></txpdiv>    <txpdiv class=\"txp_icon_loading txp_none\" data-role=\"txp-ui-loading\"></txpdiv>  </txpdiv><txpdiv data-role=\"txp-ui-tips\" class=\"txp_alert_info txp_none\">    <txpdiv class=\"txp_alert_content\">      <txpdiv data-role=\"txp-ui-tips-text\" class=\"txp_alert_text\"></txpdiv> ";
        Pattern pattern = Pattern.compile(VIDEO_COVER, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            System.out.println(matcher.group());
            String group = matcher.group();
            String[] split = group.split("&quot;");
            System.out.println(split[1]);
        }

        String str1 = "<script async=\"\" src=\"//vm.gtimg.cn/c/=/tencentvideo/txp/js/plugins/htmlframe.824790.js,v4hdplayer.35f45f.js,uishadow.75025f.js,v4hdadapter.dea405.js,uiposter.1c348c.js,v4h5report.f06104.js,v4hdplayerreport.45663c.js,uiloading.3e2cb6.js,uiloadingwithad.79b0ab.js,hdplayerhistory.be0e2b.js,hlshelper.79722e.js,v4hdplayercontrol.e1f861.js,downloadmonitor.3b18a9.js,v4uierror.84dc79.js,uitips.c5ff43.js,uicontrol.ac7494.js,uiprogress.55d3b5.js,v4uicontrolplay.4771d5.js,uiplaynext.ed3e6a.js,uiloopplay.712021.js?max_age=604800&amp;_ts=1531197691368\" charset=\"utf-8\">";
        String s = str1.replaceAll("=\"//", "=\"https:\\/\\/");
        System.out.println(s);
    }

    public static String delHTMLTag(String htmlStr) {

        // 过滤script标签
        Pattern p_script = Pattern.compile(REGEX_SCRIPT, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll("");

        // 过滤style标签
        Pattern p_style = Pattern.compile(REGEX_STYLE, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll("");

        // 过滤html标签
        Pattern p_html = Pattern.compile(REGEX_HTML, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll("");

        // 过滤空格回车标签
        Pattern p_space = Pattern.compile(REGEX_SPACE, Pattern.CASE_INSENSITIVE);
        Matcher m_space = p_space.matcher(htmlStr);
        htmlStr = m_space.replaceAll("");

        // 过滤空格回车标签
        Pattern p_nbsp = Pattern.compile(REGEX_NBSP, Pattern.CASE_INSENSITIVE);
        Matcher m_nbsp = p_nbsp.matcher(htmlStr);
        htmlStr = m_nbsp.replaceAll("");

        return htmlStr.trim();
    }
}
