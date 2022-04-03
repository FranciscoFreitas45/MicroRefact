package com.sobey.framework.utils;
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
import com.google.common.net.HttpHeaders;
public class Servlets {

 public  long ONE_YEAR_SECONDS;


public void setLastModifiedHeader(HttpServletResponse response,long lastModifiedDate){
    response.setDateHeader(HttpHeaders.LAST_MODIFIED, lastModifiedDate);
}


public void setFileDownloadHeader(HttpServletResponse response,String fileName){
    try {
        // 中文文件名支持
        String encodedfileName = new String(fileName.getBytes(), "ISO8859-1");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedfileName + "\"");
    } catch (UnsupportedEncodingException e) {
    }
}


@SuppressWarnings("rawtypes")
public Map<String,Object> getParametersStartingWith(ServletRequest request,String prefix){
    Validate.notNull(request, "Request must not be null");
    Enumeration paramNames = request.getParameterNames();
    Map<String, Object> params = new TreeMap<String, Object>();
    if (prefix == null) {
        prefix = "";
    }
    while (paramNames != null && paramNames.hasMoreElements()) {
        String paramName = (String) paramNames.nextElement();
        if ("".equals(prefix) || paramName.startsWith(prefix)) {
            String unprefixed = paramName.substring(prefix.length());
            String[] values = request.getParameterValues(paramName);
            if (values == null || values.length == 0) {
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
    if (params == null || params.size() == 0) {
        return "";
    }
    if (prefix == null) {
        prefix = "";
    }
    StringBuilder queryStringBuilder = new StringBuilder();
    Iterator<Entry<String, Object>> it = params.entrySet().iterator();
    while (it.hasNext()) {
        Entry<String, Object> entry = it.next();
        queryStringBuilder.append(prefix).append(entry.getKey()).append('=').append(entry.getValue());
        if (it.hasNext()) {
            queryStringBuilder.append('&');
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


public void setExpiresHeader(HttpServletResponse response,long expiresSeconds){
    // Http 1.0 header, set a fix expires date.
    response.setDateHeader(HttpHeaders.EXPIRES, System.currentTimeMillis() + expiresSeconds * 1000);
    // Http 1.1 header, set a time after now.
    response.setHeader(HttpHeaders.CACHE_CONTROL, "private, max-age=" + expiresSeconds);
}


public void setEtag(HttpServletResponse response,String etag){
    response.setHeader(HttpHeaders.ETAG, etag);
}


}