package com.qidian.hcm.module.center.config;
 import com.qidian.hcm.common.constants.SystemConstant;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.MultiTenancyStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.lang.Boolean.TRUE;
@Slf4j
@Configuration
public class MultiTenantEntityManagerFactory {

@Autowired
 private  MultiTenantConnectionProviderImpl multiTenantConnectionProviderImpl;

@Autowired
 private  MultiTenantIdentifierResolver multiTenantIdentifierResolver;

@Autowired
 private  Environment env;

@Autowired
 private  DataSource dataSource;


@PersistenceContext
@Primary
@Bean
public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder){
    Map<String, Object> props = new HashMap<>();
    props.put("hibernate.multiTenancy", MultiTenancyStrategy.DATABASE.name());
    props.put("hibernate.multi_tenant_connection_provider", multiTenantConnectionProviderImpl);
    props.put("hibernate.tenant_identifier_resolver", multiTenantIdentifierResolver);
    props.put("hibernate.cache.use_query_cache", TRUE);
    LocalContainerEntityManagerFactoryBean result = builder.dataSource(dataSource).persistenceUnit(SystemConstant.TENANT_KEY).properties(props).packages("com.qidian.hcm").build();
    result.setJpaProperties(this.properties());
    return result;
}


public Properties properties(){
    Properties properties = new Properties();
    String ddlAuto = this.env.getProperty("spring.jpa.hibernate.ddl-auto", "none");
    String physicalNamingStrategy = env.getProperty("spring.jpa.hibernate.physical_naming_strategy", "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
    String showSql = this.env.getProperty("spring.jpa.show-sql", "true");
    properties.setProperty("hibernate.ddl-auto", ddlAuto);
    properties.setProperty("hibernate.physical_naming_strategy", physicalNamingStrategy);
    properties.setProperty("hibernate.show_sql", showSql);
    return properties;
}


}