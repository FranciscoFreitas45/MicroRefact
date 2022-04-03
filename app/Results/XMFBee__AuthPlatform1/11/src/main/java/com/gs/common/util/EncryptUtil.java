package com.gs.common.util;
 import sun.misc.BASE64Encoder;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class EncryptUtil {

 public  String CHARSET;

 public  String MD5;

 public  String SHA;


public String oneWayEncrypt(String str,String type){
    MessageDigest md = MessageDigest.getInstance(type);
    BASE64Encoder encoder = new BASE64Encoder();
    byte[] bytes = str.getBytes(CHARSET);
    md.update(bytes);
    return encoder.encode(md.digest(bytes));
}


public String shaEncrypt(String str){
    String encryptStr = null;
    try {
        encryptStr = oneWayEncrypt(str, SHA);
    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    }
    return encryptStr;
}


public String md5Encrypt(String str){
    String encryptStr = null;
    try {
        encryptStr = oneWayEncrypt(str, MD5);
    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    }
    return encryptStr;
}


}