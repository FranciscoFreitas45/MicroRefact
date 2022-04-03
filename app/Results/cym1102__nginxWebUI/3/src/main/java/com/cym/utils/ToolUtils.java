package com.cym.utils;
 public class ToolUtils {


public String handleConf(String path){
    return path.replace("};", "  }");
}


public String handlePath(String path){
    return path.replace("\\", "/");
}


}