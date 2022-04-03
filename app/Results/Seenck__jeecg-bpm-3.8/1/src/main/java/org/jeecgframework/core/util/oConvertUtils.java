package org.jeecgframework.core.util;
 import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringEscapeUtils;
public class oConvertUtils {


public String code2code(String strIn,String sourceCode,String targetCode){
    String strOut = null;
    if (strIn == null || (strIn.trim()).equals(""))
        return strIn;
    try {
        byte[] b = strIn.getBytes(sourceCode);
        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i] + "  ");
        }
        strOut = new String(b, targetCode);
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
    return strOut;
}


public String getIp(){
    String ip = null;
    try {
        InetAddress address = InetAddress.getLocalHost();
        ip = address.getHostAddress();
    } catch (UnknownHostException e) {
        e.printStackTrace();
    }
    return ip;
}


public String getRealIp(){
    // 本地IP，如果没有配置外网IP则返回它
    String localip = null;
    // 外网IP
    String netip = null;
    Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
    InetAddress ip = null;
    // 是否找到外网IP
    boolean finded = false;
    while (netInterfaces.hasMoreElements() && !finded) {
        NetworkInterface ni = netInterfaces.nextElement();
        Enumeration<InetAddress> address = ni.getInetAddresses();
        while (address.hasMoreElements()) {
            ip = address.nextElement();
            if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
                // 外网IP
                netip = ip.getHostAddress();
                finded = true;
                break;
            } else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
                // 内网IP
                localip = ip.getHostAddress();
            }
        }
    }
    if (netip != null && !"".equals(netip)) {
        return netip;
    } else {
        return localip;
    }
}


public double getDouble(String s,double defval){
    if (s == null || s == "") {
        return (defval);
    }
    try {
        return (Double.parseDouble(s));
    } catch (NumberFormatException e) {
        return (defval);
    }
}


public String decode(String strIn,String sourceCode,String targetCode){
    String temp = code2code(strIn, sourceCode, targetCode);
    return temp;
}


public Integer[] getIntegerArry(String[] object){
    int len = object.length;
    Integer[] result = new Integer[len];
    try {
        for (int i = 0; i < len; i++) {
            result[i] = new Integer(object[i].trim());
        }
        return result;
    } catch (NumberFormatException e) {
        return null;
    }
}


public Map<Object,Object> getHashMap(){
    return new HashMap<Object, Object>();
}


public Integer[] getInts(String[] s){
    Integer[] integer = new Integer[s.length];
    if (s == null) {
        return null;
    }
    for (int i = 0; i < s.length; i++) {
        integer[i] = Integer.parseInt(s[i]);
    }
    return integer;
}


public String escapeJava(Object s){
    return StringEscapeUtils.escapeJava(getString(s));
}


public double getDou(Double s,double defval){
    if (s == null) {
        return (defval);
    }
    return s;
}


public String getIpAddrByRequest(HttpServletRequest request){
    String ip = request.getHeader("x-forwarded-for");
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getRemoteAddr();
    }
    return ip;
}


public boolean isIn(String substring,String[] source){
    if (source == null || source.length == 0) {
        return false;
    }
    for (int i = 0; i < source.length; i++) {
        String aSource = source[i];
        if (aSource.equals(substring)) {
            return true;
        }
    }
    return false;
}


public String replaceBlank(String str){
    String dest = "";
    if (str != null) {
        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        Matcher m = p.matcher(str);
        dest = m.replaceAll("");
    }
    return dest;
}


public boolean isInnerIP(String ipAddress){
    boolean isInnerIp = false;
    long ipNum = getIpNum(ipAddress);
    /**
     * 私有IP：A类 10.0.0.0-10.255.255.255 B类 172.16.0.0-172.31.255.255 C类 192.168.0.0-192.168.255.255 当然，还有127这个网段是环回地址
     */
    long aBegin = getIpNum("10.0.0.0");
    long aEnd = getIpNum("10.255.255.255");
    long bBegin = getIpNum("172.16.0.0");
    long bEnd = getIpNum("172.31.255.255");
    long cBegin = getIpNum("192.168.0.0");
    long cEnd = getIpNum("192.168.255.255");
    isInnerIp = isInner(ipNum, aBegin, aEnd) || isInner(ipNum, bBegin, bEnd) || isInner(ipNum, cBegin, cEnd) || ipAddress.equals("127.0.0.1");
    return isInnerIp;
}


public String camelName(String name){
    StringBuilder result = new StringBuilder();
    // 快速检查
    if (name == null || name.isEmpty()) {
        // 没必要转换
        return "";
    } else if (!name.contains("_")) {
        // 不含下划线，仅将首字母小写
        // update-begin--Author:zhoujf  Date:20180503 for：TASK #2500 【代码生成器】代码生成器开发一通用模板生成功能
        // update-begin--Author:zhoujf  Date:20180503 for：TASK #2500 【代码生成器】代码生成器开发一通用模板生成功能
        return name.substring(0, 1).toLowerCase() + name.substring(1).toLowerCase();
    // update-end--Author:zhoujf  Date:20180503 for：TASK #2500 【代码生成器】代码生成器开发一通用模板生成功能
    }
    // 用下划线将原始字符串分割
    String[] camels = name.split("_");
    for (String camel : camels) {
        // 跳过原始字符串中开头、结尾的下换线或双重下划线
        if (camel.isEmpty()) {
            continue;
        }
        // 处理真正的驼峰片段
        if (result.length() == 0) {
            // 第一个驼峰片段，全部字母都小写
            result.append(camel.toLowerCase());
        } else {
            // 其他的驼峰片段，首字母大写
            result.append(camel.substring(0, 1).toUpperCase());
            result.append(camel.substring(1).toLowerCase());
        }
    }
    return result.toString();
}


public Map<Object,Object> SetToMap(Set<Object> setobj){
    Map<Object, Object> map = getHashMap();
    for (Iterator iterator = setobj.iterator(); iterator.hasNext(); ) {
        Map.Entry<Object, Object> entry = (Map.Entry<Object, Object>) iterator.next();
        map.put(entry.getKey().toString(), entry.getValue() == null ? "" : entry.getValue().toString().trim());
    }
    return map;
}


public Short getShort(String s){
    if (StringUtil.isNotEmpty(s)) {
        return (Short.parseShort(s));
    } else {
        return null;
    }
}


public long stringToLong(String str){
    Long test = new Long(0);
    try {
        test = Long.valueOf(str);
    } catch (Exception e) {
    }
    return test.longValue();
}


public boolean isEmpty(Object object){
    if (object == null) {
        return (true);
    }
    if (object.equals("")) {
        return (true);
    }
    if (object.equals("null")) {
        return (true);
    }
    return (false);
}


public String StrToUTF(String strIn,String sourceCode,String targetCode){
    strIn = "";
    try {
        strIn = new String(strIn.getBytes("ISO-8859-1"), "GBK");
    } catch (UnsupportedEncodingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return strIn;
}


public String getString(Object s,String defval){
    if (isEmpty(s)) {
        return (defval);
    }
    return (s.toString().trim());
}


public boolean isInner(long userIp,long begin,long end){
    return (userIp >= begin) && (userIp <= end);
}


public long getIpNum(String ipAddress){
    String[] ip = ipAddress.split("\\.");
    long a = Integer.parseInt(ip[0]);
    long b = Integer.parseInt(ip[1]);
    long c = Integer.parseInt(ip[2]);
    long d = Integer.parseInt(ip[3]);
    long ipNum = a * 256 * 256 * 256 + b * 256 * 256 + c * 256 + d;
    return ipNum;
}


public int getInt(BigDecimal s,int defval){
    if (s == null) {
        return (defval);
    }
    return s.intValue();
}


public String camelNameCapFirst(String name){
    StringBuilder result = new StringBuilder();
    // 快速检查
    if (name == null || name.isEmpty()) {
        // 没必要转换
        return "";
    } else if (!name.contains("_")) {
        // 不含下划线，仅将首字母小写
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }
    // 用下划线将原始字符串分割
    String[] camels = name.split("_");
    for (String camel : camels) {
        // 跳过原始字符串中开头、结尾的下换线或双重下划线
        if (camel.isEmpty()) {
            continue;
        }
        // 其他的驼峰片段，首字母大写
        result.append(camel.substring(0, 1).toUpperCase());
        result.append(camel.substring(1).toLowerCase());
    }
    return result.toString();
}


public boolean isNotEmpty(Object object){
    if (object != null && !object.equals("") && !object.equals("null")) {
        return (true);
    }
    return (false);
}


public boolean isBaseDataType(Class clazz){
    return (clazz.equals(String.class) || clazz.equals(Integer.class) || clazz.equals(Byte.class) || clazz.equals(Long.class) || clazz.equals(Double.class) || clazz.equals(Float.class) || clazz.equals(Character.class) || clazz.equals(Short.class) || clazz.equals(BigDecimal.class) || clazz.equals(BigInteger.class) || clazz.equals(Boolean.class) || clazz.equals(Date.class) || clazz.isPrimitive());
}


public String camelNames(String names){
    if (names == null || names.equals("")) {
        return null;
    }
    StringBuffer sf = new StringBuffer();
    String[] fs = names.split(",");
    for (String field : fs) {
        field = camelName(field);
        sf.append(field + ",");
    }
    String result = sf.toString();
    return result.substring(0, result.length() - 1);
}


}