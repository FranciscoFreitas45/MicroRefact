package com.lingxiang2014.plugin;
 import java.io.File;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.stereotype.Component;
import com.lingxiang2014.FileInfo;
import com.lingxiang2014.entity.PluginConfig;
import com.lingxiang2014.service.PluginConfigService;
public class StoragePlugin implements Comparable<StoragePlugin>{

@Resource(name = "pluginConfigServiceImpl")
 private  PluginConfigService pluginConfigService;


public String getVersion()


public String getName()


public String getAuthor()


public void upload(String path,File file,String contentType)


public String getInstallUrl()


public PluginConfig getPluginConfig(){
    return pluginConfigService.findByPluginId(getId());
}


public String getId(){
    return getClass().getAnnotation(Component.class).value();
}


public String getSiteUrl()


public int compareTo(StoragePlugin storagePlugin){
    return new CompareToBuilder().append(getOrder(), storagePlugin.getOrder()).append(getId(), storagePlugin.getId()).toComparison();
}


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


@Override
public int hashCode(){
    return new HashCodeBuilder(17, 37).append(getId()).toHashCode();
}


public Integer getOrder(){
    PluginConfig pluginConfig = getPluginConfig();
    return pluginConfig != null ? pluginConfig.getOrder() : null;
}


public List<FileInfo> browser(String path)


@Override
public boolean equals(Object obj){
    if (obj == null) {
        return false;
    }
    if (getClass() != obj.getClass()) {
        return false;
    }
    if (this == obj) {
        return true;
    }
    StoragePlugin other = (StoragePlugin) obj;
    return new EqualsBuilder().append(getId(), other.getId()).isEquals();
}


}