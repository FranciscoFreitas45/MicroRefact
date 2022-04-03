package com.lingxiang2014.service;
 import java.util.List;
import com.lingxiang2014.plugin.StoragePlugin;
public interface PluginService {


public List<StoragePlugin> getStoragePlugins(boolean isEnabled)
;

public StoragePlugin getStoragePlugin(String id)
;

}