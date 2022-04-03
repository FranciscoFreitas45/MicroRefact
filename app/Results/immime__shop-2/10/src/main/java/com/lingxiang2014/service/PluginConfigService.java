package com.lingxiang2014.service;
 import com.lingxiang2014.entity.PluginConfig;
public interface PluginConfigService extends BaseService<PluginConfig, Long>{


public boolean pluginIdExists(String pluginId)
;

public PluginConfig findByPluginId(String pluginId)
;

}