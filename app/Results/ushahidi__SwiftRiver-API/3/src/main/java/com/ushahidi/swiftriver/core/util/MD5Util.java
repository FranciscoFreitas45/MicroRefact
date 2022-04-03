package com.ushahidi.swiftriver.core.util;
 import java.io;
import java.security;
public class MD5Util {


public String md5Hex(String message){
    String input = "";
    for (String token : message) {
        input += token;
    }
    return md5Hex(input);
}


public String hex(byte[] array){
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < array.length; ++i) {
        sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
    }
    return sb.toString();
}


}