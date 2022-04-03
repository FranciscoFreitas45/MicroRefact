package com.example.steam.config;
 import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Configuration
public class DataSourceConfiguration {


@Primary
@Bean(name = "master")
@ConfigurationProperties(prefix = "spring.datasource")
public DataSource dataSourceOne(){
    return DruidDataSourceBuilder.create().build();
}


@Bean
public DynamicDataSource dynamicDataSource(DataSource masterDataSource,DataSource slaveDataSource){
    DynamicDataSource dynamicDataSource = new DynamicDataSource();
    Map<Object, Object> targetDataSources = new HashMap<Object, Object>(10);
    targetDataSources.put("master", masterDataSource);
    targetDataSources.put("slave1", slaveDataSource);
    dynamicDataSource.setTargetDataSources(targetDataSources);
    List<Object> slaveDataSources = new ArrayList<>();
    slaveDataSources.add("slave1");
    dynamicDataSource.setDefaultTargetDataSource(masterDataSource);
    dynamicDataSource.setSlaveDataSources(slaveDataSources);
    return dynamicDataSource;
}


@Bean(name = "slave")
@ConfigurationProperties(prefix = "spring.datasource1")
public DataSource dataSourceTwo(){
    return DruidDataSourceBuilder.create().build();
}


}