package org.jeecgframework.core.util;
 import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.HTMLWriter;
import org.dom4j.io.OutputFormat;
public class StringUtil {

 private  Pattern numericPattern;

 private  Pattern numericStringPattern;

 private  Pattern floatNumericPattern;

 private  Pattern abcPattern;

 public  String splitStrPattern;

 private  Log logger;


public boolean isJavaClass(Class<?> clazz){
    boolean isBaseClass = false;
    if (clazz.isArray()) {
        isBaseClass = false;
    } else if (clazz.isPrimitive() || clazz.getPackage() == null || clazz.getPackage().getName().equals("java.lang") || clazz.getPackage().getName().equals("java.math") || clazz.getPackage().getName().equals("java.util")) {
        isBaseClass = true;
    }
    return isBaseClass;
}


public String fomateToFullForm(String str,String pt){
    String regEx = "<" + pt + "\\s+([\\S&&[^<>]]*)/>";
    Pattern p = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
    Matcher m = p.matcher(str);
    String[] sa = null;
    String sf = "";
    String sf2 = "";
    String sf3 = "";
    for (; m.find(); ) {
        sa = p.split(str);
        if (sa == null) {
            break;
        }
        sf = str.substring(sa[0].length(), str.indexOf("/>", sa[0].length()));
        sf2 = sf + "></" + pt + ">";
        sf3 = str.substring(sa[0].length() + sf.length() + 2);
        str = sa[0] + sf2 + sf3;
        sa = null;
    }
    return str;
}


public String intArraytoString(int[] a){
    if (a == null)
        return "";
    int iMax = a.length - 1;
    if (iMax == -1)
        return "";
    StringBuilder b = new StringBuilder();
    for (int i = 0; ; i++) {
        b.append(a[i]);
        if (i == iMax)
            return b.toString();
        b.append(",");
    }
}


public String splitNotNumber(String content){
    Pattern pattern = Pattern.compile("\\D+");
    Matcher matcher = pattern.matcher(content);
    while (matcher.find()) {
        return matcher.group(0);
    }
    return "";
}


public String formatHtml(String str){
    Document document = null;
    document = DocumentHelper.parseText(str);
    OutputFormat format = OutputFormat.createPrettyPrint();
    format.setEncoding("utf-8");
    StringWriter writer = new StringWriter();
    HTMLWriter htmlWriter = new HTMLWriter(writer, format);
    htmlWriter.write(document);
    htmlWriter.close();
    return writer.toString();
}


public String simpleEncrypt(String str){
    if (str != null && str.length() > 0) {
        // str = str.replaceAll("0","a");
        str = str.replaceAll("1", "b");
        // str = str.replaceAll("2","c");
        str = str.replaceAll("3", "d");
        // str = str.replaceAll("4","e");
        str = str.replaceAll("5", "f");
        str = str.replaceAll("6", "g");
        str = str.replaceAll("7", "h");
        str = str.replaceAll("8", "i");
        str = str.replaceAll("9", "j");
    }
    return str;
}


public String replace(String strSource,String strOld,String strNew){
    if (strSource == null) {
        return null;
    }
    int i = 0;
    if ((i = strSource.indexOf(strOld, i)) >= 0) {
        char[] cSrc = strSource.toCharArray();
        char[] cTo = strNew.toCharArray();
        int len = strOld.length();
        StringBuffer buf = new StringBuffer(cSrc.length);
        buf.append(cSrc, 0, i).append(cTo);
        i += len;
        int j = i;
        while ((i = strSource.indexOf(strOld, i)) > 0) {
            buf.append(cSrc, j, i - j).append(cTo);
            i += len;
            j = i;
        }
        buf.append(cSrc, j, cSrc.length - j);
        return buf.toString();
    }
    return strSource;
}


public String getCaption(String captions,int index){
    if (index > 0 && captions != null && !captions.equals("")) {
        String[] ss = captions.split(",");
        if (ss != null && ss.length > 0 && index < ss.length) {
            return ss[index];
        }
    }
    return null;
}


public String getMaskStr(String str,int start,int len){
    if (StringUtil.isEmpty(str)) {
        return str;
    }
    if (str.length() < start) {
        return str;
    }
    // 获取*之前的字符串
    String ret = str.substring(0, start);
    // 获取最多能打的*个数
    int strLen = str.length();
    if (strLen < start + len) {
        len = strLen - start;
    }
    // 替换成*
    for (int i = 0; i < len; i++) {
        ret += "*";
    }
    // 加上*之后的字符串
    if (strLen > start + len) {
        ret += str.substring(start + len);
    }
    return ret;
}


public String removeHTMLLable(String str){
    // 去掉页面上看不到的字符
    str = stringReplace(str, "\\s", "");
    // 去<br><br />
    str = stringReplace(str, "<br ?/?>", "\n");
    // 去掉<>内的字符
    str = stringReplace(str, "<([^<>]+)>", "");
    // 替换空格
    str = stringReplace(str, "&nbsp;", " ");
    // 去<br><br />
    str = stringReplace(str, "&(\\S)(\\S?)(\\S?)(\\S?);", "");
    return str;
}


@SuppressWarnings("unchecked")
public Map<String,String> parseQuery(String query,char split1,char split2,String dupLink){
    if (!isEmpty(query) && query.indexOf(split2) > 0) {
        Map<String, String> result = new HashMap();
        String name = null;
        String value = null;
        String tempValue = "";
        int len = query.length();
        for (int i = 0; i < len; i++) {
            char c = query.charAt(i);
            if (c == split2) {
                value = "";
            } else if (c == split1) {
                if (!isEmpty(name) && value != null) {
                    if (dupLink != null) {
                        tempValue = result.get(name);
                        if (tempValue != null) {
                            value += dupLink + tempValue;
                        }
                    }
                    result.put(name, value);
                }
                name = null;
                value = null;
            } else if (value != null) {
                value += c;
            } else {
                name = (name != null) ? (name + c) : "" + c;
            }
        }
        if (!isEmpty(name) && value != null) {
            if (dupLink != null) {
                tempValue = result.get(name);
                if (tempValue != null) {
                    value += dupLink + tempValue;
                }
            }
            result.put(name, value);
        }
        return result;
    }
    return null;
}


public String moneyToString(Object money,String style){
    if (money != null && style != null && (money instanceof Double || money instanceof Float)) {
        Double num = (Double) money;
        if (style.equalsIgnoreCase("default")) {
            // 缺省样式 0 不输出 ,如果没有输出小数位则不输出.0
            if (num == 0) {
                // 不输出0
                return "";
            } else if ((num * 10 % 10) == 0) {
                // 没有小数
                return Integer.toString((int) num.intValue());
            } else {
                // 有小数
                return num.toString();
            }
        } else {
            DecimalFormat df = new DecimalFormat(style);
            return df.format(num);
        }
    }
    return null;
}


public double toDouble(String s){
    if (s != null && !"".equals(s.trim())) {
        return Double.parseDouble(s);
    }
    return 0;
}


public int[] getSubStringPos(String str,String sub,boolean b){
    // int[] i = new int[(new Integer((str.length()-stringReplace( str , sub
    // , "" ).length())/sub.length())).intValue()] ;
    String[] sp = null;
    int l = sub.length();
    sp = splitString(str, sub);
    if (sp == null) {
        return null;
    }
    int[] ip = new int[sp.length - 1];
    for (int i = 0; i < sp.length - 1; i++) {
        ip[i] = sp[i].length() + l;
        if (i != 0) {
            ip[i] += ip[i - 1];
        }
    }
    if (b) {
        for (int j = 0; j < ip.length; j++) {
            ip[j] = ip[j] - l;
        }
    }
    return ip;
}


public String numberToString(Object num){
    if (num == null) {
        return null;
    } else if (num instanceof Integer && (Integer) num > 0) {
        return Integer.toString((Integer) num);
    } else if (num instanceof Long && (Long) num > 0) {
        return Long.toString((Long) num);
    } else if (num instanceof Float && (Float) num > 0) {
        return Float.toString((Float) num);
    } else if (num instanceof Double && (Double) num > 0) {
        return Double.toString((Double) num);
    } else {
        return "";
    }
}


public String getNumbers(String content){
    Pattern pattern = Pattern.compile("\\d+");
    Matcher matcher = pattern.matcher(content);
    while (matcher.find()) {
        return matcher.group(0);
    }
    return "";
}


public String subYhooString(String subject,int size){
    subject = subject.substring(1, size);
    return subject;
}


public Object xmlToObject(String xml){
    try {
        ByteArrayInputStream in = new ByteArrayInputStream(xml.getBytes("UTF8"));
        XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(in));
        return decoder.readObject();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}


public String getEncodePra(String property){
    String trem = "";
    if (isNotEmpty(property)) {
        try {
            trem = URLDecoder.decode(property, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    return trem;
}


public boolean isABC(String src){
    boolean return_value = false;
    if (src != null && src.length() > 0) {
        Matcher m = abcPattern.matcher(src);
        if (m.find()) {
            return_value = true;
        }
    }
    return return_value;
}


public String removeURL(String str){
    if (str != null)
        str = str.toLowerCase().replaceAll("(http|www|com|cn|org|\\.)+", "");
    return str;
}


public String stringReplace(String s,String pf,String pb,int start){
    Pattern pattern_hand = Pattern.compile(pf);
    Matcher matcher_hand = pattern_hand.matcher(s);
    int gc = matcher_hand.groupCount();
    int pos = start;
    String sf1 = "";
    String sf2 = "";
    String sf3 = "";
    int if1 = 0;
    String strr = "";
    while (matcher_hand.find(pos)) {
        sf1 = matcher_hand.group();
        if1 = s.indexOf(sf1, pos);
        if (if1 >= pos) {
            strr += s.substring(pos, if1);
            pos = if1 + sf1.length();
            sf2 = pb;
            for (int i = 1; i <= gc; i++) {
                sf3 = "\\" + i;
                sf2 = replaceAll(sf2, sf3, matcher_hand.group(i));
            }
            strr += sf2;
        } else {
            return s;
        }
    }
    strr = s.substring(0, start) + strr;
    return strr;
}


public long toLong(String s){
    try {
        if (s != null && !"".equals(s.trim()))
            return Long.parseLong(s);
    } catch (Exception exception) {
    }
    return 0L;
}


public String changCoding(String s,String fencode,String bencode){
    String str;
    try {
        if (StringUtil.isNotEmpty(s)) {
            str = new String(s.getBytes(fencode), bencode);
        } else {
            str = "";
        }
        return str;
    } catch (UnsupportedEncodingException e) {
        return s;
    }
}


public boolean isContentRepeat(String content){
    int similarNum = 0;
    int forNum = 0;
    int subNum = 0;
    int thousandNum = 0;
    String startStr = "";
    String nextStr = "";
    boolean result = false;
    float endNum = (float) 0.0;
    if (content != null && content.length() > 0) {
        if (content.length() % 1000 > 0)
            thousandNum = (int) Math.floor(content.length() / 1000) + 1;
        else
            thousandNum = (int) Math.floor(content.length() / 1000);
        if (thousandNum < 3)
            subNum = 100 * thousandNum;
        else if (thousandNum < 6)
            subNum = 200 * thousandNum;
        else if (thousandNum < 9)
            subNum = 300 * thousandNum;
        else
            subNum = 3000;
        for (int j = 1; j < subNum; j++) {
            if (content.length() % j > 0)
                forNum = (int) Math.floor(content.length() / j) + 1;
            else
                forNum = (int) Math.floor(content.length() / j);
            if (result || j >= content.length())
                break;
            else {
                for (int m = 0; m < forNum; m++) {
                    if (m * j > content.length() || (m + 1) * j > content.length() || (m + 2) * j > content.length())
                        break;
                    startStr = content.substring(m * j, (m + 1) * j);
                    nextStr = content.substring((m + 1) * j, (m + 2) * j);
                    if (startStr.equals(nextStr)) {
                        similarNum = similarNum + 1;
                        endNum = (float) similarNum / forNum;
                        if (endNum > 0.4) {
                            result = true;
                            break;
                        }
                    } else
                        similarNum = 0;
                }
            }
        }
    }
    return result;
}


public String formatFloat(float f,String format){
    DecimalFormat df = new DecimalFormat(format);
    return df.format(f);
}


public int getStringLen(String SrcStr){
    int return_value = 0;
    if (SrcStr != null) {
        char[] theChars = SrcStr.toCharArray();
        for (int i = 0; i < theChars.length; i++) {
            return_value += (theChars[i] <= 255) ? 1 : 2;
        }
    }
    return return_value;
}


public String full2Half(String str){
    if (str == null || "".equals(str))
        return "";
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < str.length(); i++) {
        char c = str.charAt(i);
        if (c >= 65281 && c < 65373)
            sb.append((char) (c - 65248));
        else
            sb.append(str.charAt(i));
    }
    return sb.toString();
}


public boolean check(String str){
    String sIllegal = "'\"";
    int len = sIllegal.length();
    if (null == str)
        return false;
    for (int i = 0; i < len; i++) {
        if (str.indexOf(sIllegal.charAt(i)) != -1)
            return true;
    }
    return false;
}


public boolean isMatch(String str,String pattern){
    Pattern pattern_hand = Pattern.compile(pattern);
    Matcher matcher_hand = pattern_hand.matcher(str);
    boolean b = matcher_hand.matches();
    return b;
}


public String subStringNotEncode(String subject,int size){
    if (subject != null && subject.length() > size) {
        subject = subject.substring(0, size) + "...";
    }
    return subject;
}


public String getChar(String str){
    String dest = str.substring(2, str.length() - 1);
    char ch = (char) Integer.parseInt(dest);
    return "" + ch;
}


public String subYhooStringDot(String subject,int size){
    subject = subject.substring(1, size) + "...";
    return subject;
}


public String getGBK(String str){
    return transfer(str);
}


public boolean contains(String[] stringArray,String source){
    // 转换为list
    List<String> tempList = Arrays.asList(stringArray);
    // 利用list的包含方法,进行判断
    if (oConvertUtils.isEmpty(source) || tempList.contains(source)) {
        return true;
    } else {
        return false;
    }
}


public String transfer(String str){
    Pattern p = Pattern.compile("&#\\d+;");
    Matcher m = p.matcher(str);
    while (m.find()) {
        String old = m.group();
        str = str.replaceAll(old, getChar(old));
    }
    return str;
}


public boolean isNumeric(String src){
    boolean return_value = false;
    if (src != null && src.length() > 0) {
        Matcher m = numericPattern.matcher(src);
        if (m.find()) {
            return_value = true;
        }
    }
    return return_value;
}


public String subStrNotEncode(String subject,int size){
    if (subject.length() > size) {
        subject = subject.substring(0, size);
    }
    return subject;
}


public boolean strPos(String sou,String finds){
    List<String> t = splitToList(",", finds);
    return strPos(sou, t);
}


public boolean isDigit(String strNum){
    Pattern pattern = Pattern.compile("[0-9]{1,}");
    Matcher matcher = pattern.matcher((CharSequence) strNum);
    return matcher.matches();
}


public String numRandom(int bit){
    if (bit == 0)
        // 默认6位
        bit = 6;
    String str = "";
    // 初始化种子
    str = "0123456789";
    // 返回6位的字符串
    return RandomStringUtils.random(bit, str);
}


public String removeHTMLLableExe(String str){
    str = stringReplace(str, ">\\s*<", "><");
    // 替换空格
    str = stringReplace(str, "&nbsp;", " ");
    // 去<br><br />
    str = stringReplace(str, "<br ?/?>", "\n");
    // 去掉<>内的字符
    str = stringReplace(str, "<([^<>]+)>", "");
    // 将多个空白变成一个空格
    str = stringReplace(str, "\\s\\s\\s*", " ");
    // 去掉头的空白
    str = stringReplace(str, "^\\s*", "");
    // 去掉尾的空白
    str = stringReplace(str, "\\s*$", "");
    str = stringReplace(str, " +", " ");
    return str;
}


public boolean isNotEmpty(Object str){
    boolean flag = true;
    if (str != null && !str.equals("")) {
        if (str.toString().length() > 0) {
            flag = true;
        }
    } else {
        flag = false;
    }
    return flag;
}


public String getHtmlSubString(String str,int len,String tail){
    if (str == null || str.length() <= len) {
        return str;
    }
    int length = str.length();
    char c = ' ';
    String tag = null;
    String name = null;
    int size = 0;
    String result = "";
    boolean isTag = false;
    List<String> tags = new ArrayList<String>();
    int i = 0;
    for (int end = 0, spanEnd = 0; i < length && len > 0; i++) {
        c = str.charAt(i);
        if (c == '<') {
            end = str.indexOf('>', i);
        }
        if (end > 0) {
            // 截取标签
            tag = str.substring(i, end + 1);
            int n = tag.length();
            if (tag.endsWith("/>")) {
                isTag = true;
            } else if (tag.startsWith("</")) {
                // 结束符
                name = tag.substring(2, end - i);
                size = tags.size() - 1;
                // 堆栈取出html开始标签
                if (size >= 0 && name.equals(tags.get(size))) {
                    isTag = true;
                    tags.remove(size);
                }
            } else {
                // 开始符
                spanEnd = tag.indexOf(' ', 0);
                spanEnd = spanEnd > 0 ? spanEnd : n;
                name = tag.substring(1, spanEnd);
                if (name.trim().length() > 0) {
                    // 如果有结束符则为html标签
                    spanEnd = str.indexOf("</" + name + ">", end);
                    if (spanEnd > 0) {
                        isTag = true;
                        tags.add(name);
                    }
                }
            }
            // 非html标签字符
            if (!isTag) {
                if (n >= len) {
                    result += tag.substring(0, len);
                    break;
                } else {
                    len -= n;
                }
            }
            result += tag;
            isTag = false;
            i = end;
            end = 0;
        } else {
            // 非html标签字符
            len--;
            result += c;
        }
    }
    // 添加未结束的html标签
    for (String endTag : tags) {
        result += "</" + endTag + ">";
    }
    if (i < length) {
        result += tail;
    }
    return result;
}


public List<String> stringToStringListBySlipStr(String slipStr,String src){
    if (src == null)
        return null;
    List<String> list = new ArrayList<String>();
    String[] result = src.split(slipStr);
    for (int i = 0; i < result.length; i++) {
        list.add(result[i]);
    }
    return list;
}


public String removeOutHTMLLable(String str){
    str = stringReplace(str, ">([^<>]+)<", "><");
    str = stringReplace(str, "^([^<>]+)<", "<");
    str = stringReplace(str, ">([^<>]+)$", ">");
    return str;
}


public String joinString(String[] array,String symbol){
    String result = "";
    if (array != null) {
        for (int i = 0; i < array.length; i++) {
            String temp = array[i];
            if (temp != null && temp.trim().length() > 0)
                result += (temp + symbol);
        }
        if (result.length() > 1)
            result = result.substring(0, result.length() - 1);
    }
    return result;
}


@SuppressWarnings("unchecked")
public LinkedHashMap<String,String> toLinkedHashMap(String str){
    if (str != null && !str.equals("") && str.indexOf("=") > 0) {
        LinkedHashMap result = new LinkedHashMap();
        String name = null;
        String value = null;
        int i = 0;
        while (i < str.length()) {
            char c = str.charAt(i);
            switch(c) {
                case // =
                61:
                    value = "";
                    break;
                case // &
                38:
                    if (name != null && value != null && !name.equals("")) {
                        result.put(name, value);
                    }
                    name = null;
                    value = null;
                    break;
                default:
                    if (value != null) {
                        value = (value != null) ? (value + c) : "" + c;
                    } else {
                        name = (name != null) ? (name + c) : "" + c;
                    }
            }
            i++;
        }
        if (name != null && value != null && !name.equals("")) {
            result.put(name, value);
        }
        return result;
    }
    return null;
}


public String replaceAll(String s,String sf,String sb){
    int i = 0, j = 0;
    int l = sf.length();
    boolean b = true;
    boolean o = true;
    String str = "";
    do {
        j = i;
        i = s.indexOf(sf, j);
        if (i > j) {
            str += s.substring(j, i);
            str += sb;
            i += l;
            o = false;
        } else {
            str += s.substring(j);
            b = false;
        }
    } while (b);
    if (o) {
        str = s;
    }
    return str;
}


public String replaceBracketStr(String str){
    if (str != null && str.length() > 0) {
        str = str.replaceAll("（", "(");
        str = str.replaceAll("）", ")");
    }
    return str;
}


public String random(int bit){
    if (bit == 0)
        // 默认6位
        bit = 6;
    // 因为o和0,l和1很难区分,所以,去掉大小写的o和l
    String str = "";
    // 初始化种子
    str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz";
    // 返回6位的字符串
    return RandomStringUtils.random(bit, str);
}


public String[] midString(String s,String b,String e){
    int i = s.indexOf(b) + b.length();
    int j = s.indexOf(e, i);
    String[] sa = new String[2];
    if (i < b.length() || j < i + 1 || i > j) {
        sa[1] = s;
        sa[0] = null;
        return sa;
    } else {
        sa[0] = s.substring(i, j);
        sa[1] = s.substring(j);
        return sa;
    }
}


public String listTtoString(List<T> list){
    if (list == null || list.size() < 1)
        return "";
    Iterator<T> i = list.iterator();
    if (!i.hasNext())
        return "";
    StringBuilder sb = new StringBuilder();
    for (; ; ) {
        T e = i.next();
        sb.append(e);
        if (!i.hasNext())
            return sb.toString();
        sb.append(",");
    }
}


public String firstUpperCase(String realName){
    return StringUtils.replaceChars(realName, realName.substring(0, 1), realName.substring(0, 1).toUpperCase());
}


public String getEmptyString(){
    return "";
}


public String[] getStringArrayByPattern(String str,String pattern){
    Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
    Matcher matcher = p.matcher(str);
    // 范型
    // 目的是：相同的字符串只返回一个。。。 不重复元素
    Set<String> result = new HashSet<String>();
    // boolean find() 尝试在目标字符串里查找下一个匹配子串。
    while (matcher.find()) {
        for (int i = 0; i < matcher.groupCount(); i++) {
            // int groupCount()
            // 返回当前查找所获得的匹配组的数量。
            // org.jeecgframework.core.util.LogUtil.info(matcher.group(i));
            result.add(matcher.group(i));
        }
    }
    String[] resultStr = null;
    if (result.size() > 0) {
        resultStr = new String[result.size()];
        // 将Set result转化为String[] resultStr
        return result.toArray(resultStr);
    }
    return resultStr;
}


public String repeat(String src,int num){
    StringBuffer s = new StringBuffer();
    for (int i = 0; i < num; i++) s.append(src);
    return s.toString();
}


public List<String> parseString2ListByCustomerPattern(String pattern,String src){
    if (src == null)
        return null;
    List<String> list = new ArrayList<String>();
    String[] result = src.split(pattern);
    for (int i = 0; i < result.length; i++) {
        list.add(result[i]);
    }
    return list;
}


public boolean isNumericString(String src){
    boolean return_value = false;
    if (src != null && src.length() > 0) {
        Matcher m = numericStringPattern.matcher(src);
        if (m.find()) {
            return_value = true;
        }
    }
    return return_value;
}


public String replaceBlank(String str){
    if (str != null) {
        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        Matcher m = p.matcher(str);
        str = m.replaceAll("");
    }
    return str;
}


public String Q2B(String QJstr){
    String outStr = "";
    String Tstr = "";
    byte[] b = null;
    for (int i = 0; i < QJstr.length(); i++) {
        try {
            Tstr = QJstr.substring(i, i + 1);
            b = Tstr.getBytes("unicode");
        } catch (java.io.UnsupportedEncodingException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e);
            }
        }
        if (b[3] == -1) {
            b[2] = (byte) (b[2] + 32);
            b[3] = 0;
            try {
                outStr = outStr + new String(b, "unicode");
            } catch (java.io.UnsupportedEncodingException ex) {
                if (logger.isErrorEnabled()) {
                    logger.error(ex);
                }
            }
        } else {
            outStr = outStr + Tstr;
        }
    }
    return outStr;
}


public int toInt(String s){
    if (s != null && !"".equals(s.trim())) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return 0;
        }
    }
    return 0;
}


@SuppressWarnings("unchecked")
public String listToStringSlipStr(List list,String slipStr){
    StringBuffer returnStr = new StringBuffer();
    if (list != null && list.size() > 0) {
        for (int i = 0; i < list.size(); i++) {
            returnStr.append(list.get(i)).append(slipStr);
        }
    }
    if (returnStr.toString().length() > 0)
        return returnStr.toString().substring(0, returnStr.toString().lastIndexOf(slipStr));
    else
        return "";
}


public String getProperty(String property){
    if (property.contains("_")) {
        return property.replaceAll("_", "\\.");
    }
    return property;
}


public String isEmpty(String s,String result){
    if (s != null && !s.equals("")) {
        return s;
    }
    return result;
}


public String formatEmpty(Object o){
    if (o == null) {
        return "";
    } else {
        return o.toString();
    }
}


public Float toFloat(String s){
    try {
        return Float.parseFloat(s);
    } catch (NumberFormatException e) {
        return new Float(0);
    }
}


public String URLEncode(String src){
    String return_value = "";
    try {
        if (src != null) {
            return_value = URLEncoder.encode(src, "GBK");
        }
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
        return_value = src;
    }
    return return_value;
}


public String HashMapToJsonContent(HashMap<String,String> map){
    if (map != null && map.size() > 0) {
        String result = "{";
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            String name = (String) it.next();
            String value = (String) map.get(name);
            result += (result.equals("{")) ? "" : ", ";
            result += String.format("\"%s\":\"%s\"", name, value);
        }
        result += "}";
        return result;
    }
    return null;
}


public String replaceWapStr(String str){
    if (str != null) {
        str = str.replaceAll("<span class=\"keyword\">", "");
        str = str.replaceAll("</span>", "");
        str = str.replaceAll("<strong class=\"keyword\">", "");
        str = str.replaceAll("<strong>", "");
        str = str.replaceAll("</strong>", "");
        str = str.replace('$', '＄');
        str = str.replaceAll("&amp;", "＆");
        str = str.replace('&', '＆');
        str = str.replace('<', '＜');
        str = str.replace('>', '＞');
    }
    return str;
}


public String[] splitString(String str,String ms){
    String regEx = ms;
    Pattern p = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
    String[] sp = p.split(str);
    return sp;
}


public List<String> splitToList(String split,String src){
    // 默认,
    String sp = ",";
    if (split != null && split.length() == 1) {
        sp = split;
    }
    List<String> r = new ArrayList<String>();
    int lastIndex = -1;
    int index = src.indexOf(sp);
    if (-1 == index && src != null) {
        r.add(src);
        return r;
    }
    while (index >= 0) {
        if (index > lastIndex) {
            r.add(src.substring(lastIndex + 1, index));
        } else {
            r.add("");
        }
        lastIndex = index;
        index = src.indexOf(sp, index + 1);
        if (index == -1) {
            r.add(src.substring(lastIndex + 1, src.length()));
        }
    }
    return r;
}


public String getLimitLengthString(String str,int len){
    return getLimitLengthString(str, len, "...");
}


public String firstLowerCase(String realName){
    return StringUtils.replaceChars(realName, realName.substring(0, 1), realName.substring(0, 1).toLowerCase());
}


public boolean isFloatNumeric(String src){
    boolean return_value = false;
    if (src != null && src.length() > 0) {
        Matcher m = floatNumericPattern.matcher(src);
        if (m.find()) {
            return_value = true;
        }
    }
    return return_value;
}


public List<String> parseString2ListByPattern(String src){
    String pattern = "，|,|、|。";
    return parseString2ListByCustomerPattern(pattern, src);
}


public boolean equals(String s1,String s2){
    if (StringUtil.isEmpty(s1) && StringUtil.isEmpty(s2)) {
        return true;
    } else if (!StringUtil.isEmpty(s1) && !StringUtil.isEmpty(s2)) {
        return s1.equals(s2);
    }
    return false;
}


public String getHideEmailPrefix(String email){
    if (null != email) {
        int index = email.lastIndexOf('@');
        if (index > 0) {
            email = repeat("*", index).concat(email.substring(index));
        }
    }
    return email;
}


public String subStringExe(String s,String jmp,String sb,String se){
    if (isEmpty(s)) {
        return "";
    }
    int i = s.indexOf(jmp);
    if (i >= 0 && i < s.length()) {
        s = s.substring(i + 1);
    }
    i = s.indexOf(sb);
    if (i >= 0 && i < s.length()) {
        s = s.substring(i + 1);
    }
    if (se == "") {
        return s;
    } else {
        i = s.indexOf(se);
        if (i >= 0 && i < s.length()) {
            s = s.substring(i + 1);
        }
        return s;
    }
}


public String linkedHashMapToString(LinkedHashMap<String,String> map){
    if (map != null && map.size() > 0) {
        String result = "";
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            String name = (String) it.next();
            String value = (String) map.get(name);
            result += (result.equals("")) ? "" : "&";
            result += String.format("%s=%s", name, value);
        }
        return result;
    }
    return null;
}


public String concate(Object objs){
    if (objs == null || objs.length <= 0) {
        return "";
    }
    StringBuffer result = new StringBuffer();
    for (int i = 0; i < objs.length; i++) {
        result.append(formatEmpty(objs[i]));
        result.append("_");
    }
    return result.toString();
}


}