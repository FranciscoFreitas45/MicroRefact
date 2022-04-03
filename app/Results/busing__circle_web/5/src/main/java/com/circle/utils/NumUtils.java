package com.circle.utils;
 public class NumUtils {


public String subZeroAndDot(String s){
    if (s.indexOf(".") > 0) {
        // 去掉多余的0
        s = s.replaceAll("0+?$", "");
        // 如最后一位是.则去掉
        s = s.replaceAll("[.]$", "");
    }
    return s;
}


}