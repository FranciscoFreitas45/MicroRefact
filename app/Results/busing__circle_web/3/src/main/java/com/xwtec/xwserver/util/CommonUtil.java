package com.xwtec.xwserver.util;
 import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class CommonUtil {


public String getMD5(String sourceStr){
    if (sourceStr == null || "".equals(sourceStr.trim())) {
        return null;
    }
    byte[] source = sourceStr.getBytes();
    String s = null;
    char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    try {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(source);
        // MD5 的计算结果是一个 128 位的长整数， 用字节表示就是 16 个字节
        byte[] tmp = md.digest();
        // 每个字节用 16 进制表示的话，使用两个字符， 所以表示成 16 进制需要 32 个字符
        char[] str = new char[16 * 2];
        // 表示转换结果中对应的字符位置
        int k = 0;
        for (int i = 0; i < 16; i++) {
            // 从第一个字节开始，对 MD5 的每一个字节 ,转换成 16 进制字符的转换
            // 取第 i 个字节
            byte byte0 = tmp[i];
            // 取字节中高 4 位的数字转换,  >>> 为逻辑右移，将符号位一起右移
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            // 取字节中低 4 位的数字转换
            str[k++] = hexDigits[byte0 & 0xf];
        }
        // 换后的结果转换为字符串
        s = new String(str);
    } catch (NoSuchAlgorithmException e) {
        throw e;
    }
    return s;
}


public String changeNullToEmptyString(Object obj){
    if (obj == null)
        return "";
    else
        return obj.toString();
}


public boolean isEmpty(String str){
    if (str == null || "".equals(str.trim())) {
        return true;
    }
    return false;
}


public String changeNullToEmpty(String str){
    if (isEmpty(str)) {
        return "";
    } else {
        return str;
    }
}


public String getErrorMessage(StackTraceElement stackTraceElement,String errorMessage){
    StringBuilder result = new StringBuilder();
    result.append("[" + stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + "]:failed. throw e:" + errorMessage);
    return result.toString();
}


}