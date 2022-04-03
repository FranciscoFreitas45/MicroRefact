package org.live.config.dataComponent;
 import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class JdbcDataComponent {

 private  String url;

 private  String username;

 private  String password;

 private  String driverClassName;


public String getDriverClassName(){
    return driverClassName;
}


public String getUrl(){
    return url;
}


public void setPassword(String password){
    this.password = password;
}


public String getPassword(){
    return password;
}


public void setDriverClassName(String driverClassName){
    this.driverClassName = driverClassName;
}


public void setUsername(String username){
    this.username = username;
}


public void setUrl(String url){
    this.url = url;
}


public String getUsername(){
    return username;
}


}