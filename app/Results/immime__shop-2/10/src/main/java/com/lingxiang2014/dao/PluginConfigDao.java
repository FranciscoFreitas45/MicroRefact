package com.lingxiang2014.dao;
 import com.lingxiang2014.entity.PluginConfig;
public interface PluginConfigDao extends BaseDao<PluginConfig, Long>{


public boolean pluginIdExists(String pluginId)
;

public PluginConfig findByPluginId(String pluginId)
;

}