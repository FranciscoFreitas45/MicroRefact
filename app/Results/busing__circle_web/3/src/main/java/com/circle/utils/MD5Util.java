package com.circle.utils;
 import java.security.MessageDigest;
public class MD5Util {

 private  String[] hexDigits;


public String byteArrayToHexString(byte[] b){
    StringBuffer resultSb = new StringBuffer();
    for (int i = 0; i < b.length; i++) resultSb.append(byteToHexString(b[i]));
    return resultSb.toString();
}


public String MD5Encode(String origin,String charsetname){
    String resultString = null;
    try {
        resultString = new String(origin);
        MessageDigest md = MessageDigest.getInstance("MD5");
        if (charsetname == null || "".equals(charsetname))
            resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
        else
            resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
    } catch (Exception exception) {
    }
    return resultString;
}


public String byteToHexString(byte b){
    int n = b;
    if (n < 0)
        n += 256;
    int d1 = n / 16;
    int d2 = n % 16;
    return hexDigits[d1] + hexDigits[d2];
}


}