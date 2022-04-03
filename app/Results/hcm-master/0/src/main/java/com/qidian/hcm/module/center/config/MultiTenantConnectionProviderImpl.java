package com.qidian.hcm.module.center.config;
 import com.qidian.hcm.common.constants.SystemConstant;
import com.qidian.hcm.module.center.repository.GroupConfigRepository;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
@Component
public class MultiTenantConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl{

 private  long serialVersionUID;

@Autowired
 private  TenantDataSourceProvider tenantDataSourceProvider;

@Autowired
 private  ApplicationContext context;

 private  boolean init;


@Override
public DataSource selectAnyDataSource(){
    return tenantDataSourceProvider.getTenantDataSource(SystemConstant.DEFAULT_DB);
}


@Override
public DataSource selectDataSource(String tenantIdentifier){
    if (!init) {
        init = true;
        GroupConfigRepository groupConfigRepository = context.getBean(GroupConfigRepository.class);
        tenantDataSourceProvider.initDataSource(groupConfigRepository.findAll());
    }
    return tenantDataSourceProvider.getTenantDataSource(tenantIdentifier);
}


}