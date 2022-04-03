package com.lingxiang2014.service.impl;
 import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lingxiang2014.dao.PluginConfigDao;
import com.lingxiang2014.entity.PluginConfig;
import com.lingxiang2014.service.PluginConfigService;
@Service("pluginConfigServiceImpl")
public class PluginConfigServiceImpl extends BaseServiceImpl<PluginConfig, Long>implements PluginConfigService{

@Resource(name = "pluginConfigDaoImpl")
 private  PluginConfigDao pluginConfigDao;


@Transactional(readOnly = true)
public boolean pluginIdExists(String pluginId){
    return pluginConfigDao.pluginIdExists(pluginId);
}


@Resource(name = "pluginConfigDaoImpl")
public void setBaseDao(PluginConfigDao pluginConfigDao){
    super.setBaseDao(pluginConfigDao);
}


@Transactional(readOnly = true)
public PluginConfig findByPluginId(String pluginId){
    return pluginConfigDao.findByPluginId(pluginId);
}


}