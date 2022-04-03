package com.circle.utils;
 import java.util.UUID;
public class IDUtil {


public String getID(){
    // 获取uuid
    String id = UUID.randomUUID().toString();
    id = id.replace("-", "");
    return id;
}


}