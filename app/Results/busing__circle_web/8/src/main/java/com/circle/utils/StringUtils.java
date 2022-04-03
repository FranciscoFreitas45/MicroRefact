package com.circle.utils;
 import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
public class StringUtils {

 public  String YMD;


public String getOrderNo(){
    try {
        int uuid = UUID.randomUUID().toString().hashCode();
        if (uuid < 0) {
            uuid = Math.abs(uuid);
        }
        StringBuffer uuidStr = new StringBuffer(uuid + "");
        if (uuidStr.length() < 10) {
            int l = 10 - uuidStr.length();
            for (int i = 0; i < l; i++) {
                uuidStr.append(0 + "");
            }
        }
        if (uuidStr.length() > 10) {
            uuidStr.delete(18, uuidStr.length());
        }
        uuidStr.insert(0, YMD);
        return uuidStr.toString();
    } catch (Exception e) {
        e.printStackTrace();
        return "";
    }
}


public String md5Sum(String password){
    try {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        password = new BigInteger(1, md.digest()).toString(16);
    } catch (NoSuchAlgorithmException e) {
        password = "";
    }
    return password;
}


public void main(String[] args){
    for (int i = 0; i < 100; i++) {
        System.out.println(getOrderNo());
    }
}


}