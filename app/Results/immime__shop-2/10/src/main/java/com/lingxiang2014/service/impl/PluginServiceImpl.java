package com.lingxiang2014.service.impl;
 import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.stereotype.Service;
import com.lingxiang2014.plugin.StoragePlugin;
import com.lingxiang2014.service.PluginService;
@Service("pluginServiceImpl")
public class PluginServiceImpl implements PluginService{

@Resource
 private  List<StoragePlugin> storagePlugins;

@Resource
 private  Map<String,StoragePlugin> storagePluginMap;


public List<StoragePlugin> getStoragePlugins(boolean isEnabled){
    List<StoragePlugin> result = new ArrayList<StoragePlugin>();
    CollectionUtils.select(storagePlugins, new Predicate() {

        public boolean evaluate(Object object) {
            StoragePlugin storagePlugin = (StoragePlugin) object;
            return storagePlugin.getIsEnabled() == isEnabled;
        }
    }, result);
    Collections.sort(result);
    return result;
}


public StoragePlugin getStoragePlugin(String id){
    return storagePluginMap.get(id);
}


public boolean evaluate(Object object){
    StoragePlugin storagePlugin = (StoragePlugin) object;
    return storagePlugin.getIsEnabled() == isEnabled;
}


}