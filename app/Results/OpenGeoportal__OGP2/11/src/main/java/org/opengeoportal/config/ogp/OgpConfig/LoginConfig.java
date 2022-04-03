package org.opengeoportal.config.ogp.OgpConfig;
 import java.net.URL;
public class LoginConfig {

 private String repositoryId;

 private String type;

 private String url;

 private String secureDomain;


public String getUrl(){
    return url;
}


public String getRepositoryId(){
    return repositoryId;
}


public String getType(){
    return type;
}


public void setSecureDomain(String secureDomain){
    this.secureDomain = secureDomain;
}


public String getSecureDomain(){
    return secureDomain;
}


public void setType(String type){
    this.type = type;
}


public void setRepositoryId(String repositoryId){
    this.repositoryId = repositoryId;
}


public void setUrl(String url){
    this.url = url;
}


}