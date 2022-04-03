package com.gbcom.DTO;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.gbcom.op.util.xml.XStreamUtil;
import com.gbcom.update.common.VersionInfo;
import com.hc.core.utils.ReflectionUtils;
public class UpdateClientContextManager {

 private  Logger LOG;

 private  Map<String,VersionInfo> versionInfoMap;

 private  Map<String,UpdateClient> updateClientMap;

 private  Map<String,List<FileFilter>> fileFilterMap;

 private  UpdateClientContext updateClientContext;

 private  String CLIENT_SITE_PATH;

 private  UpdateClientContextManager instance;

/**
 * 私有构造器
 */
private UpdateClientContextManager() {
    init();
}
public UpdateClient getupdateClient(String product){
    return updateClientMap.get(product);
}


public String getUpdateProduct(){
    List<String> products = new ArrayList<String>(versionInfoMap.keySet());
    if (products.size() > 0) {
        return products.get(0);
    }
    return "";
}


public VersionInfo getVersionInfo(String product){
    return versionInfoMap.get(product);
}


public UpdateClientContext getUpdateClientContext(){
    return updateClientContext;
}


public Map<String,VersionInfo> getVersionInfoMap(){
    return versionInfoMap;
}


public UpdateClientContextManager getInstance(){
    if (instance == null) {
        instance = new UpdateClientContextManager();
    }
    return instance;
}


public Map<String,UpdateClient> getUpdateClientMap(){
    return updateClientMap;
}


public List<FileFilter> getFileFilter(String product){
    return fileFilterMap.get(product);
}


}