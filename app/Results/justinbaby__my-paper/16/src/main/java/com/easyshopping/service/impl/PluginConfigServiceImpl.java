package com.easyshopping.service.impl;
 import javax.annotation.Resource;
import com.easyshopping.dao.PluginConfigDao;
import com.easyshopping.entity.PluginConfig;
import com.easyshopping.service.PluginConfigService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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