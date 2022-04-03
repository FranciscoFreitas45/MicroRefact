package org.live.config.dataComponent;
 import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@ConfigurationProperties(prefix = "druid")
@Component
public class DruidDataComponent {

 private  String loginUsername;

 private  String loginPassword;

 private  String resetEnable;

 private  int initialSize;

 private  int minIdle;

 private  int maxActive;

 private  long maxWait;

 private  long timeBetweenEvictionRunsMillis;

 private  long minEvictableIdleTimeMillis;

 private  String validationQuery;

 private  boolean testWhileIdle;

 private  boolean testOnBorrow;

 private  boolean testOnReturn;

 private  boolean poolPreparedStatements;

 private  int maxPoolPreparedStatementPerConnectionSize;

 private  String filters;


public void setMinEvictableIdleTimeMillis(long minEvictableIdleTimeMillis){
    this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
}


public void setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis){
    this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
}


public boolean isTestOnBorrow(){
    return testOnBorrow;
}


public void setValidationQuery(String validationQuery){
    this.validationQuery = validationQuery;
}


public void setLoginUsername(String loginUsername){
    this.loginUsername = loginUsername;
}


public void setLoginPassword(String loginPassword){
    this.loginPassword = loginPassword;
}


public String getLoginUsername(){
    return loginUsername;
}


public long getMinEvictableIdleTimeMillis(){
    return minEvictableIdleTimeMillis;
}


public String getFilters(){
    return filters;
}


public void setInitialSize(int initialSize){
    this.initialSize = initialSize;
}


public long getMaxWait(){
    return maxWait;
}


public void setResetEnable(String resetEnable){
    this.resetEnable = resetEnable;
}


public boolean isTestOnReturn(){
    return testOnReturn;
}


public int getMinIdle(){
    return minIdle;
}


public void setMinIdle(int minIdle){
    this.minIdle = minIdle;
}


public void setTestWhileIdle(boolean testWhileIdle){
    this.testWhileIdle = testWhileIdle;
}


public long getTimeBetweenEvictionRunsMillis(){
    return timeBetweenEvictionRunsMillis;
}


public boolean isPoolPreparedStatements(){
    return poolPreparedStatements;
}


public void setTestOnReturn(boolean testOnReturn){
    this.testOnReturn = testOnReturn;
}


public String getLoginPassword(){
    return loginPassword;
}


public String getResetEnable(){
    return resetEnable;
}


public int getMaxPoolPreparedStatementPerConnectionSize(){
    return maxPoolPreparedStatementPerConnectionSize;
}


public int getInitialSize(){
    return initialSize;
}


public int getMaxActive(){
    return maxActive;
}


public boolean isTestWhileIdle(){
    return testWhileIdle;
}


public void setFilters(String filters){
    this.filters = filters;
}


public String getValidationQuery(){
    return validationQuery;
}


public void setPoolPreparedStatements(boolean poolPreparedStatements){
    this.poolPreparedStatements = poolPreparedStatements;
}


public void setMaxWait(long maxWait){
    this.maxWait = maxWait;
}


public void setTestOnBorrow(boolean testOnBorrow){
    this.testOnBorrow = testOnBorrow;
}


public void setMaxActive(int maxActive){
    this.maxActive = maxActive;
}


public void setMaxPoolPreparedStatementPerConnectionSize(int maxPoolPreparedStatementPerConnectionSize){
    this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
}


}