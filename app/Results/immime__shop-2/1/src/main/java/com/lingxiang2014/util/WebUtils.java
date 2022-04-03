package com.lingxiang2014.util;
 import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;
import com.lingxiang2014.Setting;
public class WebUtils {

private WebUtils() {
}
public String[] getParameterValues(String queryString,String encoding,String name){
    return getParameterMap(queryString, encoding).get(name);
}


public void addCookie(HttpServletRequest request,HttpServletResponse response,String name,String value){
    Setting setting = SettingUtils.get();
    addCookie(request, response, name, value, null, setting.getCookiePath(), setting.getCookieDomain(), null);
}


public void putMapEntry(Map<String,String[]> map,String name,String value){
    String[] newValues = null;
    String[] oldValues = map.get(name);
    if (oldValues == null) {
        newValues = new String[] { value };
    } else {
        newValues = new String[oldValues.length + 1];
        System.arraycopy(oldValues, 0, newValues, 0, oldValues.length);
        newValues[oldValues.length] = value;
    }
    map.put(name, newValues);
}


public void removeCookie(HttpServletRequest request,HttpServletResponse response,String name){
    Setting setting = SettingUtils.get();
    removeCookie(request, response, name, setting.getCookiePath(), setting.getCookieDomain());
}


public byte convertHexDigit(byte b){
    if ((b >= '0') && (b <= '9')) {
        return (byte) (b - '0');
    }
    if ((b >= 'a') && (b <= 'f')) {
        return (byte) (b - 'a' + 10);
    }
    if ((b >= 'A') && (b <= 'F')) {
        return (byte) (b - 'A' + 10);
    }
    throw new IllegalArgumentException();
}


public Map<String,String[]> getParameterMap(String queryString,String encoding){
    Map<String, String[]> parameterMap = new HashMap<String, String[]>();
    Charset charset = Charset.forName(encoding);
    if (StringUtils.isNotEmpty(queryString)) {
        byte[] bytes = queryString.getBytes(charset);
        if (bytes != null && bytes.length > 0) {
            int ix = 0;
            int ox = 0;
            String key = null;
            String value = null;
            while (ix < bytes.length) {
                byte c = bytes[ix++];
                switch((char) c) {
                    case '&':
                        value = new String(bytes, 0, ox, charset);
                        if (key != null) {
                            putMapEntry(parameterMap, key, value);
                            key = null;
                        }
                        ox = 0;
                        break;
                    case '=':
                        if (key == null) {
                            key = new String(bytes, 0, ox, charset);
                            ox = 0;
                        } else {
                            bytes[ox++] = c;
                        }
                        break;
                    case '+':
                        bytes[ox++] = (byte) ' ';
                        break;
                    case '%':
                        bytes[ox++] = (byte) ((convertHexDigit(bytes[ix++]) << 4) + convertHexDigit(bytes[ix++]));
                        break;
                    default:
                        bytes[ox++] = c;
                }
            }
            if (key != null) {
                value = new String(bytes, 0, ox, charset);
                putMapEntry(parameterMap, key, value);
            }
        }
    }
    return parameterMap;
}


public String getCookie(HttpServletRequest request,String name){
    Assert.notNull(request);
    Assert.hasText(name);
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        try {
            name = URLEncoder.encode(name, "UTF-8");
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return URLDecoder.decode(cookie.getValue(), "UTF-8");
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    return null;
}


public String getParameter(String queryString,String encoding,String name){
    String[] parameterValues = getParameterMap(queryString, encoding).get(name);
    return parameterValues != null && parameterValues.length > 0 ? parameterValues[0] : null;
}


}