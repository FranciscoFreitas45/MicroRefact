package com.gbcom.update.client.manager;
 import java.util.HashMap;
import java.util.Map;
import com.gbcom.update.common.VersionInfo;
public class VersionInfoManager {

 private  Map<String,VersionInfo> versionCache;

 private  VersionInfoManager instance;

/**
 * 私有构造器
 */
private VersionInfoManager() {
    init();
}
public void init(){
    if (versionCache == null) {
        versionCache = new HashMap<String, VersionInfo>();
    }
}


public VersionInfo get(String product){
    return versionCache.get(product);
}


public VersionInfoManager getInstance(){
    if (instance == null) {
        instance = new VersionInfoManager();
    }
    return instance;
}


public void put(String product,VersionInfo info){
    versionCache.put(product, info);
}


}