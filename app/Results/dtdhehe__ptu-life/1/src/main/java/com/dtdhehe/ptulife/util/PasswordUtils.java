package com.dtdhehe.ptulife.util;
 import org.apache.shiro.crypto.hash.Md5Hash;
public class PasswordUtils {

 private  String SALT;


public String getPWD(String userPwd){
    return new Md5Hash(userPwd, SALT).toString();
}


}