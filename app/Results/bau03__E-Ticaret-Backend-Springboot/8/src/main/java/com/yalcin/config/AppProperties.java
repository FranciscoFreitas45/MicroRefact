package com.yalcin.config;
 import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
@Component
@ConfigurationProperties(prefix = "yalcin")
public class AppProperties {

 private  OAuth2 oauth2;

 private  List<String> authorizedRedirectUris;


public List<String> getAuthorizedRedirectUris(){
    return authorizedRedirectUris;
}


public OAuth2 authorizedRedirectUris(List<String> authorizedRedirectUris){
    this.authorizedRedirectUris = authorizedRedirectUris;
    return this;
}


public OAuth2 getOauth2(){
    return oauth2;
}


}