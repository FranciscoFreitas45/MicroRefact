package com.gp.cricket.configuration;
 import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class EmailConfiguration {

@Value("${spring.mail.host}")
 private  String host;

@Value("${spring.mail.port}")
 private  Integer port;

@Value("${spring.mail.username}")
 private  String username;

@Value("${spring.mail.password}")
 private  String password;


public void setHost(String host){
    this.host = host;
}


public void setPassword(String password){
    this.password = password;
}


public String getPassword(){
    return password;
}


public Integer getPort(){
    return port;
}


public void setUsername(String username){
    this.username = username;
}


@Override
public String toString(){
    return "EmailConfig [host=" + host + ", port=" + port + ", username=" + username + ", password=" + password + "]";
}


public void setPort(Integer port){
    this.port = port;
}


public String getHost(){
    return host;
}


public String getUsername(){
    return username;
}


}