package com.yalcin.config.AppProperties;
 import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
public class OAuth2 {

 private  List<String> authorizedRedirectUris;


public List<String> getAuthorizedRedirectUris(){
    return authorizedRedirectUris;
}


public OAuth2 authorizedRedirectUris(List<String> authorizedRedirectUris){
    this.authorizedRedirectUris = authorizedRedirectUris;
    return this;
}


}