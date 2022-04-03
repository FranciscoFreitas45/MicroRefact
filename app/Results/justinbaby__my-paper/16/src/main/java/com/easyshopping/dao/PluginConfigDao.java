package com.easyshopping.dao;
 import com.easyshopping.entity.PluginConfig;
public interface PluginConfigDao extends BaseDao<PluginConfig, Long>{


public boolean pluginIdExists(String pluginId)
;

public PluginConfig findByPluginId(String pluginId)
;

}