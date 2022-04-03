package com.easyshopping.DTO;
 import java.io.File;
import java.util.List;
import javax.annotation.Resource;
import com.easyshopping.FileInfo;
import com.easyshopping.entity.PluginConfig;
import com.easyshopping.service.PluginConfigService;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.stereotype.Component;
public class StoragePlugin implements Comparable<StoragePlugin>{

 private  PluginConfigService pluginConfigService;


public String getVersion()


public String getName()


public String getAuthor()


public String getInstallUrl()


public PluginConfig getPluginConfig(){
    return pluginConfigService.findByPluginId(getId());
}


public String getId(){
    return getClass().getAnnotation(Component.class).value();
}


public String getSiteUrl()


public boolean getIsEnabled(){
    PluginConfig pluginConfig = getPluginConfig();
    return pluginConfig != null ? pluginConfig.getIsEnabled() : false;
}


public String getUninstallUrl()


public String getSettingUrl()


public String getUrl(String path)


public String getAttribute(String name){
    PluginConfig pluginConfig = getPluginConfig();
    return pluginConfig != null ? pluginConfig.getAttribute(name) : null;
}


public boolean getIsInstalled(){
    return pluginConfigService.pluginIdExists(getId());
}


public Integer getOrder(){
    PluginConfig pluginConfig = getPluginConfig();
    return pluginConfig != null ? pluginConfig.getOrder() : null;
}


}