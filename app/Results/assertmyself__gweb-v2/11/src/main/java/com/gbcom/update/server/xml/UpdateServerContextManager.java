package com.gbcom.update.server.xml;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.gbcom.op.util.xml.XStreamUtil;
import com.gbcom.update.common.VersionInfo;
import com.hc.core.utils.ReflectionUtils;
public class UpdateServerContextManager {

 private  Logger LOG;

 private  Map<String,VersionInfo> versionInfoMap;

 private  Map<String,UpdateServer> updateServerMap;

 private  UpdateServerContext context;

 private  String SERVER_SITE_PATH;

 private  UpdateServerContextManager instance;

/**
 * 私有构造器
 */
private UpdateServerContextManager() {
    init();
}
public void init(){
    try {
        if (versionInfoMap == null) {
            versionInfoMap = new HashMap<String, VersionInfo>();
        }
        if (updateServerMap == null) {
            updateServerMap = new HashMap<String, UpdateServer>();
        }
        Class<?>[] classContext = new Class<?>[] { UpdateServerContext.class, UpdateServer.class, FilterRule.class, ZipRule.class };
        context = XStreamUtil.fromXML(UpdateServerContext.class, SERVER_SITE_PATH, classContext);
        if (context != null) {
            List<UpdateServer> updateServerList = context.getList();
            for (int i = 0; i < updateServerList.size(); i++) {
                UpdateServer updateServer = updateServerList.get(i);
                // 这里可以考虑直接使用UpdateServer,而不使用VersionInfo
                VersionInfo versionInfo = new VersionInfo();
                ReflectionUtils.copyBean(updateServer, versionInfo, new String[] { "name", "product", "version", "no", "date", "method" });
                versionInfoMap.put(updateServer.getProduct(), versionInfo);
                updateServerMap.put(updateServer.getProduct(), updateServer);
            }
        }
    } catch (Exception e) {
        LOG.error(e.getMessage(), e);
    }
}


public VersionInfo getVersionInfo(String product){
    return versionInfoMap.get(product);
}


public List<ZipRule> getZipRule(String product){
    UpdateServer update = getUpdateServer(product);
    if (update == null) {
        return new ArrayList<ZipRule>();
    }
    return update.getZipRules();
}


public UpdateServer getUpdateServer(String product){
    return updateServerMap.get(product);
}


public Map<String,VersionInfo> getVersionInfoMap(){
    return versionInfoMap;
}


public List<String> getExcludes(String product){
    List<ZipRule> zipRules = getZipRule(product);
    List<String> rules = new ArrayList<String>();
    for (ZipRule zipRule : zipRules) {
        if (zipRule.getExclude() == null || zipRule.getExclude().equals("")) {
            continue;
        }
        rules.add(zipRule.getExclude());
    }
    return rules;
}


public List<String> getIncludes(String product){
    List<ZipRule> zipRules = getZipRule(product);
    List<String> rules = new ArrayList<String>();
    for (ZipRule zipRule : zipRules) {
        if (zipRule.getInclude() == null || zipRule.getInclude().equals("")) {
            continue;
        }
        rules.add(zipRule.getInclude());
    }
    return rules;
}


public UpdateServerContextManager getInstance(){
    if (instance == null) {
        instance = new UpdateServerContextManager();
    }
    return instance;
}


public UpdateServerContext getUpdateServerContext(){
    return context;
}


public List<String> getProducts(){
    return new ArrayList<String>(versionInfoMap.keySet());
}


public Map<String,UpdateServer> getUpdateServerMap(){
    return updateServerMap;
}


public List<FilterRule> getFilterRules(String product){
    UpdateServer update = getUpdateServer(product);
    if (update == null) {
        return new ArrayList<FilterRule>();
    }
    return update.getFilterRules();
}


}