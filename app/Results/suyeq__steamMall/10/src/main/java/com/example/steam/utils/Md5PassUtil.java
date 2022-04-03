package com.example.steam.utils;
 import org.apache.commons.codec.digest.DigestUtils;
public class Md5PassUtil {

 private  String salt;


public String firstMd5(String password){
    String camouflagePassword = "" + salt.charAt(0) + salt.charAt(4) + password + salt.charAt(5) + salt.charAt(2);
    return DigestUtils.md5Hex(camouflagePassword);
}


public String md5Conver(String password,String salt){
    return secondMd5(firstMd5(password), salt);
}


public String secondMd5(String password,String salt){
    String camouflagePassword = "" + salt.charAt(0) + salt.charAt(4) + password + salt.charAt(5) + salt.charAt(2);
    return DigestUtils.md5Hex(camouflagePassword);
}


}