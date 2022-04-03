package com.gbcom.common.template.xml;
 import com.gbcom.op.properties.Config;
import com.gbcom.op.properties.SimpleConfig;
public class LogConfigUtil {

 private  Config config;

 private  LogConfigUtil instance;

/**
 * 配合单例模式，私有化构造方法
 */
private LogConfigUtil() {
    // 配合单例模式，私有化构造方法
    config = new SimpleConfig("config/log/log.properties");
}
public int getEachDayHour(){
    return getConfig().getInt("eachdayhour");
}


public LogConfigUtil getInstance(){
    return instance;
}


public Config getConfig(){
    if (config == null) {
        getInstance();
    }
    return config;
}


}