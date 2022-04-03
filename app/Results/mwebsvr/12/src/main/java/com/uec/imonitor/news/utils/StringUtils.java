package com.uec.imonitor.news.utils;
 public class StringUtils {


public boolean isEmpty(CharSequence cs){
    return cs == null || cs.length() == 0;
}


public boolean isNotBlank(CharSequence cs){
    return !isBlank(cs);
}


public boolean isNotEmpty(CharSequence str){
    return !isEmpty(str);
}


public boolean isBlank(CharSequence cs){
    int strLen;
    if (cs == null || (strLen = cs.length()) == 0) {
        return true;
    }
    for (int i = 0; i < strLen; i++) {
        if (Character.isWhitespace(cs.charAt(i)) == false) {
            return false;
        }
    }
    return true;
}


}