package com.zis.common.util;
 public class StringUtil {


public String conjunction(String conj,String strings){
    StringBuilder builder = new StringBuilder();
    for (String str : strings) {
        builder.append(str).append(conj);
    }
    return builder.substring(0, builder.length() - conj.length()).toString();
}


}