package org.live.config.dataComponent;
 import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@ConfigurationProperties(prefix = "shiro")
@Component
public class ShiroDataComponent {

 private  long sessionValidationInterval;

 private  long globalSessionTimeout;

 private  String cookieName;

 private  int passwordRetryLimitCount;

 private  String loginUrl;

 private  String unauthorizedUrl;

 private  String successUrl;

 private  String filterChainDefinitions;


public void setFilterChainDefinitions(String filterChainDefinitions){
    this.filterChainDefinitions = filterChainDefinitions;
}


public void setSessionValidationInterval(long sessionValidationInterval){
    this.sessionValidationInterval = sessionValidationInterval;
}


public void setGlobalSessionTimeout(long globalSessionTimeout){
    this.globalSessionTimeout = globalSessionTimeout;
}


public long getGlobalSessionTimeout(){
    return globalSessionTimeout;
}


public String getSuccessUrl(){
    return successUrl;
}


public void setSuccessUrl(String successUrl){
    this.successUrl = successUrl;
}


public void setCookieName(String cookieName){
    this.cookieName = cookieName;
}


public void setLoginUrl(String loginUrl){
    this.loginUrl = loginUrl;
}


public void setPasswordRetryLimitCount(int passwordRetryLimitCount){
    this.passwordRetryLimitCount = passwordRetryLimitCount;
}


public String getCookieName(){
    return cookieName;
}


public String getUnauthorizedUrl(){
    return unauthorizedUrl;
}


public String getLoginUrl(){
    return loginUrl;
}


public int getPasswordRetryLimitCount(){
    return passwordRetryLimitCount;
}


public String getFilterChainDefinitions(){
    return filterChainDefinitions;
}


public long getSessionValidationInterval(){
    return sessionValidationInterval;
}


public void setUnauthorizedUrl(String unauthorizedUrl){
    this.unauthorizedUrl = unauthorizedUrl;
}


}