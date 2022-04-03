package com.qidian.hcm.module.center.service;
 import com.qidian.hcm.common.constants.SystemConstant;
import com.qidian.hcm.module.center.entity.GroupConfig;
import com.qidian.hcm.module.center.repository.GroupConfigRepository;
import com.qidian.hcm.module.center.config.FlywayConfigurer;
import com.qidian.hcm.module.center.config.TenantDataSourceProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.lang.Boolean.TRUE;
@Service
@Slf4j
public class GroupConfigService {

@Autowired
 private  GroupConfigRepository groupConfigRepository;

@Autowired
 private  DataSourceProperties dataSourceProperties;

@Autowired
 private  TenantDataSourceProvider tenantDataSourceProvider;

@Autowired
 private  FlywayConfigurer flywayConfigurer;


@Transactional
public void createGroupConfig(Long groupId,String tenantName){
    GroupConfig groupConfig = new GroupConfig();
    groupConfig.setPassword(dataSourceProperties.getPassword());
    groupConfig.setUsername(dataSourceProperties.getUsername());
    groupConfig.setInitialize(TRUE);
    groupConfig.setTenantName(tenantName);
    groupConfig.setGroupId(groupId);
    groupConfig.setUrl(dataSourceProperties.getUrl());
    groupConfigRepository.save(groupConfig);
    tenantDataSourceProvider.initializeDataBase(tenantName);
    tenantDataSourceProvider.addDataSource(groupConfig);
    flywayConfigurer.migrateForTenantDB(SystemConstant.TENANT_KEY + "_" + tenantName);
}


}