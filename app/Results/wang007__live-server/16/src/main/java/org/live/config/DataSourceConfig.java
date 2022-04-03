package org.live.config;
 import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.live.config.dataComponent.DruidDataComponent;
import org.live.config.dataComponent.JdbcDataComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;
@Configuration
public class DataSourceConfig {

 private  Logger LOGGER;

@Resource
 private  JdbcDataComponent jdbcData;

@Resource
 private  DruidDataComponent druidData;


@Bean("druidStatViewServlet")
public ServletRegistrationBean druidStatViewServlet(){
    ServletRegistrationBean servletReg = new ServletRegistrationBean();
    servletReg.setServlet(new StatViewServlet());
    servletReg.addUrlMappings("/druid/*");
    servletReg.addInitParameter("loginUsername", druidData.getLoginUsername());
    servletReg.addInitParameter("loginPassword", druidData.getLoginPassword());
    servletReg.addInitParameter("druid.resetEnable", druidData.getResetEnable());
    return servletReg;
}


@Bean(name = "druidDataSource")
public DataSource druidDataSource(){
    LOGGER.info("jdbc-url->:{}", jdbcData.getUrl());
    LOGGER.info("jdbc-username->:{}", jdbcData.getUsername());
    LOGGER.info("jdbc-pasword->:{}", jdbcData.getPassword());
    LOGGER.info("jdbc-driverClassName->:{}", jdbcData.getDriverClassName());
    LOGGER.info("druid-initialSize->{}", druidData.getInitialSize());
    LOGGER.info("druid-MinIdle->{}", druidData.getMinIdle());
    LOGGER.info("druid-MaxActive->{}", druidData.getMaxActive());
    DruidDataSource dataSource = new DruidDataSource();
    // jdbc
    dataSource.setUrl(jdbcData.getUrl());
    dataSource.setUsername(jdbcData.getUsername());
    dataSource.setPassword(jdbcData.getPassword());
    dataSource.setDriverClassName(jdbcData.getDriverClassName());
    // 初始化连接池大小
    dataSource.setInitialSize(druidData.getInitialSize());
    dataSource.setMinIdle(druidData.getMinIdle());
    dataSource.setMaxActive(druidData.getMaxActive());
    dataSource.setMaxWait(druidData.getMaxWait());
    dataSource.setTimeBetweenEvictionRunsMillis(druidData.getTimeBetweenEvictionRunsMillis());
    dataSource.setMinEvictableIdleTimeMillis(druidData.getMinEvictableIdleTimeMillis());
    dataSource.setValidationQuery(druidData.getValidationQuery());
    dataSource.setTestWhileIdle(druidData.isTestWhileIdle());
    dataSource.setTestOnBorrow(druidData.isTestOnBorrow());
    dataSource.setTestOnReturn(druidData.isTestOnReturn());
    dataSource.setPoolPreparedStatements(druidData.isPoolPreparedStatements());
    dataSource.setMaxPoolPreparedStatementPerConnectionSize(druidData.getMaxPoolPreparedStatementPerConnectionSize());
    try {
        dataSource.setFilters(druidData.getFilters());
    } catch (SQLException e) {
        LOGGER.error(e.getMessage(), e);
    }
    return dataSource;
}


@Bean("druidWebStatFilter")
public FilterRegistrationBean webStatFilter(){
    FilterRegistrationBean filterReg = new FilterRegistrationBean();
    filterReg.setFilter(new WebStatFilter());
    filterReg.addUrlPatterns("/*");
    filterReg.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
    return filterReg;
}


}