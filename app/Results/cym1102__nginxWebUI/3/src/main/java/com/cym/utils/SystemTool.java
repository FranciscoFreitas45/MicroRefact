package com.cym.utils;
 import cn.hutool.system.SystemUtil;
public class SystemTool {


public Boolean isLinux(){
    return getSystem().equals("Linux");
}


public boolean hasRoot(){
    if (SystemTool.isLinux()) {
        String user = System.getProperties().getProperty(SystemUtil.USER_NAME);
        if ("root".equals(user)) {
            return true;
        } else {
            return false;
        }
    }
    return true;
}


public String getSystem(){
    if (SystemUtil.get(SystemUtil.OS_NAME).toLowerCase().contains("windows")) {
        return "Windows";
    } else if (SystemUtil.get(SystemUtil.OS_NAME).toLowerCase().contains("mac os")) {
        return "Mac OS";
    } else {
        return "Linux";
    }
}


public Boolean isWindows(){
    return getSystem().equals("Windows");
}


public Boolean isMacOS(){
    return getSystem().equals("Mac OS");
}


}