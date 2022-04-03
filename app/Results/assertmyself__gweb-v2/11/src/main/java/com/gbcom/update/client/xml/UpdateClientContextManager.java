package com.gbcom.update.client.xml;
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
public void init(){
    try {
        if (versionInfoMap == null) {
            versionInfoMap = new HashMap<String, VersionInfo>();
        }
        if (updateClientMap == null) {
            updateClientMap = new HashMap<String, UpdateClient>();
        }
        if (fileFilterMap == null) {
            fileFilterMap = new HashMap<String, List<FileFilter>>();
        }
        Class<?>[] classContext = new Class<?>[] { UpdateClientContext.class, UpdateClient.class, FileFilter.class };
        updateClientContext = XStreamUtil.fromXML(UpdateClientContext.class, CLIENT_SITE_PATH, classContext);
        if (updateClientContext != null) {
            UpdateClient updateClient = updateClientContext.getUpdateClient();
            VersionInfo versionInfo = new VersionInfo();
            ReflectionUtils.copyBean(updateClient, versionInfo, new String[] { "name", "product", "version", "no", "date", "method" });
            LOG.info(versionInfo.toString());
            versionInfoMap.put(updateClient.getProduct(), versionInfo);
            updateClientMap.put(updateClient.getProduct(), updateClient);
            fileFilterMap.put(updateClient.getProduct(), updateClient.getFileFilters());
        }
    } catch (Exception e) {
        LOG.error(e.getMessage(), e);
    }
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


public void save(){
    try {
        Class<?>[] classContext = new Class<?>[] { UpdateClientContext.class, UpdateClient.class, FileFilter.class };
        String filePath = this.getClass().getClassLoader().getResource(CLIENT_SITE_PATH).getPath();
        LOG.info("client_xml path :" + filePath);
        XStreamUtil.toXML(updateClientContext, filePath, classContext);
    } catch (Exception e) {
        LOG.error("client_xml parse failed !", e);
        e.printStackTrace();
    }
}


public Map<String,VersionInfo> getVersionInfoMap(){
    return versionInfoMap;
}


public void main(String[] args){
    System.out.println(UpdateClientContextManager.getInstance().getUpdateClientContext().getUpdateClient().getDate());
    UpdateClientContextManager.getInstance().getUpdateClientContext().getUpdateClient().setDate("20151125");
    UpdateClientContextManager.getInstance().save();
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