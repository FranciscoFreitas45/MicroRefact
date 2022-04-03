package com.uec.imonitor.config.service.impl;
 import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.uec.imonitor.common.base.BaseService;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.exception.RequestParamException;
import com.uec.imonitor.config.bean.ConfigTenantParamEntity;
import com.uec.imonitor.config.dao.IConfigTenantParamJPARepository;
import com.uec.imonitor.config.service.IConfigTenantParamService;
@Service("configTenantParamService")
@Transactional(value = "transactionManager")
public class ConfigTenantParamServiceImpl extends BaseServiceimplements IConfigTenantParamService{

@Autowired
 private  IConfigTenantParamJPARepository configTenantParamRepository;


@Override
public List<ConfigTenantParamEntity> findByTenantId(Integer tenantId){
    if (null == tenantId) {
        throw new RequestParamException(new String[] { "tenantId" });
    }
    List<ConfigTenantParamEntity> list = configTenantParamRepository.findByTenantId(tenantId);
    return list;
}


@Override
public ConfigTenantParamEntity findByTenantIdAndParamName(Integer tenantId,String paramName){
    if (null == tenantId) {
        throw new RequestParamException(new String[] { "tenantId" });
    }
    if (StringUtils.isBlank(paramName)) {
        throw new RequestParamException(new String[] { "paramName" });
    }
    ConfigTenantParamEntity entity = configTenantParamRepository.findByTenantIdAndParamName(tenantId, paramName);
    return entity;
}


@Override
public ConfigTenantParamEntity findById(Integer id){
    if (null == id) {
        throw new RequestParamException(new String[] { "id" });
    }
    ConfigTenantParamEntity entity = configTenantParamRepository.findOne(id);
    return entity;
}


@Override
public ConfigTenantParamEntity save(ConfigTenantParamEntity entity){
    if (null == entity) {
        throw new RequestParamException(new String[] { "entity" });
    }
    ConfigTenantParamEntity save = configTenantParamRepository.save(entity);
    return save;
}


@Override
public ConfigTenantParamEntity update(ConfigTenantParamEntity entity){
    if (null == entity) {
        throw new RequestParamException(new String[] { "entity" });
    }
    ConfigTenantParamEntity save = configTenantParamRepository.save(entity);
    return save;
}


@Override
public void delete(Integer id){
    if (null == id) {
        throw new RequestParamException(new String[] { "id" });
    }
    configTenantParamRepository.delete(id);
}


@Override
public ConfigTenantParamEntity findByTenantIdAndParamId(Integer tenantId,Integer paramId){
    if (null == tenantId) {
        throw new RequestParamException(new String[] { "tenantId" });
    }
    if (null == paramId) {
        throw new RequestParamException(new String[] { "paramId" });
    }
    ConfigTenantParamEntity entity = configTenantParamRepository.findByTenantIdAndParamId(tenantId, paramId);
    return entity;
}


}