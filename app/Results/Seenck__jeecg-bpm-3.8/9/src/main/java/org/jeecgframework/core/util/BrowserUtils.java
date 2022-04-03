package org.jeecgframework.core.util;
 import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
public class BrowserUtils {

 private  String IE11;

 private  String IE10;

 private  String IE9;

 private  String IE8;

 private  String IE7;

 private  String IE6;

 private  String MAXTHON;

 private  String QQ;

 private  String GREEN;

 private  String SE360;

 private  String FIREFOX;

 private  String OPERA;

 private  String CHROME;

 private  String SAFARI;

 private  String OTHER;

 private  Map<String,String> langMap;

 private  String ZH;

 private  String ZH_CN;

 private  String EN;

 private  String EN_US;


public String checkBrowse(HttpServletRequest request){
    String userAgent = request.getHeader("USER-AGENT");
    if (regex(OPERA, userAgent))
        return OPERA;
    if (regex(CHROME, userAgent))
        return CHROME;
    if (regex(FIREFOX, userAgent))
        return FIREFOX;
    if (regex(SAFARI, userAgent))
        return SAFARI;
    if (regex(SE360, userAgent))
        return SE360;
    if (regex(GREEN, userAgent))
        return GREEN;
    if (regex(QQ, userAgent))
        return QQ;
    if (regex(MAXTHON, userAgent))
        return MAXTHON;
    if (regex(IE11, userAgent))
        return IE11;
    if (regex(IE10, userAgent))
        return IE10;
    if (regex(IE9, userAgent))
        return IE9;
    if (regex(IE8, userAgent))
        return IE8;
    if (regex(IE7, userAgent))
        return IE7;
    if (regex(IE6, userAgent))
        return IE6;
    return OTHER;
}


public boolean regex(String regex,String str){
    Pattern p = Pattern.compile(regex, Pattern.MULTILINE);
    Matcher m = p.matcher(str);
    return m.find();
}


public Double getIEversion(HttpServletRequest request){
    Double version = 0.0;
    if (getBrowserType(request, IE11)) {
        version = 11.0;
    } else if (getBrowserType(request, IE10)) {
        version = 10.0;
    } else if (getBrowserType(request, IE9)) {
        version = 9.0;
    } else if (getBrowserType(request, IE8)) {
        version = 8.0;
    } else if (getBrowserType(request, IE7)) {
        version = 7.0;
    } else if (getBrowserType(request, IE6)) {
        version = 6.0;
    }
    return version;
}


public boolean isIE(HttpServletRequest request){
    return (request.getHeader("USER-AGENT").toLowerCase().indexOf("msie") > 0 || request.getHeader("USER-AGENT").toLowerCase().indexOf("rv:11.0") > 0) ? true : false;
}


public String getBrowserLanguage(HttpServletRequest request){
    String browserLang = request.getLocale().getLanguage();
    String browserLangCode = (String) langMap.get(browserLang);
    if (browserLangCode == null) {
        browserLangCode = EN_US;
    }
    return browserLangCode;
}


public boolean getBrowserType(HttpServletRequest request,String brosertype){
    return request.getHeader("USER-AGENT").toLowerCase().indexOf(brosertype) > 0 ? true : false;
}


}