package com.ukefu.util;
 import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class CheckMobile {

 static  String phoneReg;

 static  String tableReg;

 static  Pattern phonePat;

 static  Pattern tablePat;


public boolean check(String userAgent){
    if (null == userAgent) {
        userAgent = "";
    }
    // 匹配
    Matcher matcherPhone = phonePat.matcher(userAgent);
    Matcher matcherTable = tablePat.matcher(userAgent);
    if (matcherPhone.find() || matcherTable.find()) {
        return true;
    } else {
        return false;
    }
}


}