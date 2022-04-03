package com.easyshopping.service;
 import com.easyshopping.entity.PluginConfig;
public interface PluginConfigService extends BaseService<PluginConfig, Long>{


public boolean pluginIdExists(String pluginId)
;

public PluginConfig findByPluginId(String pluginId)
;

}