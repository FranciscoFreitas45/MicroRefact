package org.opengeoportal.config.proxy;
 import java.util.ArrayList;
import java.util.List;
public class ProxyConfig {

 private String repositoryId;

 private List<String> accessLevels;

 private List<ServerMapping> serverMapping;


public String getRepositoryId(){
    return repositoryId;
}


public void setAccessLevels(List<String> accessLevels){
    this.accessLevels = accessLevels;
}


public List<ServerMapping> getServerMapping(){
    return serverMapping;
}


public List<String> getAccessLevels(){
    return accessLevels;
}


public void setServerMapping(List<ServerMapping> serverMapping){
    this.serverMapping = serverMapping;
}


public void setRepositoryId(String repositoryId){
    this.repositoryId = repositoryId;
}


}