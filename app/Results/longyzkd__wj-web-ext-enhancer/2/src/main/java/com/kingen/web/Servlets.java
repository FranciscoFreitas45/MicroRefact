package com.kingen.web;
 import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.Validate;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.google.common.net.HttpHeaders;
import com.kingen.bean.User;
import com.kingen.util.Encodes;
import com.kingen.util.Global;
import com.kingen.util.StringUtils;
import com.kingen.util.UserUtils;
public class Servlets {

 public  long ONE_YEAR_SECONDS;

 private  String[] staticFiles;

 private  String urlSuffix;


public void setLastModifiedHeader(HttpServletResponse response,long lastModifiedDate){
    response.setDateHeader(HttpHeaders.LAST_MODIFIED, lastModifiedDate);
}


public boolean isAjaxRequest(HttpServletRequest request){
    String accept = request.getHeader("accept");
    String xRequestedWith = request.getHeader("X-Requested-With");
    User user = (User) SecurityUtils.getSubject().getPrincipal();
    // Principal principal = UserUtils.getPrincipal();
    // 如果是异步请求或是手机端，则直接返回信息
    return ((accept != null && accept.indexOf("application/json") != -1 || (xRequestedWith != null && xRequestedWith.indexOf("XMLHttpRequest") != -1) || // || (principal != null && principal.isMobileLogin())));
    (user != null)));
}


public HttpServletRequest getRequest(){
    try {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    } catch (Exception e) {
        return null;
    }
}


public boolean isStaticFile(String uri){
    if (staticFiles == null) {
        try {
            throw new Exception("检测到“app.properties”中没有配置“web.staticFile”属性。配置示例：\n#静态文件后缀\n" + "web.staticFile=.css,.js,.png,.jpg,.gif,.jpeg,.bmp,.ico,.swf,.psd,.htc,.crx,.xpi,.exe,.ipa,.apk");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // if ((StringUtils.startsWith(uri, "/static/") || StringUtils.endsWithAny(uri, sfs))
    // && !StringUtils.endsWithAny(uri, ".jsp") && !StringUtils.endsWithAny(uri, ".java")){
    // return true;
    // }
    if (StringUtils.endsWithAny(uri, staticFiles) && !StringUtils.endsWithAny(uri, urlSuffix) && !StringUtils.endsWithAny(uri, ".jsp") && !StringUtils.endsWithAny(uri, ".java")) {
        return true;
    }
    return false;
}


public void setExpiresHeader(HttpServletResponse response,long expiresSeconds){
    // Http 1.0 header, set a fix expires date.
    response.setDateHeader(HttpHeaders.EXPIRES, System.currentTimeMillis() + expiresSeconds * 1000);
    // Http 1.1 header, set a time after now.
    response.setHeader(HttpHeaders.CACHE_CONTROL, "private, max-age=" + expiresSeconds);
}


public void setFileDownloadHeader(HttpServletResponse response,String fileName){
    try {
        // 中文文件名支持
        String encodedfileName = new String(fileName.getBytes(), "ISO8859-1");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedfileName + "\"");
    } catch (UnsupportedEncodingException e) {
        e.getMessage();
    }
}


@SuppressWarnings("rawtypes")
public Map<String,Object> getParametersStartingWith(ServletRequest request,String prefix){
    Validate.notNull(request, "Request must not be null");
    Enumeration paramNames = request.getParameterNames();
    Map<String, Object> params = new TreeMap<String, Object>();
    String pre = prefix;
    if (pre == null) {
        pre = "";
    }
    while (paramNames != null && paramNames.hasMoreElements()) {
        String paramName = (String) paramNames.nextElement();
        if ("".equals(pre) || paramName.startsWith(pre)) {
            String unprefixed = paramName.substring(pre.length());
            String[] values = request.getParameterValues(paramName);
            if (values == null || values.length == 0) {
                values = new String[] {};
            // Do nothing, no values found at all.
            } else if (values.length > 1) {
                params.put(unprefixed, values);
            } else {
                params.put(unprefixed, values[0]);
            }
        }
    }
    return params;
}


public String encodeParameterStringWithPrefix(Map<String,Object> params,String prefix){
    StringBuilder queryStringBuilder = new StringBuilder();
    String pre = prefix;
    if (pre == null) {
        pre = "";
    }
    Iterator<Entry<String, Object>> it = params.entrySet().iterator();
    while (it.hasNext()) {
        Entry<String, Object> entry = it.next();
        queryStringBuilder.append(pre).append(entry.getKey()).append("=").append(entry.getValue());
        if (it.hasNext()) {
            queryStringBuilder.append("&");
        }
    }
    return queryStringBuilder.toString();
}


public void setNoCacheHeader(HttpServletResponse response){
    // Http 1.0 header
    response.setDateHeader(HttpHeaders.EXPIRES, 1L);
    response.addHeader(HttpHeaders.PRAGMA, "no-cache");
    // Http 1.1 header
    response.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, max-age=0");
}


public boolean checkIfNoneMatchEtag(HttpServletRequest request,HttpServletResponse response,String etag){
    String headerValue = request.getHeader(HttpHeaders.IF_NONE_MATCH);
    if (headerValue != null) {
        boolean conditionSatisfied = false;
        if (!"*".equals(headerValue)) {
            StringTokenizer commaTokenizer = new StringTokenizer(headerValue, ",");
            while (!conditionSatisfied && commaTokenizer.hasMoreTokens()) {
                String currentToken = commaTokenizer.nextToken();
                if (currentToken.trim().equals(etag)) {
                    conditionSatisfied = true;
                }
            }
        } else {
            conditionSatisfied = true;
        }
        if (conditionSatisfied) {
            response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
            response.setHeader(HttpHeaders.ETAG, etag);
            return false;
        }
    }
    return true;
}


public String encodeHttpBasic(String userName,String password){
    String encode = userName + ":" + password;
    return "Basic " + Encodes.encodeBase64(encode.getBytes());
}


public boolean checkIfModifiedSince(HttpServletRequest request,HttpServletResponse response,long lastModified){
    long ifModifiedSince = request.getDateHeader(HttpHeaders.IF_MODIFIED_SINCE);
    if ((ifModifiedSince != -1) && (lastModified < ifModifiedSince + 1000)) {
        response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
        return false;
    }
    return true;
}


public void setEtag(HttpServletResponse response,String etag){
    response.setHeader(HttpHeaders.ETAG, etag);
}


}