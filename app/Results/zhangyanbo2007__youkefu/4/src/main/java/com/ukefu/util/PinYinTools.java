package com.ukefu.util;
 public class PinYinTools {

 private  int[] pyvalue;

 public  String[] pystr;

 private  StringBuilder buffer;

 private  String resource;

 private  PinYinTools characterParser;


public String getResource(){
    return resource;
}


public int getChsAscii(String chs){
    int asc = 0;
    try {
        byte[] bytes = chs.getBytes("gb2312");
        if (bytes == null || bytes.length > 2 || bytes.length <= 0) {
            throw new RuntimeException("illegal resource string");
        }
        if (bytes.length == 1) {
            asc = bytes[0];
        }
        if (bytes.length == 2) {
            int hightByte = 256 + bytes[0];
            int lowByte = 256 + bytes[1];
            asc = (256 * hightByte + lowByte) - 256 * 256;
        }
    } catch (Exception e) {
        System.out.println("ERROR:ChineseSpelling.class-getChsAscii(String chs)" + e);
    }
    return asc;
}


public String getFirstPinYin(String word){
    String firstWord = "0";
    String pinYin = PinYinTools.getInstance().getSelling(word);
    if (pinYin.length() > 0) {
        firstWord = pinYin.substring(0, 1);
    }
    return firstWord;
}


public String getFirstPinYinAll(String word){
    String key, value;
    buffer = new StringBuilder();
    for (int i = 0; i < word.length(); i++) {
        key = word.substring(i, i + 1);
        if (key.getBytes().length >= 2) {
            value = (String) convert(key);
        } else {
            value = key;
        }
        if (value != null && value.length() > 0) {
            buffer.append(value.substring(0, 1));
        }
    }
    return buffer.toString();
}


public String getSpelling(){
    return this.getSelling(this.getResource());
}


public PinYinTools getInstance(){
    return characterParser;
}


public String convert(String str){
    String result = null;
    int ascii = getChsAscii(str);
    if (ascii > 0 && ascii < 160) {
        result = String.valueOf((char) ascii);
    } else {
        for (int i = (pyvalue.length - 1); i >= 0; i--) {
            if (pyvalue[i] <= ascii) {
                result = pystr[i];
                break;
            }
        }
    }
    return result;
}


public String getSelling(String chs){
    String key, value;
    buffer = new StringBuilder();
    for (int i = 0; i < chs.length(); i++) {
        key = chs.substring(i, i + 1);
        if (key.getBytes().length >= 2) {
            value = (String) convert(key);
            if (value == null) {
                value = "unknown";
            }
        } else {
            value = key;
        }
        buffer.append(value);
    }
    return buffer.toString();
}


public void setResource(String resource){
    this.resource = resource;
}


}