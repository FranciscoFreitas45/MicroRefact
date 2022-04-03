package com.qidian.hcm.module.center.config;
 import com.qidian.hcm.common.config.HCMConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import javax.sql.DataSource;
@Slf4j
@Configuration
public class CenterDataSource {

@Autowired
 private  DataSourceProperties dataSourceProperties;

@Autowired
 private  HCMConfig hcmConfig;


@Bean
@Primary
public DataSource dataSource(){
    DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
    dataSourceBuilder.url(dataSourceProperties.getUrl() + dataSourceProperties.getName() + "?" + hcmConfig.getMysqlConnParams());
    dataSourceBuilder.username(dataSourceProperties.getUsername());
    dataSourceBuilder.password(dataSourceProperties.getPassword());
    dataSourceBuilder.driverClassName(dataSourceProperties.getDriverClassName());
    return dataSourceBuilder.build();
}


}