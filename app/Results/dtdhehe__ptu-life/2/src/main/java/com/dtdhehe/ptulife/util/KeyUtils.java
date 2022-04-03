package com.dtdhehe.ptulife.util;
 import java.util.UUID;
public class KeyUtils {


public void main(String[] args){
    String s = getUniqueKey();
    System.out.println(s);
}


public String getUniqueKey(){
    // 取前8位为用户主键
    String uuid = UUID.randomUUID().toString().substring(0, 8);
    return uuid;
}


}