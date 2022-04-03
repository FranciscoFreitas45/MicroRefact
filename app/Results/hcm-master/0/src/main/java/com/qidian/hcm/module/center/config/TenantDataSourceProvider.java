package com.qidian.hcm.module.center.config;
 import com.qidian.hcm.common.config.HCMConfig;
import com.qidian.hcm.common.constants.SystemConstant;
import com.qidian.hcm.common.exception.BizException;
import com.qidian.hcm.common.interceptor.TenantThreadHelper;
import com.qidian.hcm.common.jwt.JwtUserInfo;
import com.qidian.hcm.common.utils.ResultCode;
import com.qidian.hcm.module.center.entity.GroupConfig;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.core.io.InputStreamResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import com.qidian.hcm.Interface.HCMConfig;
@Slf4j
@Service
public class TenantDataSourceProvider {

 private  Map<String,DataSource> dataSourceMap;

 private  Map<String,ProcessEngine> processEngineMap;

@Autowired
 private  DataSourceProperties dataSourceProperties;

@Autowired
 private  DataSource centerDataSource;

@Autowired
 private  HCMConfig hcmConfig;


public DataSource getTenantDataSource(String tenantId){
    if (!dataSourceMap.containsKey(tenantId)) {
        throw new BizException(ResultCode.NOT_FOUND_TENANT);
    }
    return dataSourceMap.get(tenantId);
}


public void dropDataBase(Long userId,String tenantName){
    String dbName = SystemConstant.TENANT_KEY + "_" + tenantName;
    String sb = "USE " + dataSourceProperties.getName();
    sb += ";DELETE FROM company_group WHERE id = (SELECT group_id FROM user WHERE id = " + userId + ")";
    sb += ";DELETE FROM group_config WHERE group_id = (SELECT group_id FROM user WHERE id = " + userId + ")";
    sb += ";DELETE FROM user WHERE group_id = (SELECT group_id FROM (SELECT * FROM user) AS sub_user " + "where sub_user.id = " + userId + ")";
    sb += ";DROP DATABASE " + dbName;
    InputStream inputStream = new ByteArrayInputStream(sb.getBytes(StandardCharsets.UTF_8));
    InputStreamResource isr = new InputStreamResource(inputStream);
    ResourceDatabasePopulator populate = new ResourceDatabasePopulator(isr);
    populate.execute(dataSourceMap.get(SystemConstant.DEFAULT_DB));
    dataSourceMap.remove(tenantName);
}


public ProcessEngine getProcessEngine(){
    JwtUserInfo userToken = TenantThreadHelper.getToken();
    String tenantId = Objects.nonNull(userToken) ? userToken.getTenantName() : "";
    return processEngineMap.computeIfAbsent(tenantId, key -> ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration().setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE).setDataSource(getTenantDataSource(tenantId)).setAsyncExecutorActivate(false).buildProcessEngine());
}


public void initDataSource(Iterable<GroupConfig> list){
    list.forEach(this::addDataSource);
}


@PostConstruct
public void initCenterDataSource(){
    dataSourceMap.put(SystemConstant.DEFAULT_DB, centerDataSource);
}


public DataSource addDataSource(GroupConfig groupConfig){
    DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create().url(groupConfig.getUrl() + SystemConstant.TENANT_KEY + "_" + groupConfig.getTenantName() + "?" + hcmConfig.getMysqlConnParams()).username(groupConfig.getUsername()).password(groupConfig.getPassword()).driverClassName(dataSourceProperties.getDriverClassName());
    DataSource dataSource = dataSourceBuilder.build();
    dataSourceMap.put(groupConfig.getTenantName(), dataSource);
    return dataSource;
}


public void initializeDataBase(String tenantName){
    // 创建数据库
    String dbName = SystemConstant.TENANT_KEY + "_" + tenantName;
    String sb = "create database " + dbName;
    InputStream inputStream = new ByteArrayInputStream(sb.getBytes(StandardCharsets.UTF_8));
    InputStreamResource isr = new InputStreamResource(inputStream);
    ResourceDatabasePopulator populate = new ResourceDatabasePopulator(isr);
    populate.execute(dataSourceMap.get(SystemConstant.DEFAULT_DB));
}


}