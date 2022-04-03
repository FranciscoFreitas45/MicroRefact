package com.example.steam.config;
 import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class PlatformDynamicDataSourceConfiguration {


@Bean
public SqlSessionFactoryBean sqlSessionFactory(DynamicDataSource dataSource){
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource);
    Properties properties = new Properties();
    properties.setProperty("sqlType", "mysql");
    sqlSessionFactoryBean.setConfigurationProperties(properties);
    return sqlSessionFactoryBean;
}


}