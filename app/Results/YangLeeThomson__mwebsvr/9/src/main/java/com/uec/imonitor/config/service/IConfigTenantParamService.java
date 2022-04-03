package com.uec.imonitor.config.service;
 import java.util.List;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.config.bean.ConfigTenantParamEntity;
public interface IConfigTenantParamService {


public List<ConfigTenantParamEntity> findByTenantId(Integer tenantId)
;

public ConfigTenantParamEntity findByTenantIdAndParamName(Integer tenantId,String paramName)
;

public ConfigTenantParamEntity findById(Integer id)
;

public ConfigTenantParamEntity save(ConfigTenantParamEntity entity)
;

public ConfigTenantParamEntity update(ConfigTenantParamEntity entity)
;

public void delete(Integer id)
;

public ConfigTenantParamEntity findByTenantIdAndParamId(Integer tenantId,Integer paramId)
;

}