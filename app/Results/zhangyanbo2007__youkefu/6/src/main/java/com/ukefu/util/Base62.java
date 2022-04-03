package com.ukefu.util;
 import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
public class Base62 {

 private  int BINARY;

 private  int NUMBER_61;

 static  char[] DIGITS;


public String encode(String str){
    String md5Hex = DigestUtils.md5Hex(str);
    // 6 digit binary can indicate 62 letter & number from 0-9a-zA-Z
    int binaryLength = 6 * 6;
    long binaryLengthFixer = Long.valueOf(StringUtils.repeat("1", binaryLength), BINARY);
    for (int i = 0; i < 4; ) {
        String subString = StringUtils.substring(md5Hex, i * 8, (i + 1) * 8);
        subString = Long.toBinaryString(Long.valueOf(subString, 16) & binaryLengthFixer);
        subString = StringUtils.leftPad(subString, binaryLength, "0");
        StringBuilder sbBuilder = new StringBuilder();
        for (int j = 0; j < 6; j++) {
            String subString2 = StringUtils.substring(subString, j * 6, (j + 1) * 6);
            int charIndex = Integer.valueOf(subString2, BINARY) & NUMBER_61;
            sbBuilder.append(DIGITS[charIndex]);
        }
        String shortUrl = sbBuilder.toString();
        if (shortUrl != null) {
            return shortUrl;
        }
    }
    // if all 4 possibilities are already exists
    return null;
}


@SuppressWarnings("unused")
public void print(Object messagr){
    System.out.println(messagr);
}


}