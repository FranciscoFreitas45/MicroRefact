package com.xwtec.xwserver.util;
 import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
public class StringUtil {

 public  String[] arrTest;

 public  String[] arrParam;

 public  String[] arrCode;


public String replaceMark(String content){
    String strContent = "";
    String strMark = "<\\s*[^>]*>";
    strContent = content.trim();
    strContent = strContent.replaceAll("\"", "");
    strContent = strContent.replaceAll("\'", "");
    // 删除所有<>标记
    strContent = strContent.replaceAll(strMark, "");
    strContent = strContent.replaceAll("&nbsp;", "");
    strContent = strContent.replaceAll(" ", "");
    strContent = strContent.replaceAll("　", "");
    strContent = strContent.replaceAll("\r", "");
    strContent = strContent.replaceAll("\n", "");
    strContent = strContent.replaceAll("\r\n", "");
    return strContent;
}


public String replaceHtmlToChar(String content){
    String strContent = content;
    strContent = strContent.replaceAll("&lt;", "<");
    strContent = strContent.replaceAll("&gt;", ">");
    strContent = strContent.replaceAll("&quot;", "\"");
    return strContent;
}


public String replaceCharToSql(String content){
    String strContent = content;
    strContent = strContent.replaceAll("%", "\\\\%");
    return strContent;
}


public Map<String,String> getConfigValue(String elementStr){
    try {
        elementStr = java.net.URLDecoder.decode(elementStr, "utf-8");
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    }
    int start = elementStr.indexOf("configvalue");
    // 参数的键值对
    Map<String, String> map = null;
    if (start != -1) {
        map = new HashMap<String, String>();
        start = elementStr.indexOf("\"", start);
        int end = elementStr.lastIndexOf("||");
        if (start < 0 || end < 0) {
            return null;
        }
        String configValue = elementStr.substring(start + 1, end);
        String[] values = configValue.split("\\|\\|");
        for (int i = 0; i < values.length; i++) {
            String value = values[i];
            if (value != null && value.trim().length() > 1) {
                int de = value.indexOf("=");
                if (de > 0) {
                    String name = value.substring(0, de);
                    String v = value.substring(de + 1);
                    map.put(name, v);
                }
            }
        }
    }
    return map;
}


public String percent(double p1,double p2){
    if (p2 == 0) {
        return "0.00%";
    }
    String str;
    double p3 = p1 / p2;
    NumberFormat nf = NumberFormat.getPercentInstance();
    nf.setMinimumFractionDigits(2);
    str = nf.format(p3);
    return str;
}


public String changeCharset(String str,String newCharset){
    try {
        if (str != null) {
            // 用默认字符编码解码字符串。
            byte[] bs = str.getBytes();
            // 用新的字符编码生成字符串
            return new String(bs, newCharset);
        }
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    }
    return "";
}


public String substring(String str,int start,int maxLength){
    if (str != null) {
        int realLength = str.length();
        if (start >= realLength) {
            return null;
        }
        str = str.substring(start);
        if (str.length() > maxLength) {
            return str.substring(0, maxLength);
        }
        return str;
    }
    return str;
}


public String subStringToPoint(String str,int length,String more){
    if (str == null)
        return "";
    String reStr = "";
    if (str.length() * 2 - 1 > length) {
        int reInt = 0;
        char[] tempChar = str.toCharArray();
        for (int kk = 0; (kk < tempChar.length && length > reInt); kk++) {
            String s1 = String.valueOf(tempChar[kk]);
            byte[] b = s1.getBytes();
            reInt += b.length;
            reStr += tempChar[kk];
        }
        if (length == reInt || (length == reInt - 1)) {
            if (!reStr.equals(str)) {
                reStr += more;
            }
        }
    } else {
        reStr = str;
    }
    return reStr;
}


public String replaceNumber(String content){
    String strMark = "[^[0-9]+$]";
    String strContent = "";
    strContent = content.replaceAll(strMark, "");
    return strContent;
}


public String rpad(String str,char chr,int size){
    if (str != null) {
        while (str.length() < size) {
            str = str + chr;
        }
    }
    return str;
}


public String lpad(String str,char chr,int size){
    if (str != null) {
        while (str.length() < size) {
            str = chr + str;
        }
    }
    return str;
}


public String replaceCharToHtml(String content){
    String strContent = content;
    strContent = strContent.replaceAll("<", "&lt;");
    strContent = strContent.replaceAll(">", "&gt;");
    strContent = strContent.replaceAll("\"", "&quot;");
    return strContent;
}


public String rTrim(String str,String trimChars){
    int end;
    if (str == null || (end = str.length()) == 0) {
        return str;
    }
    if (trimChars == null) {
        while ((end != 0) && Character.isWhitespace(str.charAt(end - 1))) {
            end--;
        }
    } else if (trimChars.length() == 0) {
        return str;
    } else {
        while ((end != 0) && (trimChars.indexOf(str.charAt(end - 1)) != -1)) {
            end--;
        }
    }
    return str.substring(0, end);
}


public String replaceUbb(String content){
    String strContent = content;
    try {
        for (int num = 0; num < arrTest.length; num++) {
            if ((strContent.indexOf(arrTest[num])) >= 0) {
                try {
                    strContent = strContent.replaceAll(arrParam[num], arrCode[num]);
                } catch (Exception ex) {
                }
            }
        }
    } catch (Exception e) {
    // System.out.println("UBB CODE 错误"+e);
    }
    return strContent;
}


public String replaceSymbol(String content){
    int intPlaceNum = 0;
    int Num = 0;
    String strContent = content;
    while (true) {
        // 判断是否还存在"
        intPlaceNum = strContent.indexOf("\"");
        if (intPlaceNum < 0) {
            break;
        } else {
            if (Num % 2 == 0) {
                strContent = strContent.replaceFirst("\"", "“");
            } else {
                strContent = strContent.replaceFirst("\"", "”");
            }
            Num = Num + 1;
        }
    }
    return strContent;
}


public boolean isEmpty(String s){
    if (s == null || "".equals(s.trim()) || "null".equals(s.trim())) {
        return true;
    }
    return false;
}


public String checkTeamId(String teamId){
    String strTeamId = "";
    String strTempId = "";
    String strTemp = "";
    String[] arrTeamId = teamId.split(",");
    for (int num = 0; num < arrTeamId.length; num++) {
        strTemp = arrTeamId[num].trim();
        if ((!strTemp.equals("")) && (!strTemp.equals("0"))) {
            if ((strTempId.indexOf("," + strTemp + ",")) >= 0) {
            // 表示已经保存过了
            } else {
                if (strTeamId.equals("")) {
                    strTeamId = strTemp;
                    strTempId = strTempId + "," + strTemp + ",";
                    ;
                } else {
                    strTeamId = strTeamId + "," + strTemp;
                    strTempId = strTempId + strTemp + ",";
                }
            }
        }
    }
    return strTeamId;
}


public String getString(Object curObject){
    if (null == curObject) {
        throw new NullPointerException("The input object is null.");
    } else {
        return curObject.toString();
    }
}


public long getLong(String content){
    long lngContent;
    try {
        lngContent = Long.parseLong(content);
    } catch (Exception e) {
        lngContent = 0L;
    }
    return lngContent;
}


public int getInt(String content){
    int intContent;
    try {
        intContent = Integer.parseInt(content);
    } catch (Exception e) {
        intContent = 0;
    }
    return intContent;
}


public String lrTrim(String str,String trimChars){
    if (StringUtil.isEmpty(str)) {
        return str;
    }
    str = lTrim(str, trimChars);
    return rTrim(str, trimChars);
}


public String replaceSign(String content){
    String strContent = "";
    strContent = content.replaceAll("\\*", "");
    strContent = strContent.replaceAll("\\$", "");
    strContent = strContent.replaceAll("\\+", "");
    String[] arrStr = { ":", "：", "●", "▲", "■", "@", "＠", "◎", "★", "※", "＃", "〓", "＼", "§", "☆", "○", "◇", "◆", "□", "△", "＆", "＾", "￣", "＿", "♂", "♀", "Ю", "┭", "①", "「", "」", "≮", "§", "￡", "∑", "『", "』", "⊙", "∷", "Θ", "の", "↓", "↑", "Ф", "~", "Ⅱ", "∈", "┣", "┫", "╋", "┇", "┋", "→", "←", "!", "Ж", "#" };
    for (int i = 0; i < arrStr.length; i++) {
        if ((strContent.indexOf(arrStr[i])) >= 0) {
            strContent = strContent.replaceAll(arrStr[i], "");
        }
    }
    return strContent;
}


public String replaceChar(String content){
    String newstr = "";
    newstr = content.replaceAll("\'", "''");
    return newstr;
}


public String clearWord(String content){
    String strContent = "";
    strContent = content.trim();
    strContent = strContent.replaceAll("x:str", "");
    // Remove Style attributes
    strContent = strContent.replaceAll("<(\\w[^>]*) style=\"([^\"]*)\"", "<$1");
    // Remove all SPAN  tags
    strContent = strContent.replaceAll("<\\/?SPAN[^>]*>", "");
    // Remove Lang attributes
    strContent = strContent.replaceAll("<(\\w[^>]*) lang=([^ |>]*)([^>]*)", "<$1$3");
    // Remove Class attributes
    strContent = strContent.replaceAll("<(\\w[^>]*) class=([^ |>]*)([^>]*)", "<$1$3");
    // Remove XML elements and declarations
    strContent = strContent.replaceAll("<\\\\?\\?xml[^>]*>", "");
    // Remove Tags with XML namespace declarations: <o:p></o:p>
    strContent = strContent.replaceAll("<\\/?\\w+:[^>]*>", "");
    return strContent;
}


public String toHtmlValue(String value){
    if (null == value) {
        return null;
    }
    char a = 0;
    StringBuffer buf = new StringBuffer();
    for (int i = 0; i < value.length(); i++) {
        a = value.charAt(i);
        switch(a) {
            // 双引号
            case 34:
                buf.append("&#034;");
                break;
            // &号
            case 38:
                buf.append("&amp;");
                break;
            // 单引号
            case 39:
                buf.append("&#039;");
                break;
            // 小于号
            case 60:
                buf.append("&lt;");
                break;
            // 大于号
            case 62:
                buf.append("&gt;");
                break;
            default:
                buf.append(a);
                break;
        }
    }
    return buf.toString();
}


public boolean isNull(Object obj){
    return (null == obj) ? true : false;
}


public String conventString(String str){
    return null == str || "".equals(str) ? "" + "0" : str;
}


public String lTrim(String str,String trimChars){
    int strLen;
    if (str == null || (strLen = str.length()) == 0) {
        return str;
    }
    int start = 0;
    if (trimChars == null) {
        while ((start != strLen) && Character.isWhitespace(str.charAt(start))) {
            start++;
        }
    } else if (trimChars.length() == 0) {
        return str;
    } else {
        while ((start != strLen) && (trimChars.indexOf(str.charAt(start)) != -1)) {
            start++;
        }
    }
    return str.substring(start);
}


public String paramTrim(String val){
    if (null == val || "".equals(val)) {
        return "";
    }
    return val.trim();
}


public String convertNull(String string){
    return convertNull(string, "");
}


public String replaceLetter(String content){
    String strMark = "[^[A-Za-z]+$]";
    String strContent = "";
    strContent = content.replaceAll(strMark, "");
    return strContent;
}


public String replaceBr(String content){
    if (content == null) {
        return "";
    }
    String strContent = "";
    // String strMark ="[/\n\r\t]";
    // strContent = content.replaceAll(strMark,"<br>");
    strContent = content.replaceAll("\n\r\t", "<br>");
    strContent = strContent.replaceAll("\n\r", "<br>");
    strContent = strContent.replaceAll("\r\n", "<br>");
    strContent = strContent.replaceAll("\n", "<br>");
    strContent = strContent.replaceAll("\r", "<br>");
    strContent = strContent.replaceAll(" ", "&nbsp;");
    return strContent;
}


}