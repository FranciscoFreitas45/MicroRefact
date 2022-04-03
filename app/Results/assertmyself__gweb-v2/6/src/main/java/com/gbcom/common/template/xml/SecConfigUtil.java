package com.gbcom.common.template.xml;
 import com.gbcom.op.properties.Config;
import com.gbcom.op.properties.SimpleConfig;
public class SecConfigUtil {

 private  Config config;

 private  SecConfigUtil instance;

/**
 * 配合单例模式，私有化构造方法
 */
private SecConfigUtil() {
    // 配合单例模式，私有化构造方法
    config = new SimpleConfig("config/sec/sec.properties");
}
public String getAuthClass(){
    return getConfig().getString("authClass");
}


public int getSecurityTaskTimeGap(){
    return getConfig().getInt("securityTaskTimeGap");
}


public SecConfigUtil getInstance(){
    return instance;
}


public Config getConfig(){
    if (config == null) {
        getInstance();
    }
    return config;
}


}