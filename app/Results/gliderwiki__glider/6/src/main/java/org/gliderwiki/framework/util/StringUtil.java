package org.gliderwiki.framework.util;
 import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
public class StringUtil {

 public  String YES;

 public  String NO;

 public  String EMPTY;

 public  String[] EMPTY_ARRAY;

 public  String ENCODING_CHARSET;


public String cut(String str,Integer maxLength){
    if (isEmpty(str)) {
        return str;
    }
    if (maxLength < 0) {
        return str;
    }
    if (str.length() <= maxLength) {
        return str;
    }
    String cutStr = str.substring(0, maxLength) + "...";
    return cutStr;
}


public String strCut(String str,Integer size){
    // Vector returnsStr = new Vector();
    // String ctmp = str.replaceAll("'", "''");
    int cntlen = str.getBytes().length;
    int bylen = 0, strlen = str.length();
    char c;
    String ct = "";
    if (cntlen > size) {
        for (int i = 0; i < strlen; i++) {
            c = str.charAt(i);
            bylen++;
            if (c > 127) {
                // 한글이다..
                bylen++;
            }
            if (size < bylen) {
                ct = str.substring(0, i);
                break;
            }
        }
        ct = ct + "...";
    } else {
        ct = str;
    }
    return ct;
}


public String cutAndRemoveTag(String str,Integer len){
    return strCut(removeTag(str), len);
}


public String removeTags(String contents){
    StringBuffer source = new StringBuffer(contents);
    StringBuffer target = new StringBuffer();
    boolean deleteChar = false;
    for (int i = 0; i < source.length(); i++) {
        if (source.charAt(i) == '<') {
            deleteChar = true;
        } else if (source.charAt(i) == '>') {
            deleteChar = false;
        } else if (!deleteChar) {
            target.append(source.charAt(i));
        }
    }
    return target.toString();
}


public String remakeXSS(String str){
    String strVal = str;
    strVal = strVal.replaceAll("&lt;", "<").replaceAll("&gt;", ">");
    strVal = strVal.replaceAll("&#40;", "\\(").replaceAll("&#41;", "\\)");
    strVal = strVal.replaceAll("&#39;", "'");
    return strVal;
}


public String cutByteString(String source,int length){
    return cutByteString(source, length, "");
}


public String strNull(String str){
    String strVal = str;
    if (strVal == null || strVal.length() < 1) {
        return "";
    }
    return strVal;
}


public String stringBuffersChars(int len){
    StringBuffer buffer = new StringBuffer();
    Random random = new Random();
    String[] chars = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z".split(",");
    for (int i = 0; i < len; i++) {
        buffer.append(chars[random.nextInt(chars.length)]);
    }
    return buffer.toString();
}


public String cleanXSS(String str){
    String strVal = str;
    strVal = strVal.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
    strVal = strVal.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
    strVal = strVal.replaceAll("'", "&#39;");
    strVal = strVal.replaceAll("eval\\((.*)\\)", "");
    strVal = strVal.replaceAll("[\\\"\\'][\\s]*javascript:(.*)[\\\"\\']", "\"\"");
    strVal = strVal.replaceAll("[\\\"\\'][\\s]*JAVASCRIPT:(.*)[\\\"\\']", "\"\"");
    strVal = strVal.replaceAll("&lt;/script&gt;", "");
    strVal = strVal.replaceAll("&lt;SCRIPT&gt;", "");
    strVal = strVal.replaceAll("&lt;/script&gt;", "");
    strVal = strVal.replaceAll("&lt;SCRIPT&gt;", "");
    return strVal;
}


public String getFileExt(String filePath){
    int index = filePath.lastIndexOf(".");
    if (index == -1) {
        return "";
    }
    return filePath.substring(index + 1, filePath.length());
}


public String removeTag(String s){
    if (s == null || s.equals("")) {
        s = "";
    } else {
        // s =
        // s.replaceAll("(?:<!.*?(?:--.*?--\\s*)*.*?>)|(?:<(?:[^>'\"]*|\".*?\"|'.*?')+>)","");
        Pattern p = Pattern.compile("\\<(\\/?)(\\w+)*([^<>]*)>");
        Matcher m = p.matcher(s);
        s = m.replaceAll("");
    // s = s.replaceAll("&nbsp;", "");
    }
    return s;
}


public String removeScript(String s){
    if (s == null || s.equals("")) {
        s = "";
    } else {
        Pattern p = Pattern.compile("\\<(\\/?)(script|style|SCRIPT|STYLE)([^<>]*)>");
        Matcher m = p.matcher(s);
        s = m.replaceAll("");
    }
    return s;
}


public String replaceEx(String str,String from,String to){
    String sResult = "";
    try {
        if (str == null || str.length() == 0 || from == null || from.length() == 0 || to == null || to.length() == 0)
            return str;
        StringBuffer sb = null;
        sb = new StringBuffer(str.length() * 2);
        String posString = str.toLowerCase();
        String cmpString = from.toLowerCase();
        int i = 0;
        boolean done = false;
        while (i < str.length() && !done) {
            int start = posString.indexOf(cmpString, i);
            if (start == -1) {
                done = true;
            } else {
                sb.append(str.substring(i, start) + to);
                i = start + from.length();
            }
        }
        if (i < str.length()) {
            sb.append(str.substring(i));
        }
        sResult = sb.toString();
    } catch (Exception e) {
        sResult = str;
    } finally {
    }
    return sResult;
}


public String[] getSMSMessage(String msg){
    Charset charset = Charset.forName(StringUtil.ENCODING_CHARSET);
    byte[] bytes = msg.getBytes(charset);
    // 문자 분할
    List<String> messages = new ArrayList<String>();
    if (bytes.length <= 40) {
        messages.add(msg);
    } else {
        int begin = 0;
        int finish = 40;
        boolean doParse = true;
        while (doParse) {
            int counter = 0;
            for (int i = finish - 1; i >= begin; i--) {
                if ((bytes[i] & 0x80) == 0)
                    break;
                counter++;
            }
            int length = 40 - (counter % 3);
            if (finish - begin < 40) {
                length = finish - begin;
            }
            messages.add(new String(bytes, begin, length, charset));
            if (finish >= bytes.length) {
                doParse = false;
            }
            begin += length;
            finish = begin + 40;
            if (finish > bytes.length) {
                finish = bytes.length;
            }
        // System.out.println(begin);
        // System.out.println(finish);
        }
    }
    String[] arr = new String[messages.size()];
    return messages.toArray(arr);
}


public String cutString(String source,int length){
    return cutString(source, length, "");
}


public boolean hasLength(String str){
    return (str != null && str.length() > 0);
}


public String convertImgURLEncode(String contents,String enc){
    String returnContents = contents;
    if (contents == null || enc == null)
        return null;
    int endIdx = 0;
    for (int i = 0; i < contents.length(); i = endIdx + 1) {
        int imgIdx = contents.indexOf("<IMG", i);
        if (imgIdx == -1)
            break;
        int srcIdx = contents.indexOf("src", imgIdx);
        if (srcIdx == -1)
            break;
        int beginIdx = contents.indexOf("\"", srcIdx);
        if (beginIdx == -1)
            break;
        endIdx = contents.indexOf("\"", beginIdx + 1);
        if (endIdx == -1)
            break;
        String replaceStr = contents.substring(beginIdx + 1, endIdx);
        returnContents = contents.replace(replaceStr, URLEncoder.encode(replaceStr, enc));
    }
    return returnContents;
}


public String strNullToSpace(String str,String strDefault){
    String strVal = str;
    if (strVal == null || strVal.length() < 1) {
        return strDefault;
    }
    return strVal;
}


public boolean isEmpty(String str){
    if (str == null || str.length() < 1)
        return true;
    return false;
}


public boolean wildCardMatch(String text,String pattern){
    String[] blocks = pattern.split("\\*");
    for (String block : blocks) {
        int idx = text.indexOf(block);
        if (idx == -1) {
            return false;
        }
        text = text.substring(idx + block.length());
    }
    return true;
}


public boolean hasText(String text){
    if (!hasLength(text)) {
        return false;
    }
    for (int i = 0, l = text.length(); i < l; i++) {
        if (!Character.isWhitespace(text.charAt(i))) {
            return true;
        }
    }
    return false;
}


public String getURL(HttpServletRequest request){
    Enumeration<?> param = request.getParameterNames();
    StringBuffer strParam = new StringBuffer();
    StringBuffer strURL = new StringBuffer();
    if (param.hasMoreElements()) {
        strParam.append("?");
    }
    while (param.hasMoreElements()) {
        String name = (String) param.nextElement();
        String value = request.getParameter(name);
        strParam.append(name + "=" + value);
        if (param.hasMoreElements()) {
            strParam.append("&");
        }
    }
    strURL.append(request.getRequestURI());
    strURL.append(strParam);
    return strURL.toString();
}


public String convertHtmlBr(String comment){
    int length = comment.length();
    StringBuffer buffer = new StringBuffer();
    if (comment.indexOf("<table") == -1) {
        for (int i = 0; i < length; ++i) {
            String comp = comment.substring(i, i + 1);
            if ("\r".compareTo(comp) == 0) {
                comp = comment.substring(++i, i + 1);
                if ("\n".compareTo(comp) == 0) {
                    buffer.append("<BR>\r");
                } else {
                    buffer.append("\r");
                }
            }
            buffer.append(comp);
        }
    } else {
        return comment;
    }
    return buffer.toString();
}


public String replaceHtmlTag(String sHTML){
    if (sHTML == null || sHTML.trim().equals(""))
        return "";
    String sResult = "";
    StringBuffer sbResult = null;
    try {
        String s = "";
        sbResult = new StringBuffer();
        for (int i = 0; i < sHTML.length(); i++) {
            s = sHTML.substring(i, i + 1);
            if (s.equals("<")) {
                sbResult.append("&lt;");
            } else if (s.equals(">")) {
                sbResult.append("&gt;");
            } else if (s.equals("\"")) {
                sbResult.append("&quot;");
            } else if (s.equals("'")) {
                sbResult.append("&#39;");
            } else if (s.equals("&")) {
                sbResult.append("&amp;");
            } else {
                sbResult.append(s);
            }
        }
        sResult = sbResult.toString();
    } finally {
        sbResult = null;
    }
    sResult = replaceEx(sResult, "/*", "");
    sResult = replaceEx(sResult, "*/", "");
    sResult = replaceEx(sResult, "%", "");
    sResult = replaceEx(sResult, "%00", "");
    return sResult;
}


public boolean checkScriptTag(String data){
    boolean bExistTag = false;
    if (data == null || data.length() <= 0) {
        return false;
    }
    try {
        // new String(data);
        String sHTML = data;
        sHTML = sHTML.toLowerCase().replace(" ", "");
        sHTML = sHTML.replace("\n", "");
        if (sHTML.length() <= 0)
            return false;
        if (sHTML.indexOf("<script") != -1 || sHTML.indexOf("<iframe") != -1 || sHTML.indexOf("style") != -1 || sHTML.indexOf("src") != -1) {
            bExistTag = true;
        }
    } catch (Exception e) {
        throw new Exception(e);
    } finally {
    }
    return bExistTag;
}


public String nullToDate(String str){
    if (str == null || str.length() < 1) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", java.util.Locale.KOREA);
        return dateFormat.format(new java.util.Date());
    }
    return str;
}


public String text2html(String contents){
    contents = contents.replaceAll("&lt;", "<");
    contents = contents.replaceAll("&gt;", ">");
    contents = contents.replaceAll("&nbsp;", " ");
    return contents;
}


public int patternMatchCount(String str,String delim){
    Pattern p = Pattern.compile(delim);
    Matcher m = p.matcher(str);
    int count = 0;
    for (int i = 0; m.find(i); i = m.end()) {
        count++;
    }
    return count;
}


public Integer intNull(Integer strInt){
    if (strInt == null) {
        return 0;
    }
    return strInt;
}


public String getThumbnailYn(String extendname){
    if (extendname.equalsIgnoreCase("jpg") || extendname.equalsIgnoreCase("png") || extendname.equalsIgnoreCase("gif")) {
        return "Y";
    } else {
        return "N";
    }
}


public String getRandomKey(){
    String temp = String.valueOf(System.nanoTime());
    String randomKey = temp.substring(temp.length() - 8, temp.length() - 4);
    return randomKey;
}


}