package com.easyshopping.service.impl;
 import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import com.easyshopping.plugin.PaymentPlugin;
import com.easyshopping.plugin.StoragePlugin;
import com.easyshopping.service.PluginService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.stereotype.Service;
@Service("pluginServiceImpl")
public class PluginServiceImpl implements PluginService{

@Resource
 private  List<PaymentPlugin> paymentPlugins;

@Resource
 private  List<StoragePlugin> storagePlugins;

@Resource
 private  Map<String,PaymentPlugin> paymentPluginMap;

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


public PaymentPlugin getPaymentPlugin(String id){
    return paymentPluginMap.get(id);
}


public List<PaymentPlugin> getPaymentPlugins(boolean isEnabled){
    List<PaymentPlugin> result = new ArrayList<PaymentPlugin>();
    CollectionUtils.select(paymentPlugins, new Predicate() {

        public boolean evaluate(Object object) {
            PaymentPlugin paymentPlugin = (PaymentPlugin) object;
            return paymentPlugin.getIsEnabled() == isEnabled;
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